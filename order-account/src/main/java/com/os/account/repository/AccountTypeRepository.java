package com.os.account.repository;

import com.os.beans.entities.AccountTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountTypeEntity,Long> {

}
