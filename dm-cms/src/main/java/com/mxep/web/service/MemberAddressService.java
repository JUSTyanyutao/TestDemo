package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.member.MemberAddress;
import com.mxep.model.repositories.member.MemberAddressDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service - 会员地址
 *
 * @author xingkong1221
 * @since 2016-04-26
 */
@Service
public class MemberAddressService extends BaseService<MemberAddress, Integer> {

    private MemberAddressDao memberAddressDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<MemberAddress, Integer> baseDao) {
        this.baseDao = baseDao;
        memberAddressDao = (MemberAddressDao) baseDao;
    }


}
