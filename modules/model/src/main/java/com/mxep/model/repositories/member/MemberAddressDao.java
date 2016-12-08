package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.member.MemberAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Dao － 会员地址
 *
 * @author xingkong1221
 * @date 2015-12-14
 */
public interface MemberAddressDao extends BaseDao<MemberAddress, Integer> {

    /**
     * 查询会员地址信息
     *
     * @param memberId 会员编号
     * @param pageable 分页信息
     * @return 会员地址信息
     */
    @Query("select a from MemberAddress a where a.memberId = :memberId and a.flag = 1")
    Page<MemberAddress> findMemberAddressByMemberId(@Param("memberId") Integer memberId, Pageable pageable);

    /**
     * 把用户的默认地址设置为非默认地址
     *
     * @param memberId 会员编号
     */
    @Modifying
    @Query("update MemberAddress a set a.isDefault = 0 where a.flag = 1 and a.memberId = :memberId")
    void resetDefaultAddress(@Param("memberId") Integer memberId);

    /**
     * 设置成为默认地址
     *
     * @param id 地址编号
     */
    @Modifying
    @Query("update MemberAddress a set a.isDefault = 1 where a.flag = 1 and a.id = :id")
    void setDefaultAddress(@Param("id") Integer id);

    /**
     * 更新第一条收货地址为默认收货地址
     *
     * @param memberId 会员编号
     */
    @Modifying
    @Query(value = "update mx_member_address a set a.is_default = 1 where a.flag = 1 and a.member_id = :memberId and a.is_default <> 1 order by id limit 1", nativeQuery = true)
    void setFirstAddressDefault(@Param("memberId") Integer memberId);

    /**
     * 查询收货地址信息
     *
     * @param memberId 会员编号
     * @return 收货地址信息
     */
    @Query("select a from MemberAddress a where a.memberId = ?1 order by isDefault desc")
    List<MemberAddress> findByMemberId(Integer memberId);
}
