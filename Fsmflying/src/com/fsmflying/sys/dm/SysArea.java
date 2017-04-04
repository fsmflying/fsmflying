package com.fsmflying.sys.dm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;




@Entity
@Table(name = "Sys_Areas")
@TableGenerator(name = "tableIdGenerator", 
table = "Sys_IdGenerators", 
pkColumnName = "KeyName", 
valueColumnName = "NextValue", 
pkColumnValue = "sys.area", 
initialValue = 1)
public class SysArea extends AbstractBean {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int mAreaId;
	private String mAreaName;
	private String mAreaNo;
	private String mPAreaId;
	private int mLevelDepth;
	private int mShowOrder;
	private String mMemo;

	
	
	
	public SysArea(int areaId) {
		super();
		mAreaId = areaId;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.earea";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="AreaId",nullable=false)
	public int getAreaId() {
		return mAreaId;
	}

	public void setAreaId(int mAreaId) {
		this.mAreaId = mAreaId;
	}

	@Column(name="AreaName",nullable=false)
	public String getAreaName() {
		return mAreaName;
	}

	public void setAreaName(String areaName) {
		mAreaName = areaName;
	}
	@Column(name="AreaNo")
	public String getAreaNo() {
		return mAreaNo;
	}

	public void setAreaNo(String areaNo) {
		mAreaNo = areaNo;
	}

	@Column(name="AreaName",nullable=false,columnDefinition="int default -1")
	public String getPAreaId() {
		return mPAreaId;
	}

	public void setPAreaId(String pAreaId) {
		mPAreaId = pAreaId;
	}
	@Column(name="LevelDepth",nullable=false,columnDefinition="int default -1")
	public int getLevelDepth() {
		return mLevelDepth;
	}

	public void setLevelDepth(int depth) {
		mLevelDepth = depth;
	}

	@Column(name="ShowOrder",nullable=false,columnDefinition="int default 10000")
	public int getShowOrder() {
		return mShowOrder;
	}

	public void setShowOrder(int showOrder) {
		mShowOrder = showOrder;
	}
	@Column(name="Memo")
	public String getMemo() {
		return mMemo;
	}

	public void setMemo(String memo) {
		mMemo = memo;
	}
	
	@Override
	@Column(name="DbDeleted",nullable=false,columnDefinition="int default 0")
	public int getDbDeleted() {
		return super.getDbDeleted();
	}

	@Override
	@Column(name="DbCreateBy",nullable=false,columnDefinition="int default -1")
	public int getDbCreateBy() {
		return super.getDbCreateBy();
	}

	@Override
	@Column(name="DbCreateTime")
	public Date getDbCreateTime() {
		return super.getDbCreateTime();
	}

	@Override
	@Column(name="DbLastUpdateBy",nullable=false,columnDefinition="int default -1")
	public int getDbLastUpdateBy() {
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name="DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		return super.getDbLastUpdateTime();
	}

}
