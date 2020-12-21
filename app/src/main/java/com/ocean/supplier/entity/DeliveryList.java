package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/9/8.
 */
public class DeliveryList {


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
             * sdlv_id : 3
             * sdl_id : 10
             * tlogistics_name : 大洋1
             * createTime : 1599133814
             * endTime : 1599135045
             * price : 1000.00000000
             * pickType : 1
             * dispatch : 1
             * downtime : 0
             * choose_vehicle : 1
             */

            private String sdlv_id;
            private String sdl_id;
            private String tlogistics_name;
            private String createTime;
            private String endTime;
            private String price;
            private String pickType;
            private String dispatch;
            private long downtime;
            private int choose_vehicle;

            public String getVehicle_num() {
                return vehicle_num;
            }

            public void setVehicle_num(String vehicle_num) {
                this.vehicle_num = vehicle_num;
            }

            private String vehicle_num;

            public String getSdlv_id() {
                return sdlv_id;
            }

            public void setSdlv_id(String sdlv_id) {
                this.sdlv_id = sdlv_id;
            }

            public String getSdl_id() {
                return sdl_id;
            }

            public void setSdl_id(String sdl_id) {
                this.sdl_id = sdl_id;
            }

            public String getTlogistics_name() {
                return tlogistics_name;
            }

            public void setTlogistics_name(String tlogistics_name) {
                this.tlogistics_name = tlogistics_name;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPickType() {
                return pickType;
            }

            public void setPickType(String pickType) {
                this.pickType = pickType;
            }

            public String getDispatch() {
                return dispatch;
            }

            public void setDispatch(String dispatch) {
                this.dispatch = dispatch;
            }

            public long getDowntime() {
                return downtime;
            }

            public void setDowntime(long downtime) {
                this.downtime = downtime;
            }

            public int getChoose_vehicle() {
                return choose_vehicle;
            }

            public void setChoose_vehicle(int choose_vehicle) {
                this.choose_vehicle = choose_vehicle;
            }
        }

}
