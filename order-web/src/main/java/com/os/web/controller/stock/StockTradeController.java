package com.os.web.controller.stock;

import com.os.stock.beans.vos.StockTradeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class StockTradeController {

    @RequestMapping("/stock/summary")
    public String summary(Model model){
        StockTradeVO vo1 = new StockTradeVO();
        vo1.setStockNo("600487");
        vo1.setStockName("亨通光电");
        vo1.setTradeType("买入");
        vo1.setTradeDate(new Date());
        vo1.setAmount(2552);
        vo1.setCost(new BigDecimal(25.52));

        StockTradeVO vo2 = new StockTradeVO();
        vo2.setStockNo("600487");
        vo2.setStockName("中信证券\n");
        vo2.setTradeType("买入");
        vo2.setTradeDate(new Date());
        vo2.setAmount(2552);
        vo2.setCost(new BigDecimal(2.1));
        List<StockTradeVO> stocks = Stream.of(vo1,vo2).collect(Collectors.toList());

        model.addAttribute("stocks",stocks);
        return "stockSummary";
    }
}
