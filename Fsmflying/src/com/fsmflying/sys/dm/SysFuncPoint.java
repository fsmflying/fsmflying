package com.fsmflying.sys.dm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="Sys_FuncPoints")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.efuncpoint",
		initialValue=1
		
	)
public class SysFuncPoint extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int mFuncPointId; 
//	private int mMenuId;
	private String mFuncPointName; 
	private String mKeyCode; 
	private int mShowOrder;
	private String mMemo;
	private int mMenuId;
	
	private SysMenu mMenu;

	
	public SysFuncPoint() {
		super();
	}

	@ManyToOne(targetEntity=SysMenu.class)
	@JoinColumn(name="MenuId",nullable=true)
	public SysMenu getMenu() {
		return mMenu;
	}

	public void setMenu(SysMenu menu) {
		mMenu = menu;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="FuncPointId",nullable=false)
	public int getFuncPointId() {
		return mFuncPointId;
	}

	public void setFuncPointId(int funcPointId) {
		mFuncPointId = funcPointId;
	}

	@Column(name="FuncPointName",nullable=false)
	public String getFuncPointName() {
		return mFuncPointName;
	}

	public void setFuncPointName(String funcPointName) {
		mFuncPointName = funcPointName;
	}
	@Column(name="KeyCode",nullable=false)
	public String getKeyCode() {
		return mKeyCode;
	}

	public void setKeyCode(String keyCode) {
		mKeyCode = keyCode;
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
		return "sys.efunctionpoint";
	}


	@Column(name="MenuId",nullable=false,columnDefinition="int default -1")
	public int getMenuId() {
		return mMenuId;
	}

	public void setMenuId(int menuId) {
		mMenuId = menuId;
	}
	
}
