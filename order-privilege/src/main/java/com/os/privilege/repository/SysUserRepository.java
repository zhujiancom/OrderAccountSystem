package com.os.privilege.repository;

import com.os.privilege.beans.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findByUserName(String userName);
}