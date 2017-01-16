package com.os.frames;

import com.os.frames.dashboard.ConclusionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class CheckedoutOrderPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1912248260264394907L;
	
	public static final String NAME="CheckedoutOrderPanel";
	
	private ConclusionPanel conclusionPane = new ConclusionPanel(); // 统计信息面板
	private QueryFormPanel queryPanel = new QueryFormPanel(); // 查询面板
	ContentPanel contentPane; // 订单数据内容展示面板
	private int width;
	private int height;
	private QueryListener listener;
	
	public CheckedoutOrderPanel(int width, int height){
		this.width = width;
		this.height = height;
		setName(NAME);
		initComponent();
	}

	private void initComponent() {
		BorderLayout layout = new BorderLayout(0, 0);
		setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
		setLayout(layout);
		/* 绑定查询form */
//		SwingUtilities.invokeLater(new Runnable(){
//
//			@Override
//			public void run() {
//				queryPanel.getTimeInput().setText(DateUtil.date2Str(DateUtil.getCurrentDate(), "yyyyMMdd"));
//			}
//			
//		});
		add(queryPanel,BorderLayout.NORTH);
		/* 绑定订单内容和警告日志展示列表 */
		contentPane = new ContentPanel(JSplitPane.HORIZONTAL_SPLIT,width,height);
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
		queryPanel.setFilterListener(new OrderTableFilterListener(contentPane.getMainTable(), new OrderTableRowFilter()));

		CleanListener clistener = new CleanListener(contentPane);
		clistener.setConclusionPane(conclusionPane);
		clistener.setQueryPanel(queryPanel);
		queryPanel.getCleanBtn().addActionListener(clistener);
	}

	public ConclusionPanel getConclusionPane() {
		return conclusionPane;
	}

	public ContentPanel getContentPane() {
		return contentPane;
	}

	public void setConclusionPane(ConclusionPanel conclusionPane) {
		this.conclusionPane = conclusionPane;
	}

	public void setContentPane(ContentPanel contentPane) {
		this.contentPane = contentPane;
	}

	public QueryFormPanel getQueryPanel() {
		return queryPanel;
	}

	public void setQueryPanel(QueryFormPanel queryPanel) {
		this.queryPanel = queryPanel;
	}
}
