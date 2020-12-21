package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/9/23.
 */
public class QuotationData {

        private List<String> headerlist;
        private List<List<String>> list;

        public List<String> getHeaderlist() {
            return headerlist;
        }

        public void setHeaderlist(List<String> headerlist) {
            this.headerlist = headerlist;
        }

        public List<List<String>> getList() {
            return list;
        }

        public void setList(List<List<String>> list) {
            this.list = list;
        }



//
//        private List<HeaderlistBean> headerlist;
//        private List<List<String>> list;
//
//        public List<HeaderlistBean> getHeaderlist() {
//            return headerlist;
//        }
//
//        public void setHeaderlist(List<HeaderlistBean> headerlist) {
//            this.headerlist = headerlist;
//        }
//
//        public List<List<String>> getList() {
//            return list;
//        }
//
//        public void setList(List<List<String>> list) {
//            this.list = list;
//        }
//
//        public static class HeaderlistBean {
//            /**
//             * name : 出发地
//             */
//
//            private String name;
//
//            public String getName() {
//                return name;
//            }
//
//            public void setName(String name) {
//                this.name = name;
//            }
//        }

}

