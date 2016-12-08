package com.mxep.model.base;

import javax.persistence.*;

import com.mxep.model.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Entity - 地区
 */
@Entity
@Table(name = "sys_area")
public class Area extends IdEntity<Integer> {

	private static final long serialVersionUID = 7609688778510687374L;

	/** 名称 */
	private String name;

	/** 英文名称 */
	private String enName;

	/** 拼音 */
	private String pingyin;

	/** 经度 */
	private Double lng = 0.0000000000;// 中心点经度

	/** 纬度 */
	private Double lat = 0.0000000000;// 中心点纬度

	/** 介绍 */
	private String intro;

	/** 父地区编号 */
	private Integer pid = 0;

	/** 层级 */
	private Integer level = 0;// 0: 国家，1: 省份，2: 城市 3：地区

	private Integer code = 0;

	private Integer status = 0;

	private Integer hot = 0;

	/** 上级地区 */
	private Area parent;

	public Area() {
	}

	public Area(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getPingyin() {
		return pingyin;
	}

	public void setPingyin(String pingyin) {
		this.pingyin = pingyin;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getHot() {
		return hot;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取上级地区
	 * 
	 * @return 上级地区
	 */
	@JsonIgnore
	@NotFound(action = NotFoundAction.IGNORE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid", insertable = false, updatable = false)
	public Area getParent() {
		return parent;
	}

	/***
	 * 设置上级地区
	 * 
	 * @param parent
	 *            上级地区
	 */
	public void setParent(Area parent) {
		this.parent = parent;
	}
}