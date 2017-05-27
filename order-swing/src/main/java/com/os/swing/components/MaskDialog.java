package com.os.swing.components;

import com.sun.awt.AWTUtilities;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class MaskDialog extends JDialog {
    private static final int DEFAULT_WIDTH=300;

    private static final int DEFAULT_HEIGHT=150;

    private Icon loadingIcon;

    private Icon doneIcon;

    private Icon errorIcon;

    public MaskDialog(JFrame parent,int width,int height){
        super(parent,true);
        URL loadingIconUrl = this.getClass().getClassLoader().getResource("skin/gray/images/24x24/loading.gif");
        loadingIcon = new ImageIcon(loadingIconUrl);
        URL doneIconUrl = this.getClass().getClassLoader().getResource("skin/gray/images/24x24/done.png");
        doneIcon = new ImageIcon(doneIconUrl);
        URL errorIconUrl = this.getClass().getClassLoader().getResource("skin/gray/images/24x24/error.png");
        errorIcon = new ImageIcon(errorIconUrl);
        setSize(width, height);
        setLocationRelativeTo(parent);
        getContentPane().setBackground(Color.BLACK);
    }

    public MaskDialog(JFrame parent){
        this(parent,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

    public void loading(String message){
        setUndecorated(true);
        getRootPane().setOpaque(false);
        AWTUtilities.setWindowOpacity(this, 0.6f);
        AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0d, 0d, this.getWidth(), this.getHeight(), 10, 10));
        this.getContentPane().removeAll();
        JLabel messageLabel = new JLabel(message,loadingIcon, SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        this.getContentPane().add(messageLabel);
        this.setVisible(true);
    }

    public void done(String message){
        this.getContentPane().removeAll();
        JLabel messageLabel = new JLabel(message,doneIcon,SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        this.getContentPane().add(messageLabel);
        this.setVisible(true);
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        final JDialog dialog = this;
        s.schedule(new Runnable() {

            @Override
            public void run() {
                dialog.dispose();
            }
        }, 2, TimeUnit.SECONDS);
    }

    public void error(String message){
        this.getContentPane().removeAll();
        JLabel messageLabel = new JLabel(message,errorIcon,SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        this.getContentPane().add(messageLabel);
        this.setVisible(true);
        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
        final JDialog dialog = this;
        s.schedule(new Runnable() {

            @Override
            public void run() {
                dialog.dispose();
            }
        }, 5, TimeUnit.SECONDS);
    }

}
