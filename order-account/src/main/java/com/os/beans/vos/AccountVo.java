package com.os.beans.vos;

import com.os.account.constant.Currency;
import com.os.beans.entities.TradeFlow;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AccountVo {
    private Long accId;

    private Long typeId;

    private String accNo;

    private String name;

    private Currency currency;

    private BigDecimal earningAmount;

    private BigDecimal expenseAmount;

    private BigDecimal balance;

    private String description;

    private List<TradeFlow> flows;
}
