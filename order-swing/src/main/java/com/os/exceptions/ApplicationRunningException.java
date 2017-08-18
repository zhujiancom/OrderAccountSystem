package com.os.exceptions;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by jian zhu on 05/24/2017.
 */
public class ApplicationRunningException extends NestedRuntimeException {
    public ApplicationRunningException(String msg) {
        super(msg);
    }

    public ApplicationRunningException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
