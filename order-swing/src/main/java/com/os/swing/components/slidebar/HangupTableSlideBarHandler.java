package com.os.swing.components.slidebar;

import com.os.swing.frames.dashboard.HangupOrderPanel;
import org.springframework.util.CollectionUtils;

import java.awt.event.ActionEvent;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class HangupTableSlideBarHandler extends AbstractSlideBarListener {
    private HangupOrderPanel.HangupTableDetailInfoPanel detailInfoPanel;

    private HangupOrderPanel.HangupOrderItemInfoPanel itemInfoPanel;

    @Override
    public void moveTo(SlideElement currentElement) {
        fireUIUpdate(currentElement);
        detailInfoPanel.setSelectedElement(currentElement);
        itemInfoPanel.setSelectedElement(currentElement);
    }

    @Override
    public void moveTo(int index) {
        SlideElement currentElement = elements.get(index);
        fireUIUpdate(currentElement);
        detailInfoPanel.setSelectedElement(currentElement);
        itemInfoPanel.setSelectedElement(currentElement);
        currentElement.requestFocus();
    }

    @Override
    public void cleanAllElements() {
        elements.clear();
    }

    @Override
    public Integer getElementCount() {
        if(CollectionUtils.isEmpty(elements)){
            return 0;
        }
        return elements.size();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        selectedElement = (SlideElement) event.getSource();
        fireUIUpdate(selectedElement);
        detailInfoPanel.setSelectedElement(selectedElement);
        itemInfoPanel.setSelectedElement(selectedElement);
    }

    public void setDetailInfoPanel(HangupOrderPanel.HangupTableDetailInfoPanel detailInfoPanel) {
        this.detailInfoPanel = detailInfoPanel;
    }

    public void setItemInfoPanel(HangupOrderPanel.HangupOrderItemInfoPanel itemInfoPanel) {
        this.itemInfoPanel = itemInfoPanel;
    }
}
