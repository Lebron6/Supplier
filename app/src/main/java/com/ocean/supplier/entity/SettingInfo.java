package com.ocean.supplier.entity;

/**
 * Created by James on 2020/9/24.
 */
public class SettingInfo {

        /**
         * sw_id : 23
         * username : 测试名称
         * email :
         * phone : 18852403627
         * headimg :
         * department : 部门信息
         * position :
         * sex : 1
         */

        private int sw_id;
        private String username;
        private String email;
        private String phone;
        private String headimg;
        private String department;
        private String position;
        private String sex;

        public int getSw_id() {
            return sw_id;
        }

        public void setSw_id(int sw_id) {
            this.sw_id = sw_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
}
