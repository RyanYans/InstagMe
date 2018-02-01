package com.paisheng.instagme.business.home.hometab.model.entity;

import java.util.List;

/**
 * @author: yuanbaining
 * @Filename: PictResultInfo
 * @Description:
 * @Copyright: Copyright (c) 2017 Tuandai Inc. All rights reserved.
 * @date: 2018/2/1 11:26
 */


public class PictResultInfo {

    /**
     * error : 错误描述
     * results : ResultsBean 结果
     **/

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PictResultInfo{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }

    public static class ResultsBean {
        /**
         * desc : 时间描述
         * url : 图片地址 - http://7xi8d6.com1.z0.glb.clouddn.com/20180129074038_O3ydq4_Screenshot.jpeg
         */

        private String desc;
        private String url;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "desc='" + desc + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
