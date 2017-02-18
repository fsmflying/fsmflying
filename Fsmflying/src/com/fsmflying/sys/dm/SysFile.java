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
@Table(name="Sys_Files")
@TableGenerator(name = "tableIdGenerator", 
	table = "Sys_IdGenerators", 
	pkColumnName = "KeyName", 
	valueColumnName = "NextValue", 
	pkColumnValue = "sys.file", 
	initialValue = 1)
public class SysFile extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private int 	mFileId;
	private String 	mFileName;
	private String 	mFileExtName;
	private int 	mFileGroupId;
	private String 	mFileAddress;
	private int 	mFileLength;
	private int		mFileType;
	private String	mMemo;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="FileId",nullable=false)
	public int getmFileId() {
		return mFileId;
	}

	public void setmFileId(int mFileId) {
		this.mFileId = mFileId;
	}


	@Column(name="FileName",nullable=false)
	public String getmFileName() {
		return mFileName;
	}

	public void setmFileName(String mFileName) {
		this.mFileName = mFileName;
	}
	@Column(name="FileExtName")
	public String getmFileExtName() {
		return mFileExtName;
	}

	
	public void setmFileExtName(String mFileExtName) {
		this.mFileExtName = mFileExtName;
	}
	
	@Column(name="FileGroupId")
	public int getmFileGroupId() {
		return mFileGroupId;
	}

	public void setmFileGroupId(int mFileGroupId) {
		this.mFileGroupId = mFileGroupId;
	}
	
	@Column(name="FileAddress",nullable=false)
	public String getmFileAddress() {
		return mFileAddress;
	}

	public void setmFileAddress(String mFileAddress) {
		this.mFileAddress = mFileAddress;
	}
	
	@Column(name="FileLength",nullable=false,columnDefinition="int default 0")
	public int getmFileLength() {
		return mFileLength;
	}

	public void setmFileLength(int mFileLength) {
		this.mFileLength = mFileLength;
	}

	@Column(name="FileType")
	public int getmFileType() {
		return mFileType;
	}

	public void setmFileType(int mFileType) {
		this.mFileType = mFileType;
	}

	@Column(name="Memo")
	public String getmMemo() {
		return mMemo;
	}

	public void setmMemo(String mMemo) {
		this.mMemo = mMemo;
	}

	public SysFile() {
		super();
	}

	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.efile";
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
	/*
	@Override
	@Column(name="DbLastUpdateBy",nullable=false,columnDefinition="int default -1")
	public int getLastUpdateBy() {
		return super.getLastUpdateBy();
	}

	@Override
	@Column(name="DbLastUpdateTime")
	public Date getLastUpdateTime() {
		return super.getLastUpdateTime();
	}
*/
}
