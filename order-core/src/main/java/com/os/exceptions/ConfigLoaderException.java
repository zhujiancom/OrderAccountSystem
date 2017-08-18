package com.os.exceptions;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by Jian Zhu on 12/27/2016.
 */
public class ConfigLoaderException extends NestedRuntimeException {
    public ConfigLoaderException(String msg) {
        super(msg);
    }

    public ConfigLoaderException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
