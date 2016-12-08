package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Coupon;
import com.mxep.model.enums.EnumCouponStatus;
import com.mxep.model.member.Member;
import com.mxep.model.repositories.member.CouponDao;
import com.mxep.service.CommonService;
import com.mxep.service.push.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Service - 会员优惠券
 */
@Service
public class MemberCouponService extends BaseService<Coupon, Integer> {

	private final Logger logger = LoggerFactory.getLogger(MemberCouponService.class);

	private CouponDao couponDao;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private PushService pushService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Coupon, Integer> baseDao) {
		this.baseDao = baseDao;
		couponDao = (CouponDao)baseDao;
	}
	
	
	/**
	 * 查询优惠券
	 *
	 * @param id 优惠券编号
	 * @return 优惠券信息
	 */
	@Transactional(readOnly = true)
	public Coupon findCoupon(Integer id) {
		if (id == null) {
			return null;
		}
		return couponDao.findOne(id);
	}
	
	/**
	 * 新增优惠券
	 * @param coupon
	 * @param request
	 * @return
	 */
	@Transactional
	public Coupon save(Coupon coupon,HttpServletRequest request) {
		Timestamp currentTime = commonService.getCurrentTime();
//		Integer id = coupon.getId();
//		Integer count = 0;
////		if(id == null){
////			 count = Integer.valueOf(request.getParameter("count"));
////		}
		Integer memberId = Integer.valueOf(request.getParameter("memberId"));
		Member member = memberService.findMember(memberId);
		//新增
//		if(count != null && count > 0){
			Coupon newCoupon = new Coupon();
//			for(int j = 0;j < count; j++){
//				newCoupon = new Coupon();
			newCoupon.setId(coupon.getId());
			newCoupon.setCreateTime(currentTime);
			newCoupon.setDesc(coupon.getDesc());
			newCoupon.setTitle(coupon.getTitle());
			newCoupon.setDenomination(coupon.getDenomination());
			newCoupon.setRestrictionMoney(coupon.getRestrictionMoney());
			newCoupon.setExpireTime(coupon.getExpireTime());
			newCoupon.setStatus(EnumCouponStatus.Activated.value);
			newCoupon.setActivationTime(currentTime);
			newCoupon.setFlag( (byte)1);
			newCoupon.setMemberId(memberId);
			couponDao.save(newCoupon);

//			}

//		}
//		//编辑
//		else{
//			if(id != null){
//				Coupon old = findCoupon(id);
//				coupon.setCreateTime(old.getCreateTime());
//				coupon.setUpdateTime(currentTime);
//				coupon.setActivationTime(old.getActivationTime());
//				coupon.setMember(member);
//				couponDao.save(coupon);
//			}
//		}
		return null;
	}


	/**
	 * 批量新增优惠券
	 * @param coupon
	 * @param request
	 * @return
	 */
	@Transactional
	public Coupon saveBatch(Coupon coupon,String[] memberPhones, HttpServletRequest request) {
		Timestamp currentTime = commonService.getCurrentTime();
		for(String s : memberPhones){
			if(memberPhones.length>0){
				Member member = memberService.findByAccount(s);
				if(member!=null){
					Coupon newCoupon = new Coupon();
					newCoupon.setCreateTime(currentTime);
					newCoupon.setDesc(coupon.getDesc());
					newCoupon.setTitle(coupon.getTitle());
					newCoupon.setDenomination(coupon.getDenomination());
					newCoupon.setRestrictionMoney(coupon.getRestrictionMoney());
					newCoupon.setExpireTime(coupon.getExpireTime());
					newCoupon.setStatus(EnumCouponStatus.Activated.value);
					newCoupon.setActivationTime(currentTime);
					newCoupon.setMember(member);
					couponDao.save(newCoupon);
				}
			}
			//新增

		}
		return null;
	}



	
	/**
	 * 删除优惠券(设为无效)
	 *
	 * @param ids 编号数组
	 */
	@Override
	@Transactional
	public void delete(Integer[] ids) {
		Coupon coupon;
		for (Integer id : ids) {
			coupon = findCoupon(id);
			if (coupon == null) {
				continue;
			}
			coupon.setStatus(EnumCouponStatus.InValid.value);
			couponDao.save(coupon);
		}
	}
	
}
