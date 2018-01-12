package com.os.derby;

import com.os.exceptions.ServiceException;
import com.os.utils.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.derby.drda.NetworkServerControl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Properties;

/**
 * Created by jian zhu on 05/31/2017.
 */
public final class DerbyServer {
    private static final Logger logger = LogManager.getLogger();

    private NetworkServerControl serverControl;

    private PrintWriter writer;

    private String host;

    private int port;

    private String homeName;

    private boolean logConnections;

    private boolean traceAll;

    private String traceArchivePath;

    private DerbyServer(DerbyServerBuilder builder){
        this.host = builder.host;
        this.port = builder.port;
        this.homeName = builder.homeName;
        this.traceAll = builder.traceAll;
        this.traceArchivePath = builder.traceArchivePath;
        this.logConnections = builder.logConnections;
        initServerControl();
    }

    private void initServerControl() {
        try {
//            writer = new PrintWriter(new BufferedWriter(new FileWriter(new File("derby_server_console.log"))),true);
            writer = new PrintWriter(new LoggingOutputStream(logger, logger.getLevel()));
            if(StringUtils.hasText(host) && port != 0){
                serverControl = new NetworkServerControl(InetAddress.getByName(host), port);
            }else{
                //use default url:127.0.0.1 and default port:1527
                serverControl = new NetworkServerControl();
            }
            if(logger.isDebugEnabled()){
                serverControl.logConnections(logConnections);
                serverControl.trace(traceAll);
                serverControl.setTraceDirectory(traceArchivePath);
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
            }else{
                writer.println("Derby Server is running, there is no need to start derby server again.");
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

    public boolean testForConnection() {
        try {
            serverControl.ping();
            return true;
        } catch (Exception e) {
            writer.println("attempt to connect to derby server failed, maybe attempt to start derby server. - "+e.getMessage());
            logger.warn(e);
        }
        return false;
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

        private boolean logConnections;

        private boolean traceAll;

        private String traceArchivePath;

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

        public DerbyServerBuilder logConnections(boolean on){
            this.logConnections = on;
            return this;
        }

        public DerbyServerBuilder traceAll(boolean traceAll){
            this.traceAll = traceAll;
            return this;
        }

        public DerbyServerBuilder traceArchivePath(String archivePath){
            this.traceArchivePath = archivePath;
            return this;
        }

        private void internalBuild(Properties p){
            homeName(p.getProperty(DerbyConfigConstant.SERVER_HOME));
            host(p.getProperty(DerbyConfigConstant.SERVER_URL));
            port(Integer.valueOf(p.getProperty(DerbyConfigConstant.SERVER_PORT)));
            logConnections(Boolean.valueOf(p.getProperty(DerbyConfigConstant.LOG_CONNECTIONS)));
            traceAll(Boolean.valueOf(p.getProperty(DerbyConfigConstant.TRACE_ALL)));
            traceArchivePath(p.getProperty(DerbyConfigConstant.TRACE_DIRECTORY));
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
                internalBuild(p);
                System.setProperty(DerbyConfigConstant.SERVER_HOME, homeName);
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
