package com.os.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Created by zhujian on 2017/8/6.
 */
public class DBUtils {
    private static final Logger logger = LogManager.getLogger();

    public static boolean testConnection(DataSource ds){
        boolean hasConnection = false;
        try(Connection conn = DataSourceUtils.getConnection(ds);) {
            hasConnection = true;
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return hasConnection;
    }
}
