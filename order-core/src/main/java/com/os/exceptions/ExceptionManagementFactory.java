package com.os.exceptions;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class ExceptionManagementFactory {
    public static void throwServiceException(ExceptionConstant.SERVICE service, String msg) throws ServiceException{
        throw new ServiceException(service,msg);
    }

    public static void throwServiceException(ExceptionConstant.SERVICE service, String msg, Throwable cause) throws ServiceException{
        throw new ServiceException(service,msg,cause);
    }
}
