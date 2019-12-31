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
}
