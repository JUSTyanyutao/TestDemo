package com.mxep.web.common.bo;

import java.util.List;

import com.mxep.model.sys.Menu;

public class MenuBo {

	public static String outputHtml(List<Menu> menus) {
		StringBuffer sb = new StringBuffer();
		if (null != menus) {
			for (Menu menu : menus) {
			}
		}
		return sb.toString();
	}

}
