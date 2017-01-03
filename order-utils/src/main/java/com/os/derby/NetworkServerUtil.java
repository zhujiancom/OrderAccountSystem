package com.os.derby;

import com.os.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.derby.drda.NetworkServerControl;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Created by Jian Zhu on 12/27/2016.
 */
public class NetworkServerUtil {
    private static Properties p;
    private static final Log logger = LogFactory.getLog(NetworkServerUtil.class);
    private static final String DERBY_PROPERTY_FILE_NAME="derby.properties";
    private static final String DB_SERVER_URL="derby.drda.host";
    private static final String DB_SERVER_PORT="derby.drda.portNumber";
    private static final String DB_SYSTEM_HOME="derby.system.home";

    private NetworkServerControl serverControl;
    private PrintWriter writer;

    private static class NetworkServerUtilHolder{
        private static NetworkServerUtil instance = new NetworkServerUtil();
    }

    public static NetworkServerUtil getInstance(){
        return NetworkServerUtilHolder.instance;
    }

    private NetworkServerUtil(){
        try {
            loadDerbyProperties();
            writer = new PrintWriter(new File("serverconsole.log"));
            if(StringUtils.hasText(p.getProperty(DB_SERVER_URL)) && StringUtils.hasText(p.getProperty(DB_SERVER_PORT))){
                String url = p.getProperty(DB_SERVER_URL);
                int port = Integer.valueOf(p.getProperty(DB_SERVER_PORT));
                serverControl = new NetworkServerControl(InetAddress.getByName(url), port);
            }else{
                //use default url:127.0.0.1 and default port:1527
                serverControl = new NetworkServerControl();
            }
        } catch (Exception e) {
            logger.error("create derby server failed", e);
            writer.println("create derby server failed - "+e.getMessage());
        }
    }

    private static void loadDerbyProperties() throws IOException{
        p = new Properties();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource(DERBY_PROPERTY_FILE_NAME);
        if(resource == null){
            logger.info("there is no derby.properties, Derby server will use default properties!");
            return;
        }
        PropertiesLoaderUtils.fillProperties(p, resource);
        String systemHomePath = p.getProperty(DB_SYSTEM_HOME);
        System.setProperty(DB_SYSTEM_HOME, systemHomePath);
        File destFile = new File(System.getProperty("user.dir")+File.separator+systemHomePath+File.separator+DERBY_PROPERTY_FILE_NAME);
        FileUtils.forceMkdir(destFile.getParentFile());
        p.store(new FileOutputStream(destFile), "auto generate by application as the configuration of derby system-wide properties.");
    }

    /**
     * start up derby server
     */
    public void start(){
        try {
            serverControl.start(writer);
        } catch (Exception e) {
            logger.error("startup derby server failed", e);
            writer.println("startup derby server failed - "+e.getMessage());
        }
    }

    public void shutdown(){
        try {
            serverControl.shutdown();
        } catch (Exception e) {
            logger.error("shutdown derby server failed", e);
            writer.println("shutdown derby server failed - "+e.getMessage());
        }
    }
}
