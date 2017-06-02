package com.os;

import com.os.derby.DerbyServer;
import com.os.swing.frames.RootFrame;
import com.os.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by jian zhu on 1-16-17.
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
//@EnableAutoConfiguration
@EnableJpaRepositories
@EnableCaching
@ComponentScan(basePackages = {"com.os"})
public class AppEntry {
    private static final Logger logger = LogManager.getLogger();

    public static RandomAccessFile fis;

    public static void main(String[] args) throws Exception{
        if(isRunning()){
            logger.error("Application is Running!");
            if(fis != null){
                fis.close();
            }
            System.exit(-1);
        }

        DerbyServer dbServer = new DerbyServer.DerbyServerBuilder().propertiesFile("swing-derby.properties").build();
        dbServer.start();

        final ConfigurableApplicationContext ctx = new SpringApplicationBuilder(AppEntry.class)
                                                    .bannerMode(Banner.Mode.OFF)
                                                    .headless(false).run(args);
//        final ConfigurableApplicationContext ctx = SpringApplication.run(AppEntry.class,args);
        EventQueue.invokeLater(() -> {
            JFrame dashboard = ctx.getBean(RootFrame.class);
            dashboard.setVisible(true);
        });
    }

    private static boolean isRunning()
    {
        boolean rv=false;
        //
        String os_name=System.getProperty("os.name");
        //指定文件锁路径
        String path=null;
        if(os_name.indexOf("Windows")>-1)
        {
            //如果是Windows操作系统
            path=System.getProperty("user.dir")+System.getProperty("file.separator");
        }
        else
        {
            path="/usr/temp/";
        }
        File dir=new File(path);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        //程序名称
        String applicationName= PropertyUtils.getStringValue("application.name");
        try{
            String lockFileName = path+applicationName+".lock";
            fis = new RandomAccessFile(lockFileName,"rw");
            FileChannel lockfc = fis.getChannel();
            FileLock flock = lockfc.tryLock();
            if(flock == null) {
                JOptionPane.showMessageDialog(null,"Application is Running!");
                rv=true;
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
        return rv;
    }
}
