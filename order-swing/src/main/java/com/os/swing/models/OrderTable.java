package com.os.swing.models;

import com.os.enums.CommonEnums;
import com.os.swing.renderers.AbstractLineColorMarkRenderer;
import com.os.utils.DateUtil;
import com.os.bean.vos.OrderVO;
import org.springframework.util.CollectionUtils;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class OrderTable extends BaseTable<OrderVO>{

    public OrderTable(int columnNum){
        super(columnNum);
        this.setRowHeight(25);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    protected void setModel() {
        OrderTableModel model = new OrderTableModel(columnNum);
        this.setModel(model);
        TableRowSorter<OrderTableModel> sorter = new TableRowSorter<OrderTableModel>(model);
        this.setRowSorter(sorter);
    }

    @Override
    protected void setHeaderLabel() {
        OrderTableRedMarkRenderer redmarkRenderer = new OrderTableRedMarkRenderer();
        OrderTableZeroMarkRenderer zeromarkRenderer = new OrderTableZeroMarkRenderer();
        TableColumnModel cm = this.getColumnModel();
        cm.getColumn(0).setHeaderValue("序号");
        cm.getColumn(0).setPreferredWidth(40);
        cm.getColumn(0).setCellRenderer(redmarkRenderer);
        cm.getColumn(1).setHeaderValue("付款编号");
        cm.getColumn(1).setPreferredWidth(105);
        cm.getColumn(1).setCellRenderer(redmarkRenderer);
        cm.getColumn(2).setHeaderValue("桌号");
        cm.getColumn(2).setPreferredWidth(75);
        cm.getColumn(2).setCellRenderer(redmarkRenderer);
        cm.getColumn(3).setHeaderValue("原价");
        cm.getColumn(3).setPreferredWidth(75);
        cm.getColumn(3).setCellRenderer(redmarkRenderer);
        cm.getColumn(4).setHeaderValue("实收金额");
        cm.getColumn(4).setPreferredWidth(75);
        cm.getColumn(4).setCellRenderer(redmarkRenderer);
        cm.getColumn(5).setHeaderValue("折扣方案");
        cm.getColumn(5).setPreferredWidth(215);
        cm.getColumn(5).setCellRenderer(redmarkRenderer);
        cm.getColumn(6).setHeaderValue("开桌时间");
        cm.getColumn(6).setPreferredWidth(100);
        cm.getColumn(6).setCellRenderer(redmarkRenderer);
        cm.getColumn(7).setHeaderValue("结账时间");
        cm.getColumn(7).setPreferredWidth(100);
        cm.getColumn(7).setCellRenderer(redmarkRenderer);
        cm.getColumn(8).setHeaderValue("优惠金额");
        cm.getColumn(8).setPreferredWidth(75);
        cm.getColumn(8).setCellRenderer(redmarkRenderer);
        cm.getColumn(9).setHeaderValue("入账总金额");
        cm.getColumn(9).setPreferredWidth(115);
        cm.getColumn(9).setCellRenderer(zeromarkRenderer);
    }

    public void refreshTable(List<OrderVO> orders){
        OrderTableModel otm = (OrderTableModel) this.getModel();
        otm.setOrders(orders);
        otm.fireTableDataChanged();
        if(!CollectionUtils.isEmpty(orders)){
            this.setRowSelectionAllowed(true);
            this.setRowSelectionInterval(0, 0);
        }
        this.repaint();
    }

    /**
     * 行标记红色 renderer
     */
    private class OrderTableRedMarkRenderer extends AbstractLineColorMarkRenderer<OrderTableModel> {

        /**
         *
         */
        private static final long serialVersionUID = 4436771966347867353L;

        @Override
        public boolean markColor(OrderTableModel tm, int rowIndex) {
            OrderVO order = tm.getOrderAt(rowIndex);
            if(order == null){
                return false;
            }
            if(CommonEnums.YOrN.Y.equals(order.getUnusual())){
                return true;
            }
            return false;
        }
    }

    /**
     * 订单总额为0是标记为跑单标红
     */
    private class OrderTableZeroMarkRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value, boolean isSelected, boolean hasFocus, int row,
                                                       int column) {
            int modelRowIndex = table.convertRowIndexToModel(row);
            OrderTableModel tm = (OrderTableModel) table.getModel();
            OrderVO order = tm.getOrderAt(modelRowIndex);
            boolean zeroFlag = BigDecimal.ZERO.compareTo(order.getTotalAmount()) == 0?true:false;
            if(zeroFlag){
                if(isSelected){
                    table.setSelectionBackground(Color.ORANGE);
                    table.setSelectionForeground(Color.WHITE);
                }
                setBackground(Color.ORANGE);
                setForeground(Color.WHITE);
            }else{
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                    row, column);
        }
    }

    public static class OrderTableModel extends AbstractTableModel {
        /**
         *
         */
        private static final long serialVersionUID = -4006879882193678115L;
        private List<OrderVO> orders = Collections.emptyList();
        private int columnNum;

        public OrderTableModel(int columnNum){
            this.columnNum = columnNum;
        }

        public OrderTableModel(List<OrderVO> orders){
            this.orders = orders;
        }

        @Override
        public int getRowCount() {
            return orders.size();
        }

        @Override
        public int getColumnCount() {
            return this.columnNum;
        }

        public OrderVO getOrderAt(int rowIndex){
            if(CollectionUtils.isEmpty(orders)){
                return null;
            }
            return orders.get(rowIndex);
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(CollectionUtils.isEmpty(orders)){
                return new Object();
            }
            OrderVO order = orders.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return ++rowIndex;
                case 1:
                    return order.getPayNo();
                case 2:
                    return order.getTableName();
                case 3:
                    return order.getOriginAmount();
                case 4:
                    return order.getActualAmount();
                case 5:
                    return order.getSchemeName();
                case 6:
                    return DateUtil.getTimeStampOfDate(order.getOpenDeskTime());
                case 7:
                    return DateUtil.getTimeStampOfDate(order.getCheckoutTime());
                case 8:
                    return order.getFreeAmount();
                case 9:
                    return order.getTotalAmount();
                default:
                    break;
            }
            return null;
        }

        public void setRowCount(int rowCount){
            int old = getRowCount();
            orders = Collections.emptyList();
            if(old > 0){
                super.fireTableRowsDeleted(rowCount,old-1);
            }
        }

        public List<OrderVO> getOrders() {
            return orders;
        }

        public void setOrders(List<OrderVO> orders) {
            this.orders = orders;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if(columnIndex >= 0 && columnIndex < getColumnCount()){
                if(getValueAt(0, columnIndex) == null){
                    return Object.class;
                }
                return getValueAt(0, columnIndex).getClass();
            }
            return super.getColumnClass(columnIndex);
        }
    }
}
