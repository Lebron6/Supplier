package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/8/26.
 */
public class VehicleGetInfo {

        /**
         * vehicle : {"vehicle_id":"1","num":"苏E654321","runNum":"25463573567457","f_time":"2020-08-05 17:33","max_weight":"8.00","max_volume":"8.00","car_type":"3","car_info":"4。1*1.7*1.7","make_car":"阿斯顿发","driver_id":"1","name":"张三","phone":"3795417604","gps":"4234234","colour_car":"紫色","remarks":"发山东分公司的","sys_status":"1"}
         * drivers : [{"drivers_id":"1","name":"张三","phone":"3795417604"}]
         */

        private VehicleBean vehicle;
        private List<DriversBean> drivers;

        public VehicleBean getVehicle() {
            return vehicle;
        }

        public void setVehicle(VehicleBean vehicle) {
            this.vehicle = vehicle;
        }

        public List<DriversBean> getDrivers() {
            return drivers;
        }

        public void setDrivers(List<DriversBean> drivers) {
            this.drivers = drivers;
        }

        public static class VehicleBean {
            /**
             * vehicle_id : 1
             * num : 苏E654321
             * runNum : 25463573567457
             * f_time : 2020-08-05 17:33
             * max_weight : 8.00
             * max_volume : 8.00
             * car_type : 3
             * car_info : 4。1*1.7*1.7
             * make_car : 阿斯顿发
             * driver_id : 1
             * name : 张三
             * phone : 3795417604
             * gps : 4234234
             * colour_car : 紫色
             * remarks : 发山东分公司的
             * sys_status : 1
             */

            private String vehicle_id;
            private String num;
            private String runNum;
            private String f_time;
            private String max_weight;
            private String max_volume;
            private String car_type;
            private String car_info;
            private String make_car;
            private String driver_id;
            private String name;
            private String phone;
            private String gps;
            private String colour_car;
            private String remarks;
            private String sys_status;

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

            public String getRunNum() {
                return runNum;
            }

            public void setRunNum(String runNum) {
                this.runNum = runNum;
            }

            public String getF_time() {
                return f_time;
            }

            public void setF_time(String f_time) {
                this.f_time = f_time;
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

            public String getMake_car() {
                return make_car;
            }

            public void setMake_car(String make_car) {
                this.make_car = make_car;
            }

            public String getDriver_id() {
                return driver_id;
            }

            public void setDriver_id(String driver_id) {
                this.driver_id = driver_id;
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

            public String getGps() {
                return gps;
            }

            public void setGps(String gps) {
                this.gps = gps;
            }

            public String getColour_car() {
                return colour_car;
            }

            public void setColour_car(String colour_car) {
                this.colour_car = colour_car;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getSys_status() {
                return sys_status;
            }

            public void setSys_status(String sys_status) {
                this.sys_status = sys_status;
            }
        }

        public static class DriversBean {
            /**
             * drivers_id : 1
             * name : 张三
             * phone : 3795417604
             */

            private String drivers_id;
            private String name;
            private String phone;

            public String getDrivers_id() {
                return drivers_id;
            }

            public void setDrivers_id(String drivers_id) {
                this.drivers_id = drivers_id;
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

    }
}
