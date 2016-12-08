package com.mxep.web.service;

import com.mxep.model.BaseDao;
import com.mxep.model.common.Faq;
import com.mxep.model.repositories.common.FaqDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.exception.WebRequestException;
import com.mxep.web.common.persistence.DynamicSpecifications;
import com.mxep.web.common.persistence.SearchFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;


/**
 * Service - Faq分类
 */
@Service
public class FaqService extends BaseService<Faq, Integer> {

	private FaqDao faqDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Faq, Integer> baseDao) {
		this.baseDao = baseDao;
		faqDao =( FaqDao)baseDao;
	}
	/**
	 * 查询QA信息
	 * 
	 * @param id
	 *            QA编号
	 * @return QA信息
	 */
	@Transactional(readOnly = true)
	public Faq findFaq(Integer id) {
		if (id == null) {
			return null;
		}
		return faqDao.findOne(id);
	}

	/**
	 * 保存QA
	 */
	@Override
	@Transactional
	public Faq save(Faq faq) {
		// 从数据库总查询QA信息
		Timestamp currentTime = commonService.getCurrentTime();
		Faq entity = findFaq(faq.getId());
		if (entity == null) {
			// 新建QA
			entity = faq;
			entity.setCreateTime(currentTime);
			entity.setUpdateTime(currentTime);
		} else {
			// 保存QA
			Date createTime = entity.getCreateTime();
			entity = faq;
			entity.setCreateTime(createTime);
			entity.setUpdateTime(currentTime);
		}

		return faqDao.save(entity);
	}

	/**
	 * 删除QA
	 * 
	 * @param ids
	 *            编号数组
	 */
	@Override
	@Transactional
	public void delete(Integer[] ids) {
		Faq faq;
		for (Integer id : ids) {
			faq = findFaq(id);
			if (faq == null) {
				continue;
			}
			else{
				// 查询当前分类下所有QA记录
//		        List<Faq> questionAnswerList = this.findFaqByPid(id);
//				if(questionAnswerList != null && questionAnswerList.size() > 0 ){
//					 throw new WebRequestException("《" + faq.getQuestion() +
//					 "》分类存在子问题，请先删除子问题");
//				}

			}
			faq.setFlag((byte) -1);
			faqDao.save(faq);
		}
	}

    
    /**
     * 查询QA分类
     *
     * @return QA分类
     */
    @Transactional(readOnly = true)
    public List<Faq> findQAParent() {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pr = new PageRequest(0, Integer.MAX_VALUE, sort);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("EQ_pid", String.valueOf(0));
        Map<String, SearchFilter> filters = SearchFilter.parse(params);
        Specification<Faq> spec = DynamicSpecifications.bySearchFilter(filters.values(), Faq.class);
        return faqDao.findAll(spec, pr).getContent();
    }
    
     public List<Faq> findAllParent(){
		return Arrays.asList();
	}
}
