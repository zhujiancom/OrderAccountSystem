package com.os.derby;

import com.os.exceptions.ServiceException;
import com.os.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.derby.drda.NetworkServerControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Created by jian zhu on 05/31/2017.
 */
public class DerbyServer {
    private static final Logger logger = LogManager.getLogger();
    private static final String DB_SERVER_URL="derby.drda.host";
    private static final String DB_SERVER_PORT="derby.drda.portNumber";
    private static final String DB_SYSTEM_HOME="derby.system.home";

    private NetworkServerControl serverControl;

    private PrintWriter writer;

    private String host;

    private int port;

    private String homeName;

    private Properties properties;

    private DerbyServer(DerbyServerBuilder builder){
        this.host = builder.host;
        this.port = builder.port;
        this.homeName = builder.homeName;
        initServerControl();
    }

    private void initServerControl() {
        try {
            writer = new PrintWriter(new File("derby_server_console.log"));
            if(StringUtils.hasText(host) && port != 0){
                serverControl = new NetworkServerControl(InetAddress.getByName(host), port);
            }else{
                //use default url:127.0.0.1 and default port:1527
                serverControl = new NetworkServerControl();
            }
        } catch (Exception e) {
            logger.error("create derby server failed", e);
            writer.println("create derby server failed - "+e.getMessage());
        }
    }

    public void start(){
        try {
            if(!testForConnection()){
                serverControl.start(writer);
            }
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

    public boolean testForConnection() throws Exception{
        try {
            serverControl.ping();
            return true;
        } catch (Exception e) {
            writer.println("attempt to connect to derby server failed, maybe attempt to start derby server. - "+e.getMessage());
            logger.warn(e);
            return false;
        }
    }

    public void trace(boolean onoff) {
        try {
            serverControl.trace(onoff);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sysinfo(){
        try {
            return serverControl.getSysinfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String runtimeInfo(){
        try {
            return serverControl.getRuntimeInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class DerbyServerBuilder{
        private String host;

        private int port;

        private String homeName;

        public DerbyServerBuilder host(String host){
            this.host = host;
            return this;
        }

        public DerbyServerBuilder port(int port){
            this.port = port;
            return this;
        }

        public DerbyServerBuilder homeName(String homeName){
            this.homeName = homeName;
            return this;
        }

        public DerbyServerBuilder propertiesFile(String propertiesFile){
            Properties p = new Properties();
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            try {
                Resource resource = resourcePatternResolver.getResource(propertiesFile);
                if(resource == null){
                    logger.info("there is no derby configure file {}, Derby server will use default properties!",propertiesFile);
                }
                PropertiesLoaderUtils.fillProperties(p, resource);
                this.homeName = p.getProperty(DB_SYSTEM_HOME);
                this.host = p.getProperty(DB_SERVER_URL);
                this.port = Integer.valueOf(p.getProperty(DB_SERVER_PORT));
                System.setProperty(DB_SYSTEM_HOME, homeName);
                File destFile = new File(System.getProperty("user.dir")+File.separator+homeName+File.separator+"derby.properties");
                FileUtils.forceMkdir(destFile.getParentFile());
                p.store(new FileOutputStream(destFile), "auto generate by application as the configuration of derby system-wide properties.");
            }
            catch (IOException ioe) {
                logger.error(ioe);
                throw new ServiceException("create derby properties file occurs error.",ioe);
            }
            return this;
        }

        public DerbyServer build(){
            return new DerbyServer(this);
        }
    }
}
