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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="Sys_Menus")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.emenu",
		initialValue=1
		
	)
public class SysMenu extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private int		mMenuId;
	private String 	mMenuName;
	private String 	mDefaultUrl;
	private int 	mShowOrder;
	private int 	mParentMenuId;
	private int 	mLevelDepth;
	private int 	mMenuType;
	private String 	mMemo;
	
	
	
	
//	private Set<SysTab> mTabs ;//= new HashSet<SysTab>();
	private Set<SysFuncPoint> mFuncPoints ;//= new HashSet<SysFuncPoint>();
	
	

	public SysMenu() {
		super();
	}
	
	public SysMenu(int menuId) {
		super();
		mMenuId = menuId;
	}

	public SysMenu(String menuName, String defaultUrl, int showOrder,
			int pMenuId, int depth, int menuType, String memo) {
		super();
		mMenuName = menuName;
		mDefaultUrl = defaultUrl;
		mShowOrder = showOrder;
		mParentMenuId = pMenuId;
		mLevelDepth = depth;
		mMenuType = menuType;
		mMemo = memo;
	}

//	@ManyToMany(targetEntity=SysTab.class)
//	@JoinTable(name="Sys_RTabMenu",
//		joinColumns=@JoinColumn(name="MenuId",referencedColumnName="MenuId"),
//		inverseJoinColumns=@JoinColumn(name="TabId",referencedColumnName="TabId")
//			)
//	@Transient
//	public Set<SysTab> getTabs() {
//		return mTabs;
//	}
//
//	public void setTabs(Set<SysTab> tabs) {
//		mTabs = tabs;
//	}
	
//	@OneToMany(targetEntity=SysFuncPoint.class)
//	@JoinTable(name="Sys_RMenuFuncPoint",
//		joinColumns=@JoinColumn(name="CMenuId",referencedColumnName="MenuId"),
//		inverseJoinColumns=@JoinColumn(name="CFuncPointId",referencedColumnName="FuncPointId")
//			)
//	@JoinColumn(name="MenuId")
	@Transient
	public Set<SysFuncPoint> getFuncPoints() {
		return mFuncPoints;
	}

	public void setFuncPoints(Set<SysFuncPoint> funcPoints) {
		mFuncPoints = funcPoints;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="MenuId",nullable=false)
	public int getMenuId() {
		return mMenuId;
	}

	public void setMenuId(int menuId) {
		mMenuId = menuId;
	}
	@Column(name="MenuName",nullable=false)
	public String getMenuName() {
		return mMenuName;
	}

	public void setMenuName(String menuName) {
		mMenuName = menuName;
	}

	@Column(name="DefaultUrl")
	public String getDefaultUrl() {
		return mDefaultUrl;
	}
	public void setDefaultUrl(String defaultUrl) {
		mDefaultUrl = defaultUrl;
	}
	@Column(name="ShowOrder",nullable=false,columnDefinition="int default 10000")
	public int getShowOrder() {
		return mShowOrder;
	}
	public void setShowOrder(int showOrder) {
		mShowOrder = showOrder;
	}
	@Column(name="ParentMenuId",nullable=false,columnDefinition="int default -1")
	public int getParentMenuId() {
		return mParentMenuId;
	}
	public void setParentMenuId(int parentMenuId) {
		mParentMenuId = parentMenuId;
	}
	@Column(name="LevelDepth",nullable=false,columnDefinition="int default -1")
	public int getLevelDepth() {
		return mLevelDepth;
	}
	public void setLevelDepth(int depth) {
		mLevelDepth = depth;
	}
	@Column(name="MenuType",nullable=false,columnDefinition="int default 1")
	public int getMenuType() {
		return mMenuType;
	}
	public void setMenuType(int menuType) {
		mMenuType = menuType;
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
		return "sys.emenu";
	}
	
	
}
