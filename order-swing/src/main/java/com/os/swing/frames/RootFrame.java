package com.os.swing.frames;

import com.os.AppEntry;
import com.os.config.PropertyConstants;
import com.os.exceptions.UIException;
import com.os.properties.FrameConfigProperties;
import com.os.properties.ImageConfigProperties;
import com.os.swing.components.buttons.ButtonFactory;
import com.os.swing.components.switcher.MainPaneSwitcherHandler;
import com.os.swing.components.switcher.SwitcherElement;
import com.os.swing.listeners.FrameListener;
import com.os.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;

/**
 * Created by jian zhu on 05/24/2017.
 */
@Component
public class RootFrame extends JFrame implements InitializingBean{
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private ApplicationContext context;

    private FrameListener frameListener;

    private JMenuBar jMenuBar;

    @Autowired
    private FrameConfigProperties frameConfigProperties;

    @Autowired
    private ImageConfigProperties imageConfigProperties;

    private JMenuBar buildMenuBar() {
        jMenuBar = new JMenuBar();
        jMenuBar.setBackground(Color.BLACK);
        JButton logoBtn = ButtonFactory.createImageButton(imageConfigProperties.getLogo(), null);
        JMenu system = new JMenu("系统");
        system.setForeground(Color.WHITE);
        JMenu statistic = new JMenu("统计");
        statistic.setForeground(Color.WHITE);
        JMenu management = new JMenu("管理");
        management.setForeground(Color.WHITE);
        final JFrame frame = this;
        jMenuBar.add(logoBtn);
        jMenuBar.add(Box.createHorizontalStrut(10));
        jMenuBar.add(system);
        jMenuBar.add(statistic);
        jMenuBar.add(management);
        JPanel rightP = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightP.setBackground(Color.BLACK);
        rightP.addMouseListener(frameListener);
        rightP.addMouseMotionListener(frameListener);

        MainPaneSwitcherHandler handler = new MainPaneSwitcherHandler(this);
//        SwitcherBar mainPaneSwitcherBar = new SwitcherBar(handler);
        SwitcherElement checkedout = new SwitcherElement("已结订单");
        checkedout.setActionCommand("checked");
        SwitcherElement hangup = new SwitcherElement("未结订单",true);
        hangup.setActionCommand("unchecked");
//        mainPaneSwitcherBar.addElement(hangup);
//        mainPaneSwitcherBar.addElement(checkedout);

        JButton minimizeBtn = ButtonFactory.createImageButton(imageConfigProperties.getMinimizeBtn(),null);
        final JButton maximizeBtn = ButtonFactory.createImageButton(imageConfigProperties.getMaximizeBtn(),null);
        maximizeBtn.setToolTipText("最大化");
        jMenuBar.add(maximizeBtn);
        JButton closeBtn = ButtonFactory.createImageButton(imageConfigProperties.getCloseBtn(),null);
        closeBtn.setToolTipText("关闭");
//        rightP.add(mainPaneSwitcherBar);
        rightP.add(minimizeBtn);
        rightP.add(maximizeBtn);
        rightP.add(closeBtn);
        jMenuBar.add(rightP);
        frameListener.setMaximizeBtn(maximizeBtn);
        JMenuItem sysInit = ButtonFactory.createMenuItem("系统初始化",imageConfigProperties.getSystemInitMenu());
        JMenuItem baseReset = ButtonFactory.createMenuItem("基础数据重置", imageConfigProperties.getBaseDataResetMenu());
        system.add(sysInit);
        system.add(baseReset);

        JMenuItem expressRate = ButtonFactory.createMenuItem("外送率统计", imageConfigProperties.getTakeoutMenu());
        JMenuItem earning = ButtonFactory.createMenuItem("营业额统计", imageConfigProperties.getBusVolumeStatisticMenu());
        JMenuItem costControl = ButtonFactory.createMenuItem("成本统计", imageConfigProperties.getCostStatisticMenu());
        JMenuItem saleStatistic = ButtonFactory.createMenuItem("菜品销量统计", imageConfigProperties.getDishSalVolumeStatisticMenu());
        statistic.add(expressRate);
        statistic.add(earning);
        statistic.add(costControl);
        statistic.add(saleStatistic);

        JMenuItem dishManage = ButtonFactory.createMenuItem("菜品管理", imageConfigProperties.getDishManagementMenu());
        JMenuItem inventoryManage = ButtonFactory.createMenuItem("库存管理", imageConfigProperties.getStockManagementMenu());
        JMenuItem activityManage = ButtonFactory.createMenuItem("活动管理", imageConfigProperties.getActivityManagementMenu());
        management.add(dishManage);
        management.add(inventoryManage);
        management.add(activityManage);

//        MenuItemActionHandler mhandler = new MenuItemActionHandler();
//        mhandler.setFrame(this);
//        baseReset.addActionListener(mhandler.doBaseInfoResetAction());
        // 外送率统计事件
//        expressRate.addActionListener(mhandler.doExpressRateStatisticAction());
        // 营业额统计事件
//        earning.addActionListener(mhandler.doEarningStatisticAction());
        //菜品销量统计
//        saleStatistic.addActionListener(mhandler.doSaleStatisticAction());
        //成本控制
//        costControl.addActionListener(mhandler.doCostControlAction());

        //系统退出
        closeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SpringApplication.exit(context,() -> -1);
                WindowEvent we = new WindowEvent(frame,WindowEvent.WINDOW_CLOSING);
                frame.dispatchEvent(we);
            }
        });
        //窗口最大化
        maximizeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                doMaximizeWindow(maximizeBtn);
            }
        });
        //窗口最小化
        minimizeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setExtendedState(JFrame.ICONIFIED);
            }
        });

        //库存管理
//        inventoryManage.addActionListener(mhandler.doInventoryManagmentAction());

        //菜品管理
//        dishManage.addActionListener(mhandler.doDishManagmentAction());

        // 活动设置
//        activityManage.addActionListener(mhandler.doActivitySettingAction());

        return jMenuBar;
    }

    public void doMaximizeWindow(final JButton maximizeBtn){
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if(device.getFullScreenWindow() == null){
            device.setFullScreenWindow(this);
            URL btnUrl = this.getClass().getClassLoader().getResource(imageConfigProperties.getNormalizeBtn());
            maximizeBtn.setIcon(new ImageIcon(btnUrl));
            maximizeBtn.setToolTipText("向下还原");
        }else{
            device.setFullScreenWindow(null);
            URL btnUrl = this.getClass().getClassLoader().getResource(imageConfigProperties.getMaximizeBtn());
            maximizeBtn.setIcon(new ImageIcon(btnUrl));
            maximizeBtn.setToolTipText("最大化");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setUndecorated(true);
//        this.setSize(PropertyUtils.getIntegerValue("mainframe.width"), PropertyUtils.getIntegerValue("mainframe.height"));
        this.setSize(frameConfigProperties.getMainFrame().getWidth(),frameConfigProperties.getMainFrame().getHeight());
        frameListener = new FrameListener(this);
        this.setTitle((String) PropertyUtils
                .getProperties(PropertyConstants.SYSNAME));
        Container containerPanel = this.getContentPane();
        BorderLayout layout = new BorderLayout(0, 0);
        containerPanel.setLayout(layout);
        containerPanel.setBackground(Color.WHITE);
//        HangupOrderPanel mainPanel = new HangupOrderPanel(this);
//        containerPanel.add(mainPanel,BorderLayout.CENTER);
        this.setJMenuBar(buildMenuBar());
        URL sysIconUrl = AppEntry.class.getClassLoader().getResource(imageConfigProperties.getLogo());
        Image frameIcon = Toolkit.getDefaultToolkit().createImage(sysIconUrl);
        this.setIconImage(frameIcon);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // 相对居中, 在frame设置size之后
        this.getContentPane().setBackground(Color.WHITE);
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            throw new UIException("set Look and Feel occured error",e);
        }
    }
}
