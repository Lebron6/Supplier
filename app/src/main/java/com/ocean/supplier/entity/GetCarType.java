package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/8/25.
 */
public class GetCarType {

    /**
     * code : 1
     * msg : 请求成功
     * time : 1598343001
     * data : [{"id":1,"name":"无要求"},{"id":2,"name":"货的"},{"id":3,"name":"栏板车"},{"id":4,"name":"半封闭厢车"},{"id":5,"name":"全封闭厢车"},{"id":6,"name":"集装厢车"},{"id":7,"name":"平板拖车"},{"id":8,"name":"保温车"},{"id":9,"name":"冷藏车"},{"id":10,"name":"特种车"},{"id":11,"name":"面包车"},{"id":12,"name":"罐装车"}]
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
         * id : 1
         * name : 无要求
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
