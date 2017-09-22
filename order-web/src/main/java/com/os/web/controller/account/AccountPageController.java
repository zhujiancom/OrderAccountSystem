package com.os.web.controller.account;

import com.os.account.repository.AccountTypeRepository;
import com.os.beans.entities.AccountTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AccountPageController {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @RequestMapping("/acc")
    public String accountPage(ModelMap modelMap){
        List<AccountTypeEntity> accountTypes = accountTypeRepository.findAll();
        modelMap.put("accountTypes",accountTypes);
        return "account/account";
    }
}
