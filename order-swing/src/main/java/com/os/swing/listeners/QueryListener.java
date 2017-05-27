package com.os.swing.listeners;

import com.os.exceptions.ExceptionConstant;
import com.os.exceptions.ExceptionManagementFactory;
import com.os.services.OrderDataLoader;
import com.os.swing.frames.OrderPageDisplayProvider;
import com.os.swing.frames.dashboard.ConclusionPanel;
import com.os.swing.frames.dashboard.ContentPanel;
import com.os.swing.frames.dashboard.QueryFormPanel;
import com.os.swing.handlers.InventoryWarningHandler;
import com.os.swing.models.OrderItemTable;
import com.os.utils.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class QueryListener implements ActionListener,ListSelectionListener {
    private static final Logger logger = LogManager.getLogger();

    private ContentPanel contentPane;
    private ConclusionPanel conclusionPane;
    private String time;
    private QueryFormPanel queryPane;

    public QueryListener(QueryFormPanel queryPane,ContentPanel contentPane){
        this.queryPane =queryPane;
        this.contentPane = contentPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time = DateUtil.date2Str(queryPane.getDatepicker().getDate(), "yyyyMMdd");
        try{
            loadOrderData(time);
            contentPane.getMainTable().getSelectionModel().addListSelectionListener(this);
            InventoryWarningHandler.getInstance().displayWarningInfo();
        }catch(ServiceException se){
            JOptionPane.showMessageDialog(null, new JLabel("<html><font color='red'>"+se.getMessage()+"</font></html>"));
        }catch (ParseException pe) {
            JOptionPane.showMessageDialog(null, pe.getMessage());
        }catch(Exception ex){
            logger.error(ex);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent event) {
        if(event.getSource() == contentPane.getMainTable().getSelectionModel()
                && contentPane.getMainTable().getRowSelectionAllowed()){
            int row = contentPane.getMainTable().getSelectedRow();
            if(row != -1){
                String payno = (String) contentPane.getMainTable().getValueAt(row, 1);
                ((OrderItemTable)contentPane.getItemTable()).reflushTable(time,payno);
            }
        }
    }

    /**
     * 装载 订单order 数据
     * @param time
     * @throws ParseException
     */
    public void loadOrderData(String time) throws ParseException, ServiceException{
        contentPane.getTextArea().setText("");
        if(!DateUtil.isDateFormat(time,"yyyyMMdd")){
            ExceptionManagementFactory.throwServiceException(ExceptionConstant.SERVICE.TIME_FORMAT, "日期格式错误");
        }
        Date queryDate = DateUtil.parseDate(time,"yyyyMMdd");
        Date currentDate = DateUtil.getCurrentDate();
        if(queryDate.after(currentDate)){
            ExceptionManagementFactory.throwServiceException(ExceptionConstant.SERVICE.TIME_FORMAT, "查询时间不能晚于当前时间");
        }

        OrderPageDisplayProvider provider = new OrderPageDisplayProvider(queryPane,contentPane,conclusionPane);
        OrderDataLoader dataLoader = new OrderDataLoader(queryDate,provider);

        Thread loaderThread = new Thread(dataLoader);
        loaderThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        queryPane.displayErrorInfo("订单加载失败");
                    }
                });
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        });
        loaderThread.start();
    }

    public void setConclusionPane(ConclusionPanel conclusionPane) {
        this.conclusionPane = conclusionPane;
    }
}
