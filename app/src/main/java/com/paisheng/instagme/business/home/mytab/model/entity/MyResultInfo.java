package com.paisheng.instagme.business.home.mytab.model.entity;

/**
 * @author: yuanbaining
 * @Filename: MyResultInfo
 * @Description:    用户信息实体类
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/31 16:24
 */

public class MyResultInfo {

    /**
     * regdate : 注册时间
     * uid : 用户id
     * username : 用户名
     * gender : 性别
     */

    private int gender;
    private String regdate;
    private String uid;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
}
