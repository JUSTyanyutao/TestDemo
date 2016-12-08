package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.log.LogPayment;
import com.mxep.model.repositories.member.LogPaymentDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by lq on 2016/9/10.
 *  支付管理
 */

@Service
public class LogPaymentService extends BaseService<LogPayment,Integer> {

    private LogPaymentDao logPaymentDao;
    
    @Resource
    @Override
    public void setBaseDao(BaseDao<LogPayment, Integer> baseDao) {
        this.baseDao = baseDao;
        logPaymentDao =( LogPaymentDao) baseDao;
    }


    /**
     * 查询 支付记录
     */
    @Transactional(readOnly = true)
    public LogPayment findLogPayment(Integer id) {
        if (id == null) {
            return null;
        }
        return logPaymentDao.findOne(id);
    }


    /**
     * 删除支付记录
     *
     * @param ids
     *            编号数组
     */
    @Override
    @Transactional
    public void delete(Integer[] ids) {
        LogPayment logPayment;
        for (Integer id : ids) {
            logPayment = findLogPayment(id);
            if (logPayment == null) {
                continue;
            }
            logPaymentDao.delete(logPayment);
        }
    }

}
