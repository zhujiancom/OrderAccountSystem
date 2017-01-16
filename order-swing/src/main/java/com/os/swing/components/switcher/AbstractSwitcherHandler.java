package com.os.swing.components.switcher;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSwitcherHandler implements ActionListener {
	protected List<SwitcherElement> elements = new ArrayList<SwitcherElement>();
	
	protected SwitcherElement selectedElement;
	
	protected Icon loadingIcon;
	
	public AbstractSwitcherHandler(){
		URL loadingIconUrl = this.getClass().getClassLoader().getResource("skin/gray/images/24x24/loading.gif");
		loadingIcon = new ImageIcon(loadingIconUrl);
	}
	
	public void addElement(SwitcherElement element){
		int len = elements.size();
		element.setIndex(len);
		element.setListener(this);
		elements.add(element);
	}
	
	public void refreshUI(){
		int curIndex = selectedElement.getIndex();
		selectedElement.setSelected(true);
		for(SwitcherElement element:elements){
			if(element.getIndex() != curIndex){
				element.setSelected(false);
				element.repaint();
			}
			selectedElement.repaint();
		}
	}
}
