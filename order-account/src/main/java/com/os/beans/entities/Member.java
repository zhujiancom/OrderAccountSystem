package com.os.beans.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BUS_TB_MEMBER")
@Data
public class Member extends BaseEntity {
    @Column(name = "NAME")
    private String name;
}
