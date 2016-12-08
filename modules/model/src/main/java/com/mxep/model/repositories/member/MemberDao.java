package com.mxep.model.repositories.member;

import java.util.List;

import com.mxep.model.BaseDao;
import com.mxep.model.member.Member;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Dao - 会员
 */
public interface MemberDao extends BaseDao<Member, Integer> {


	@Query("select a from Member a where a.role = ?1")
	List<Member> getMemberByRole(byte role);

	
	@Query("select a from Member a")
	List<Member> findMember();

	/**
	 * 根据手机号码查询会员账号
	 *
	 * @param mobile 手机号码
	 * @return 会员账号
	 */
	@Query("select a from Member a where a.mobile = :mobile and a.flag = 1 and a.status = 1")
	Member findMemberByMobile(@Param("mobile") String mobile);
	
	/**
	 * 根据微信用户惟一标志查看用户信息
	 *
	 * @param openid 微信用户惟一标志
	 * @return 会员用户信息
	 */
	@Query("select a from Member a where a.openId = ?1 and a.flag = 1")
	Member findMemberByWxOpenId(String openid);

	/**
	 * 更新会员头像
	 *
	 * @param memberId 会员编号
	 * @param avatar 头像
	 */
	@Modifying
	@Query("update Member a set a.avatar = ?2 where a.id = ?1 and a.flag = 1")
	void updateAvatar(Integer memberId, String avatar);

	/**
	 * 查询有效用户
	 * @param startId
	 * @param endId
	 * @return
	 */
	@Query("select a from Member a where a.id > ?1 and a.id <= ?2 and a.status = 1")
	public List<Member> findEffectiveMembers(Integer startId, Integer endId);
	
	
	@Query("select a from Member a where a.status = 1 and a.flag = 1")
	public List<Member> findAllEffectiveMembers();
	
	@Modifying
	@Query("update Member a set a.points = (a.points + :point) where a.status = 1 and a.flag = 1")
	void changeTotalPoints(@Param("point") Integer point);
}
