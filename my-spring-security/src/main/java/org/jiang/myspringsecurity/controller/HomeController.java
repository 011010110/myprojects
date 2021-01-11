package org.jiang.myspringsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "home"})
    public String home() {
        return "this is home!";
    }

    @RequestMapping("loginPage")
    public String login(String username, String password) {
        return "login";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
