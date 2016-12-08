package com.mxep.web.service;

import com.mxep.core.utils.Md5Util;
import com.mxep.model.BaseDao;
import com.mxep.model.common.City;
import com.mxep.model.enums.EnumMembeRole;
import com.mxep.model.enums.EnumPointsLogType;
import com.mxep.model.hibernate.Hibernates;
import com.mxep.model.log.LogMemberPoints;
import com.mxep.model.member.Member;
import com.mxep.model.member.MemberProfile;
import com.mxep.model.member.Shop;
import com.mxep.model.repositories.JpaDao;
import com.mxep.model.repositories.member.MemberDao;
import com.mxep.model.repositories.member.LogMemberPointsDao;
import com.mxep.model.repositories.member.MemberProfileDao;
import com.mxep.model.repositories.member.ShopDao;
import com.mxep.service.CommonService;
import com.mxep.service.member.BaseMemberService;
import com.mxep.web.common.exception.WebRequestException;
import com.mxep.web.common.persistence.DynamicSpecifications;
import com.mxep.web.common.persistence.SearchFilter;
import com.mxep.web.common.vo.Pagination;
import com.mxep.web.web.JsonMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.security.Credential;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service - 菜单
 */
@Service
public class MemberService extends BaseService<Member, Integer> {

    private final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private MemberDao memberDao;

    @Resource
    private CommonService commonService;

    @Resource
    private ShopService shopService;

    @Resource
    private CityService cityService;

    @Resource
    private JpaDao jpaDao;

    @Resource
    private LogMemberPointsDao memberPointsDao;

    @Resource
    private MemberProfileDao memberProfileDao;



    @Resource
    @Override
    public void setBaseDao(BaseDao<Member, Integer> baseDao) {
        this.baseDao = baseDao;
        memberDao = (MemberDao) baseDao;
    }


    /**
     * 根据类型获取所有 会员
     * @param role
     * @return
     */
    public List<Member> getMemberByRole(byte role)
    {
        return memberDao.getMemberByRole(role);
    }


    /**
     * 根据 手机号码  获取 会员
     * @param mobile
     * @return
     */
    public Member getMemberByMobile(String mobile)
    {
        return memberDao.findMemberByMobile(mobile);
    }



    /**
     * 根据账号查询会员信息
     *
     * @return 会员信息
     */
    @Transactional(readOnly = true)
    public String getCityList(Member member) {
        List<City> cityList = member.getCityList();
        String s = "";
        for(int i=0; i<cityList.size(); i++)
        {
            if(i != cityList.size()-1)
            {
                s += cityList.get(i).getId()+",";
                continue;
            }
            s += cityList.get(i).getId();
        }
        return  s;
    }

    /**
     *  获取 门店  默认选中
     * @param member
     * @return
     */
    public String getShopList(Member member)
    {
        List<Shop> shopList = member.getShopList();
        String s = "";
        for(int i=0; i<shopList.size(); i++)
        {
            if(i != shopList.size()-1)
            {
                s += shopList.get(i).getId()+",";
                continue;
            }
            s += shopList.get(i).getId();
        }
        return s;
    }


    /**
     * 根据账号查询会员信息
     *
     * @param mobile 手机号码
     * @return 会员信息
     */
    @Transactional(readOnly = true)
    public Member findByAccount(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return null;
        }
        return memberDao.findMemberByMobile(mobile);
    }


    /**
     * 查询会员信息
     *
     * @param pagination
     * @param params
     * @param sort
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Member> queryAsPage(Pagination pagination, Map<String, Object> params, Sort sort) {
        Page<Member> page = list(pagination, params, sort);
        return page;
    }

    /**
     * 保存 普通 会员
     *
     * @param member 会员信息
     * @return 会员信息
     */
    @Override
    @Transactional
    public Member save(Member member) {
        Timestamp currentTime = commonService.getCurrentTime();
        Member temp1 = null;

        //新建 用户
        if(null == member.getId())
        {
            if(null != getMemberByMobile(member.getMobile()))
            {
                throw new WebRequestException("该手机号已经被注册");
//                member.setId(getMemberByMobile(member.getMobile()).getId());
            }
            MemberProfile memberProfile = member.getProfile();
            member.setUpdateTime(currentTime);
            member.setCreateTime(currentTime);
            member.setRegisterTime(currentTime);
            member.setProfile(null);
            memberProfile.setCreateTime(currentTime);
            memberProfile.setUpdateTime(currentTime);
            temp1 = memberDao.save(member);
            memberProfile.setMemberId(temp1.getId());
            memberProfileDao.save(memberProfile);
        }
        //编辑用户
        else
        {
            Member temp = memberDao.findOne(member.getId());
            member.setPoints(temp.getPoints());
            member.setSalt(temp.getSalt());
            member.setAvatar(temp.getAvatar());
            member.setOpenId(temp.getOpenId());
            member.setCreateTime(temp.getCreateTime());
            member.setOpenId(temp.getOpenId());
            member.setUpdateTime(currentTime);
            member.setRegisterTime(temp.getRegisterTime());
            MemberProfile m = temp.getProfile();
            MemberProfile memberProfile = member.getProfile();
            member.setProfile(null);
            temp1 = memberDao.save(member);
            memberProfile.setUpdateTime(currentTime);
            memberProfile.setMemberId(temp1.getId());
            if(memberProfile.getId() == null){
                memberProfile.setId(memberProfileDao.findByMemberId(member.getId()).getId());
            }
            memberProfileDao.save(memberProfile);
        }
        return temp1;
    }

    /**
     * 普通会员 转变 成 区域经理
     *
     * @param ids 编号数组
     */
    @Transactional
    public void changeManager(Integer[] city , Integer[] ids) {
        Member member;
        Timestamp currentTime = commonService.getCurrentTime();
        List<City> cityList = new ArrayList<>();
        for(Integer i:city)
        {
            cityList.add(cityService.get(i));
        }
        for (Integer id : ids) {
            member = findMember(id);
            if (member == null) {
                continue;
            }
            member.setCityList(cityList);
            member.setRole(EnumMembeRole.Manager.getValue());
            member.setUpdateTime(currentTime);
            // member.s(Member.MemberStatus.disable.value);
            memberDao.save(member);
        }
    }


    /**
     * 普通会员 转变 成 企业用户
     *
     * @param ids 编号数组
     */
    @Transactional
    public void changeShoper(Integer[] shop , Integer[] ids) {
        Member member;
        Timestamp currentTime = commonService.getCurrentTime();
        List<Shop> shopList = new ArrayList<>();
        for(Integer i:shop)
        {
            shopList.add(shopService.get(i));
        }
        for (Integer id : ids) {
            member = findMember(id);
            if (member == null) {
                continue;
            }
            member.setShopList(shopList);
            member.setRole(EnumMembeRole.Shoper.getValue());
            member.setUpdateTime(currentTime);
            // member.s(Member.MemberStatus.disable.value);
            memberDao.save(member);
        }
    }


    /**
     * 查询会员
     *
     * @param id 会员编号
     * @return 会员信息
     */
    @Transactional(readOnly = true)
    public Member findMember(Integer id) {
        if (id == null) {
            return null;
        }
        return memberDao.findOne(id);
    }

    /**
     * 删除会员
     *
     * @param ids 编号数组
     */
    @Override
    @Transactional
    public void delete(Integer[] ids) {
        Member member;
        for (Integer id : ids) {
            member = findMember(id);
            if (member == null) {
                continue;
            }
            // member.s(Member.MemberStatus.disable.value);
            member.setFlag( (byte)-1);
            memberDao.save(member);
        }
    }

    /**
     * 更新会员状态
     *
     * @param ids    编号
     * @param status 状态
     */
    @Transactional
    public void updateStatus(Integer[] ids, byte status) {
        Member member;
        if (ids != null && ids.length > 0) {
            for (Integer id : ids) {
                member = findMember(id);
                if (member == null) {
                    continue;
                }
                member.setStatus(status);

                memberDao.save(member);
            }
        }
    }

    /**
     * 根据搜索条件查找所有的会员
     *
     * @param params
     * @param sort
     * @return
     */
    @Transactional(readOnly = true)
    public List<Member> list(Map<String, Object> params, Sort sort) {

        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        Specification<Member> spec = DynamicSpecifications.bySearchFilter(
                filters.values(),
                (Class<Member>) ((ParameterizedType) getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[0]);

        return baseDao.findAll(spec, sort);
    }

    /**
     * 查询所有有效用户
     *
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Transactional
    public List<Member> findAllEffectiveMembers() {
        List list = memberDao.findAllEffectiveMembers();
        return list;
    }

    /**
     * 保存积分
     *
     * @param request
     * @return
     */
    @Transactional
    public JsonMap savePoints(HttpServletRequest request) {
        JsonMap result = new JsonMap(0, "操作成功");
        String mobiles = request.getParameter("mobile");
        String selectWay = request.getParameter("selectWay");
        String handleWay = request.getParameter("handleWay");
        String totalPoints = request.getParameter("totalPoints");
        Timestamp current = commonService.getCurrentTime();
        // 针对全部会员进行操作
        if ("2".endsWith(selectWay)) {
            // 增加积分
            Integer point = 0;
            if ("1".endsWith(handleWay)) {
                point = point + Integer.valueOf(totalPoints);
            }
            // 扣除积分
            else {
                point = point - Integer.valueOf(totalPoints);
            }
            memberDao.changeTotalPoints(point);
//            List<Member> list = memberDao.findAllEffectiveMembers();
//            if (list != null && list.size() > 0) {
//                for (Member member : list) {
//                    LogMemberPoints memberPoints = new LogMemberPoints();
//                    memberPoints.setPoints(Integer.valueOf(totalPoints));
//                    memberPoints.setType(EnumPointsLogType.Income.value);
//                    memberPoints.setMemberId(member.getId());
//                    memberPoints.setCreateTime(current);
////                    memberPoints.setType(point > 0 ? EnumPointsLogType.Income.value : EnumPointsLogSource.)
//                    memberPointsDao.save(memberPoints);
//                }
//            }
        }
        if("1".equals(selectWay))
        {
            String[] mobileList = mobiles.split(",");
            for(String s : mobileList)
            {
                Member member = memberDao.findOne(Integer.parseInt(s));
                if ("1".endsWith(handleWay)) {
                    member.setPoints(member.getPoints()+Integer.parseInt(totalPoints));
                }
                if ("2".endsWith(handleWay)) {
                    member.setPoints(member.getPoints()-Integer.parseInt(totalPoints));
                }
                memberDao.save(member);
//                LogMemberPoints memberPoints = new LogMemberPoints();
//                memberPoints.setPoints(Integer.valueOf(totalPoints));
//                memberPoints.setType(EnumPointsLogType.Income.value);
//                memberPoints.setMemberId(member.getId());
//                memberPoints.setCreateTime(current);
//                    memberPoints.setType(point > 0 ? EnumPointsLogType.Income.value : EnumPointsLogSource.)
//                memberPointsDao.save(memberPoints);
            }
        }
        return result;
    }

    /**
     * 统计会员
     * @param pageNum
     * @param startDate
     * @param endDate
     * @return
     */
    public Page findMemberTotal(int pageNum, String startDate, String endDate)
    {
        String sql = "select  COUNT(*) as num , DATE_FORMAT(register_time,\"%Y-%m-%d\") as day1 from mx_member where flag = 1";
        if(!"".equals(startDate) && "".equals(endDate))
        {
            startDate = "\""+startDate+"\"";
            sql += " AND DATE_FORMAT(register_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")";
        }
        if("".equals(startDate) && !"".equals(endDate))
        {
            endDate = "\""+endDate+"\"";
            sql += " AND DATE_FORMAT(register_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
        }
        if( !"".equals(startDate) && !"".equals(endDate))
        {
            startDate = "\""+startDate+"\"";
            endDate = "\""+endDate+"\"";
            sql += " AND DATE_FORMAT(register_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")"
                    +" AND DATE_FORMAT(register_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
        }
        sql += " GROUP BY DATE_FORMAT(register_time,\"%Y-%m-%d\") ORDER BY DATE_FORMAT(register_time,\"%Y-%m-%d\") DESC";

        Page page=jpaDao.queryAsPage(pageNum,sql,Map.class);

        return page;
    }


    /**
     * 统计会员
     * @param startDate
     * @param endDate
     * @return
     */
    public String findTotal(String startDate, String endDate)
    {
        String sql = "select count(*) as num from mx_member where flag = 1";
        if(!"".equals(startDate) && "".equals(endDate))
        {
            startDate = "\""+startDate+"\"";
            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")";
        }
        if("".equals(startDate) && !"".equals(endDate))
        {
            endDate = "\""+endDate+"\"";
            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
        }
        if( !"".equals(startDate) && !"".equals(endDate))
        {
            startDate = "\""+startDate+"\"";
            endDate = "\""+endDate+"\"";
            sql += " AND DATE_FORMAT(create_time,\"%Y-%m-%d\") >= str_to_date("+startDate+",\"%Y-%m-%d\")"
                    +" AND DATE_FORMAT(create_time,\"%Y-%m-%d\") <= str_to_date("+endDate +",\"%Y-%m-%d\")";
        }

        List<Map> list =jpaDao.queryAsList(sql,Map.class);
        String num= null == list.get(0).get("num")?"无":list.get(0).get("num").toString();
        return num;
    }





}
