package com.os.exceptions;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class ServiceException extends NestedRuntimeException {
    private ExceptionConstant.SERVICE service;

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * @param msg
     */
    public ServiceException(ExceptionConstant.SERVICE service,String msg){
        super(msg);
        this.service = service;
    }

    public ServiceException(ExceptionConstant.SERVICE service,Throwable cause){
        super(cause.getMessage(),cause);
        this.service = service;
    }

    public ServiceException(ExceptionConstant.SERVICE service,String msg, Throwable cause){
        super(msg,cause);
        this.service = service;
    }

    @Override
    public String getMessage(){
        if(service != null){
            return "["+service+"]>>>"+super.getMessage();
        }
        return super.getMessage();
    }

    public ExceptionConstant.SERVICE getService() {
        return service;
    }

    public void setService(ExceptionConstant.SERVICE service) {
        this.service = service;
    }
}
