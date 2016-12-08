package com.mxep.web.service;


import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mxep.model.BaseDao;
import com.mxep.model.base.Label;
import com.mxep.model.repositories.base.LabelDao;
import com.mxep.service.CommonService;
import com.mxep.web.common.bo.Option;
import com.google.common.collect.Lists;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * Service - 标签
 */
@Service
public class LabelService extends BaseService<Label, Integer> {

	private LabelDao labelDao;

	@Resource
	private CommonService commonService;

	@Resource
	@Override
	public void setBaseDao(BaseDao<Label, Integer> baseDao) {
		this.baseDao = baseDao;
		labelDao =( LabelDao)baseDao;
	}
	/**
	 * 查询标签信息
	 * 
	 * @param id
	 *            标签编号
	 * @return 标签信息
	 */
	@Transactional(readOnly = true)
	public Label findLabel(Integer id) {
		if (id == null) {
			return null;
		}
		return labelDao.findOne(id);
	}

	/**
	 * 保存标签
	 * 
	 * @param
	 * @return 标签
	 */
	@Override
	@Transactional
	public Label save(Label label) {
		// 从数据库总查询标签信息
		Label entity = findLabel(label.getId());
		Timestamp currentTime = commonService.getCurrentTime();
		if (entity == null) {
			// 新建标签
			entity = label;
			entity.setCreateTime(currentTime);
		} else {
			// 保存标签
			Date createTime = label.getCreateTime();
			entity = label;
			entity.setCreateTime(createTime);
			entity.setUpdateTime(currentTime);
		}
		return labelDao.save(entity);
	}

	/**
	 * 删除标签
	 * 
	 * @param ids
	 *            编号数组
	 */
	@Override
	@Transactional
	public void delete(Integer[] ids) {
		Label label;
		for (Integer id : ids) {
			label = findLabel(id);
			if (label == null) {
				continue;
			}
			label.setFlag( (byte)-1);
			labelDao.save(label);
		}
	}

	
//	/**
//	 * 获取标签类型列表
//	 * @param type
//	 * @return
//	 */
//	public List<Option> getLabelTypeList(String type) {
//		List<Option> options = Lists.newArrayList();
//		options.add(new Option(LabelType.GoodsKeepMode.value,
//				LabelType.GoodsKeepMode.name, false));
//		options.add(new Option(LabelType.GoodsSupportService.value,
//				LabelType.GoodsSupportService.name, false));
//		options.add(new Option(LabelType.GoodsActivity.value,
//				LabelType.GoodsActivity.name, false));
//		options.add(new Option(LabelType.CommentLabel.value,
//				LabelType.CommentLabel.name, false));
//		for (Option option : options) {
//			if (StringUtils.isNotBlank(type)
//					&& Integer.parseInt(type) == Integer.parseInt(option
//							.getValue().toString())) {
//				option.setSelected("selected");
//				break;
//			}
//		}
//		return options;
//	}
	
	/**
	 * 获取所有的标签
	 * @param labelId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Option> getAllLabelList(String labelId) {
		List<Option> options = Lists.newArrayList();
		List<Label> list = labelDao.findAllLabelList();
		for (Label label : list) {
			options.add(new Option(label.getId(), label.getName(), false));
		}
		for (Option option : options) {
			if (StringUtils.isNotBlank(labelId)
					&& Integer.parseInt(labelId) == Integer.parseInt(option.getValue().toString())) {
				option.setSelected("selected");
				break;
			}
		}
		return options;
	}
	
	/**
	 * 获取某种类型的全部标签
	 * @param labelId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Label> getLabelListByType(Integer type) {
		
		return labelDao.findLabelListByType(type);
	}
}
