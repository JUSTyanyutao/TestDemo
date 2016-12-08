package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.CarPart;
import com.mxep.model.goods.Category;
import com.mxep.model.repositories.goods.CarPartDao;
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
 * Created by Administrator on 2016/11/1 0001.
 */
@Service
public class CarPartService extends BaseService<CarPart,Integer> {

    private CarPartDao carPartDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<CarPart, Integer> baseDao) {
        this.baseDao = baseDao;
        carPartDao = (CarPartDao) baseDao;
    }


    /**
     * 查找所有 可能 的部位
     * @return
     */
    public List<CarPart> findAll()
    {
        return carPartDao.findAll();
    }



    /**
     * 删除部位
     */
    @Transactional
    public JsonMap deleteCarPart(Integer[] ids) {
        CarPart carPart;
        for (Integer id : ids) {
            carPart = carPartDao.findOne(id);
            if (carPart == null) {
                continue;
            }
            carPartDao.delete(carPart);
        }
        return new JsonMap(0,"删除成功");
    }

    /**
     * 保存 部位
     */
    @Transactional
    public CarPart save(CarPart carPart) {
        carPartDao.save(carPart);
        return carPart;
    }

    /**
     * 启用 部位
     */
    @Transactional
    public JsonMap enable(Integer[] ids) {
        CarPart carPart;
        for (Integer id : ids) {
            carPart = carPartDao.findOne(id);
            if (carPart == null) {
                continue;
            }
            carPart.setStatus( (byte) 1);
            save(carPart);
        }
        return new JsonMap(0,"操作成功");
    }

    /**
     * 禁用 部位
     */
    @Transactional
    public JsonMap disable(Integer[] ids) {
        CarPart carPart;
        for (Integer id : ids) {
            carPart = carPartDao.findOne(id);
            if (carPart == null) {
                continue;
            }
            carPart.setStatus( (byte) 0);
            save(carPart);
        }
        return new JsonMap(0,"操作成功");
    }

}
