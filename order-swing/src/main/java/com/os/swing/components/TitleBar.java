package com.os.swing.components;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class TitleBar extends JPanel {
    private JLabel title;

    private int marginLeft;

    public TitleBar(JLabel title,int width,int height){
        this.title = title;
        initComponent(width,height);
    }

    private void initComponent(int width,int height) {
        setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
        setPreferredSize(new Dimension(width,height));
        add(title);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
    }
}
