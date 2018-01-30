package com.paisheng.instagme.network.bean;

import java.io.Serializable;

/**
 * <br> ClassName:   ServerDownInfoBean
 * <br> Description: 停机维护
 * <br>
 * <br> Author:      hehaodong
 * <br> Date:        2017-09-13 10:41
 */
public class ServerDownInfoBean implements Serializable {
    /**
     * 维护开始时间
     */
    private String validTimeBegin;
    /**
     * 维护结束时间
     */
    private String validTimeEnd;
    /**
     * 维护信息页面地址
     */
    private String maintainUrl;

    public String getValidTimeBegin() {
        return validTimeBegin;
    }

    public void setValidTimeBegin(String validTimeBegin) {
        this.validTimeBegin = validTimeBegin;
    }

    public String getValidTimeEnd() {
        return validTimeEnd;
    }

    public void setValidTimeEnd(String validTimeEnd) {
        this.validTimeEnd = validTimeEnd;
    }

    public String getMaintainUrl() {
        return maintainUrl;
    }

    public void setMaintainUrl(String maintainUrl) {
        this.maintainUrl = maintainUrl;
    }
}
