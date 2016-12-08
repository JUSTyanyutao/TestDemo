package com.mxep.web.controller.member;

import com.mxep.service.CommonService;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.controller.common.WebController;
import com.mxep.web.service.MemberCouponService;
import com.mxep.web.utils.ExcelReader;
import com.mxep.web.web.Servlets;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Controller - 优惠券统计
 */
@Controller
public class CouponController extends WebController {

	@Autowired
	private MemberCouponService memberCouponService;

	@Resource
	private CommonService commonService;

	/** 会员优惠券列表 */
	@RequestMapping(value = "/coupons", method = RequestMethod.GET)
	public String couponList(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "coupons/list";
	}
	
	/** 会员优惠券列表 */
	@RequestMapping(value = "/coupons", method = RequestMethod.POST)
	public String couponLists(Pagination pagination,
			HttpServletRequest request, Model model) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(
				request, "search_");
		model.addAttribute("page", memberCouponService.list(pagination,
				searchParams, new Sort(Sort.Direction.DESC, "id")));
		return "coupons/nested";
	}



	/** 会员优惠券列表 */
	@RequestMapping(value = "/shareCoupons", method = RequestMethod.GET)
	public String list1(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		Date currentDate = commonService.getCurrentDate();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("defaultStartDate", format.format(DateUtils.addDays(currentDate, -30)));
		model.addAttribute("defaultEndDate", format.format(currentDate));
		return "coupons/shareList";
	}


	/** 会员优惠券列表 */
	@RequestMapping(value = "/couponDetails", method = RequestMethod.GET)
	public String couponDetails(Pagination pagination, Model model) {
		model.addAttribute("pagination", pagination);
		return "coupons/details";
	}

	/** 批量导入数据 */
	@ResponseBody
	@RequestMapping(value = "/coupon/export", method = RequestMethod.POST)
	public List export(HttpServletRequest request, Model model) throws IOException {
//        JsonMap ret;
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
        MultipartFile file = multiRequest.getFile("file");
        ExcelReader ex = new ExcelReader();
        List list= ex.read(file.getInputStream());
       // ret = new JsonMap(0,s.size()+"nihao");
        return list;
	}
}
