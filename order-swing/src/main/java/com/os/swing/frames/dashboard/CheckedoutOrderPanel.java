package com.os.swing.frames.dashboard;

import com.os.swing.listeners.QueryListener;

import javax.swing.JPanel;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class CheckedoutOrderPanel extends JPanel{
    public static final String NAME="CheckedoutOrderPanel";

    private ConclusionPanel conclusionPane = new ConclusionPanel(); // 统计信息面板
    private QueryFormPanel queryPanel = new QueryFormPanel(); // 查询面板
    ContentPanel contentPane; // 订单数据内容展示面板
    private QueryListener listener;
}
