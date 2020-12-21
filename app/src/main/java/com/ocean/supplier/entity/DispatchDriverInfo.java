package com.ocean.supplier.entity;

/**
 * Created by James on 2020/11/26.
 */
public class DispatchDriverInfo {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1605764581
     * data : {"d_id":"118","name":"杜岩2","phone":"15200010004","has_v":0,"v_id":null,"v_num":null,"goods_jnum":null,"goods_num":null}
     */

    private int code;
    private String msg;
    private int time;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * d_id : 118
         * name : 杜岩2
         * phone : 15200010004
         * has_v : 0
         * v_id : null
         * v_num : null
         * goods_jnum : null
         * goods_num : null
         */

        private String d_id;
        private String name;
        private String phone;
        private int has_v;
        private String v_id;
        private String v_num;
        private String goods_jnum;
        private String goods_num;

        public String getD_id() {
            return d_id;
        }

        public void setD_id(String d_id) {
            this.d_id = d_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getHas_v() {
            return has_v;
        }

        public void setHas_v(int has_v) {
            this.has_v = has_v;
        }

        public String getV_id() {
            return v_id;
        }

        public void setV_id(String v_id) {
            this.v_id = v_id;
        }

        public String getV_num() {
            return v_num;
        }

        public void setV_num(String v_num) {
            this.v_num = v_num;
        }

        public String getGoods_jnum() {
            return goods_jnum;
        }

        public void setGoods_jnum(String goods_jnum) {
            this.goods_jnum = goods_jnum;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }
    }
}
