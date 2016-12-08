package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.common.SignIn;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by ranfi on 3/15/16.
 */
public interface SignInDao extends BaseDao<SignIn, Integer> {

    /**
     * 查询签到信息
     *
     * @param memberId 会员编号
     * @param date 日期
     * @return 签到信息
     */
    @Query("SELECT a FROM SignIn a WHERE a.memberId = ?1 AND a.signInDate = ?2")
    SignIn findByMemberIdAndDate(Integer memberId, Date date);


    @Query(value = "select a from SignIn a where a.memberId = ?1 and a.signInDate = ?2 ")
    SignIn findSignin(int memberId, Date date);

    /**
     * 获取签到次数
     *
     * @param memberId 会员编号
     * @return 签到次数
     */
    @Query("select count(a) from SignIn a where a.memberId = ?1 and a.flag = 1")
    Integer getSigninTimes(int memberId);

    /**
     * 获取N天前到当天期间的签到次数
     *
     * @param memberId
     * @param minDate
     * @param currentDate
     * @return
     */
    @Query(value = "select count(a) from SignIn a where a.memberId=?1 and a.signInDate >=?2 and a.signInDate < ?3")
    Long getSigninTimes(int memberId, Date minDate, Date currentDate);


}
