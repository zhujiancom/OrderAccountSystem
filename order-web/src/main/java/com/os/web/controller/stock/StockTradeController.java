package com.os.web.controller.stock;

import com.os.stock.beans.vos.StockTradeVO;
import com.os.stock.service.StockServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class StockTradeController {
    @Autowired
    private StockServiceFacade stockServiceFacade;

    @RequestMapping("/stock/summary")
    public String summary(Model model){
        List<StockTradeVO> stocks = stockServiceFacade.findAll();
        model.addAttribute("stocks",stocks);
        return "stockSummary";
    }

    @RequestMapping("/stock/registerPage")
    public String summaryView(@ModelAttribute("stockTrade") StockTradeVO stockTradeVO){
        return "stockRegister";
    }

    @PostMapping("/stock/registerStock")
    public String register(@ModelAttribute("stockTrade") StockTradeVO stockTradeVO,Model model){
        stockServiceFacade.insert(stockTradeVO);
        return "forward:/stock/summary";
    }
}
