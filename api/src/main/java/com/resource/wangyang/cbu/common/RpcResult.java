package com.resource.wangyang.cbu.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class RpcResult<T> implements Serializable {

    private boolean success;

    private String code;

    private String msg;

    private T data;

    public RpcResult(T data) {
        this.data = data;
        this.success=true;
    }

    public RpcResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.success=false;
    }
}
