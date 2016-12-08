package com.mxep.service.cache;

import com.mxep.model.RedisKeys;
import org.springframework.stereotype.Service;

/**
 * Cache - 注册
 *
 * @author xingkong1221
 * @since 2016-09-09
 */
@Service
public class RegisterTokenCache extends BaseCache {

    /**
     * 注册验证码前缀
     */
    private String RegisterCaptchaPrefix = "captcha_register_";

    /**
     * 注册凭证前缀
     */
    private String RegisterTokenPrefix = "register_token_";

    /**
     * 保存注册手机号码
     *
     * @param registerToken 注册凭证
     * @param mobile 手机号码
     */
    public void pushRegisterMobile(String registerToken, String mobile) {
        redisService.setEntity(RegisterTokenPrefix + registerToken, mobile, RedisKeys.LiveTime.MINUTES_10.time);
    }

    /**
     * 获取注册手机号码
     *
     * @param registerToken 注册凭证
     * @return 注册手机号码
     */
    public String getRegisterMobile(String registerToken) {
        return redisService.getEntity(RegisterTokenPrefix + registerToken);
    }

    /**
     * 保存注册验证码
     *
     * @param registerToken 注册凭证
     * @param code 验证码
     */
    public void pushRegisterCode(String registerToken, String code) {
        redisService.setEntity(RegisterCaptchaPrefix + registerToken, code, RedisKeys.LiveTime.MINUTES_10.time);
    }

    /**
     * 获取注册验证码
     *
     * @param registerToken 注册凭证
     * @return 验证码
     */
    public String getRegisterCode(String registerToken) {
        return redisService.getEntity(RegisterCaptchaPrefix + registerToken);
    }

    /**
     * 删除注册手机号码
     *
     * @param registerToken 注册凭证
     */
    public void deleteRegisterMobile(String registerToken) {
        redisService.del(RegisterTokenPrefix + registerToken);
    }

    /**
     * 删除注册验证码
     *
     * @param registerToken 注册凭证
     */
    public void deleteRegisterCode(String registerToken) {
        redisService.del(RegisterCaptchaPrefix + registerToken);
    }

//    /**
//     * 验证注册验证码
//     *
//     * @param registerToken 注册凭证
//     * @param code 验证码
//     * @return 验证码是否正确
//     */
//    public boolean verifyRegisterCode(String registerToken, String code) {
//        // 校验注册验证码是否正确
//        boolean ret = StringUtils.isNotEmpty(code) &&
//                        code.equals();
//
//        // 若校验成功，则删除redis中的code
//        if (ret) {
//            redisService.del(RegisterCaptchaPrefix + registerToken);
//        }
//
//        return ret;
//    }

}
