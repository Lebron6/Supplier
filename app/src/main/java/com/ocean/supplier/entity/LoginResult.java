package com.ocean.supplier.entity;

/**
 * Created by James on 2020/8/11.
 */
public class LoginResult {


        private String token;
        private boolean fill_info;
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean isFill_info() {
        return fill_info;
    }

    public void setFill_info(boolean fill_info) {
        this.fill_info = fill_info;
    }

    public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

}
