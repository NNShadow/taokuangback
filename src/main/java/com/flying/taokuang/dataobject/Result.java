package com.flying.taokuang.dataobject;

import java.io.Serializable;

/**
 * @author NNShadow
 * @date 2019/12/31 10:50
 */
public class Result<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
