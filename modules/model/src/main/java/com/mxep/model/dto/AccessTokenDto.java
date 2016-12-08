package com.mxep.model.dto;

import com.mxep.model.BaseView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;

/**
 * dto - 接口调用凭证
 *
 * @author ranfi
 * @since 2016-7-8
 */
public class AccessTokenDto implements Serializable {

    private static final long serialVersionUID = 7616904335895625128L;

    /**
     * 接口调用凭证
     */
    private String accessToken;

    /**
     * 置换凭证
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 实例化一个接口调用凭证dto
     */
    public AccessTokenDto() {
    }

    /**
     * 实例化一个接口调用凭证dto
     *
     * @param accessToken 接口调用凭证
     * @param refreshToken 置换凭证
     * @param expireTime 有效时间
     */
    public AccessTokenDto(String accessToken, String refreshToken, Long expireTime) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
    }

    /**
     * 获取接口调用凭证
     *
     * @return 接口调用凭证
     */
    @JsonView(BaseView.class)
    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * 设置接口调用凭证
     *
     * @param accessToken 接口调用凭证
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取置换凭证
     *
     * @return 置换凭证
     */
    @JsonView(BaseView.class)
    @JsonProperty("refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * 设置置换凭证
     *
     * @param refreshToken 置换凭证
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * 获取更新时间
     *
     * @return 更新时间
     */
    @JsonView(BaseView.class)
    @JsonProperty("expire_time")
    public Long getExpireTime() {
        return expireTime;
    }

    /**
     * 设置更新时间
     *
     * @param expireTime 更新时间
     */
    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
