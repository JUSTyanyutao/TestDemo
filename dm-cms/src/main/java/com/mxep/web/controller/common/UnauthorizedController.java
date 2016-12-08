package com.mxep.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller - 未授权
 *
 * @author xingkong1221
 * @date 2015-11-06
 */
@Controller
public class UnauthorizedController extends WebController {

    @RequestMapping(value = "/unauthorized")
    public String index() {
        return "error/403";
    }
}
