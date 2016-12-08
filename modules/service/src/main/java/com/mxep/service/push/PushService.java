package com.mxep.service.push;


import com.mxep.core.utils.DateUtils;
import com.mxep.model.repositories.order.OrderDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 推送和短信相关的实现类
 */
@Service
@Transactional
public class PushService {

    private final Logger logger = LoggerFactory.getLogger(PushService.class);


    @Resource
    private OrderDao orderDao;


    /**
     * 计算派送时间
     *
     * @param deliveryDate
     * @param deliverySection
     * @param hours
     * @return
     */
    public String getDeliveryDate(Date deliveryDate, String deliverySection, int hours) {

        String result = "";
        if (StringUtils.isNotBlank(deliverySection)) {
            String startTime = StringUtils.split(deliverySection, "-")[0];
            String deliveryTime = DateUtils.date2Str(deliveryDate, "yyyy-MM-dd");

            Date date = DateUtils.str2Date(deliveryTime + " " + startTime + ":00", null);
            result = DateUtils.date2Str(DateUtils.dateAddHour(date, hours), DateUtils.DEFAULT_FORMAT);
        }
        return result;
    }


    public void validateCode(String account, String code) {

    }


}
