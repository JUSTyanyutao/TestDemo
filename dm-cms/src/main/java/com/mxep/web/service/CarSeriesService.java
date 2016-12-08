package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.CarSeries;
import com.mxep.model.goods.CarSeries;
import com.mxep.model.repositories.goods.CarSeriesDao;
import com.mxep.model.repositories.goods.CarSeriesDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yyt on 2016/9/6.
 *  车的 系列
 */
@Service
@Transactional
public class CarSeriesService extends BaseService<CarSeries,Integer> {

    private CarSeriesDao carSeriesDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<CarSeries, Integer> baseDao) {
        this.baseDao = baseDao;
        carSeriesDao = (CarSeriesDao) baseDao;
    }



    /**
     * 删除 车的 系列
     */
    @Transactional
    public JsonMap deleteCarSeries(Integer[] ids) {
        CarSeries carSeries;
        for (Integer id : ids) {
            carSeries = carSeriesDao.findOne(id);
            if (carSeries == null) {
                continue;
            }
            if(carSeries.getCarModelList().size() != 0)
            {
                return new JsonMap(1,"该车牌下有子类，无法删除!");
            }
            carSeries.setFlag( (byte)-1);
            carSeriesDao.save(carSeries);
        }
        return new JsonMap(0,"删除成功");
    }

    /**
     * 保存  车的 系列
     */
    @Transactional
    public CarSeries save(CarSeries carSeries) {
        carSeriesDao.save(carSeries);
        return carSeries;
    }

    /**
	 * 启用 车 系列
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		CarSeries carSeries;
		for (Integer id : ids) {
			carSeries = carSeriesDao.findOne(id);
			if (carSeries == null) {
				continue;
			}
			carSeries.setStatus( (byte) 1);
			carSeriesDao.save(carSeries);
		}
        return new JsonMap(0,"操作成功");
	}

	/**
	 * 禁用  车  系列
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
        CarSeries carSeries;
		for (Integer id : ids) {
            carSeries = carSeriesDao.findOne(id);
			if (carSeries == null) {
				continue;
			}
            if(carSeries.getCarModelList().size() != 0)
            {
                    return new JsonMap(1,"该品牌下有子分类，无法禁用！");
            }
            carSeries.setStatus( (byte) 0);
			carSeriesDao.save(carSeries);
		}
        return new JsonMap(0,"操作成功");
	}

}
