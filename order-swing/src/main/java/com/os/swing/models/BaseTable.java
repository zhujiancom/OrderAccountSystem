package com.os.swing.models;

import com.os.services.IMetaDataFacadeService;
import com.os.utils.SpringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by jian zhu on 05/27/2017.
 */
public abstract class BaseTable<T> extends JTable {
    private static final Logger logger = LogManager.getLogger();

    protected int columnNum;

    protected List<T> dataList;

    protected static final IMetaDataFacadeService metadataFacade;

    static{
        metadataFacade = (IMetaDataFacadeService) SpringUtils.getBean("MetaDataFacadeService");
    }

    public BaseTable(){
        this.setRowHeight(30);
    }

    public BaseTable(int columnNum){
        this();
        this.columnNum = columnNum;
        setModel();
        setHeaderLabel();
    }

    public BaseTable(TableModel dataModel){
        super(dataModel);
        setHeaderLabel();
        this.setRowHeight(30);
    }

    @Override
    public String getToolTipText(MouseEvent event) {
        int row = this.rowAtPoint(event.getPoint());
        int col = this.columnAtPoint(event.getPoint());
        if(row > -1 && col > -1){
            Object value = this.getValueAt(row, col);
            if(null != value && !"".equals(value)){
                this.setToolTipText(value.toString());
            }else{
                this.setToolTipText(null);
            }
        }
        return super.getToolTipText(event);
    }

    /**
     *
     * Describle(描述)： 装载data Model
     *
     * 方法名称：setModel
     *
     * 所在类名：BaseTable
     *
     * Create Time:2015年7月31日 下午1:17:27
     *
     */
    protected abstract void setModel();

    /**
     *
     * Describle(描述)：设置 table header
     *
     * 方法名称：setHeaderLabel
     *
     * 所在类名：BaseTable
     *
     * Create Time:2015年7月31日 下午1:17:54
     *
     */
    protected abstract void setHeaderLabel();

    /**
     * Describle(描述)：自适应表格列宽度
     *
     * 方法名称：adaptiveColumns
     *
     * 所在类名：BaseTable
     *
     * Create Time:2015年12月9日 上午11:07:17
     *
     * @param table
     */
    protected void adaptiveColumns(JTable table){
        JTableHeader header = table.getTableHeader();
        int rowcount = table.getRowCount();
        Enumeration<TableColumn> columns = table.getColumnModel().getColumns();
        while(columns.hasMoreElements()){
            TableColumn column = columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int) header.getDefaultRenderer()
                    .getTableCellRendererComponent(table, column.getIdentifier(), false, false, -1, col)
                    .getPreferredSize()
                    .getWidth();
            for(int row=0;row < rowcount;row++){
                int preferedWidth = (int) table.getCellRenderer(row, col)
                        .getTableCellRendererComponent(table, table.getValueAt(row, col), false, false, row, col)
                        .getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width+table.getIntercellSpacing().width);
        }
    }
}
