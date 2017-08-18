package com.os.swing.frames.dashboard;

import com.os.swing.handlers.InventoryWarningHandler;
import com.os.swing.listeners.CleanListener;
import com.os.swing.listeners.QueryListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class CheckedoutOrderPanel extends JPanel{
    public static final String NAME="CheckedoutOrderPanel";

    private ConclusionPanel conclusionPane = new ConclusionPanel(); // 统计信息面板
    private QueryFormPanel queryPanel = new QueryFormPanel(); // 查询面板
    ContentPanel contentPane; // 订单数据内容展示面板
    private QueryListener listener;

    private int _width;
    private int _height;

    public CheckedoutOrderPanel(int width,int height){
        this._width = width;
        this._height = height;
        setName(NAME);
        initComponent();
    }

    private void initComponent(){
        BorderLayout layout = new BorderLayout(0, 0);
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        setLayout(layout);
        add(queryPanel,BorderLayout.NORTH);
		/* 绑定订单内容和警告日志展示列表 */
        contentPane = new ContentPanel(JSplitPane.HORIZONTAL_SPLIT,_width,_height);
        add(contentPane, BorderLayout.CENTER);
		/* 绑定 总结页脚 */
        add(conclusionPane, BorderLayout.SOUTH);
		/* 绑定事件 */
        bindListeners();
    }

    private void bindListeners() {
        InventoryWarningHandler.getInstance().setDisplayPanel(queryPanel);
        listener = new QueryListener(queryPanel,contentPane);
        listener.setConclusionPane(conclusionPane);
        queryPanel.getQueryBtn().registerKeyboardAction(listener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        queryPanel.getQueryBtn().addActionListener(listener);
//        queryPanel.setFilterListener(new OrderTableFilterListener(contentPane.getMainTable(), new OrderTableRowFilter()));

        CleanListener clistener = new CleanListener(contentPane);
        clistener.setConclusionPane(conclusionPane);
        clistener.setQueryPanel(queryPanel);
        queryPanel.getCleanBtn().addActionListener(clistener);
    }

}
