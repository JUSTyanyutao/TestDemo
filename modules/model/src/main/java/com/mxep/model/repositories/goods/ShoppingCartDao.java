package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.common.ShoppingCart;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 购物车
 */
public interface ShoppingCartDao extends BaseDao<ShoppingCart, Integer> {

	/**
	 * 根据会员编号查询购物车信息
	 *
	 * @param memberId 会员编号
	 * @return 购物车信息
	 */
	List<ShoppingCart> findByMemberId(Integer memberId);

//	/**
//	 * 更新购物车中的商品数量
//	 *
//	 * @param memberId 会员编号
//	 * @param auctionId 竞拍品编号
//	 */
//	ShoppingCart findByMemberIdAndAuctionId(Integer memberId, Integer auctionId);

//	/**
//	 * 设置购物车中的商品数量
//	 *
//	 * @param memberId 会员编号
//	 * @param auctionId 竞拍品编号
//	 * @param quantity 数量
//	 */
//	@Modifying
//	@Query("UPDATE ShoppingCart a SET a.quantity = ?3 WHERE a.memberId = ?1 and a.auctionId = ?2")
//	void setQuantity(Integer memberId, Integer auctionId, Integer quantity);
//
//	/**
//	 * 根据编号删除购物车中数据
//	 *
//	 * @param ids 编号
//     */
//	@Modifying
//	@Query("DELETE FROM ShoppingCart a WHERE a.memberId = ?1 and a.auctionId in ?2")
//	void deleteByIds(Integer memberId, List<Integer> ids);
//
//	/**
//	 * 查询购物车中的商品总数量
//	 *
//	 * @param memberId 会员编号
//	 * @return 数量
//	 */
//	@Query("SELECT SUM(a.quantity) FROM ShoppingCart a WHERE a.memberId = ?1")
//	Integer countByMemberId(Integer memberId);

}
