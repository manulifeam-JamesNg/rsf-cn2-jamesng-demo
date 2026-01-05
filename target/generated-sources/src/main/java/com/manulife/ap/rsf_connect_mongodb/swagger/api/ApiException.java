package com.manulife.ap.rsf_connect_mongodb.swagger.api;


public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
