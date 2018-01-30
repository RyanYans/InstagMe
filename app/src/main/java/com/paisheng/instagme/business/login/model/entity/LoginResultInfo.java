package com.paisheng.instagme.business.login.model.entity;

/**
 * @author: yuanbaining
 * @Filename: LoginResultInfo
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/29 15:54
 */


public class LoginResultInfo {

    /**
     * uid : 用户标识
     * error : 错误标识
     * success : 是否登陆成功
     */

    private int uid;
    private boolean success;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "LoginResultInfo{" +
                "uid=" + uid +
                ", success=" + success +
                ", error='" + error + '\'' +
                '}';
    }
}
