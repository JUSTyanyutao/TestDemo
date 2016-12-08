package com.mxep.model.repositories.member;


import com.mxep.model.BaseDao;
import com.mxep.model.log.LogMemberBalance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

/**
 * Dao - 账户余额日志
 */
public interface LogMemberBalanceDao extends BaseDao<LogMemberBalance, Integer> {

	/**
	 * 根据会员编号和类型查询账户日志
	 *
	 * @param memberId 会员编号
	 * @param type 类型
	 * @param pageable 分页
	 * @return 分页信息
	 */
	@Query("select a from LogMemberBalance a where a.memberId = ?1 and a.type = ?2 and a.flag = 1")
	Page<LogMemberBalance> findByMemberIdAndType(Integer memberId, byte type, Pageable pageable);
}
