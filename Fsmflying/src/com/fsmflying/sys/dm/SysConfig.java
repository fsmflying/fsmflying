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
import javax.persistence.UniqueConstraint;



@Entity
@Table(name="Sys_Configs",
uniqueConstraints=@UniqueConstraint(columnNames = { "ConfigKey" }))
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.config",
		initialValue=1
		
	)
public class SysConfig extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private int		mConfigId;
	private String	mConfigName;
	private String	mConfigKey;
	private String	mConfigValue;
	private String	mMemo;
	private int		mParentConfigId;
	private int		mDisabled;
	private int		mLevelDepth;
	
	public SysConfig() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="ConfigId",nullable=false)
	public int getConfigId() {
		return mConfigId;
	}
	public void setConfigId(int configId) {
		mConfigId = configId;
	}
	@Column(name="ConfigName",nullable=false)
	public String getConfigName() {
		return mConfigName;
	}
	public void setConfigName(String configName) {
		mConfigName = configName;
	}
	@Column(name="ConfigKey",nullable=false)
	public String getConfigKey() {
		return mConfigKey;
	}
	public void setConfigKey(String configKey) {
		mConfigKey = configKey;
	}
	@Column(name="ConfigValue")
	public String getConfigValue() {
		return mConfigValue;
	}
	public void setConfigValue(String configValue) {
		mConfigValue = configValue;
	}
	@Column(name="Memo")
	public String getMemo() {
		return mMemo;
	}
	public void setMemo(String memo) {
		mMemo = memo;
	}
	@Column(name="ParentConfigId",nullable=false,columnDefinition="int default -1")
	public int getParentConfigId() {
		return mParentConfigId;
	}
	public void setParentConfigId(int pConfigId) {
		mParentConfigId = pConfigId;
	}
	@Column(name="Disabled",nullable=false,columnDefinition="int default 0")
	public int getDisabled() {
		return mDisabled;
	}
	public void setDisabled(int disable) {
		mDisabled = disable;
	}
	@Column(name="LevelDepth",nullable=false,columnDefinition="int default -1")
	public int getLevelDepth() {
		return mLevelDepth;
	}
	public void setLevelDepth(int depth) {
		mLevelDepth = depth;
	}
	
	@Override
	@Column(name="DbDeleted",nullable=false,columnDefinition="int default 0")
	public int getDbDeleted() {
		// TODO Auto-generated method stub
		return super.getDbDeleted();
	}

	@Override
	@Column(name="DbCreateBy",nullable=false,columnDefinition="int default -1")
	public int getDbCreateBy() {
		// TODO Auto-generated method stub
		return super.getDbCreateBy();
	}

	@Override
	@Column(name="DbCreateTime")
	public Date getDbCreateTime() {
		// TODO Auto-generated method stub
		return super.getDbCreateTime();
	}

	@Override
	@Column(name="DbLastUpdateBy",nullable=false,columnDefinition="int default -1")
	public int getDbLastUpdateBy() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name="DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateTime();
	}

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.econfig";
	}

	
}
