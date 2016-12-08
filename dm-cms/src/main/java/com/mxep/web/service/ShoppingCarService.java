package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.ShoppingCart;
import com.mxep.model.log.LogMemberPoints;
import com.mxep.model.repositories.goods.ShoppingCartDao;
import com.mxep.model.repositories.member.LogMemberPointsDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.persistence.DynamicSpecifications;
import com.mxep.web.common.persistence.SearchFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lq on 2016/9/6.
 */

/**
 * service  会员购物篮
 */

@Service
public class ShoppingCarService extends BaseService<ShoppingCart, Integer> {

    private ShoppingCartDao shoppingCartDao;

    @Resource
    private CommonService commonService;

    @Resource

    @Override
    public void setBaseDao(BaseDao<ShoppingCart, Integer> baseDao) {
        this.baseDao = baseDao;
        shoppingCartDao =( ShoppingCartDao)baseDao;
    }

    /**
     * 删除 购物篮中的 商品
     * @param ids 编号数组
     */
    public void delete(Integer[] ids)
    {
        for(Integer id:ids)
        {
            ShoppingCart shoppingCart = shoppingCartDao.findOne(id);
            if(null == shoppingCart)
            {
                continue;
            }
            shoppingCart.setFlag( (byte) -1);
            shoppingCartDao.save(shoppingCart);
        }
    }


}
