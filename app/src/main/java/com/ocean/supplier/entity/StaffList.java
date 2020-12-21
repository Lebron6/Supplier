package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/9/7.
 */
public class StaffList {


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
             * id : 12
             * name : Abc
             * phone : 17625642535
             * sex : 1
             * headimg : http://img.oceanscm.com/driver/headimg/2020081498354504251985.png
             * auth : 系统最高角色
             * department : AB
             * position : iOS
             * createtime : 2020-09-02 11:00
             * isadmin : 1
             * delType : 1
             * delType_n : 正常
             */

            private String sw_id;

            public void setSw_id(String sw_id) {
                this.sw_id = sw_id;
            }

            private String username;
            private String phone;
            private String sex;
            private String headimg;
            private String auth;

            public String getUsername() {
                return username;
            }

            public String getSw_id() {
                return sw_id;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            private String department;
            private String position;
            private String createtime;
            private String isadmin;
            private String delType;
            private String delType_n;
            private int selectStatus=0;

            public int getSelectStatus() {
                return selectStatus;
            }

            public void setSelectStatus(int selectStatus) {
                this.selectStatus = selectStatus;
            }



            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
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

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getIsadmin() {
                return isadmin;
            }

            public void setIsadmin(String isadmin) {
                this.isadmin = isadmin;
            }

            public String getDelType() {
                return delType;
            }

            public void setDelType(String delType) {
                this.delType = delType;
            }

            public String getDelType_n() {
                return delType_n;
            }

            public void setDelType_n(String delType_n) {
                this.delType_n = delType_n;
            }
        }

}
