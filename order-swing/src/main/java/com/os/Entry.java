package com.os;

import com.os.frames.RootFrame;
import com.os.utils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * Created by jian zhu on 1-16-17.
 */
public class Entry {
    private static final Log logger = LogFactory.getLog(Entry.class);

    private static JFrame frame;

    public static void main(String[] args){
        if(isRunning()){
            System.exit(0);
        }
        try{
            new ClassPathXmlApplicationContext("spring/spring-mysql-context.xml","spring/spring-ehcache.xml");
            frame = new RootFrame();
            URL sysIconUrl = Entry.class.getClassLoader().getResource("skin/gray/images/24x24/logo.png");
            Image frameIcon = Toolkit.getDefaultToolkit().createImage(sysIconUrl);
            frame.setIconImage(frameIcon);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null); // 相对居中, 在frame设置size之后
            frame.getContentPane().setBackground(Color.WHITE);
            frame.setVisible(true);
        }catch(BeansException bex){
            logger.error("Spring container start failure!",bex);
            JOptionPane.showMessageDialog(null, "Spring container start failure!");
        }finally{
        }
    }

    private static boolean isRunning()
    {
        boolean rv=false;
        try {
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
            @SuppressWarnings("resource")
            RandomAccessFile fis = new RandomAccessFile(path+applicationName+".lock","rw");
            FileChannel lockfc = fis.getChannel();
            FileLock flock = lockfc.tryLock();
            if(flock == null) {
                JOptionPane.showMessageDialog(null,"程序正在运行！");
                rv=true;
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return rv;
    }
}
