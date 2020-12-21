package com.ocean.supplier.entity;

import java.util.List;

/**
 * Created by James on 2020/11/20.
 */
public class Track {


        private List<StatusListBean> status_list;
        private List<List<TrackBean>> track;

        public List<StatusListBean> getStatus_list() {
            return status_list;
        }

        public void setStatus_list(List<StatusListBean> status_list) {
            this.status_list = status_list;
        }

        public List<List<TrackBean>> getTrack() {
            return track;
        }

        public void setTrack(List<List<TrackBean>> track) {
            this.track = track;
        }

        public static class StatusListBean {
            /**
             * value : 新建
             * selected : false
             * time : --
             */

            private String value;
            private boolean selected;
            private String time;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }
        }

        public static class TrackBean {
            /**
             * lng : 112.5633
             * lat : 31.53453
             */

            private String lng;
            private String lat;

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }
        }

}

