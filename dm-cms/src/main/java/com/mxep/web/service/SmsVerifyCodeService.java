package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.log.Sms;
import com.mxep.model.log.SmsVerifyCode;
import com.mxep.model.repositories.log.SmsDao;
import com.mxep.model.repositories.log.SmsVerifyCodeDao;
import com.mxep.service.CommonService;
import com.mxep.service.push.JPushService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * Created by lq on 2016/9/10.
 * 短信管理 service
 */

@Service
public class SmsVerifyCodeService extends BaseService<SmsVerifyCode,Integer> {

    private SmsVerifyCodeDao smsVerifyCodeDao;

    @Resource
    private CommonService commonService;

    @Resource
    private JPushService jPushService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<SmsVerifyCode, Integer> baseDao) {
        this.baseDao = baseDao;
        smsVerifyCodeDao =(SmsVerifyCodeDao) baseDao;
    }


    /**
     * 查询短信
     *
     *
     */
    @Transactional(readOnly = true)
    public SmsVerifyCode findSmsVerifyCode(Integer id) {
        if (id == null) {
            return null;
        }
        return smsVerifyCodeDao.findOne(id);
    }


    /**
     * 删除短信
     *
     * @param ids
     *            编号数组
     */
    @Override
    @Transactional
    public void delete(Integer[] ids) {
        SmsVerifyCode smsVerifyCode;
        for (Integer id : ids) {
            smsVerifyCode = findSmsVerifyCode(id);
            if (smsVerifyCode == null) {
                continue;
            }
            smsVerifyCodeDao.delete(smsVerifyCode);
        }
    }

//    /**
//     * 保存短信
//     *
//     */
//    @Override
//    @Transactional
//    public SmsVerifyCode save(SmsVerifyCode smsVerifyCode) {
//        // 从数据库中查询短信
//        Timestamp currentTime = commonService.getCurrentTime();
//        SmsVerifyCode entity = findSmsVerifyCode(smsVerifyCode.getId());
//        if (entity == null) {
//            // 新建短信
//            entity = new SmsVerifyCode();
//            entity.setCreateTime(currentTime);
//        }
//        entity.set
//        entity.setCreateTime(createTime);
//
//
////        /**
////         * 推送全局消息
////         */
////        Map extraMap = Maps.newHashMap();
////        JPushService
//
//        return smsDao.save(entity);
//    }


}
