package com.os.swing.handlers;

import com.os.services.IMetaDataFacadeService;
import com.os.swing.frames.dashboard.QueryFormPanel;
import com.os.utils.SpringUtils;

/**
 * Created by jz on 2017/5/28.
 */
public class InventoryWarningHandler {
    private static IMetaDataFacadeService metadataFacade;
    static{
        metadataFacade = (IMetaDataFacadeService) SpringUtils.getBean("MetaDataFacadeService");
    }
    private InventoryWarningHandler(){}

    private static class InventoryWarningHandlerHolder{
        private static final InventoryWarningHandler instance = new InventoryWarningHandler();
    }

    public static InventoryWarningHandler getInstance(){
        return InventoryWarningHandlerHolder.instance;
    }

    private QueryFormPanel displayPanel;

    public QueryFormPanel getDisplayPanel() {
        return displayPanel;
    }

    public void setDisplayPanel(QueryFormPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    public void displayWarningInfo(){
        if(displayPanel == null){
            return;
        }
        if(metadataFacade.hasSellOffWarningInfo()){
            InventoryWarningHandler.getInstance().showWarningInfo("有库存将被沽清，请尽快进货！");
        }else{
            InventoryWarningHandler.getInstance().hideWarningInfo();
        }
    }

    public void showWarningInfo(String warningInfo){
        if(displayPanel.isWarningShowing()){
            return;
        }
        displayPanel.displayWarningInfo(warningInfo);
    }

    public void hideWarningInfo(){
        if(!displayPanel.isWarningShowing()){
            return;
        }
        displayPanel.removeWarningInfo();
    }
}
