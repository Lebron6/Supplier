package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/9/7.
 */
public class StaffAddInit {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1599098189
     * data : [{"id":"15","name":"测试23","rules":"1,14,18","status":"1","new_rules":["1","14","18"]},{"id":"16","name":"测啊啊","rules":"1,14,15,16,17,22,23","status":"1","new_rules":["1","14","15","16","17","22","23"]},{"id":"17","name":"得为","rules":"1,14,16,17,18,19,23","status":"1","new_rules":["1","14","16","17","18","19","23"]},{"id":"19","name":"测角色j","rules":"16,17","status":"1","new_rules":["16","17"]}]
     */

    private int code;
    private String msg;
    private int time;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 15
         * name : 测试23
         * rules : 1,14,18
         * status : 1
         * new_rules : ["1","14","18"]
         */

        private String id;
        private String name;
        private String rules;
        private String status;
        private List<String> new_rules;

        private int selectStatus=0;//未选中   1选中

        public int getSelectStatus() {
            return selectStatus;
        }

        public void setSelectStatus(int selectStatus) {
            this.selectStatus = selectStatus;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRules() {
            return rules;
        }

        public void setRules(String rules) {
            this.rules = rules;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<String> getNew_rules() {
            return new_rules;
        }

        public void setNew_rules(List<String> new_rules) {
            this.new_rules = new_rules;
        }
    }
}
