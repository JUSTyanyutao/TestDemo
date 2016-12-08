package com.mxep.model.repositories.log;

import com.mxep.model.BaseDao;
import com.mxep.model.log.SmsTemplate;
import com.mxep.model.log.SmsVerifyCode;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 短信记录
 *
 * @author yyt
 * @since 2016-11
 */
public interface SmsTemplateDao extends BaseDao<SmsTemplate, Integer> {

    @Query("select a from SmsTemplate a ")
    List<SmsTemplate> findAll();


}
