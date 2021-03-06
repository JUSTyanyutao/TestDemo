package com.mxep.web.common.vo;

import com.mxep.web.common.bo.Constant;
import com.mxep.model.sys.Menu;

public class MenuVo extends Menu {

	private static final long serialVersionUID = 2479184186404669014L;

	private String isLeafName;

	private String statusName;

	public String getIsLeafName() {
		isLeafName = Constant.MenuNode.getNodeName(getParentMenu() == null ? 1 : 0);
		return isLeafName;
	}

	public String getStatusName() {
		statusName = Constant.Status.getStatusName(getStatus());
		return statusName;
	}
}
