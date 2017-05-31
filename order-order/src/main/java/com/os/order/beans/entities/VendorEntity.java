package com.os.order.beans.entities;

import com.os.beans.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jian zhu on 05/31/2017.
 *
 * 活动平台设置
 *
 */
@Entity
@Table(name = "bus_tb_vendor")
public class VendorEntity extends BaseEntity{
    private long id;

    private String vendorName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, updatable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="VENDOR_NAME")
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
