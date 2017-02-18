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
@Table(name="Sys_CustomPages")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.custompage",
		initialValue=1
		
	)
public class SysCustomPage extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private	int		mCustomPageId;
	private String	mCustomPageName;
	private String	mSysName;
	private String	mSourceUrl;
	private String	mDestinationUrl;
	private int		mHandleWay;
	private String	mMemo;
	
	public SysCustomPage() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="CustomPageId",nullable=false)
	public int getCustomPageId() {
		return mCustomPageId;
	}

	public void setCustomPageId(int customPageId) {
		mCustomPageId = customPageId;
	}

	@Column(name="CustomPageName",nullable=false)
	public String getCustomPageName() {
		return mCustomPageName;
	}

	public void setCustomPageName(String customPageName) {
		mCustomPageName = customPageName;
	}

	@Column(name="SysName",nullable=false)
	public String getSysName() {
		return mSysName;
	}

	public void setSysName(String sysName) {
		mSysName = sysName;
	}


	@Column(name="SourceUrl",nullable=false)
	public String getSourceUrl() {
		return mSourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		mSourceUrl = sourceUrl;
	}

	@Column(name="DestinationUrl",nullable=false)
	public String getDestinationUrl() {
		return mDestinationUrl;
	}

	public void setDestinationUrl(String destinationUrl) {
		mDestinationUrl = destinationUrl;
	}

	@Column(name="HandleWay",nullable=false)
	public int getHandleWay() {
		return mHandleWay;
	}

	public void setHandleWay(int handleWay) {
		mHandleWay = handleWay;
	}

	@Column(name="Memo")
	public String getMemo() {
		return mMemo;
	}
	public void setMemo(String memo) {
		mMemo = memo;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.ecustompage";
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