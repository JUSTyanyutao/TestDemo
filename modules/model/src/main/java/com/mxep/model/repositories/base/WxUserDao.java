package com.mxep.model.repositories.base;


import com.mxep.model.BaseDao;
import com.mxep.model.base.WxUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Dao - 微信用户
 */
public interface WxUserDao extends BaseDao<WxUser, Integer> {

    /**
     * 根据openid查询微信用户信息
     *
     * @param openId 微信用户惟一标志
     * @return 微信用户信息
     */
    @Query("select a from WxUser a where a.flag = 1 and a.openId = ?1")
	WxUser findWxUserByOpenId(String openId);
}
