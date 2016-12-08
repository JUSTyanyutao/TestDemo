package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.member.MemberToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Dao - 会员接口凭证
 *
 * @author xingkong1221
 * @date 2015-12-01
 */
public interface MemberTokenDao extends BaseDao<MemberToken, Integer> {

    /**
     * 根据置换凭证查询凭证信息
     *
     * @param refreshToken 置换凭证
     * @return 凭证信息
     */
    @Query("select a from MemberToken a where a.refreshToken = :refreshToken and a.flag = 1")
    MemberToken findByRefreshToken(@Param("refreshToken") String refreshToken);
}
