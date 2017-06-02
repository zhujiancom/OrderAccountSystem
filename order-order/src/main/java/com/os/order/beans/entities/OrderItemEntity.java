package com.os.order.beans.entities;

import com.os.beans.entities.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ORDER_ITEM")
public class OrderItemEntity extends BaseEntity {
    /* 订单编号 */
    @Column(name = "BILL_NO")
    private String billNo;

    /* 付费编号 */
    @Column(name = "PAY_NO")
    private String payNo;

    /* 菜品编号 */
    @Column(name = "DISH_NO")
    private String dishNo;

    /* 套餐编号 */
    @Column(name = "SUIT_NO")
    private String suitNo;

    /* 是否套餐 */
    @Column(name="SUIT_FLAG")
    private Boolean suitFlag;

    /* 折扣率 */
    @Column(name="DISCOUNT_RATE")
    private BigDecimal discountRate;

    /* 打折金额 */
    @Column(name="DISCOUNT_AMOUNT")
    private BigDecimal discountAmount;

    /* 实际收入金额 */
    @Column(name="REAL_AMOUNT")
    private BigDecimal realAmount;

    /* 点菜数量 */
    @Column(name="COUNT")
    private BigDecimal count;

    /* 退菜数量 */
    @Column(name="COUNT_BACK")
    private BigDecimal countBack;

    /* 菜品单价 */
    @Column(name="PRICE")
    private BigDecimal price;

    /* 点菜时间 */
    @Column(name="CONSUME_TIME")
    private BigDecimal consumeTime;

    /*退菜时间*/
    @Column(name="BACK_TIME")
    private BigDecimal backTime;

    /* 对应 订单 */
    @ManyToOne(cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getDishNo() {
        return dishNo;
    }

    public void setDishNo(String dishNo) {
        this.dishNo = dishNo;
    }

    public String getSuitNo() {
        return suitNo;
    }

    public void setSuitNo(String suitNo) {
        this.suitNo = suitNo;
    }

    public Boolean getSuitFlag() {
        return suitFlag;
    }

    public void setSuitFlag(Boolean suitFlag) {
        this.suitFlag = suitFlag;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCountBack() {
        return countBack;
    }

    public void setCountBack(BigDecimal countBack) {
        this.countBack = countBack;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(BigDecimal consumeTime) {
        this.consumeTime = consumeTime;
    }

    public BigDecimal getBackTime() {
        return backTime;
    }

    public void setBackTime(BigDecimal backTime) {
        this.backTime = backTime;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }
}
