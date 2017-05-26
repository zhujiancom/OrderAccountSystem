package com.os;

import com.os.exceptions.ApplicationRunningException;
import com.os.swing.frames.RootFrame;
import com.os.utils.PropertyUtils;
import oracle.jrockit.jfr.JFR;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;

/**
 * Created by jian zhu on 1-16-17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.os"})
//@SpringBootApplication
public class AppEntry {
    private static final Log logger = LogFactory.getLog(AppEntry.class);

    @Bean
    public RootFrame createDashboard(){
        RootFrame frame = new RootFrame();
        URL sysIconUrl = AppEntry.class.getClassLoader().getResource("skin/gray/images/24x24/logo.png");
        Image frameIcon = Toolkit.getDefaultToolkit().createImage(sysIconUrl);
        frame.setIconImage(frameIcon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // 相对居中, 在frame设置size之后
        frame.getContentPane().setBackground(Color.WHITE);
        return frame;
    }

    public static void main(String[] args){
        final ConfigurableApplicationContext ctx = new SpringApplicationBuilder(AppEntry.class).headless(false).run(args);
//        final ConfigurableApplicationContext ctx = SpringApplication.run(AppEntry.class,args);
        EventQueue.invokeLater(() -> {
            if(isRunning()){
                SpringApplication.exit(ctx,() -> -1);
                throw new ApplicationRunningException("Application is running!");
            }
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
            RandomAccessFile fis = new RandomAccessFile(path+applicationName+".lock","rw");
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
