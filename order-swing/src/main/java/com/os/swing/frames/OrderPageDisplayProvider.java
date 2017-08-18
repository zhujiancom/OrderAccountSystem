package com.os.swing.frames;

import com.os.exceptions.ExceptionConstant;
import com.os.exceptions.ExceptionManagementFactory;
import com.os.modelview.OrderPage;
import com.os.swing.components.MaskDialog;
import com.os.swing.frames.dashboard.ConclusionPanel;
import com.os.swing.frames.dashboard.ContentPanel;
import com.os.swing.frames.dashboard.QueryFormPanel;
import com.os.swing.models.OrderItemTable;
import com.os.swing.models.OrderTable;
import com.os.utils.StringUtils;
import com.os.bean.vos.OrderVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class OrderPageDisplayProvider {
    private static final Logger logger = LogManager.getLogger();
    protected QueryFormPanel queryPane;

    protected ContentPanel contentPane;

    protected ConclusionPanel conclusionPane;

    public OrderPageDisplayProvider(QueryFormPanel queryPane,ContentPanel contentPane,ConclusionPanel conclusionPane){
        this.queryPane = queryPane;
        this.contentPane = contentPane;
        this.conclusionPane = conclusionPane;
    }

    public void displayOrders(IDataLoaderCallback callback){
        final MaskDialog dialog = new MaskDialog(null);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                dialog.loading("OrderEntity Data is loading, Please waiting...");
            }
        });
        OrderPage page = new OrderPage();
        try{
            page = callback.doLoad();
        }catch(Exception e){
            dialog.dispose();
            logger.error("OrderEntity data loading error.",e);
            ExceptionManagementFactory.throwServiceException(ExceptionConstant.SERVICE.DATA_ERROR,"OrderEntity data loading occurs Error.");
        }
        final Date date = page.getOrderDate();
        OrderTable.OrderTableModel otm = (OrderTable.OrderTableModel) contentPane.getMainTable().getModel();
        OrderItemTable.OrderItemTableModel ottm = (OrderItemTable.OrderItemTableModel) contentPane.getItemTable().getModel();

        List<OrderVO> ordervos = new ArrayList<OrderVO>();
        final StringBuffer warningInfo = new StringBuffer();
        Map<String,OrderVO> orderMapping = page.getOrderMapping();

        if(!CollectionUtils.isEmpty(orderMapping)){
            int count = 0;
            for(Iterator<Map.Entry<String,OrderVO>> it = orderMapping.entrySet().iterator(); it.hasNext();){
                Map.Entry<String,OrderVO> entry = it.next();
                String payno = entry.getKey();
                OrderVO order = entry.getValue();
                if(StringUtils.hasText(order.getWarningInfo())){
                    warningInfo.append(++count).append(". ")
                            .append("[").append(payno).append("]-")
                            .append(order.getWarningInfo())
                            .append("\n");
                }
                ordervos.add(order);
            }
            ordervos.stream().sorted();

            contentPane.getMainTable().refreshTable(ordervos);
            OrderVO order = otm.getOrderAt(0);
            ((OrderItemTable)contentPane.getItemTable()).reflushTable(order.getItems());

            //显示入账金额
//            conclusionPane.setDailyPostAccountMap(page.getDailyPostAccountMap());
//            conclusionPane.setQueryDate(date);
//            conclusionPane.refreshUI();
            SwingUtilities.invokeLater(() -> {
                queryPane.hideInfo();
                queryPane.getDatepicker().setDate(date);
                contentPane.getTextArea().setText(warningInfo.toString());
                dialog.done("订单加载完成！");
            });
        }else{
            otm.setRowCount(0);
            ottm.setRowCount(0);
            SwingUtilities.invokeLater(() -> {
                conclusionPane.clearData();
                dialog.error("没有订单数据！");});
        }
    }

    public ContentPanel getContentPane() {
        return contentPane;
    }

    public interface IDataLoaderCallback{
        OrderPage doLoad();
    }

}
