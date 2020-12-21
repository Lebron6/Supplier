package com.ocean.supplier.entity;

/**
 * Created by James on 2020/6/8.
 */
public class Extras {
    /**
     * goto_type : 1
     * msgcode : 4
     * wa_id : 369
     */

    private int goto_type;
    private int msgcode;

    public String getOs_id() {
        return os_id;
    }

    public void setOs_id(String os_id) {
        this.os_id = os_id;
    }

    private String os_id;

    public int getGoto_type() {
        return goto_type;
    }

    public void setGoto_type(int goto_type) {
        this.goto_type = goto_type;
    }

    public int getMsgcode() {
        return msgcode;
    }

    public void setMsgcode(int msgcode) {
        this.msgcode = msgcode;
    }
}
