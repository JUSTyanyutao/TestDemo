package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.CarModel;
import com.mxep.model.goods.CarModel;
import com.mxep.model.repositories.goods.CarModelDao;
import com.mxep.model.repositories.goods.CarModelDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yyt on 2016/9/6.
 *  车的 型号
 */
@Service
@Transactional
public class CarModelService extends BaseService<CarModel,Integer> {

    private CarModelDao carModelDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<CarModel, Integer> baseDao) {
        this.baseDao = baseDao;
        carModelDao = (CarModelDao) baseDao;
    }



    /**
     * 删除 车的 型号
     */
    @Transactional
    public JsonMap deleteCarModel(Integer[] ids) {
        CarModel carModel;
        for (Integer id : ids) {
            carModel = carModelDao.findOne(id);
            if (carModel == null) {
                continue;
            }
            carModel.setFlag( (byte)-1);
            carModelDao.save(carModel);
        }
        return new JsonMap(0,"删除成功");
    }

    /**
     * 保存  车的 型号
     */
    @Transactional
    public CarModel save(CarModel carModel) {
        carModelDao.save(carModel);
        return carModel;
    }

    /**
	 * 启用 车 型号
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		CarModel carModel;
		for (Integer id : ids) {
			carModel = carModelDao.findOne(id);
			if (carModel == null) {
				continue;
			}
			carModel.setStatus( (byte) 1);
			carModelDao.save(carModel);
		}
        return new JsonMap(0,"操作成功");
	}

	/**
	 * 禁用  车  型号
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
        CarModel carModel;
		for (Integer id : ids) {
            carModel = carModelDao.findOne(id);
			if (carModel == null) {
				continue;
			}
            carModel.setStatus( (byte) 0);
			carModelDao.save(carModel);
		}
        return new JsonMap(0,"操作成功");
	}

}
