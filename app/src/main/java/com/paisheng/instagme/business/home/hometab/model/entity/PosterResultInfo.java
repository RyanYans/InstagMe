package com.paisheng.instagme.business.home.hometab.model.entity;

import java.util.List;

/**
 * @author: yuanbaining
 * @Filename: PosterResultInfo
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/1/31 10:42
 */


public class PosterResultInfo {

    /**
     * nexttime : 加载下一页的时间参数
     * data : List数据
     * */

    private int nexttime;
    private List<DataBean> data;

    public int getNexttime() {
        return nexttime;
    }

    public void setNexttime(int nexttime) {
        this.nexttime = nexttime;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * username : 发贴人姓名
         * dateline : 发帖时间
         * message : 帖子部分内容
         * replynum : 回复个数
         * subject : 帖子标题
         */

        private String username;
        private String dateline;
        private String message;
        private int replynum;
        private String subject;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getReplynum() {
            return replynum;
        }

        public void setReplynum(int replynum) {
            this.replynum = replynum;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }
    }
}
