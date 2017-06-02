package com.os.order.beans.entities;

import com.os.beans.entities.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private String vendorName;

    @Column(name="VENDOR_NAME")
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

}
