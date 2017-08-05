package com.os.swing.frames.dashboard;

import com.os.services.IMetaDataFacadeService;
import com.os.swing.components.TitleBar;
import com.os.swing.components.buttons.ButtonFactory;
import com.os.swing.components.slidebar.HangupTableSlideBarHandler;
import com.os.swing.components.slidebar.SlideBar;
import com.os.swing.components.slidebar.SlideElement;
import com.os.swing.models.OrderItemTable;
import com.os.ApplicationContextProvider;
import com.os.utils.DateUtil;
import com.os.utils.PropertyUtils;
import com.os.bean.vos.HangupTabelInfoVO;
import org.springframework.util.CollectionUtils;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class HangupOrderPanel extends JPanel {
    public static final String NAME="HangupOrderPanel";

    protected Container parentContainer;

    private SlideBar slideBar;

    private HangupTableSlideBarHandler handler;

    private JButton refreshBtn;

    private ScheduledExecutorService executor;

    private IMetaDataFacadeService metadataFacade;

    private JLabel noDataMsg = new JLabel("<html><font color='red' size='20'>No Data Fetch</font></html>");

    public HangupOrderPanel(Container parentContainer){
        this.parentContainer = parentContainer;
        setName(NAME);
//        metadataFacade = (IMetaDataFacadeService) SpringUtils.getBean("MetaDataFacadeService");
        metadataFacade = ApplicationContextProvider.getBean("MetaDataFacadeService",IMetaDataFacadeService.class);
        initComponent();
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(() -> {updateSlideBar();}, 20, 20, TimeUnit.SECONDS);
    }

    private void initComponent(){
        setLayout(new BorderLayout(0, 0));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
        refreshBtn = ButtonFactory.createImageButton(PropertyUtils.getStringValue("image.path.refreshBtn"),PropertyUtils.getStringValue("image.path.refreshPressedBtn"));
        refreshBtn.setToolTipText("Refresh");
        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));
        upPanel.setBackground(Color.WHITE);
        upPanel.add(createSlideBar());
        upPanel.add(refreshBtn);
        add(upPanel,BorderLayout.NORTH);
        refreshBtn.addActionListener((event) -> {updateSlideBar();});
    }

    public void updateSlideBar(){
        this.removeAll();
        handler.cleanAllElements();
        List<HangupTabelInfoVO> tables = metadataFacade.queryHangupTableInfo();
        List<SlideElement> elements = new ArrayList<SlideElement>();
        if(CollectionUtils.isEmpty(tables)){
            add(noDataMsg,BorderLayout.CENTER);
        }
        boolean hasSelected = false;
        for(int i=0;i<tables.size();i++){
            HangupTabelInfoVO table = tables.get(i);
            SlideElement element =new SlideElement(table.getTableName(),table,new Font("微软雅黑", Font.BOLD, 32));
            element.setIndex(i);
            element.addActionListener(handler);
            elements.add(element);
            if(element.equals(handler.getSelectedElement())){
                hasSelected = true;
                element.setChecked(true);
                element.setState(SlideElement.State.SELECTED);
                HangupTableDetailInfoPanel detailInfoPanel = new HangupTableDetailInfoPanel(element);
                detailInfoPanel.setName("detailInfoPanel");
                HangupOrderItemInfoPanel itemInfoPanel = new HangupOrderItemInfoPanel(element);
                itemInfoPanel.setName("orderItemInfoPanel");
                handler.setDetailInfoPanel(detailInfoPanel);
                handler.setItemInfoPanel(itemInfoPanel);
                add(detailInfoPanel,BorderLayout.CENTER);
                add(itemInfoPanel,BorderLayout.EAST);
            }
        }
        if(!hasSelected){
            SlideElement element  = elements.get(0);
            element.setChecked(true);
            element.setState(SlideElement.State.SELECTED);
            HangupTableDetailInfoPanel detailInfoPanel = new HangupTableDetailInfoPanel(element);
            detailInfoPanel.setName("detailInfoPanel");
            HangupOrderItemInfoPanel itemInfoPanel = new HangupOrderItemInfoPanel(element);
            itemInfoPanel.setName("orderItemInfoPanel");
            handler.setDetailInfoPanel(detailInfoPanel);
            handler.setItemInfoPanel(itemInfoPanel);
            add(detailInfoPanel,BorderLayout.CENTER);
            add(itemInfoPanel,BorderLayout.EAST);
        }
        slideBar = new SlideBar(elements) ;
        JPanel upPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,20,0));
        upPanel.setName("upPanel");
        upPanel.setBackground(Color.WHITE);
        upPanel.add(slideBar);
        upPanel.add(refreshBtn);
        add(upPanel,BorderLayout.NORTH);
        this.updateUI();
    }

    /**
     *
     *
     * Describle(描述)：创建slide bar
     *
     * 方法名称：createTableSlideBar
     *
     * 所在类名：HangupOrderPanel
     *
     * Create Time:2015年12月12日 下午2:49:07
     *
     * @return
     */
    public JPanel createSlideBar() {
        List<HangupTabelInfoVO> tables = metadataFacade.queryHangupTableInfo();
        handler = new HangupTableSlideBarHandler();
        List<SlideElement> elements = new ArrayList<SlideElement>();
        if(CollectionUtils.isEmpty(tables)){
            add(noDataMsg,BorderLayout.CENTER);
        }
        for(int i=0;i<tables.size();i++){
            HangupTabelInfoVO table = tables.get(i);
            if(i == 0){
                SlideElement element = new SlideElement(table.getTableName(),table,new Font("微软雅黑", Font.BOLD, 32), SlideElement.State.SELECTED);
                element.setIndex(i);
                element.addActionListener(handler);
                elements.add(element);
                HangupTableDetailInfoPanel detailInfoPanel = new HangupTableDetailInfoPanel(element);
                HangupOrderItemInfoPanel itemInfoPanel = new HangupOrderItemInfoPanel(element);
                handler.setDetailInfoPanel(detailInfoPanel);
                handler.setItemInfoPanel(itemInfoPanel);
                add(detailInfoPanel,BorderLayout.CENTER);
                add(itemInfoPanel,BorderLayout.EAST);
            }else{
                SlideElement element =new SlideElement(table.getTableName(),table,new Font("微软雅黑", Font.BOLD, 32));
                element.setIndex(i);
                element.addActionListener(handler);
                elements.add(element);
            }
        }
        slideBar = new SlideBar(elements);
        return slideBar;
    }

    public void stopTimer(){
        executor.shutdown();
    }

    public class HangupTableDetailInfoPanel extends JPanel{
        private SlideElement selectedElement;

        public HangupTableDetailInfoPanel(SlideElement selectedElement){
            this.selectedElement = selectedElement;
            initInternalComponent();
        }

        /**
         *
         * Describle(描述)：
         *
         * 方法名称：ininComponent
         *
         * 所在类名：HangupTableDetailInfoPanel
         *
         * Create Time:2015年12月12日 下午5:31:04
         *
         */
        private void initInternalComponent() {
            setLayout(new BorderLayout(0, 0));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            invalidate();
            HangupTabelInfoVO tableInfo = (HangupTabelInfoVO) selectedElement.getValue();
            add(new TitleBar(new JLabel("<html>订单编号：<font color='red' style='font-weight:bold'>"+tableInfo.getBillno()+"</font><br/><font color='red' size='6'>桌号："+tableInfo.getTableName()+"</font></html>"),500,60),BorderLayout.NORTH);
            JPanel content = new JPanel();
            content.setBackground(Color.WHITE);
            content.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.add(new JLabel("<html><font size='6'>消费总额：</font><font color='green' size='18'>"+tableInfo.getConsumAmount()+"</font></html>"));
            content.add(Box.createVerticalStrut(10));
            content.add(new JLabel("<html><font size='6'>可打折金额：</font><font color='blue' size='18'>"+tableInfo.getDiscountAmount()+"</font></html>"));
            content.add(Box.createVerticalStrut(10));
            content.add(new JLabel("<html><font size='6'>不可打折金额：</font><font color='red' size='20'>"+tableInfo.getNodiscountAmount()+" </html>"));
            content.add(Box.createVerticalStrut(30));
            content.add(new JLabel("<html><font size='6'>开台时间：</font><font size='5'>"+ DateUtil.time2Str(tableInfo.getOpendeskTime())+"</font></html>"));
            add(content,BorderLayout.CENTER);
            validate();
        }

        public SlideElement getSelectedElement() {
            return selectedElement;
        }

        public void setSelectedElement(SlideElement selectedElement) {
            this.selectedElement = selectedElement;
            updateUI();
        }

    }

    public class HangupOrderItemInfoPanel extends JPanel{
        private OrderItemTable itemTable;

        private SlideElement selectedElement;


        public HangupOrderItemInfoPanel(SlideElement selectedElement){
            this.selectedElement = selectedElement;
            initInternalComponent();
        }

        /**
         *
         * Describle(描述)：
         *
         * 方法名称：initInternalComponent
         *
         * 所在类名：HangupOrderItemInfoPanel
         *
         * Create Time:2015年12月13日 下午2:20:17
         *
         */
        private void initInternalComponent() {
            setLayout(new BorderLayout(0, 0));
            add(new TitleBar(new JLabel("<html><font>订单信息</font></html>"),500,30),BorderLayout.NORTH);
            JScrollPane spane = new JScrollPane();
            spane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            spane.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
            if(itemTable == null){
                itemTable = new OrderItemTable(8);
            }
            spane.setViewportView(itemTable);
            spane.getViewport().setBackground(Color.WHITE);
            HangupTabelInfoVO tableInfo = (HangupTabelInfoVO) selectedElement.getValue();
            itemTable.reflushTable(tableInfo.getItems());
            add(spane,BorderLayout.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            HangupTabelInfoVO tableInfo = (HangupTabelInfoVO) selectedElement.getValue();
            itemTable.reflushTable(tableInfo.getItems());
        }

        public SlideElement getSelectedElement() {
            return selectedElement;
        }

        public void setSelectedElement(SlideElement selectedElement) {
            this.selectedElement = selectedElement;
            updateUI();
        }


    }
}
