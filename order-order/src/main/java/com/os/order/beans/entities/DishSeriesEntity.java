package com.os.order.beans.entities;

import com.os.beans.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class DishSeriesEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // MYSQL ID generator
    @Column(name="ID", nullable=false,updatable=false)
    private long id;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(String seriesNo) {
        this.seriesNo = seriesNo;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public List<DishEntity> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishEntity> dishes) {
        this.dishes = dishes;
    }

    public DishSeriesEntity getParent() {
        return parent;
    }

    public void setParent(DishSeriesEntity parent) {
        this.parent = parent;
    }

    public Set<DishSeriesEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<DishSeriesEntity> children) {
        this.children = children;
    }
}
