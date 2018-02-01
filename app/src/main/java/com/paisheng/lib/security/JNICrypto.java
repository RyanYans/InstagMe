package com.paisheng.lib.security;

import android.content.Context;

/**
 * <br> ClassName:   JNICrypto
 * <br> Description: 加解密库
 * <br>
 * <br> Author:      yangyinglong
 * <br> Date:        2017/5/11 10:23
 */

public class JNICrypto {

    //    private long handle;
    private boolean inited = false;
    private JNICrypto() {
//        handle = nativeInit();
    }

    public static JNICrypto getInstance() {

        return SingleTon.INSTANCE;
    }

    public void init(Context context) {
        if (!inited) {
            nativeInit(context);
            inited = true;
        }
    }
//    public synchronized void destroy() {
//        INSTANCE = null;
//        CHECKINSTANCE = null;
//        nativeDestroy();
//    }

    public String getMD5(String src) {
        if (!inited) {
            throw new IllegalStateException("You must call init method at first!");
        }
        if (null == src) {
            throw new IllegalArgumentException("The param src cannot be null!");
        }
        return nativeMD5(src);
    }

    public String decodeRSA(String src) {
        if (!inited) {
            throw new IllegalStateException("You must call init method at first!");
        }
        if (null == src) {
            throw new IllegalArgumentException("The param src cannot be null!");
        }
        try {
            return nativeDecryptRSA(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encryptBase64(String src) {
        if (!inited) {
            throw new IllegalStateException("You must call init method at first!");
        }
        if (null == src) {
            throw new IllegalArgumentException("The param src cannot be null!");
        }
        return nativeEncryptBase64(src);
    }

    public String decryptoBase64(String src) {
        if (!inited) {
            throw new IllegalStateException("You must call init method at first!");
        }
        if (null == src) {
            throw new IllegalArgumentException("The param src cannot be null!");
        }
        return nativeDecryptBase64(src);
    }

    /**
     *<br> Description: 签名算法
     *<br> Author:      yangyinglong
     *<br> Date:        2017/8/9 11:40
     * @param src 需要签名的字符串
     * @return src经过签名后得到的值,签名失败返回 null
     */
    public String rsaSha1Sign(String src) {
        if (!inited) {
            throw new IllegalStateException("You must call init method at first!");
        }
        if (null == src) {
            throw new IllegalArgumentException("The param src cannot be null!");
        }
        return nativeRSASHA1Sign(src);
    }

    /**
     *<br> Description: RSA解密方法
     *<br> Author:      yangyinglong
     *<br> Date:        2017/8/15 14:36
     * @param cryptoText RSA密文
     * @return 解密后的明文
     */
    public String decrypt(String cryptoText)  throws Exception{
        return nativeSimuDecrypt(cryptoText);
    }

    /**
     *<br> Description: 获取Realm加密key
     *<br> Author:      yangyinglong
     *<br> Date:        2017/8/9 15:09
     */
    public String getRealmKey() {
        return nativeGetRealmKey();
    }

    private native void nativeInit(Context context);

    private native void nativeDestroy();

    private native String nativeMD5(String msg);

    private native String nativeEncodeAES(String msg);

    private native String nativeDecodeAES(String msg);

    private native String nativeEncryptDES(String msg);

    private native String nativeDecryptDES(String msg);

    private native String nativeDecryptRSA(String msg) throws Exception;

    private native String nativeEncryptRSA(String msg);

    private native String nativeEncryptBase64(String msg);

    private native String nativeDecryptBase64(String msg);

    private native String nativeRSASHA1Sign(String msg);

    /**
     *<br> Description: 底层解密函数
     *<br> Author:      yangyinglong
     *<br> Date:        2017/8/15 14:36
     */
    private native String nativeSimuDecrypt(String msg) throws Exception;

    private native String nativeGetRealmKey();

    public String getString() {
        return getStringFromNative();
    }
    private native String getStringFromNative();

    private static class SingleTon {
        private static final JNICrypto INSTANCE = new JNICrypto();
    }

    static {
        System.loadLibrary("cashcrypto");
    }
}
