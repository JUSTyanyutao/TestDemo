package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.goods.ServicePrice;
import com.mxep.model.goods.ServicePrice;
import com.mxep.model.repositories.goods.ServicePriceDao;
import com.mxep.model.repositories.goods.ServicePriceDao;
import com.mxep.service.CommonService;
import com.mxep.web.web.JsonMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by yyt on 2016/9/6.
 *   服务价格
 */
@Service
@Transactional
public class ServicePriceService extends BaseService<ServicePrice,Integer> {

    private ServicePriceDao servicePriceDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<ServicePrice, Integer> baseDao) {
        this.baseDao = baseDao;
        servicePriceDao = (ServicePriceDao) baseDao;
    }



    /**
     * 删除  服务 价格
     */
    @Transactional
    public JsonMap deleteServicePrice(Integer[] ids) {
        ServicePrice servicePrice;
        for (Integer id : ids) {
            servicePrice = servicePriceDao.findOne(id);
            if (servicePrice == null) {
                continue;
            }
            servicePrice.setFlag( (byte)-1);
            servicePriceDao.save(servicePrice);
        }
        return new JsonMap(0,"删除成功");
    }

    /**
     * 保存  服务价格
     */
    @Transactional
    public ServicePrice save(ServicePrice servicePrice) {
        servicePriceDao.save(servicePrice);
        return servicePrice;
    }

    /**
	 * 启用 服务价格
	 */
	@Transactional
	public JsonMap enable(Integer[] ids) {
		ServicePrice servicePrice;
		for (Integer id : ids) {
			servicePrice = servicePriceDao.findOne(id);
			if (servicePrice == null) {
				continue;
			}
			servicePrice.setStatus( (byte) 1);
			servicePriceDao.save(servicePrice);
		}
        return new JsonMap(0,"操作成功");
	}

	/**
	 * 禁用  服务价格
	 */
	@Transactional
	public JsonMap disable(Integer[] ids) {
        ServicePrice servicePrice;
		for (Integer id : ids) {
            servicePrice = servicePriceDao.findOne(id);
			if (servicePrice == null) {
				continue;
			}
            servicePrice.setStatus( (byte) 0);
			servicePriceDao.save(servicePrice);
		}
        return new JsonMap(0,"操作成功");
	}

}
