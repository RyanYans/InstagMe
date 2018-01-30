package com.paisheng.instagme.network.encryption;

import com.alibaba.fastjson.JSONObject;
import com.paisheng.instagme.constant.AppConfig;
import com.paisheng.instagme.network.exception.HttpRequestErrorUtil;
import com.paisheng.lib.network.exception.ApiException;
import com.paisheng.lib.security.JNICrypto;

public class EncryptionUtils {

    private static final String TEST_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOmHrkwqVRaARXWImTdyjcRzHk" +
            "YCG5135TyJabPgzxPcGVaalnG7j2jjUn5VM9xFo7H+vln4gY/POQl1qXEOEvVi5k" +
            "KNiJ6kg44937Lhn+nbphHd7BnvT6qAGaR+wfoyYawMvDt+h1xp42mTY9Ww6XMfBt" +
            "2VBUuoA52FwfYTbe5wIDAQAB";
    private static final String RELEASE_PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAL6kR5I8QkeryLpz0ORKFGAGyxE1kG4b\n" +
            "66DDuEwBcCc6r8DfYku8R7oyd2qMDn/GKjUxd2P54+Mb/Z07L37Fp5ECAwEAAQ==";
    //公钥
    private static final String clientPublicKey = AppConfig.IS_DEV_MODE ? TEST_PUBLIC_KEY : RELEASE_PUBLIC_KEY;


    /**
     *<br> Description: 加密
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/2 15:20
     * @param dataJsonStr
     *                   数据
     * @return
     *                   加密数据
     */
    public static String encrypt(String dataJsonStr) {
        try {
            return clientEncode(dataJsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     *<br> Description: 解密
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/2 16:06
     */
    public static String decrypt(String response) throws Exception {
        return clientDecode(response);
    }

    /**
     *<br> Description: 客户端加密
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/2 16:35
     * @param data
     *                  明文
     * @return
     *                  密文
     * @throws Exception
     */
    private static String clientEncode(String data) throws Exception {
        JSONObject reqBody = new JSONObject();
        String desKey = java.util.UUID.randomUUID().toString();
        //3DES加密原文
        reqBody.put("data", Cryptography.TripleDESEncrypt(data, desKey));
        //RSA加密3DES Key
        reqBody.put("key",TraderRSAUtil.encrypt(clientPublicKey, desKey));
        //串Data和3DES Key
        String keyAndData = reqBody.getString("key") + "&" + reqBody.getString("data");
        //RSA签名
        reqBody.put("sign", JNICrypto.getInstance().rsaSha1Sign(keyAndData));

        return reqBody.toString();
    }

    /**
     *<br> Description: 客户端解密
     *<br> Author:      yexiaochuan
     *<br> Date:        2017/8/2 16:36
     * @param response
     *                  密文
     * @return
     *                  明文
     * @throws Exception
     */
    private static String clientDecode(String response) throws Exception {
        JSONObject requestBodyVO = JSONObject.parseObject(response);
        //验签
        boolean flag = TraderRSAUtil.checksign(clientPublicKey,
                requestBodyVO.getString("key") + "&" + requestBodyVO.getString("data"),
                requestBodyVO.getString("sign"));
        if (flag) {
            try {
                String resultDesKey = JNICrypto.getInstance().decrypt(requestBodyVO.getString("key"));
                return Cryptography.TripleDESDecrypt(requestBodyVO.getString("data"), resultDesKey);
            } catch (Exception e) {
                throw new ApiException(ApiException.CODE_RES_DECODE_ERROR,
                        HttpRequestErrorUtil.str_dataResolveError_2);
            }
        } else {
            throw new ApiException(ApiException.CODE_RES_DECODE_ERROR,
                    HttpRequestErrorUtil.str_dataResolveError_2);
        }
    }
}
