package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Package;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public interface PackageDao extends BaseDao<Package,Integer> {

    @Query("select a from Package a where a.status = 1 and a.flag = 1")
    List<Package> findAllPackage();

}
