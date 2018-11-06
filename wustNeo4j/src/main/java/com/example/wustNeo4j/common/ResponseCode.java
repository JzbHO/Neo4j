package com.example.wustNeo4j.common;

/**
 * Created by Administrator on 2018/10/22 0022.
 */
public enum ResponseCode {

    OK(1),
    FAIL(0);

    private int code;

    ResponseCode(int i) {
        this.code=i;
    }

    public int getCode(){
        return code;
    }
}
