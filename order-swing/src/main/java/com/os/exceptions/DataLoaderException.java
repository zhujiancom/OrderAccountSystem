package com.os.exceptions;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class DataLoaderException extends NestedRuntimeException {
    private ExceptionConstant.SERVICE service;

    public DataLoaderException(String msg) {
        super(msg);
    }

    public DataLoaderException(String msg , Throwable cause) {
        super(msg,cause);
    }

    public DataLoaderException(ExceptionConstant.SERVICE service,String msg){
        super(msg);
        this.service = service;
    }

    public DataLoaderException(ExceptionConstant.SERVICE service,String msg , Throwable cause) {
        super(msg,cause);
        this.service = service;
    }

    @Override
    public String getMessage() {
        if(service != null){
            return "["+service+"]>>>"+super.getMessage();
        }
        return super.getMessage();
    }
}
