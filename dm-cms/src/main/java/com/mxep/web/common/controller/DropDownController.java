package com.mxep.web.common.controller;

import com.mxep.web.common.bo.TreeNode;
import com.mxep.web.common.service.DropdownListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 获取下拉框
 */
@Controller
@RequestMapping(value = "/dropdown")
public class DropDownController {

    @Autowired
    private DropdownListService dropdownListService;

    /**
     * 获取功能菜单树形结构
     *
     * @return
     */
    @RequestMapping(value = "/menuTree")
    @ResponseBody
    public List<TreeNode> getMenuTreeList() {
        return dropdownListService.getMenuTreeList();
    }

    /**
     * 获取功能菜单树形结构，并根据用户角色初始化选中菜单项
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/menuTree/{roleId}")
    @ResponseBody
    public List<TreeNode> getMenuTreeList(@PathVariable("roleId") Integer roleId, HttpServletRequest request) {
        return dropdownListService.getMenuTreeList(roleId);
    }


}
