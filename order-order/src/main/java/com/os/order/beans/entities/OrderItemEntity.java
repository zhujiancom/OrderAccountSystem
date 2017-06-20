package com.os.order.beans.entities;

import com.os.beans.entities.BaseEntity;
import lombok.Data;

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
@Data
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
}
