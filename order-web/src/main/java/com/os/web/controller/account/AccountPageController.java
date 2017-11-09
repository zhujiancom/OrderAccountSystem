package com.os.web.controller.account;

import com.os.account.constant.Currency;
import com.os.account.repository.AccountRepository;
import com.os.account.repository.AccountTypeRepository;
import com.os.beans.entities.AccountEntity;
import com.os.beans.entities.AccountTypeEntity;
import com.os.beans.entities.TradeFlow;
import com.os.beans.vos.AccountVo;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class AccountPageController {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private Mapper beanMapper;

    @RequestMapping("/acc")
    public String accountPage(ModelMap modelMap){
        List<AccountTypeEntity> accountTypes = accountTypeRepository.findAll();

        modelMap.put("accountTypes",accountTypes);
        modelMap.put("currencyTypes", Currency.values());
        return "account/account";
    }

    @RequestMapping(value="/acc/add",method= RequestMethod.POST)
    @ResponseBody
    public AccountEntity addNew(@RequestBody AccountVo accountVo){
        AccountTypeEntity accType = accountTypeRepository.getOne(accountVo.getTypeId());
        AccountEntity accountEntity = beanMapper.map(accountVo, AccountEntity.class);
        accountEntity.setAccType(accType);
        accountEntity = accountRepository.save(accountEntity);
        return accountEntity;
    }

    @RequestMapping(value="/acc/acquireFlows/{accountId}",method= RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<TradeFlow> acquireTradeFlows(@PathVariable Long accountId){
        AccountEntity accountEntity = accountRepository.findOne(accountId);
        return accountEntity.getTradeFlows();
    }
}
