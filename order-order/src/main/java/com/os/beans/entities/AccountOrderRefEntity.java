package com.os.beans.entities;

import com.os.beans.entities.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jian zhu on 05/31/2017.
 */
@Entity
@Table(name="BUS_TB_ACCOUNT_ORDER_REF")
@Data
public class AccountOrderRefEntity extends BaseEntity{
    @Column(name="ACCOUNT_ID")
    private long accountId;

    @Column(name="ORDER_ID")
    private long orderId;
}
