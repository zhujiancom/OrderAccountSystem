package com.os.exceptions;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by jian zhu on 1-16-17.
 */
public class UIException extends NestedRuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = -5040673126034271070L;

    public UIException(String msg) {
        super(msg);
    }

    public UIException(String msg , Throwable cause) {
        super(msg,cause);
    }
}
