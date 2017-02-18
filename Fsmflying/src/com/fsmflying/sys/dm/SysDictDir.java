package com.fsmflying.sys.dm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name="Sys_DictDirs",
	uniqueConstraints=@UniqueConstraint(columnNames = { "KeyCode" })
)
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.edictdir",
		initialValue=1
		
	)
public class SysDictDir extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int 	mDirId;
	private String 	mDirName;
	private String 	mKeyCode;
	private String 	mMemo;
	
	private Set<SysDictItem> items = new HashSet<SysDictItem>();
	
	public SysDictDir() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@OneToMany(targetEntity=SysDictItem.class,mappedBy="dir")
	public Set<SysDictItem> getItems() {
		return items;
	}

	public void setItems(Set<SysDictItem> items) {
		this.items = items;
	}

	@Id
	@Column(name="DictDirId")
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	public int getDirId() {
		return mDirId;
	}

	public void setDirId(int dirId) {
		mDirId = dirId;
	}

	@Column(name="DirName")
	public String getDirName() {
		return mDirName;
	}

	public void setDirName(String dirName) {
		mDirName = dirName;
	}

	
	@Column(name="KeyCode")
	public String getKeyCode() {
		return mKeyCode;
	}

	public void setKeyCode(String keyCode) {
		mKeyCode = keyCode;
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
		// TODO Auto-generated method stub
		return "sys.edictdir";
	}

	@Override
	@Column(name="DbDeleted")
	public int getDbDeleted() {
		// TODO Auto-generated method stub
		return super.getDbDeleted();
	}

	@Override
	@Column(name="DbCreateBy")
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
	@Column(name="DbLastUpdateBy")
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

}
