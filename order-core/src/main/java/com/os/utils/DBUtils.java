package com.os.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zhujian on 2017/8/6.
 */
public class DBUtils {
    private static final Logger logger = LogManager.getLogger();

    public static boolean testConnection(DataSource ds){
        boolean isConnected = false;
        try(Connection conn = ds.getConnection();) {
            isConnected = true;
        } catch (SQLException se) {
            logger.warn("DB connection occurs Error, "+se.getMessage());
        }
        return isConnected;
    }
}
