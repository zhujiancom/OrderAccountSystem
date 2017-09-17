package com.os.beans.entities;

import com.os.beans.entities.BaseEntity;
import lombok.Data;

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
@Data
public class VendorEntity extends BaseEntity{
    @Column(name="VENDOR_NAME")
    private String vendorName;
}
