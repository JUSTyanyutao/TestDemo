package com.mxep.web.controller;

import com.mxep.web.controller.common.WebController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController extends WebController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "main";
    }

}