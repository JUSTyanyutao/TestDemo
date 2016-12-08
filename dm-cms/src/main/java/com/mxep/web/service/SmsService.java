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
public class SmsService extends BaseService<Sms,Integer> {

    private SmsDao smsDao;

    @Resource
    private CommonService commonService;

    @Resource
    private MemberService memberService;

    @Resource
    private SmsTemplateService smsTemplateService;

    @Resource
    private JPushService jPushService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<Sms, Integer> baseDao) {
        this.baseDao = baseDao;
        smsDao =(SmsDao) baseDao;
    }


    /**
     * 查询短信
     *
     *
     */
    @Transactional(readOnly = true)
    public Sms findLogSms(Integer id) {
        if (id == null) {
            return null;
        }
        return smsDao.findOne(id);
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
        Sms sms;
        for (Integer id : ids) {
            sms = findLogSms(id);
            if (sms == null) {
                continue;
            }
            smsDao.delete(sms);
        }
    }

    /**
     * 保存短信
     *
     */
    @Override
    @Transactional
    public Sms save(Sms sms) {
        // 从数据库中查询短信
        Timestamp currentTime = commonService.getCurrentTime();
        Sms entity = findLogSms(sms.getId());
        if (entity == null) {
            // 新建短信
            entity = new Sms();
            entity.setCreateTime(currentTime);
        }
        entity.setFlag( (byte)1 );
        entity.setMemberId(sms.getMemberId());
        entity.setSmsTemplateId(sms.getSmsTemplateId());
//        entity.setMember(memberService.findMember(sms.getMemberId()));
//        entity.setSmsTemplate(smsTemplateService.findSmsTemplate(sms.getSmsTemplateId()));

//        /**
//         * 推送全局消息
//         */
//        Map extraMap = Maps.newHashMap();
//        JPushService

        return smsDao.save(entity);
    }


}
