package com.os.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by jian zhu on 05/27/2017.
 */
@Component
@ConfigurationProperties("image.path")
public class ImageConfigProperties {
    private String logo;

    private String minimizeBtn;

    private String maximizeBtn;

    private String normalizeBtn;

    private String closeBtn;

    private String systemInitMenu;

    private String baseDataResetMenu;

    private String takeoutMenu;

    private String busVolumeStatisticMenu;

    private String costStatisticMenu;

    private String dishSalVolumeStatisticMenu;

    private String dishManagementMenu;

    private String stockManagementMenu;

    private String activityManagementMenu;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMinimizeBtn() {
        return minimizeBtn;
    }

    public void setMinimizeBtn(String minimizeBtn) {
        this.minimizeBtn = minimizeBtn;
    }

    public String getMaximizeBtn() {
        return maximizeBtn;
    }

    public void setMaximizeBtn(String maximizeBtn) {
        this.maximizeBtn = maximizeBtn;
    }

    public String getNormalizeBtn() {
        return normalizeBtn;
    }

    public void setNormalizeBtn(String normalizeBtn) {
        this.normalizeBtn = normalizeBtn;
    }

    public String getCloseBtn() {
        return closeBtn;
    }

    public void setCloseBtn(String closeBtn) {
        this.closeBtn = closeBtn;
    }

    public String getSystemInitMenu() {
        return systemInitMenu;
    }

    public void setSystemInitMenu(String systemInitMenu) {
        this.systemInitMenu = systemInitMenu;
    }

    public String getBaseDataResetMenu() {
        return baseDataResetMenu;
    }

    public void setBaseDataResetMenu(String baseDataResetMenu) {
        this.baseDataResetMenu = baseDataResetMenu;
    }

    public String getTakeoutMenu() {
        return takeoutMenu;
    }

    public void setTakeoutMenu(String takeoutMenu) {
        this.takeoutMenu = takeoutMenu;
    }

    public String getBusVolumeStatisticMenu() {
        return busVolumeStatisticMenu;
    }

    public void setBusVolumeStatisticMenu(String busVolumeStatisticMenu) {
        this.busVolumeStatisticMenu = busVolumeStatisticMenu;
    }

    public String getCostStatisticMenu() {
        return costStatisticMenu;
    }

    public void setCostStatisticMenu(String costStatisticMenu) {
        this.costStatisticMenu = costStatisticMenu;
    }

    public String getDishSalVolumeStatisticMenu() {
        return dishSalVolumeStatisticMenu;
    }

    public void setDishSalVolumeStatisticMenu(String dishSalVolumeStatisticMenu) {
        this.dishSalVolumeStatisticMenu = dishSalVolumeStatisticMenu;
    }

    public String getDishManagementMenu() {
        return dishManagementMenu;
    }

    public void setDishManagementMenu(String dishManagementMenu) {
        this.dishManagementMenu = dishManagementMenu;
    }

    public String getStockManagementMenu() {
        return stockManagementMenu;
    }

    public void setStockManagementMenu(String stockManagementMenu) {
        this.stockManagementMenu = stockManagementMenu;
    }

    public String getActivityManagementMenu() {
        return activityManagementMenu;
    }

    public void setActivityManagementMenu(String activityManagementMenu) {
        this.activityManagementMenu = activityManagementMenu;
    }
}
