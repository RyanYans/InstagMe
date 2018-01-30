package com.paisheng.instagme.common.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <br> ClassName:   CouponsBean
 * <br> Description: 优惠券
 * <br>
 * <br> Author:      谢文良
 * <br> Date:        2017/8/7 17:36
 */
public class CouponsBean implements Serializable {
    private int total;
    private List<CouponsItem> result;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CouponsItem> getResult() {
        return result;
    }

    public void setResult(List<CouponsItem> result) {
        this.result = result;
    }

    /**
     * <br> ClassName:   CouponsBean
     * <br> Description: 列表Item
     * <br>
     * <br> Author:      谢文良
     * <br> Date:        2017/8/9 10:49
     */
    public static class CouponsItem implements Serializable {
        /*** 优惠券编号 ***/
        private String couponId;
        /*** 优惠券名称 ***/
        private String couponName;
        /*** 使用说明 ***/
        private String couponDesc;
        /*** 有效期_开始 ***/
        private String beginDate;
        /*** 有效期_结束 ***/
        private String endDate;
        /*** 优惠券类型 ***/
        private int couponType;
        /*** 优惠券面额 ***/
        private double couponVal;
        /*** 是否可用 ***/
        private boolean useAble;
        /*** 不可用原因 ***/
        private String unUseDesc;
        /*** 是否过期 ***/
        private boolean isExpired;

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public void setCouponName(String couponName) {
            this.couponName = couponName;
        }

        public String getCouponDesc() {
            return couponDesc;
        }

        public void setCouponDesc(String couponDesc) {
            this.couponDesc = couponDesc;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public int getCouponType() {
            return couponType;
        }

        public void setCouponType(int couponType) {
            this.couponType = couponType;
        }

        public double getCouponVal() {
            return couponVal;
        }

        public void setCouponVal(double couponVal) {
            this.couponVal = couponVal;
        }

        public boolean isUseAble() {
            return useAble;
        }

        public void setUseAble(boolean useAble) {
            this.useAble = useAble;
        }

        public String getUnUseDesc() {
            return unUseDesc;
        }

        public void setUnUseDesc(String unUseDesc) {
            this.unUseDesc = unUseDesc;
        }

        public boolean isIsExpired() {
            return isExpired;
        }

        public void setIsExpired(boolean expired) {
            isExpired = expired;
        }
    }
}
