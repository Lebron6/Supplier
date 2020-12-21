package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/10/13.
 */
public class AcceptDetailsData {

        private InfoBean info;
        private List<ListBean> list;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class InfoBean {
            /**
             * tlogistics_name : 常州三人禾物流有限公司
             * tlogistics_mobile : 15261566657
             * createTime : 2020-10-13 15:55
             * vehicle_num : null
             * sdlv_id : null
             * driver_name : false
             * is_accept : null
             */

            private String tlogistics_name;
            private String tlogistics_mobile;
            private String createTime;
            private String vehicle_num;
            private String sdlv_id;
            private boolean driver_name;
            private String is_accept;

            public String getTlogistics_name() {
                return tlogistics_name;
            }

            public void setTlogistics_name(String tlogistics_name) {
                this.tlogistics_name = tlogistics_name;
            }

            public String getTlogistics_mobile() {
                return tlogistics_mobile;
            }

            public void setTlogistics_mobile(String tlogistics_mobile) {
                this.tlogistics_mobile = tlogistics_mobile;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getVehicle_num() {
                return vehicle_num;
            }

            public void setVehicle_num(String vehicle_num) {
                this.vehicle_num = vehicle_num;
            }

            public Object getSdlv_id() {
                return sdlv_id;
            }

            public void setSdlv_id(String sdlv_id) {
                this.sdlv_id = sdlv_id;
            }

            public boolean isDriver_name() {
                return driver_name;
            }

            public void setDriver_name(boolean driver_name) {
                this.driver_name = driver_name;
            }

            public Object getIs_accept() {
                return is_accept;
            }

            public void setIs_accept(String is_accept) {
                this.is_accept = is_accept;
            }
        }

        public static class ListBean {
            /**
             * dp_id : 169
             * serial_num : T202010134454
             * createTime : 2020-10-13 15:55
             * pickType : 1
             * dispatch : 2
             * dpwid : 23
             * sa_id : 56
             * swid : 0
             * delivery_address : 江苏省南京市雨花台区物流路1号
             * delivery_lng : 118.78544536406
             * delivery_lat : 31.997858805466
             * delivery_name : 江苏省南京仓库
             * delivery_liaison : 南京联系人
             * delivery_tel : 18900000000
             * unload_address : 江苏省盐城市盐都区收货地址1号
             * unload_lng : 120.16050325734
             * unload_lat : 33.344020315599
             * unload_name : 盐城机械制造有限公司
             * unload_liaison : 盐城联系人
             * unload_tel : 15200000000
             */

            private String dp_id;
            private String serial_num;
            private String createTime;
            private String pickType;
            private String dispatch;
            private String dpwid;
            private String sa_id;
            private String swid;
            private String delivery_address;
            private String delivery_lng;
            private String delivery_lat;
            private String delivery_name;
            private String delivery_liaison;
            private String delivery_tel;
            private String unload_address;
            private String unload_lng;
            private String unload_lat;
            private String unload_name;
            private String unload_liaison;
            private String unload_tel;

            public String getDp_id() {
                return dp_id;
            }

            public void setDp_id(String dp_id) {
                this.dp_id = dp_id;
            }

            public String getSerial_num() {
                return serial_num;
            }

            public void setSerial_num(String serial_num) {
                this.serial_num = serial_num;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
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

            public String getDpwid() {
                return dpwid;
            }

            public void setDpwid(String dpwid) {
                this.dpwid = dpwid;
            }

            public String getSa_id() {
                return sa_id;
            }

            public void setSa_id(String sa_id) {
                this.sa_id = sa_id;
            }

            public String getSwid() {
                return swid;
            }

            public void setSwid(String swid) {
                this.swid = swid;
            }

            public String getDelivery_address() {
                return delivery_address;
            }

            public void setDelivery_address(String delivery_address) {
                this.delivery_address = delivery_address;
            }

            public String getDelivery_lng() {
                return delivery_lng;
            }

            public void setDelivery_lng(String delivery_lng) {
                this.delivery_lng = delivery_lng;
            }

            public String getDelivery_lat() {
                return delivery_lat;
            }

            public void setDelivery_lat(String delivery_lat) {
                this.delivery_lat = delivery_lat;
            }

            public String getDelivery_name() {
                return delivery_name;
            }

            public void setDelivery_name(String delivery_name) {
                this.delivery_name = delivery_name;
            }

            public String getDelivery_liaison() {
                return delivery_liaison;
            }

            public void setDelivery_liaison(String delivery_liaison) {
                this.delivery_liaison = delivery_liaison;
            }

            public String getDelivery_tel() {
                return delivery_tel;
            }

            public void setDelivery_tel(String delivery_tel) {
                this.delivery_tel = delivery_tel;
            }

            public String getUnload_address() {
                return unload_address;
            }

            public void setUnload_address(String unload_address) {
                this.unload_address = unload_address;
            }

            public String getUnload_lng() {
                return unload_lng;
            }

            public void setUnload_lng(String unload_lng) {
                this.unload_lng = unload_lng;
            }

            public String getUnload_lat() {
                return unload_lat;
            }

            public void setUnload_lat(String unload_lat) {
                this.unload_lat = unload_lat;
            }

            public String getUnload_name() {
                return unload_name;
            }

            public void setUnload_name(String unload_name) {
                this.unload_name = unload_name;
            }

            public String getUnload_liaison() {
                return unload_liaison;
            }

            public void setUnload_liaison(String unload_liaison) {
                this.unload_liaison = unload_liaison;
            }

            public String getUnload_tel() {
                return unload_tel;
            }

            public void setUnload_tel(String unload_tel) {
                this.unload_tel = unload_tel;
            }
        }

}
