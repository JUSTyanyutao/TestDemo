package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.member.MemberProfile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Dao - 会员账号信息
 *
 * @author xingkong1221
 * @since 2015-11-20
 */
public interface MemberProfileDao extends BaseDao<MemberProfile, Integer> {

    /**
     * 更新会员昵称
     *
     * @param memberId 会员编号
     * @param nickname 昵称
     */
    @Modifying
    @Query("update MemberProfile a set a.nickname = ?2 where a.flag = 1 and a.memberId = ?1")
    void updateNickname(Integer memberId, String nickname);

    /**
     * 更新会员性别
     *
     * @param memberId 会员编号
     * @param gender 性别
     */
    @Modifying
    @Query("update MemberProfile a set a.gender = ?2 where a.flag = 1 and a.memberId = ?1")
    void updateGender(Integer memberId, Byte gender);

    @Query("select a from MemberProfile a where a.member.id = :memberId and a.flag = 1")
    MemberProfile findByMemberId(@Param("memberId") Integer memberId);
}
