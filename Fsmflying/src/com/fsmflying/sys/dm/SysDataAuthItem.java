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
@Table(name="Sys_DataAuthItems")
@TableGenerator(name = "tableIdGenerator", 
table = "Sys_IdGenerators", 
pkColumnName = "KeyName", 
valueColumnName = "NextValue", 
pkColumnValue = "sys.dataauthitem", 
initialValue = 1)
public class SysDataAuthItem extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int		mItemId;
	private int		mDataAuthId;
	private String	mItemName;
	private String	mTransferCode;
	private String	mMemo;
	
	public SysDataAuthItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="ItemId",nullable=false)
	public int getItemId() {
		return mItemId;
	}
	public void setItemId(int dataAuthItemId) {
		mItemId = dataAuthItemId;
	}
	@Column(name="DataAuthId",nullable=false)
	public int getDataAuthId() {
		return mDataAuthId;
	}

	public void setDataAuthId(int dataAuthId) {
		mDataAuthId = dataAuthId;
	}

	@Column(name="ItemName",nullable=false)
	public String getItemName() {
		return mItemName;
	}

	public void setDataAuthItemName(String dataAuthItemName) {
		mItemName = dataAuthItemName;
	}
	@Column(name="TransferCode",nullable=false)
	public String getTransferCode() {
		return mTransferCode;
	}

	public void setTransferCode(String transferCode) {
		mTransferCode = transferCode;
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
	

	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.edataauthitem";
	}

}
