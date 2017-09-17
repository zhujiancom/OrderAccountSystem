package com.os.beans.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ORDER")
@Data
@ToString()
public class OrderEntity extends BaseEntity {
    /* 订单编号   */
    @Column(name="ORDER_NO")
    private String orderNo;

    /* 付费编号 */
    @Column(name="PAY_NO")
    private String payNo;

    /* 桌号 */
    @Column(name="TABLE_NO")
    private String tableNo;

    /* 开桌时间  */
    @Column(name="OPEN_DESK_TIME")
    private Date openDeskTime;

    /* 结账时间  */
    @Column(name="CHECKOUT_TIME")
    private Date checkoutTime;

    /*日期 */
    @Column(name="DAY")
    private Date day;

    /* 订单原价   */
    @Column(name="ORIGIN_PRICE")
    private BigDecimal originPrice;

    /* 折扣方案 名称 */
    @Column(name="SCHEME_NAME")
    private String schemeName;

    /* 是否具有单品折扣  */
    @Column(name="DISCOUNT_FLAG")
    private Boolean discountFlag;

    /* 实收金额   */
    @Column(name="REAL_AMOUNT")
    private BigDecimal realAmount;

    /* 具体菜品明细  */
    @OneToMany(cascade= CascadeType.ALL,mappedBy="order")
    private Set<OrderItemEntity> items;

    /* 该单有异常 */
    @Column(name="UNUSUAL_FLAG")
    private Boolean unusualFlag;

    /* 不可打折金额   */
    @Column(name="NO_DISCOUNT_AMOUNT")
    private BigDecimal noDiscountAmount;

    @ManyToMany
    @JoinTable(name="ORDER_SCHEME_REF",joinColumns={@JoinColumn(name="ORDER_ID",referencedColumnName="id")}
            ,inverseJoinColumns={@JoinColumn(name="SCHEME_ID",referencedColumnName="id")})
    private Set<SchemeEntity> schemes;
}
