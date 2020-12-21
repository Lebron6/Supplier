package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/11/10.
 */
public class OperaDetails {



        /**
         * o_id : 141
         * os_id : 491
         * s_status : 4
         * s_type : 4
         * goods_jnum : 3
         * goods_num : 124
         * total_weight : 11.00
         * total_volume : 11.00
         * start_city : 常州市
         * end_city : 盐城市
         * reject_time : 1970-01-01 08:00
         * reject :
         * is_d_reject : 1
         * d_reject_list : [{"reject":"test1","reject_time":"2020-12-01 14:27","d_id":"99","name":"顾昶1"}]
         * show_button : 1
         * pickup_address : [{"name":"常州恒力有限公司","contract_name":"恒力联系人","contract_tel":"15200000000","info":"江苏省常州市武进区雪堰镇1123号","arrive_time":"2020-12-01 02:28"}]
         * delivery_address : [{"name":"广西壮族自治区南宁物流有限公司","contract_name":"为了你","contract_tel":"15296713935","info":"山西省大同市矿区代发货水电费说过话","arrive_time":null}]
         */

        private String o_id;
        private String os_id;
        private String s_status;
        private String s_type;
        private String goods_jnum;
        private String goods_num;
        private String total_weight;
        private String total_volume;
        private String start_city;
        private String end_city;
        private String reject_time;
        private String reject;
        private int is_d_reject;
        private int show_button;
        private List<DRejectListBean> d_reject_list;
        private List<PickupAddressBean> pickup_address;
        private List<DeliveryAddressBean> delivery_address;

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

        public String getS_status() {
            return s_status;
        }

        public void setS_status(String s_status) {
            this.s_status = s_status;
        }

        public String getS_type() {
            return s_type;
        }

        public void setS_type(String s_type) {
            this.s_type = s_type;
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

        public int getShow_button() {
            return show_button;
        }

        public void setShow_button(int show_button) {
            this.show_button = show_button;
        }

        public List<DRejectListBean> getD_reject_list() {
            return d_reject_list;
        }

        public void setD_reject_list(List<DRejectListBean> d_reject_list) {
            this.d_reject_list = d_reject_list;
        }

        public List<PickupAddressBean> getPickup_address() {
            return pickup_address;
        }

        public void setPickup_address(List<PickupAddressBean> pickup_address) {
            this.pickup_address = pickup_address;
        }

        public List<DeliveryAddressBean> getDelivery_address() {
            return delivery_address;
        }

        public void setDelivery_address(List<DeliveryAddressBean> delivery_address) {
            this.delivery_address = delivery_address;
        }

        public static class DRejectListBean {
            /**
             * reject : test1
             * reject_time : 2020-12-01 14:27
             * d_id : 99
             * name : 顾昶1
             */

            private String reject;
            private String reject_time;
            private String d_id;
            private String name;

            public String getReject() {
                return reject;
            }

            public void setReject(String reject) {
                this.reject = reject;
            }

            public String getReject_time() {
                return reject_time;
            }

            public void setReject_time(String reject_time) {
                this.reject_time = reject_time;
            }

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

        public static class PickupAddressBean {
            /**
             * name : 常州恒力有限公司
             * contract_name : 恒力联系人
             * contract_tel : 15200000000
             * info : 江苏省常州市武进区雪堰镇1123号
             * arrive_time : 2020-12-01 02:28
             */

            private String name;
            private String contract_name;
            private String contract_tel;
            private String info;
            private String arrive_time;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContract_name() {
                return contract_name;
            }

            public void setContract_name(String contract_name) {
                this.contract_name = contract_name;
            }

            public String getContract_tel() {
                return contract_tel;
            }

            public void setContract_tel(String contract_tel) {
                this.contract_tel = contract_tel;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getArrive_time() {
                return arrive_time;
            }

            public void setArrive_time(String arrive_time) {
                this.arrive_time = arrive_time;
            }
        }

        public static class DeliveryAddressBean {
            /**
             * name : 广西壮族自治区南宁物流有限公司
             * contract_name : 为了你
             * contract_tel : 15296713935
             * info : 山西省大同市矿区代发货水电费说过话
             * arrive_time : null
             */

            private String name;
            private String contract_name;
            private String contract_tel;
            private String info;
            private String arrive_time;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContract_name() {
                return contract_name;
            }

            public void setContract_name(String contract_name) {
                this.contract_name = contract_name;
            }

            public String getContract_tel() {
                return contract_tel;
            }

            public void setContract_tel(String contract_tel) {
                this.contract_tel = contract_tel;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getArrive_time() {
                return arrive_time;
            }

            public void setArrive_time(String arrive_time) {
                this.arrive_time = arrive_time;
            }
        }

}
