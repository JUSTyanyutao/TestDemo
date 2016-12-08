package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.enums.EnumShareCheck;
import com.mxep.model.repositories.base.ShareDao;
import com.mxep.model.share.Share;
import com.mxep.service.CommonService;

import com.mxep.web.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.WebService;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by lq on 2016/9/12.
 * 晒单  service
 */
@Service
public class ShareService extends BaseService<Share,Integer> {

    private static final Logger logger = LoggerFactory.getLogger(ShareService.class);

    @Resource
    private ShareDao shareDao;

    @Resource
    private CommonService commonService;

    @Resource
    @Override
    public void setBaseDao(BaseDao<Share, Integer> baseDao) {
        this.baseDao = baseDao;
        shareDao =( ShareDao)baseDao;
    }

    /**
     * 查询晒单信息
     *
     * @param id
     *            晒单编号
     * @return 晒单信息
     */
    @Transactional(readOnly = true)
    public Share findShare(Integer id) {
        if (id == null) {
            return null;
        }
        return shareDao.findOne(id);
    }

    /**
     * 保存晒单
     *
     * @param share 晒单
     * @return 晒单
     */
    @Override
    @Transactional
    public Share save(Share share) {
        // 从数据库总查询晒单信息
        Timestamp currentTime = commonService.getCurrentTime();
        Share entity = findShare(share.getId());
        if (entity == null) {
            // 新建晒单
            entity = share;
            entity.setCreateTime(currentTime);
        } else {
            // 保存晒单
            Date createTime = share.getCreateTime();
            entity = share;
            entity.setCreateTime(createTime);
            entity.setUpdateTime(currentTime);
        }
        return shareDao.save(entity);
    }

    /**
     * 删除晒单
     *
     * @param ids
     *            编号数组
     */
    @Override
    @Transactional
    public void delete(Integer[] ids) {
        Share share;
        for (Integer id : ids) {
            share = findShare(id);
            if (share == null) {
                continue;
            }
            share.setFlag((byte)-1);
            shareDao.save(share);
        }
    }



    /**
     * 审核操作
     * @param id
     */
    @Transactional
    public void check(Integer id,byte status,String comment,Integer sort){
        Share share;
        share = findShare(id);
        share.setCheckStatus(status);
        share.setComment(comment);
        share.setSort(sort);
        shareDao.save(share);
    }


}
