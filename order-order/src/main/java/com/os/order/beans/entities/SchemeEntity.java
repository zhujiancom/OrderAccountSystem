package com.os.order.beans.entities;

import com.os.beans.entities.AccessoryEntity;
import com.os.enums.CommonEnums;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * Created by jian zhu on 05/31/2017.
 * 活动方案设置
 *
 */
@Entity
@Table(name = "BUS_TB_SCHEME")
public @Data
class SchemeEntity extends AccessoryEntity {
    /* 活动编号 */
    @Column(name="SCHEME_NO")
    private String schemeNo;

    /* 活动或代金券名称 */
    @Column(name = "SCHEME_NAME")
    private String name;

    /* 实际买入价格 */
    @Column(name = "POST_PRICE")
    private BigDecimal postPrice;

    /* 抵用价格 */
    @Column(name = "PRICE")
    private BigDecimal price;

    /* 佣金抽成 */
    @Column(name = "COMMISSION")
    private BigDecimal commission;

    /* 手动差价 */
    @Column(name = "SPREAD")
    private BigDecimal spread;

    /* 活动开始时间  */
    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE")
    private Date startDate;

    /* 活动结束时间  */
    @Temporal(TemporalType.DATE)
    @Column(name="END_DATE")
    private Date endDate;

    /* 满减活动下限金额  */
    @Column(name="FLOOR_NUMBER")
    private BigDecimal floorAmount;

    /* 满减活动上限金额 */
    @Column(name="CEIL_NUMBER")
    private BigDecimal ceilAmount;

    @Enumerated(EnumType.STRING)
    @Column(name="ACTIVITY_STATUS")
    private CommonEnums.ActivityStatus activityStatus;

    @OneToOne
    @JoinColumn(name="VENDOR_ID",referencedColumnName="id" )
    private VendorEntity vendor;

    /* 包含饮料金额 */
    @Column(name="BEVERAGE_AMOUNT")
    private BigDecimal beverageAmount = BigDecimal.ZERO;

    @ManyToMany(mappedBy="schemes")
    private Set<OrderEntity> orders;

    public String getSchemeNo() {
        return schemeNo;
    }

    public void setSchemeNo(String schemeNo) {
        this.schemeNo = schemeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(BigDecimal postPrice) {
        this.postPrice = postPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFloorAmount() {
        return floorAmount;
    }

    public void setFloorAmount(BigDecimal floorAmount) {
        this.floorAmount = floorAmount;
    }

    public BigDecimal getCeilAmount() {
        return ceilAmount;
    }

    public void setCeilAmount(BigDecimal ceilAmount) {
        this.ceilAmount = ceilAmount;
    }

    public CommonEnums.ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(CommonEnums.ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    public VendorEntity getVendor() {
        return vendor;
    }

    public void setVendor(VendorEntity vendor) {
        this.vendor = vendor;
    }

    public BigDecimal getBeverageAmount() {
        return beverageAmount;
    }

    public void setBeverageAmount(BigDecimal beverageAmount) {
        this.beverageAmount = beverageAmount;
    }

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }
}
