package com.os.swing.frames.dashboard;

import com.os.enums.BusinessEnums;
import com.os.swing.components.DatePickerFactory;
import com.os.swing.components.buttons.ButtonFactory;
import com.os.utils.PropertyUtils;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单查询面板
 * Created by jian zhu on 05/24/2017.
 */
public class QueryFormPanel extends JPanel implements ItemListener {
    private JButton queryBtn;
    private JButton cleanBtn;
    private JXDatePicker datepicker;
    private JLabel actionLabel;
    private JLabel warningLabel;
    private boolean warningShowing;

    private Icon loadingIcon;
    private Icon doneIcon;
    private Icon warningIcon;
    private Icon errorIcon;

    public QueryFormPanel() {
        initComponent();
        URL loadingIconUrl = this.getClass().getClassLoader().getResource(PropertyUtils.getStringValue("image.path.loading"));
        loadingIcon = new ImageIcon(loadingIconUrl);
        URL doneIconUrl = this.getClass().getClassLoader().getResource(PropertyUtils.getStringValue("image.path.done"));
        doneIcon = new ImageIcon(doneIconUrl);
        URL errorIconUrl = this.getClass().getClassLoader().getResource(PropertyUtils.getStringValue("image.path.error"));
        errorIcon = new ImageIcon(errorIconUrl);
        URL warningIconUrl = this.getClass().getClassLoader().getResource(PropertyUtils.getStringValue("image.path.warning"));
        warningIcon = new ImageIcon(warningIconUrl);
    }

    public void initComponent(){
        setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1));
        JPanel queryPane = new JPanel();
        queryPane.setBackground(Color.WHITE);
        datepicker = DatePickerFactory.getDefaultDatePicker();
        queryBtn = ButtonFactory.createImageButton("Search", PropertyUtils.getStringValue("image.path.searchBtn"), null);
        cleanBtn = ButtonFactory.createImageButton("Reset",PropertyUtils.getStringValue("image.path.emptyTrashBtn"), null);
        JPanel actionPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
        actionPane.setBackground(Color.WHITE);
        actionPane.setPreferredSize(new Dimension(300,30));
        actionLabel = new JLabel();
        actionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        actionLabel.setForeground(Color.RED);
        actionPane.add(actionLabel);

        JPanel inventoryWarningPane = new JPanel();
        inventoryWarningPane.setBackground(Color.WHITE);
        warningLabel = new JLabel();
        warningLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        warningLabel.setForeground(Color.RED);
        inventoryWarningPane.add(warningLabel);

        queryPane.add(datepicker);
        queryPane.add(queryBtn);
        queryPane.add(cleanBtn);
        queryPane.add(actionPane);
        queryPane.add(inventoryWarningPane);
        this.add(queryPane);
//        this.add(createCheckPane()); //添加复选框
        this.setVisible(true);
        this.setSize(500, 300);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public void displayInfoLoading(String diplayInfo){
        actionLabel.setText(diplayInfo);
        actionLabel.setIcon(loadingIcon);
    }

    public void displayInfoDone(String diplayInfo){
        actionLabel.setText(diplayInfo);
        actionLabel.setIcon(doneIcon);
    }

    public void displayErrorInfo(String errorInfo){
        actionLabel.setText(errorInfo);
        actionLabel.setIcon(errorIcon);
    }

    public void displayWarningInfo(String warningInfo){
        warningLabel.setText(warningInfo);
        warningLabel.setIcon(warningIcon);
        warningShowing = true;
    }

    public void hideInfo(){
        actionLabel.setText(null);
        actionLabel.setIcon(null);
    }

    public void removeWarningInfo(){
        warningLabel.setText(null);
        warningLabel.setIcon(null);
        warningShowing = false;
    }

    public boolean isWarningShowing() {
        return warningShowing;
    }

    public void setWarningShowing(boolean warningShowing) {
        this.warningShowing = warningShowing;
    }


    public JXDatePicker getDatepicker() {
        return datepicker;
    }

    public JButton getQueryBtn() {
        return queryBtn;
    }

    public JButton getCleanBtn() {
        return cleanBtn;
    }
}
