package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.repositories.goods.search.SearchKeywordsDao;
import com.mxep.model.search.SearchKeywords;
import com.mxep.service.CommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lq on 2016/9/8.
 */
@Service
public class SearchKeywordsService extends BaseService<SearchKeywords,Integer> {

    private SearchKeywordsDao searchKeywordsDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<SearchKeywords, Integer> baseDao) {
        this.baseDao = baseDao;
        searchKeywordsDao =( SearchKeywordsDao)baseDao;
    }


    /**
     * 查询搜索关键字
     *
     *
     */
    @Transactional(readOnly = true)
    public SearchKeywords findSearchKeywords(Integer id) {
        if (id == null) {
            return null;
        }
        return searchKeywordsDao.findOne(id);
    }

    /**
     * 保存搜索关键字
     *
     */
    @Override
    @Transactional
    public SearchKeywords save(SearchKeywords searchKeywords) {
        // 从数据库总查询关键字
        Timestamp currentTime = commonService.getCurrentTime();
        SearchKeywords entity = findSearchKeywords(searchKeywords.getId());
        if (entity == null) {
            // 新建搜索关键字
            entity = searchKeywords;
            entity.setCreateTime(currentTime);
            entity.setUpdateTime(currentTime);
        } else {
            // 保存关键字
            Date createTime = searchKeywords.getCreateTime();
            entity = searchKeywords;
            entity.setCreateTime(createTime);
            entity.setUpdateTime(currentTime);
        }
        return searchKeywordsDao.save(entity);
    }

    /**
     * 删除搜索关键字
     *
     * @param ids
     *            编号数组
     */
    @Override
    @Transactional
    public void delete(Integer[] ids) {
        SearchKeywords searchKeywords;
        for (Integer id : ids) {
            searchKeywords = findSearchKeywords(id);
            if (searchKeywords == null) {
                continue;
            }
            searchKeywords.setFlag((byte)-1);
            searchKeywordsDao.save(searchKeywords);
        }
    }
}
