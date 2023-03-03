package com.baizeyule.filesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ViewsController {


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/tools")
    public String tools(){
        return "tools";
    }
}
