package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/11/9.
 */
public class OperaGoodsListData {

        /**
         * good_list : [{"wa_num":"Y202010217095","name":"冰箱零一21","good_type":"小小的","pk_name":null,"accept_num":"0","weight":"345.00","volume":null,"num":"100","jnum":"1"},{"wa_num":"Y202010226110","name":"电池零一","good_type":null,"pk_name":null,"accept_num":"0","weight":"1.00","volume":null,"num":"80","jnum":"1"}]
         * total_info : {"total_weight":"0.00","total_volume":"0.00","goods_jnum":"0","goods_num":"0"}
         */

        private TotalInfoBean total_info;
        private List<GoodListBean> good_list;

        public TotalInfoBean getTotal_info() {
            return total_info;
        }

        public void setTotal_info(TotalInfoBean total_info) {
            this.total_info = total_info;
        }

        public List<GoodListBean> getGood_list() {
            return good_list;
        }

        public void setGood_list(List<GoodListBean> good_list) {
            this.good_list = good_list;
        }

        public static class TotalInfoBean {
            /**
             * total_weight : 0.00
             * total_volume : 0.00
             * goods_jnum : 0
             * goods_num : 0
             */

            private String total_weight;
            private String total_volume;
            private String goods_jnum;
            private String goods_num;

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

        public static class GoodListBean {
            /**
             * wa_num : Y202010217095
             * name : 冰箱零一21
             * good_type : 小小的
             * pk_name : null
             * accept_num : 0
             * weight : 345.00
             * volume : null
             * num : 100
             * jnum : 1
             */

            private String wa_num;
            private String name;
            private String good_type;
            private String pk_name;
            private String accept_num;
            private String weight;
            private String volume;
            private String num;
            private String jnum;

            public String getWa_num() {
                return wa_num;
            }

            public void setWa_num(String wa_num) {
                this.wa_num = wa_num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getGood_type() {
                return good_type;
            }

            public void setGood_type(String good_type) {
                this.good_type = good_type;
            }

            public String getPk_name() {
                return pk_name;
            }

            public void setPk_name(String pk_name) {
                this.pk_name = pk_name;
            }

            public String getAccept_num() {
                return accept_num;
            }

            public void setAccept_num(String accept_num) {
                this.accept_num = accept_num;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getJnum() {
                return jnum;
            }

            public void setJnum(String jnum) {
                this.jnum = jnum;
            }
        }

}
