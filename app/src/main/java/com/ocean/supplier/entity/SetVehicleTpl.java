package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/9/25.
 */
public class SetVehicleTpl {
    /**
     * code : 1
     * msg : 请求成功
     * time : 1601021534
     * data : {"current_page":1,"per_page":30,"has_more":false,"total":"3","pageall":1,"list":[{"t_id":"1","name":"大洋1","userame":"张总","phone":"15296713935"},{"t_id":"1","name":"大洋1","userame":"张总","phone":"15296713935"},{"t_id":"1","name":"大洋1","userame":"张总","phone":"15296713935"}]}
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
         * current_page : 1
         * per_page : 30
         * has_more : false
         * total : 3
         * pageall : 1
         * list : [{"t_id":"1","name":"大洋1","userame":"张总","phone":"15296713935"},{"t_id":"1","name":"大洋1","userame":"张总","phone":"15296713935"},{"t_id":"1","name":"大洋1","userame":"张总","phone":"15296713935"}]
         */

        private int current_page;
        private int per_page;
        private boolean has_more;
        private String total;
        private int pageall;
        private List<ListBean> list;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public int getPageall() {
            return pageall;
        }

        public void setPageall(int pageall) {
            this.pageall = pageall;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * t_id : 1
             * name : 大洋1
             * userame : 张总
             * phone : 15296713935
             */

            private String t_id;
            private String name;
            private String userame;
            private String phone;

            public String getT_id() {
                return t_id;
            }

            public void setT_id(String t_id) {
                this.t_id = t_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserame() {
                return userame;
            }

            public void setUserame(String userame) {
                this.userame = userame;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
