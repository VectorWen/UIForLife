package com.vector.uiforlife.entity;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2015/6/4.
 */
public class ResponseHeader {
    private int code;
    private String msg;

    public static ResponseHeader createInstanceByJson(String json) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(json, ResponseHeader.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
