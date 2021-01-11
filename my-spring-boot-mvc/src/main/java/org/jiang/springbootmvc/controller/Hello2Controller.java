package org.jiang.springbootmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/3/30
 * @Version 1.0
 */
@RestController
@RequestMapping("hello")
public class Hello2Controller {

    @RequestMapping("index")
    public String indext() {
        return "hello";
    }
}
