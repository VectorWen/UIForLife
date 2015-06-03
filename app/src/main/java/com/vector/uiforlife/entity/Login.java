package com.vector.uiforlife.entity;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2015/6/4.
 */
public class Login extends ResponseHeader{
    private String token;

    public static Login createInstanceByJson(String json){
        Gson gson = new Gson();
        try {
            return gson.fromJson(json,Login.class);
        }catch (Exception e){
            return null;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
