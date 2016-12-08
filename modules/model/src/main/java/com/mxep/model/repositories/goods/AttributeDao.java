package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Attribute;
import com.mxep.model.goods.AttributeValue;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yyt on 2016/9/6.
 */
public interface AttributeDao extends BaseDao<Attribute,Integer> {

    @Query("select a from Attribute a where a.status = 1 and a.flag = 1")
    List<Attribute> findAllAttribute();

}
