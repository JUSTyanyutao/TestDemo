package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.SignIn;

import com.mxep.model.repositories.member.SignInDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

/**
 * Created by lq on 2016/9/13.
 * service 签到
 */

@Service
public class SignInService extends BaseService<SignIn,Integer> {

    private SignInDao signInDao;


    @Resource
    @Override
    public void setBaseDao(BaseDao<SignIn, Integer> baseDao) {

        this.baseDao = baseDao;
        signInDao =( SignInDao)baseDao;
    }

    /**
     * 查询签到
     *
     *
     */
    @Transactional(readOnly = true)
    public SignIn findSignIn(Integer id) {
        if (id == null) {
            return null;
        }
        return signInDao.findOne(id);
    }

    /**
     * 删除签到
     *
     * @param ids
     *            编号数组
     */
    @Override
    @Transactional
    public void delete(Integer[] ids) {
        SignIn signIn;
        for (Integer id : ids) {
            signIn = findSignIn(id);
            if (signIn == null) {
                continue;
            }
            signIn.setFlag((byte)-1);
            signInDao.save(signIn);
        }
    }
}
