package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.member.MemberAddress;
import com.mxep.model.member.MemberCar;
import com.mxep.model.repositories.member.MemberAddressDao;
import com.mxep.model.repositories.member.MemberCarDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service - 会员地址
 *
 * @author xingkong1221
 * @since 2016-04-26
 */
@Service
public class MemberCarService extends BaseService<MemberCar, Integer> {

    private MemberCarDao memberCarDao;

    @Override
    @Resource
    public void setBaseDao(BaseDao<MemberCar, Integer> baseDao) {
        this.baseDao = baseDao;
        memberCarDao = (MemberCarDao) baseDao;
    }


}
