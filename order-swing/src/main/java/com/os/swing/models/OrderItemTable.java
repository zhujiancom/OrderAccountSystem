package com.os.swing.models;

import com.os.swing.renderers.AbstractLineColorMarkRenderer;
import com.os.utils.DateUtil;
import com.os.bean.vos.OrderItemVO;
import org.springframework.util.CollectionUtils;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;
import java.util.Collections;
import java.util.List;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class OrderItemTable extends BaseTable<OrderItemVO> {
    public OrderItemTable(int columnNum){
        super(columnNum);
        this.setRowHeight(25);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    @Override
    protected void setModel() {
        this.setModel(new OrderItemTableModel(columnNum));
    }

    @Override
    protected void setHeaderLabel() {
        TableColumnModel cm = this.getColumnModel();
        BackDishRenderer backDishRenderer = new BackDishRenderer();
        cm.getColumn(0).setHeaderValue("菜品名称");
        cm.getColumn(0).setPreferredWidth(140);
        cm.getColumn(1).setHeaderValue("数量");
        cm.getColumn(1).setPreferredWidth(105);
        cm.getColumn(2).setHeaderValue("退菜数量");
        cm.getColumn(2).setPreferredWidth(75);
        cm.getColumn(2).setCellRenderer(backDishRenderer);
        cm.getColumn(3).setHeaderValue("原价");
        cm.getColumn(3).setPreferredWidth(75);
        cm.getColumn(4).setHeaderValue("实收金额");
        cm.getColumn(4).setPreferredWidth(75);
        cm.getColumn(5).setHeaderValue("折扣率");
        cm.getColumn(5).setPreferredWidth(115);
        cm.getColumn(6).setHeaderValue("下单时间");
        cm.getColumn(6).setPreferredWidth(140);
        cm.getColumn(7).setHeaderValue("退菜时间");
        cm.getColumn(7).setPreferredWidth(140);
    }

    public void reflushTable(String day,String payno){
        List<OrderItemVO> orderItems = metadataFacade.queryItemsByPayno(day, payno);
        OrderItemTableModel oitm = (OrderItemTableModel) this.getModel();
        oitm.setItems(orderItems);
        oitm.fireTableDataChanged();
    }

    public void reflushTable(List<OrderItemVO> items){
        OrderItemTableModel oitm = (OrderItemTableModel) this.getModel();
        oitm.setItems(items);
        oitm.fireTableDataChanged();
    }

    public static class OrderItemTableModel extends AbstractTableModel {
        private List<OrderItemVO> items = Collections.emptyList();
        private int columnNum;

        public OrderItemTableModel(int columnNum){
            this.columnNum = columnNum;
        }

        public OrderItemTableModel(List<OrderItemVO> items){
            this.items = items;
        }

        @Override
        public int getRowCount() {
            if(items == null){
                return 0;
            }
            return items.size();
        }

        @Override
        public int getColumnCount() {
            return this.columnNum;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            OrderItemVO item = items.get(rowIndex);
            switch(columnIndex){
                case 0:
                    return item.getDishName();
                case 1:
                    return item.getCount();
                case 2:
                    return item.getCountback();
                case 3:
                    return item.getPrice();
                case 4:
                    return item.getActualAmount();
                case 5:
                    return item.getDiscountRate();
                case 6:
                    return DateUtil.time2Str(item.getConsumeTime());
                case 7:
                    return DateUtil.time2Str(item.getBackTime());
                default:
                    break;
            }
            return null;
        }

        public void setRowCount(int rowCount){
            int old = getRowCount();
            items = Collections.emptyList();
            if(old > 0){
                super.fireTableRowsDeleted(rowCount,old-1);
            }
        }

        public List<OrderItemVO> getItems() {
            return items;
        }

        public void setItems(List<OrderItemVO> items) {
            this.items = items;
        }

        public OrderItemVO getOrderItemAt(int rowIndex){
            if(CollectionUtils.isEmpty(items)){
                return null;
            }
            return items.get(rowIndex);
        }
    }

    private class BackDishRenderer extends AbstractLineColorMarkRenderer<OrderItemTableModel> {

        /**
         *
         */
        private static final long serialVersionUID = 3004289115913083755L;

        @Override
        public boolean markColor(OrderItemTableModel tm, int rowIndex) {
            OrderItemVO item = tm.getOrderItemAt(rowIndex);
            if(item.getCountback() != 0){
                return true;
            }
            return false;
        }
    }
}
