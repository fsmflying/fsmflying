package com.fsmflying.sys.dm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="Sys_DataAuths")
@TableGenerator(name = "tableIdGenerator", 
table = "Sys_IdGenerators", 
pkColumnName = "KeyName", 
valueColumnName = "NextValue", 
pkColumnValue = "sys.dataauth", 
initialValue = 1)
public class SysDataAuth extends AbstractBean{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	public int 		mAuthId;
	public String 	mAuthName;
	public String 	mKeyCode;
	public String 	mMemo;
	public int 		mDisabled;
	
	
	public SysDataAuth() {
		super();
	}

	public SysDataAuth(int authId) {
		super();
		mAuthId = authId;
	}


	private Set<SysDataAuthItem> items = new HashSet<SysDataAuthItem>();
	
//	@OneToMany(targetEntity=SysDataAuthItem.class)
//	@JoinColumn(name="DataAuthId")
	@Transient
	public Set<SysDataAuthItem> getItems() {
		return items;
	}

	public void setItems(Set<SysDataAuthItem> items) {
		this.items = items;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="AuthId",nullable=false)
	public int getAuthId() {
		return mAuthId;
	}

	public void setAuthId(int dataAuthId) {
		mAuthId = dataAuthId;
	}
	@Column(name="KeyCode",nullable=false)
	public String getKeyCode() {
		return mKeyCode;
	}

	public void setKeyCode(String dataAuthKeyCode) {
		mKeyCode = dataAuthKeyCode;
	}
	@Column(name="AuthName",nullable=false)
	public String getAuthName() {
		return mAuthName;
	}
	
	public void setAuthName(String dataAuthName) {
		mAuthName = dataAuthName;
	}
	@Column(name="Memo")
	public String getMemo() {
		return mMemo;
	}

	public void setMemo(String memo) {
		mMemo = memo;
	}
	@Column(name="Disabled",nullable=false,columnDefinition="int default 0")
	public int getDisabled() {
		return mDisabled;
	}

	public void setDisabled(int disabled) {
		mDisabled = disabled;
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
	

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "SysDataAuth";
	}
}
