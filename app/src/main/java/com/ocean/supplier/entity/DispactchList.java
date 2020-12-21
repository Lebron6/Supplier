package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/11/19.
 */
public class DispactchList {


        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 1
         * pageall : 1
         * list : [{"d_id":"107","name":"古鼎盛1"}]
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
             * d_id : 107
             * name : 古鼎盛1
             */

            private String d_id;
            private String name;

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
        }

}
