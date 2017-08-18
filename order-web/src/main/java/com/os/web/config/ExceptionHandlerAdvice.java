package com.os.web.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 全局处理exception
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception,WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error"); // error page
        modelAndView.addObject("errorMessage",exception.getMessage());
        return modelAndView;
    }

    /**
     * 将键值对添加到全局
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg","extra messages.");
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }
}
