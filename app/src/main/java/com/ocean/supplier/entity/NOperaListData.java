package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/11/10.
 */
public class NOperaListData {

        /**
         * current_page : 1
         * per_page : 10
         * has_more : false
         * total : 1
         * pageall : 1
         * list : [{"o_id":"2","os_id":"66","s_type":"2","s_status":"5","goods_jnum":"0","total_weight":"0.00","total_volume":"0.00","start_city":"上海等","end_city":"北京等","pk_name":"北京等","pickup_address":"吉林省松原市扶余市金猫路9号等","delivery_address":"12324","arrival_time":"1970-01-01 08:00","reject_time":"0","reject":"","is_d_reject":0,"d_reject_time":"0","d_reject":""}]
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
             * o_id : 2
             * os_id : 66
             * s_type : 2
             * s_status : 5
             * goods_jnum : 0
             * total_weight : 0.00
             * total_volume : 0.00
             * start_city : 上海等
             * end_city : 北京等
             * pk_name : 北京等
             * pickup_address : 吉林省松原市扶余市金猫路9号等
             * delivery_address : 12324
             * arrival_time : 1970-01-01 08:00
             * reject_time : 0
             * reject :
             * is_d_reject : 0
             * d_reject_time : 0
             * d_reject :
             */

            private String o_id;
            private String os_id;
            private String s_type;
            private String s_status;
            private String goods_jnum;
            private String goods_num;

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            private String total_weight;
            private String total_volume;
            private String start_city;
            private String end_city;
            private String pk_name;
            private String pickup_address;
            private String delivery_address;
            private String arrival_time;
            private String reject_time;
            private String reject;
            private int is_d_reject;
            private String d_reject_time;
            private String d_reject;
            private String show_button;

            public String getShow_button() {
                return show_button;
            }

            public void setShow_button(String show_button) {
                this.show_button = show_button;
            }

            public String getO_id() {
                return o_id;
            }

            public void setO_id(String o_id) {
                this.o_id = o_id;
            }

            public String getOs_id() {
                return os_id;
            }

            public void setOs_id(String os_id) {
                this.os_id = os_id;
            }

            public String getS_type() {
                return s_type;
            }

            public void setS_type(String s_type) {
                this.s_type = s_type;
            }

            public String getS_status() {
                return s_status;
            }

            public void setS_status(String s_status) {
                this.s_status = s_status;
            }

            public String getGoods_jnum() {
                return goods_jnum;
            }

            public void setGoods_jnum(String goods_jnum) {
                this.goods_jnum = goods_jnum;
            }

            public String getTotal_weight() {
                return total_weight;
            }

            public void setTotal_weight(String total_weight) {
                this.total_weight = total_weight;
            }

            public String getTotal_volume() {
                return total_volume;
            }

            public void setTotal_volume(String total_volume) {
                this.total_volume = total_volume;
            }

            public String getStart_city() {
                return start_city;
            }

            public void setStart_city(String start_city) {
                this.start_city = start_city;
            }

            public String getEnd_city() {
                return end_city;
            }

            public void setEnd_city(String end_city) {
                this.end_city = end_city;
            }

            public String getPk_name() {
                return pk_name;
            }

            public void setPk_name(String pk_name) {
                this.pk_name = pk_name;
            }

            public String getPickup_address() {
                return pickup_address;
            }

            public void setPickup_address(String pickup_address) {
                this.pickup_address = pickup_address;
            }

            public String getDelivery_address() {
                return delivery_address;
            }

            public void setDelivery_address(String delivery_address) {
                this.delivery_address = delivery_address;
            }

            public String getArrival_time() {
                return arrival_time;
            }

            public void setArrival_time(String arrival_time) {
                this.arrival_time = arrival_time;
            }

            public String getReject_time() {
                return reject_time;
            }

            public void setReject_time(String reject_time) {
                this.reject_time = reject_time;
            }

            public String getReject() {
                return reject;
            }

            public void setReject(String reject) {
                this.reject = reject;
            }

            public int getIs_d_reject() {
                return is_d_reject;
            }

            public void setIs_d_reject(int is_d_reject) {
                this.is_d_reject = is_d_reject;
            }

            public String getD_reject_time() {
                return d_reject_time;
            }

            public void setD_reject_time(String d_reject_time) {
                this.d_reject_time = d_reject_time;
            }

            public String getD_reject() {
                return d_reject;
            }

            public void setD_reject(String d_reject) {
                this.d_reject = d_reject;
            }
        }

}
