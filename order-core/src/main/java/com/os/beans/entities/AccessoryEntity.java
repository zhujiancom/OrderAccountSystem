package com.os.beans.entities;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created by jian zhu on 05/31/2017.
 */
public abstract class AccessoryEntity extends BaseEntity{
    private Long creator;

    private Long modifier;

    private Date createTime;

    private Date modifyTime;

    @Column(name = "CREATOR", insertable = true, updatable = false)
    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Column(name = "MODIFIER")
    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    @Column(name="CREATE_TIME", insertable = true, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name="MODIFY_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
