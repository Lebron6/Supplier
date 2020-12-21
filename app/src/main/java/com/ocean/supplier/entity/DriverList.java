package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/8/24.
 */
public class DriverList {


        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * tl_driver_id : 9
             * name : 李四
             * phone : 13852634521
             * headimg :
             * type :
             * id_card :
             * createtime : 2020-08-06 10:45
             * status : 1
             */

            private String s_driver_id;
            private String name;
            private String phone;
            private String headimg;
            private String type;
            private String id_card;
            private String createtime;
            private String status;
            private int selectStatus=0;

            public int getSelectStatus() {
                return selectStatus;
            }

            public void setSelectStatus(int selectStatus) {
                this.selectStatus = selectStatus;
            }

            public String getS_driver_id() {
                return s_driver_id;
            }

            public void setS_driver_id(String tl_driver_id) {
                this.s_driver_id = tl_driver_id;
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

            public String getHeadimg() {
                return headimg;
            }

            public void setHeadimg(String headimg) {
                this.headimg = headimg;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }

}
