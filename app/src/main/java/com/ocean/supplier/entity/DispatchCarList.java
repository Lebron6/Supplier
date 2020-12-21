package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/11/19.
 */
public class DispatchCarList {


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
             * v_id : 147
             * num : 苏E0001
             */

            private String v_id;
            private String num;

            public String getV_id() {
                return v_id;
            }

            public void setV_id(String v_id) {
                this.v_id = v_id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }
        }

}
