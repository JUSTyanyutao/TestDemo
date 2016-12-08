package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Attribute;
import com.mxep.model.goods.AttributeValue;
import com.mxep.model.goods.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yyt on 2016/9/6.
 */
public interface AttributeValueDao extends BaseDao<AttributeValue,Integer> {

    @Query("select a from AttributeValue a where a.attrId = ?1")
    List<AttributeValue> getAttributeValueByAttrbuteId(Integer id);


}
