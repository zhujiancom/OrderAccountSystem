package com.os.order.beans.entities;

import com.os.beans.entities.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ORDER")
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // MYSQL ID generator
    @Column(name="ID", nullable=false,updatable=false)
    private Long id;

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
    @OneToMany(cascade= CascadeType.ALL,fetch= FetchType.EAGER,mappedBy="order")
    private List<OrderItemEntity> items;

    /* 该单有异常 */
    @Column(name="UNUSUAL_FLAG")
    private Boolean unusualFlag;

    /* 不可打折金额   */
    @Column(name="NO_DISCOUNT_AMOUNT")
    private BigDecimal noDiscountAmount;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="ORDER_SCHEME_REF",joinColumns={@JoinColumn(name="ORDER_ID",referencedColumnName="id")}
            ,inverseJoinColumns={@JoinColumn(name="SCHEME_ID",referencedColumnName="id")})
    private List<SchemeEntity> schemes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
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

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }

    public BigDecimal getNoDiscountAmount() {
        return noDiscountAmount;
    }

    public void setNoDiscountAmount(BigDecimal noDiscountAmount) {
        this.noDiscountAmount = noDiscountAmount;
    }

    public Boolean getDiscountFlag() {
        return discountFlag;
    }

    public void setDiscountFlag(Boolean discountFlag) {
        this.discountFlag = discountFlag;
    }

    public Boolean getUnusualFlag() {
        return unusualFlag;
    }

    public void setUnusualFlag(Boolean unusualFlag) {
        this.unusualFlag = unusualFlag;
    }

    public List<SchemeEntity> getSchemes() {
        return schemes;
    }

    public void setSchemes(List<SchemeEntity> schemes) {
        this.schemes = schemes;
    }
}
