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
 */
@Entity
@Table(name="BUS_TB_ACCOUNT_ORDER_REF")
public class AccountOrderRefEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // MYSQL ID generator
    @Column(name="ID", nullable=false,updatable=false)
    private long id;

    @Column(name="ACCOUNT_ID")
    private long accountId;

    @Column(name="ORDER_ID")
    private long orderId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
}
