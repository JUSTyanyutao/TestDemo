package com.mxep.web.controller.common;

import com.mxep.web.service.AreaService;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Controller - 地区
 *
 * @author xingkong1221
 * @since 2016-04-28
 */
@Controller
public class AreaController extends WebController {

    @Resource
    private AreaService areaService;

    /**
     * 查询地区信息
     */
    @ResponseBody
    @RequestMapping(value = "/areas", method = RequestMethod.GET)
    public JsonMap getAreas(@RequestParam("pid") Integer pid) {
        return new JsonMap().addAttribute("areas", areaService.findByPid(pid));
    }
}
