package com.os.web.controller;

import com.os.web.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Jian Zhu on 12/26/2016.
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/person")
    public String index(Model model){
        Person single = new Person("aa",11);

        Person p1 = new Person("xx",11);
        Person p2 = new Person("yy",22);
        Person p3 = new Person("zz",44);
        List<Person> people = Stream.of(p1,p2,p3).collect(Collectors.toList());

        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);

        return "person";
    }

    @RequestMapping("/hello")
    public String hello(ModelMap model){
        model.addAttribute("message","Hello Spring MVC Framework!");
        return "hello";
    }
}
