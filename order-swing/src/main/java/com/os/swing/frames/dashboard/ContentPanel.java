package com.os.swing.frames.dashboard;

import com.os.ApplicationContextProvider;
import com.os.exceptions.ExceptionConstant;
import com.os.exceptions.ExceptionManagementFactory;
import com.os.services.IDataCleanFacadeService;
import com.os.swing.models.OrderItemTable;
import com.os.swing.models.OrderTable;
import com.os.utils.DateUtil;
import com.os.utils.StringUtils;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Color;

/**
 * 订单数据内容展示面板
 * Created by jian zhu on 05/27/2017.
 */
public class ContentPanel extends JSplitPane {
    private OrderTable mainTable;
    private JTable itemTable; //展示 orderItem 列表
    private JTextArea textArea; //警告日志展示面板

    public ContentPanel(int orientation,int width, int height){
        super(orientation,true);
        this.setSize(width, height);
        buildPane();
    }

    public void buildPane() {
        this.setDividerSize(5);
        this.setDividerLocation(0.6);
        this.setResizeWeight(0.6);
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.LIGHT_GRAY));
        JScrollPane mainScrollPane = new JScrollPane(); //将表格加入到滚动条组件中
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainTable = new OrderTable(12);
        mainScrollPane.setViewportView(mainTable);
        mainScrollPane.getViewport().setBackground(Color.WHITE);
        JPanel rightPane = new JPanel();
        rightPane.setLayout(new BoxLayout(rightPane, BoxLayout.Y_AXIS));
        JScrollPane rTopScrollPane = new JScrollPane(); //将表格加入到滚动条组件中
        rTopScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rTopScrollPane.setBorder(BorderFactory.createEmptyBorder());
        itemTable = new OrderItemTable(8);
        rTopScrollPane.setViewportView(itemTable);
        rTopScrollPane.getViewport().setBackground(Color.WHITE);

        textArea = new JTextArea(10,0);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.RED);
        JScrollPane rBottomScrollPane = new JScrollPane(textArea); //将表格加入到滚动条组件中
        rBottomScrollPane.setBorder(BorderFactory.createEmptyBorder());
        rBottomScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rBottomScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rightPane.add(rTopScrollPane);
        rightPane.add(rBottomScrollPane);

        this.add(mainScrollPane);
        this.add(rightPane);
    }

    public void clearData(String time){
        if(!StringUtils.hasText(time)){
            ExceptionManagementFactory.throwServiceException(ExceptionConstant.SERVICE.TIME_FORMAT, "Please select Date.");
        }
        if(!DateUtil.isDateFormat(time,"yyyyMMdd")){
            ExceptionManagementFactory.throwServiceException(ExceptionConstant.SERVICE.TIME_FORMAT, "Date format is incorrect.");
        }
        textArea.setText("");
        if(mainTable.getModel() instanceof OrderTable.OrderTableModel){
            OrderTable.OrderTableModel orderModel = (OrderTable.OrderTableModel) mainTable.getModel();
            orderModel.setRowCount(0);
        }
        if(itemTable.getModel() instanceof OrderItemTable.OrderItemTableModel){
            OrderItemTable.OrderItemTableModel itemModel = (OrderItemTable.OrderItemTableModel) itemTable.getModel();
            itemModel.setRowCount(0);
        }
        IDataCleanFacadeService datacleaner = (IDataCleanFacadeService) ApplicationContextProvider.getBean("DataCleanFacadeService");
        datacleaner.doCleanAllOfOneDay(time);
    }

    public OrderTable getMainTable() {
        return mainTable;
    }

    public JTable getItemTable() {
        return itemTable;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
