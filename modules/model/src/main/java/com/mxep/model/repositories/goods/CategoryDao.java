package com.mxep.model.repositories.goods;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yyt on 2016/9/6.
 */
public interface CategoryDao extends BaseDao<Category,Integer> {


    /**
     * 查找所有 跟分类
     * @return
     */
    @Query("select a from Category a where a.flag = 1 and a.isDisplay = 1 and a.pid = 0")
    List<Category> findAllRootCategory();


    /**
     *   根据 父分类   查找 所有 子分类
     */
    @Query("select a from Category a where a.pid = ?1 and a.flag = 1")
    List<Category> findCategoryByPid(Integer pid);

    /**
     * 查找所有 可用的分类
     * @return
     */
    @Query("select a from Category a where a.flag = 1")
    List<Category> findAll();

    /**
     * 查询分类列表
     *
     * @param
     * @param sort 排序
     * @return 菜单列表
     */
    @Query()
    List<Category> findByCategory(Category category, Sort sort);

    /**
     * 查询  一级  分类
     * @param type
     * @param sort
     * @return
     */
    @Query("select a from Category a where a.pid = 0 and a.type = ?1 and a.flag = 1")
    List<Category> findByType(Integer type,Sort sort);


}
