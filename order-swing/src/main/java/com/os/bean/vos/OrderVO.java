package com.os.bean.vos;

import com.os.enums.CommonEnums;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by jian zhu on 05/27/2017.
 */
public class OrderVO implements Serializable,Comparable<OrderVO>{
    private Long orderId;

    private String payNo;

    private String tableName;

    private BigDecimal originAmount;

    private BigDecimal actualAmount;

    private String schemeName;

    private Date openDeskTime;

    private Date checkoutTime;

    private CommonEnums.YOrN unusual;

    private String warningInfo;

    private List<OrderItemVO> items;

    private BigDecimal freeAmount;

    /* 整单实际收入总额  */
    private BigDecimal totalAmount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public BigDecimal getOriginAmount() {
        return originAmount;
    }

    public void setOriginAmount(BigDecimal originAmount) {
        this.originAmount = originAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public Date getOpenDeskTime() {
        return openDeskTime;
    }

    public void setOpenDeskTime(Date openDeskTime) {
        this.openDeskTime = openDeskTime;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public CommonEnums.YOrN getUnusual() {
        return unusual;
    }

    public void setUnusual(CommonEnums.YOrN unusual) {
        this.unusual = unusual;
    }

    public String getWarningInfo() {
        return warningInfo;
    }

    public void setWarningInfo(String warningInfo) {
        this.warningInfo = warningInfo;
    }

    public List<OrderItemVO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemVO> items) {
        this.items = items;
    }

    public BigDecimal getFreeAmount() {
        return freeAmount;
    }

    public void setFreeAmount(BigDecimal freeAmount) {
        this.freeAmount = freeAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public int compareTo(OrderVO next) {
        if(next.getCheckoutTime().after(this.getCheckoutTime())){
            return -1;
        }
        return 1;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
