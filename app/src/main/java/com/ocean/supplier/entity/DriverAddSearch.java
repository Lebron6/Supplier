package com.ocean.supplier.entity;

/**
 * Created by James on 2020/8/27.
 */
public class DriverAddSearch {

        /**
         * info : {"driver_id":"2","name":"李四","phone":"13852634521","type":null,"headimg":"","license_num":null,"id_card":null,"tel_name":null,"tel_num":null,"driver_status":"1"}
         */

        private InfoBean info;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * driver_id : 2
             * name : 李四
             * phone : 13852634521
             * type : null
             * headimg :
             * license_num : null
             * id_card : null
             * tel_name : null
             * tel_num : null
             * driver_status : 1
             */

            private String driver_id;
            private String name;
            private String phone;
            private String type;
            private String headimg;
            private String license_num;
            private String id_card;
            private String tel_name;
            private String tel_num;
            private String driver_status;

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

            public Object getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public Object getLicense_num() {
                return license_num;
            }

            public void setLicense_num(String license_num) {
                this.license_num = license_num;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public String getTel_name() {
                return tel_name;
            }

            public void setTel_name(String tel_name) {
                this.tel_name = tel_name;
            }

            public Object getTel_num() {
                return tel_num;
            }

            public void setTel_num(String tel_num) {
                this.tel_num = tel_num;
            }

            public String getDriver_status() {
                return driver_status;
            }

            public void setDriver_status(String driver_status) {
                this.driver_status = driver_status;
            }
        }

}
