package com.mxep.service.cache;

import com.mxep.model.RedisKeys;
import org.springframework.stereotype.Service;

/**
 * Cache - 绑定手机号码
 *
 * @author xingkong1221
 * @since 2016-09-19
 */
@Service
public class BindMobileCache extends BaseCache {

    /**
     * 绑定手机验证码前缀
     */
    private String BindMobileCaptchaPrefix = "captcha_bind_mobile_";

    /**
     * 绑定手机号码前缀
     */
    private String BindMobilePrefix = "bind_mobile_";

    /**
     * 缓存验证码
     *
     * @param memberId 会员编号
     * @param captcha 验证码
     */
    public void pushCaptcha(Integer memberId, String captcha) {
        redisService.setEntity(BindMobileCaptchaPrefix + memberId, captcha, RedisKeys.LiveTime.MINUTES_10.time);
    }

    /**
     * 获取缓存的验证码
     *
     * @param memberId 会员编号
     * @return 验证码
     */
    public String getCaptcha(Integer memberId) {
        return redisService.getEntity(BindMobileCaptchaPrefix + memberId);
    }

    /**
     * 缓存手机号码
     *
     * @param memberId 会员编号
     * @param mobile 手机号码
     */
    public void pushMobile(Integer memberId, String mobile) {
        redisService.setEntity(BindMobilePrefix + memberId, mobile, RedisKeys.LiveTime.MINUTES_10.time);
    }

    /**
     * 获取缓存的手机号码
     *
     * @param memberId 会员编号
     * @return 手机号码
     */
    public String getMobile(Integer memberId) {
        return redisService.getEntity(BindMobilePrefix + memberId);
    }

    /**
     * 删除缓存的验证码
     *
     * @param memberId 会员编号
     */
    public void deleteCapctah(Integer memberId) {
        redisService.del(BindMobileCaptchaPrefix + memberId);
    }

    /**
     * 删除缓存的手机号码
     *
     * @param memberId 会员编号
     */
    public void deleteMobile(Integer memberId) {
        redisService.del(BindMobilePrefix + memberId);
    }

}
