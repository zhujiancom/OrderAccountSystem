package com.os.swing.listeners;

import com.os.exceptions.ServiceException;
import com.os.swing.frames.dashboard.ConclusionPanel;
import com.os.swing.frames.dashboard.ContentPanel;
import com.os.swing.frames.dashboard.QueryFormPanel;
import com.os.utils.DateUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by jz on 2017/5/28.
 */
public class CleanListener implements ActionListener {
    private ConclusionPanel conclusionPane;
    private ContentPanel contentPane;
    private QueryFormPanel queryPanel;

    public CleanListener(ContentPanel contentPane){
        this.contentPane = contentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date queryDate = queryPanel.getDatepicker().getDate();
        final String time = DateUtil.date2Str(queryDate, "yyyyMMdd");
        new Thread(() -> {
            try{
                SwingUtilities.invokeLater(() -> {
                    queryPanel.displayInfoLoading("正在清除 "+ time +" 数据！");
                });
                conclusionPane.clearData();
                contentPane.clearData(time);
                SwingUtilities.invokeLater(() -> {
                    queryPanel.displayInfoDone("日期："+time+" 数据清除成功！");
                });
            }catch(ServiceException se){
                JOptionPane.showMessageDialog(null, new JLabel("<html><font color='red'>"+se.getMessage()+"</font></html>"));
            }
        }).start();
    }

    public void setConclusionPane(ConclusionPanel conclusionPane) {
        this.conclusionPane = conclusionPane;
    }

    public void setContentPane(ContentPanel contentPane) {
        this.contentPane = contentPane;
    }

    public void setQueryPanel(QueryFormPanel queryPanel) {
        this.queryPanel = queryPanel;
    }
}
