package com.mxep.web.controller.system;

import com.mxep.model.sys.Menu;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.web.JsonMap;
import com.mxep.web.service.MenuService;
import com.mxep.web.web.Servlets;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * Controller - 菜单
 */
@Controller
public class MenuController extends WebController {

	@Autowired
	private MenuService menuService;

	/** 菜单 列表 */
	@RequiresPermissions("menu:view")
	@RequestMapping(value="/menus", method = RequestMethod.GET)
	public String list(Pagination pagination, @RequestParam(value = "pid", required = false) Integer pid, Model model) {
		model.addAttribute("pagination", pagination);
		model.addAttribute("pid", pid);
		return "menu/list";
	}

	/** 菜单列表 */
	@RequiresPermissions("menu:view")
	@RequestMapping(value = "/menus", method = RequestMethod.POST)
	public String list(Pagination pagination, @RequestParam(value = "pid", required = false) Integer pid,
					   HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		if (pid != null) {
			searchParams.put("EQ_parentMenu.id", pid);
		} else {
			searchParams.put("NULL_parentMenu", true);
		}
		model.addAttribute("page", menuService.list(pagination, searchParams, new Sort(Sort.Direction.ASC, "parentMenu.id", "sort")));
		return "menu/nested";
	}

	/** 创建菜单 */
	@RequiresPermissions("menu:update")
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String add(Pagination pagination, @RequestParam(value = "pid", required = false) Integer pid, Model model) {
		model.addAttribute("menu", new Menu());
		model.addAttribute("rootMenus", menuService.findRootMenuList());
		model.addAttribute("pagination", pagination);
		model.addAttribute("pid", pid);
		return "menu/edit";
	}

	/** 编辑菜单 */
	@RequiresPermissions("menu:update")
	@RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Pagination pagination, Model model) {
		model.addAttribute("menu", menuService.findMenu(id));
		model.addAttribute("rootMenus", menuService.findRootMenuList());
		model.addAttribute("pagination", pagination);
		return "menu/edit";
	}

	/** 保存菜单 */
	@ResponseBody
	@RequiresPermissions("menu:update")
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public JsonMap save(@Valid Menu menu, BindingResult result) {
		JsonMap ret;
		if (result.hasErrors()) {
			ret = parseErrorResult(result, "保存失败");
		} else {
			menuService.save(menu);
			ret = new JsonMap(0, "保存成功");
		}
		return ret;
	}

	/** 删除菜单 */
	@ResponseBody
	@RequiresPermissions("menu:delete")
	@RequestMapping(value = "/menu/delete", method = RequestMethod.POST)
	public JsonMap delete(@RequestParam("id[]") Integer[] ids) {
		menuService.delete(ids);
		return new JsonMap(0, "删除成功");
	}

}
