package com.os.swing.components.switcher;

import com.os.frames.CheckedoutOrderPanel;
import com.os.frames.HangupOrderPanel;
import com.os.swing.components.buttons.ButtonFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainPaneSwitcherHandler extends AbstractSwitcherHandler {
	protected JFrame mainFrame;
	
	private JPanel contentPanel;
	
	private JMenu operationMenu;
	
	public MainPaneSwitcherHandler(JFrame mainFrame){
		super();
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		selectedElement = (SwitcherElement) e.getSource();
		new Thread(new Runnable(){

			@Override
			public void run() {
				refreshUI();
				String actioncommand = selectedElement.getActionCommand();
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						mainFrame.getContentPane().removeAll();
						mainFrame.getContentPane().add(new JLabel("Loading",loadingIcon,SwingConstants.CENTER));
						((JPanel) mainFrame.getContentPane()).updateUI();
					}
				});
				if("checked".equals(actioncommand)){
					if(mainFrame.getContentPane().getComponent(0) instanceof HangupOrderPanel){
						((HangupOrderPanel)mainFrame.getContentPane().getComponent(0)).stopTimer();
					}
					contentPanel = createCheckedoutOrderPanel();
				}
				if("unchecked".equals(actioncommand)){
					contentPanel = createHangupOrderPanel();
				}
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						if(operationMenu == null){
							generateOperationMenu();
						}
						mainFrame.getContentPane().removeAll();
						mainFrame.getContentPane().add(contentPanel,BorderLayout.CENTER);
						if(contentPanel instanceof HangupOrderPanel){
							mainFrame.getJMenuBar().remove(operationMenu);
						}else{
							mainFrame.getJMenuBar().add(operationMenu, 3);
						}
						((JPanel) mainFrame.getContentPane()).updateUI();
					}
				});
			}
			
		}).start();
	}

	private JPanel createCheckedoutOrderPanel(){
		return new CheckedoutOrderPanel(mainFrame.getContentPane().getWidth(),mainFrame.getContentPane().getHeight());
	}
	
	private JPanel createHangupOrderPanel(){
		return new HangupOrderPanel(mainFrame.getContentPane());
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	private void generateOperationMenu(){
		operationMenu = new JMenu("操作");
		operationMenu.setForeground(Color.WHITE);
		JMenuItem dataExport = ButtonFactory.createMenuItem("订单数据导出", "skin/gray/images/24x24/export_0.png");
		JMenuItem dataImport = ButtonFactory.createMenuItem("订单数据导入", "skin/gray/images/24x24/import_0.png");
		operationMenu.add(dataExport);
		operationMenu.add(dataImport);
		// 数据导出
		DataExportImportListener dataIEListener = new OrderDataExportImportListener(mainFrame);
		dataExport.addActionListener(dataIEListener.exportData());
		//数据导入
		dataImport.addActionListener(dataIEListener.importData());
	}

}
