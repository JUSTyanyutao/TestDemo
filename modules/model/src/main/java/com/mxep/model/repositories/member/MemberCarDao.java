package com.mxep.model.repositories.member;

import com.mxep.model.BaseDao;
import com.mxep.model.member.Member;
import com.mxep.model.member.MemberCar;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Dao - 会员 车
 */
public interface MemberCarDao extends BaseDao<MemberCar, Integer> {

}
