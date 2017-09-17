package com.os.beans.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *
 * remark (备注):菜品
 *
 * @author zj
 *
 * 项目名称：ReconciliationPro
 *
 * 类名称：DishEntity
 *
 * 包名称：com.rci.bean.entity
 *
 * Create Time: 2015年6月23日 上午8:43:12
 *
 */
@Entity
@Table(name="BUS_TB_DISH")
@Data
public class DishEntity extends BaseEntity {
    /* 菜品编号 */
    @Column(name="DISH_NO")
    private String dishNo;

    /* 菜品名称   */
    @Column(name="DISH_NAME")
    private String dishName;

    /* 菜品单价 */
    @Column(name="DISH_PRICE")
    private BigDecimal dishPrice;

    /* 菜品类型 小类 */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "DISH_SERIES_ID")
    private DishSeriesEntity dishSeries;

    /* 是否停用 */
    @Column(name="STOP_FLAG")
    private Boolean stopFlag;

    /* 是否是套餐 */
    @Column(name="SUIT_FLAG")
    private Boolean suitFlag;

    /* 是否可打折 */
    @Column(name="DISCOUNT_FLAG")
    private Boolean discountFlag;

    /* 菜品成本单价 （原料成本） */
    @Column(name="COST")
    private BigDecimal cost;

    /* 是否参与统计 */
    @Column(name="STATISTIC_FLAG")
    private Boolean statisticFlag;

    /* 是否是餐盒费 等 */
    @Column(name="BOX_FEE_FLAG")
    private Boolean boxFeeFlag;

    /* 是否是外送费 */
    @Column(name="TAKEOUT_FEE_FLAG")
    private Boolean takeoutFeeFlag;
}
