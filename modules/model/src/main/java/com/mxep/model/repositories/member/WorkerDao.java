package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.member.Shop;
import com.mxep.model.member.Worker;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Dao - 技师
 *
 *
 */
public interface WorkerDao extends BaseDao<Worker, Integer> {

    @Query("select a from Worker a where a.flag = 1")
    List<Worker> findAll();

}
