package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/8/25.
 */
public class VehicleGetResult {

        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * vehicle_id : 8
             * num : 苏E654321
             * name : 李四
             * phone : 13852634521
             * car_type : 罐装车
             * car_info : 4.2米轿车
             * max_weight : 9999.00
             * max_volume : 8888.00
             * status : 4
             * sys_status : 2
             */

            private String vehicle_id;
            private String num;
            private String name;
            private String phone;
            private String car_type;
            private String car_info;
            private String max_weight;
            private String max_volume;
            private String status;
            private String sys_status;
            private String tpls;

            public String getTpls() {
                return tpls;
            }

            public void setTpls(String tpls) {
                this.tpls = tpls;
            }

            private int selectStatus=0;

            public int getSelectStatus() {
                return selectStatus;
            }

            public void setSelectStatus(int selectStatus) {
                this.selectStatus = selectStatus;
            }

            public String getVehicle_id() {
                return vehicle_id;
            }

            public void setVehicle_id(String vehicle_id) {
                this.vehicle_id = vehicle_id;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getCar_type() {
                return car_type;
            }

            public void setCar_type(String car_type) {
                this.car_type = car_type;
            }

            public String getCar_info() {
                return car_info;
            }

            public void setCar_info(String car_info) {
                this.car_info = car_info;
            }

            public String getMax_weight() {
                return max_weight;
            }

            public void setMax_weight(String max_weight) {
                this.max_weight = max_weight;
            }

            public String getMax_volume() {
                return max_volume;
            }

            public void setMax_volume(String max_volume) {
                this.max_volume = max_volume;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSys_status() {
                return sys_status;
            }

            public void setSys_status(String sys_status) {
                this.sys_status = sys_status;
            }
        }

}
