package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.CityTemplate;
import com.mxep.model.common.CityTemplate;
import com.mxep.model.repositories.common.CityTemplateDao;
import com.mxep.model.repositories.common.CityTemplateDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.persistence.DynamicSpecifications;
import com.mxep.web.common.persistence.SearchFilter;
import com.mxep.web.web.JsonMap;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Administrator on 2016/11/7 0007.
 */
@Service
@Transactional
public class CityTemplateService extends BaseService<CityTemplate,Integer>{


    private CityTemplateDao cityTemplateDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<CityTemplate, Integer> baseDao) {
        this.baseDao = baseDao;
        cityTemplateDao =( CityTemplateDao) baseDao;
    }

    @Transactional(readOnly = true)
    public List<CityTemplate> findAll() {
        return cityTemplateDao.findAll();
    }




    /**
     * 查询 模版 信息
     *
     */
    @Transactional(readOnly = true)
    public CityTemplate findCityTemplate(Integer id) {
        if (id == null) {
            return null;
        }
        return cityTemplateDao.findOne(id);
    }

    /**
     * 保存 城市 模版
     */
    @Override
    @Transactional
    public CityTemplate save(CityTemplate cityTemplate) {
        // 从数据库总查询QA信息
        CityTemplate entity = findCityTemplate(cityTemplate.getId());
        if(entity == null)
        {
            entity = new CityTemplate();
        }
        entity.setStatus(cityTemplate.getStatus());
        entity.setCities(cityTemplate.getCities());
        entity.setName(cityTemplate.getName());
        cityTemplateDao.save(entity);
       return entity;
    }

    /**
     * 删除QA
     */
    @Override
    @Transactional
    public void delete(Integer[] ids) {
        CityTemplate cityTemplate;
        for (Integer id : ids) {
            cityTemplate = findCityTemplate(id);
            if (cityTemplate == null) {
                continue;
            }
            cityTemplateDao.delete(cityTemplate);
            cityTemplateDao.save(cityTemplate);
        }
    }

    /**
     *  启用 模版
     */
    @Transactional
    public JsonMap enable(Integer[] ids) {
        CityTemplate cityTemplate;
        for (Integer id : ids) {
            cityTemplate = cityTemplateDao.findOne(id);
            if (cityTemplate == null) {
                continue;
            }
            cityTemplate.setStatus( (byte) 1);
            cityTemplateDao.save(cityTemplate);
        }
        return new JsonMap(0, "启用成功");
    }

    /**
     *  禁用 模版
     */
    @Transactional
    public JsonMap disable(Integer[] ids) {
        CityTemplate cityTemplate;
        for (Integer id : ids) {
            cityTemplate = cityTemplateDao.findOne(id);
            if (cityTemplate == null) {
                continue;
            }
            cityTemplate.setStatus( (byte) 0);
            cityTemplateDao.save(cityTemplate);
        }
        return new JsonMap(0, "禁用成功");
    }


}
