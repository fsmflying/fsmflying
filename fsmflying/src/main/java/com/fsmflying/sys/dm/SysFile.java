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
	private long 	mFileLength;
	private int		mFileType;
	private String	mMemo;
	
	public SysFile() {
		super();
	}
	
	public SysFile(int fileId) {
		super();
		mFileId = fileId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="FileId",nullable=false)
	public int getFileId() {
		return mFileId;
	}

	public void setFileId(int mFileId) {
		this.mFileId = mFileId;
	}


	@Column(name="FileName",nullable=false)
	public String getFileName() {
		return mFileName;
	}

	public void setFileName(String mFileName) {
		this.mFileName = mFileName;
	}
	@Column(name="FileExtName")
	public String getFileExtName() {
		return mFileExtName;
	}

	
	public void setFileExtName(String mFileExtName) {
		this.mFileExtName = mFileExtName;
	}
	
	@Column(name="FileGroupId")
	public int getFileGroupId() {
		return mFileGroupId;
	}

	public void setFileGroupId(int mFileGroupId) {
		this.mFileGroupId = mFileGroupId;
	}
	
	@Column(name="FileAddress",nullable=false)
	public String getFileAddress() {
		return mFileAddress;
	}

	public void setFileAddress(String mFileAddress) {
		this.mFileAddress = mFileAddress;
	}
	
	@Column(name="FileLength",nullable=false,columnDefinition="long default 0")
	public long getFileLength() {
		return mFileLength;
	}

	public void setFileLength(long mFileLength) {
		this.mFileLength = mFileLength;
	}

	@Column(name="FileType")
	public int getFileType() {
		return mFileType;
	}

	public void setFileType(int mFileType) {
		this.mFileType = mFileType;
	}

	@Column(name="Memo")
	public String getMemo() {
		return mMemo;
	}

	public void setMemo(String mMemo) {
		this.mMemo = mMemo;
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

	@Override
	public String toString() {
		return "SysFile [mFileId=" + mFileId + ", mFileName=" + mFileName
				+ ", mFileExtName=" + mFileExtName + ", mFileGroupId="
				+ mFileGroupId + ", mFileAddress=" + mFileAddress
				+ ", mFileLength=" + mFileLength + ", mFileType=" + mFileType
				+ ", mMemo=" + mMemo + "]";
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
