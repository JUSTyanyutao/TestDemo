package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.log.SmsTemplate;
import com.mxep.model.log.SmsVerifyCode;
import com.mxep.model.repositories.log.SmsTemplateDao;
import com.mxep.model.repositories.log.SmsVerifyCodeDao;
import com.mxep.service.CommonService;
import com.mxep.service.push.JPushService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lq on 2016/9/10.
 * 短信管理 service
 */

@Service
public class SmsTemplateService extends BaseService<SmsTemplate,Integer> {

    private SmsTemplateDao smsTemplateDao;

    @Resource
    private CommonService commonService;

    @Resource
    private JPushService jPushService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<SmsTemplate, Integer> baseDao) {
        this.baseDao = baseDao;
        smsTemplateDao =(SmsTemplateDao) baseDao;
    }


    /**
     * 查找所有 模版
     */
    @Transactional(readOnly = true)
    public List<SmsTemplate> findAll() {
        return smsTemplateDao.findAll();
    }


    /**
     * 查询短信
     */
    @Transactional(readOnly = true)
    public SmsTemplate findSmsTemplate(Integer id) {
        if (id == null) {
            return null;
        }
        return smsTemplateDao.findOne(id);
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
        SmsTemplate smsTemplate;
        for (Integer id : ids) {
            smsTemplate = findSmsTemplate(id);
            if (smsTemplate == null) {
                continue;
            }
            smsTemplateDao.delete(smsTemplate);
        }
    }

    /**
     * 保存短信
     *
     */
    @Override
    @Transactional
    public SmsTemplate save(SmsTemplate smsTemplate) {
        // 从数据库中查询短信
        Timestamp currentTime = commonService.getCurrentTime();
        SmsTemplate entity =findSmsTemplate(smsTemplate.getId());
        if (entity == null) {
            // 新建短信
            entity = new SmsTemplate();
            entity.setCreateTime(currentTime);
        }
        entity.setCode(smsTemplate.getCode());
        entity.setContent(smsTemplate.getContent());
        entity.setDescription(smsTemplate.getDescription());
        smsTemplateDao.save(entity);

//        /**
//         * 推送全局消息
//         */
//        Map extraMap = Maps.newHashMap();
//        JPushService

        return entity;
    }


}
