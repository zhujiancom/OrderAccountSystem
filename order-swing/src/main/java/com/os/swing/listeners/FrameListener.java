package com.os.swing.listeners;

import com.os.swing.frames.RootFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * Created by jian zhu on 1-16-17.
 */
public class FrameListener extends MouseAdapter{
    private static final int ACTION_MOVE = 0;
    private static final int ACTION_SE_RESIZE = 1;
    private static final int ACTION_E_RESIZE = 2;
    private static final int ACTION_S_RESIZE = 3;

    private Point lastPoint;
    private JFrame frame;
    private JButton maximizeBtn;
    private int action;

    public FrameListener(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastPoint = e.getLocationOnScreen();

        double width = frame.getWidth();
        double height = frame.getHeight();
        if(e.getClickCount() == 2){
            return;
        }
        if(width - e.getPoint().getX() <= 10 && height - e.getPoint().getY() <= 10){
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
            action = ACTION_SE_RESIZE;
        }else if(width - e.getPoint().getX() <= 10 && height - e.getPoint().getY() > 10){
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            action = ACTION_E_RESIZE;
        }else if(width - e.getPoint().getX() > 10 && height - e.getPoint().getY() <= 10){
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
            action = ACTION_S_RESIZE;
        }else{
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            action = ACTION_MOVE;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e){
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = e.getLocationOnScreen();
        int offsetx = point.x-lastPoint.x;
        int offsety = point.y-lastPoint.y;

        Rectangle bounds = frame.getBounds();
        if(action == ACTION_MOVE){
            bounds.x += offsetx;
            bounds.y += offsety;
        }
        else if(action == ACTION_SE_RESIZE){
            bounds.width += offsetx;
            bounds.height += offsety;
        }
        else if(action == ACTION_E_RESIZE){
            bounds.width += offsetx;
        }
        else if(action == ACTION_S_RESIZE){
            bounds.height += offsety;
        }
        frame.setBounds(bounds);
        lastPoint = point;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            ((RootFrame)frame).doMaximizeWindow(maximizeBtn);
        }
    }

    public JButton getMaximizeBtn() {
        return maximizeBtn;
    }

    public void setMaximizeBtn(JButton maximizeBtn) {
        this.maximizeBtn = maximizeBtn;
    }

    @Override
    public void mouseMoved(MouseEvent e){
        double width = frame.getWidth();
        double height = frame.getHeight();
        if(width - e.getPoint().getX() <= 10 && height - e.getPoint().getY() <= 10){
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
        }else if(width - e.getPoint().getX() <= 10 && height - e.getPoint().getY() > 10){
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        }else if(width - e.getPoint().getX() > 10 && height - e.getPoint().getY() <= 10){
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
        }else{
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }
}
