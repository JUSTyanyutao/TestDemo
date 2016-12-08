package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.CarBrand;
import com.mxep.model.goods.Category;
import com.mxep.model.repositories.goods.CarBrandDao;
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
 *  车的品牌
 */
@Service
@Transactional
public class CarBrandService extends BaseService<CarBrand,Integer> {

    private CarBrandDao carBrandDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<CarBrand, Integer> baseDao) {
        this.baseDao = baseDao;
        carBrandDao = (CarBrandDao) baseDao;
    }


    /**
     *  查找 所有的 可用  品牌
     * @return
     */
    public List<CarBrand> findCarBrand()
    {
        return  carBrandDao.findCarBrand();
    }




    /**
     * 删除 车的 品牌
     */
    @Transactional
    public JsonMap deleteCarBrand(Integer[] ids) {
        CarBrand carBrand;
        for (Integer id : ids) {
            carBrand = carBrandDao.findOne(id);
            if (carBrand == null) {
                continue;
            }
            if(carBrand.getCarSeriesList().size() != 0)
            {
                return new JsonMap(1,"该车牌下有子类，无法删除!");
            }
            carBrand.setFlag( (byte)-1);
            carBrandDao.save(carBrand);
        }
        return new JsonMap(0,"删除成功");
    }

    /**
     * 保存  车的 品牌
     */
    @Transactional
    public CarBrand save(CarBrand carBrand) {
        carBrandDao.save(carBrand);
        return carBrand;
    }

    /**
	 * 启用 车 品牌
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		CarBrand carBrand;
		for (Integer id : ids) {
			carBrand = carBrandDao.findOne(id);
			if (carBrand == null) {
				continue;
			}
			carBrand.setStatus( (byte) 1);
			carBrandDao.save(carBrand);
		}
        return new JsonMap(0,"操作成功");
	}

	/**
	 * 禁用  车 品牌
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
        CarBrand carBrand;
		for (Integer id : ids) {
            carBrand = carBrandDao.findOne(id);
			if (carBrand == null) {
				continue;
			}
            if(carBrand.getCarSeriesList().size() != 0)
            {
                    return new JsonMap(1,"该品牌下有子分类，无法禁用！");
            }
            carBrand.setStatus( (byte) 0);
			carBrandDao.save(carBrand);
		}
        return new JsonMap(0,"操作成功");
	}

}
