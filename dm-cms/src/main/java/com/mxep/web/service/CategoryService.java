package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.Category;
import com.mxep.model.repositories.goods.CategoryDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by yyt on 2016/9/6.
 */
@Service
@Transactional
public class CategoryService extends BaseService<Category,Integer> {

    private CategoryDao categoryDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<Category, Integer> baseDao) {
        this.baseDao = baseDao;
        categoryDao = (CategoryDao) baseDao;
    }



    /**
     * 查找 所有可用的  分类
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<Category> findAll() {
       return categoryDao.findAll();
    }


    /**
     * 根据 type  获取 二级分类
     *
     * @return 根分类列表
     */
    @Transactional(readOnly = true)
    public List<Category> findByType(Integer id) {
        Sort sort = new Sort(Sort.Direction.DESC, "sort");
        return categoryDao.findByType(id,sort);
    }


    /**
     * 获取最顶层的根分类列表
     *
     * @return 根分类列表
     */
    @Transactional(readOnly = true)
    public List<Category> findRootCategoryList() {
        Sort sort = new Sort(Sort.Direction.ASC, "sort");
        return categoryDao.findByCategory(null, sort);
    }


    /**
     * 删除竟拍品分类
     */
    @Transactional
    public JsonMap deleteCategory(Integer[] ids) {
        Category category;
        for (Integer id : ids) {
            category = categoryDao.findOne(id);
            if (category == null) {
                continue;
            }
            if(category.getPid() == 0)
            {
                if(categoryDao.findCategoryByPid(category.getId()).size() != 0)
                {
                    return new JsonMap(1,"该分类下有子分类，无法删除！");
                }
            }
            category.setFlag( (byte)-1);
            save(category);
        }
        return new JsonMap(0,"删除成功");
    }

    /**
     * 保存竟拍品分类
     */
    @Transactional
    public Category save(Category category) {
        Timestamp currentTime = commonService.getCurrentTime();
        if(null != category.getId())
        {
            category.setUpdateTime(currentTime);
        }
        else
        {
            category.setCreateTime(currentTime);
            category.setUpdateTime(currentTime);
        }
        if(category.getPid() == 0)
        {
            category.setLevel(0);
        }
        else
        {
            category.setLevel(1);
        }
        categoryDao.save(category);
        return category;
    }

    /**
	 * 启用分类
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		Category category;
		for (Integer id : ids) {
			category = categoryDao.findOne(id);
			if (category == null) {
				continue;
			}
			category.setIsDisplay( (byte) 1);
			save(category);
		}
        return new JsonMap(0,"操作成功");
	}

	/**
	 * 禁用分类
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
        Category category;
		for (Integer id : ids) {
            category = categoryDao.findOne(id);
			if (category == null) {
				continue;
			}
            if(category.getPid() == 0)
            {
                if(categoryDao.findCategoryByPid(category.getId()).size() != 0)
                {
                    return new JsonMap(1,"该分类下有子分类，无法禁用！");
                }
            }
            category.setIsDisplay( (byte) 0);
			save(category);
		}
        return new JsonMap(0,"操作成功");
	}

    /**
     * 获取所有 根分类
     */
    public List<Category> findAllRootCategory()
    {
        return categoryDao.findAllRootCategory();
    }

    /**
     *  获取所有  2级分类
     */
    public List<Category> findAllCategory(Integer id)
    {
        return categoryDao.findCategoryByPid(id);
    }


}
