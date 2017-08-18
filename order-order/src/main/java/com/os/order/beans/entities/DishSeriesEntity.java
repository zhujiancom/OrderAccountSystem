package com.os.order.beans.entities;

import com.os.beans.entities.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_DISH_SERIES")
@Data
public class DishSeriesEntity extends BaseEntity {
    @Column(name="SERIES_NO")
    private String seriesNo;

    @Column(name="SERIES_NAME")
    private String seriesName;

    /* 包含的菜品 */
    @OneToMany(mappedBy = "dishSeries")
    private List<DishEntity> dishes;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private DishSeriesEntity parent;

    @OneToMany(mappedBy = "parent")
    @OrderBy("id")
    private Set<DishSeriesEntity> children = new HashSet<>();
}
