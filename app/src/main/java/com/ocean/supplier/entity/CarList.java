package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/10/13.
 */
public class CarList {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 2
         * pageall : 1
         * list : [{"v_id":"57","driver_name":"啊曹","num":"京A18888","remainWeight":"5.00","remainVolume":"5.10"},{"v_id":"61","driver_name":"刘备","num":"皖A18765","remainWeight":"13.00","remainVolume":"14.00"}]
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
             * v_id : 57
             * driver_name : 啊曹
             * num : 京A18888
             * remainWeight : 5.00
             * remainVolume : 5.10
             */

            private String v_id;
            private String driver_name;
            private String num;
            private String remainWeight;
            private String remainVolume;
            private int type=0;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getV_id() {
                return v_id;
            }

            public void setV_id(String v_id) {
                this.v_id = v_id;
            }

            public String getDriver_name() {
                return driver_name;
            }

            public void setDriver_name(String driver_name) {
                this.driver_name = driver_name;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getRemainWeight() {
                return remainWeight;
            }

            public void setRemainWeight(String remainWeight) {
                this.remainWeight = remainWeight;
            }

            public String getRemainVolume() {
                return remainVolume;
            }

            public void setRemainVolume(String remainVolume) {
                this.remainVolume = remainVolume;
            }
        }

}
