package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.member.Member;
import com.mxep.model.member.Worker;
import com.mxep.model.repositories.member.WorkerDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.exception.WebRequestException;
import com.mxep.web.web.JsonMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;


/**
 * Service - 技师管理
 */
@Service
@Transactional
public class WorkerService extends BaseService<Worker, Integer> {

	private final Logger logger = LoggerFactory.getLogger(WorkerService.class);

	private WorkerDao workerDao;

	@Resource
	private MemberService memberService;

	@Resource
	private CommonService commonService;



	@Resource
	@Override
	public void setBaseDao(BaseDao<Worker, Integer> baseDao) {
		this.baseDao = baseDao;
		workerDao = (WorkerDao) baseDao;
	}


	/**
	 * 查找所有技师
	 * @return
     */
	public List<Worker> findAllWorker()
	{
		return workerDao.findAll();
	}


	/**
	 * 查找  技师
	 * @param id
	 * @return
     */
	public Worker getWorker(Integer id)
	{
		if(null == id)
		{
			return null;
		}
		return workerDao.findOne(id);
	}

	/**
	 * 删除技师
	 */
	@Transactional
	public JsonMap deleteWorker(Integer[] ids) {
		Worker worker;
		for (Integer id : ids) {
			worker = workerDao.findOne(id);
			if (worker == null) {
				continue;
			}
			worker.setFlag((byte) -1);
			workerDao.save(worker);
		}
		return new JsonMap(0,"删除成功");
	}

	/**
	 * 审核技师
	 */
	@Transactional
	public JsonMap audit(Integer id,byte status,String remark,String categoryId) {
		Worker worker = workerDao.findOne(id);
		worker.setApplyStatus(status);
		worker.setRemark(remark);
		worker.setCategoryId(categoryId);
		workerDao.save(worker);
		return new JsonMap(0, "审核成功");
	}

//	/**
//	 * 禁用技师
//	 */
//	@Transactional
//	public JsonMap disable(Integer[] ids) {
//		Worker worker;
//		for (Integer id : ids) {
//			worker = workerDao.findOne(id);
//			if (worker == null) {
//				continue;
//			}
//			worker.setStatus( (byte)0);
//			save(worker);
//		}
//		return new JsonMap(0, "禁用成功");
//	}


	/**
	 * 保存 技师
	 */
	@Transactional
	public Worker save(Worker worker) {
		Worker entity = getWorker(worker.getId());
		if(null == entity)
		{
			throw new WebRequestException("不存在的技师!");
		}
		entity.setName(worker.getName());
		entity.setIdcard(worker.getIdcard());
		entity.setBankCardNo(worker.getBankCardNo());
		entity.setBankName(worker.getBankName());
		entity.setWorkAge(worker.getWorkAge());
		entity.setWechat(worker.getWechat());
		entity.setRemark(worker.getRemark());
		entity.setSex(worker.getSex());
		entity.setWorkingStatus(worker.getWorkingStatus());
		entity.setHasDrivingLicense(worker.getHasDrivingLicense());
		entity.setCanDrive(worker.getCanDrive());
		entity.setHasCar(worker.getHasCar());
		Timestamp currentTime = commonService.getCurrentTime();
		entity.setUpdateTime(currentTime);
		Worker temp = workerDao.save(entity);
		return temp;
	}

	/**
	 * 新加 技师
	 */
	@Transactional
	public Worker saveWorker(Worker worker, Member member) {

		worker.setName(member.getProfile().getRealName());
		worker.setSex(member.getProfile().getGender());
		Member entity = memberService.getMemberByMobile(member.getMobile());
		if(entity != null)
		{
			member.setId(entity.getId());
		}
		memberService.save(member);
		Timestamp currentTime = commonService.getCurrentTime();
		worker.setCreateTime(currentTime);
		worker.setFlag( (byte)1);
		worker.setUpdateTime(currentTime);
		worker.setMemberId(member.getId());
		worker.setApplyStatus( (byte)1);
		worker.setType( (byte)1);
		return workerDao.save(worker);
	}




}
