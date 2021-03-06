package com.fsmflying.sys.dm;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name="Sys_Tabs")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.etab",
		initialValue=1
		
	)
public class SysTab extends AbstractBean{
	
	private static final long serialVersionUID = 1L;

	private int		mTabId;
	private String	mTabName;
	private int		mShowOrder;
	private int		mParentTabId;
	private String	mDefaultUrl;
	private int		mTabType;
	private int 	mLevelDepth;
	private String	mMemo;
	
	private Set<SysMenu> mMenus;// = new HashSet<SysMenu>();

	public SysTab() {
		super();
	}
	
	public SysTab(int tabId) {
		super();
		mTabId = tabId;
	}

//	@ManyToMany(targetEntity=SysMenu.class)
//	@JoinTable(name="Sys_RTabMenu",
//	 joinColumns=@JoinColumn(name="TabId",referencedColumnName="TabId"),
//	 inverseJoinColumns=@JoinColumn(name="MenuId",referencedColumnName="MenuId")
//	)
	@Transient
	public Set<SysMenu> getMenus() {
		return mMenus;
	}

	public void setMenus(Set<SysMenu> menus) {
		mMenus = menus;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	public int getTabId() {
		return mTabId;
	}

	public void setTabId(int tabId) {
		mTabId = tabId;
	}

	public String getTabName() {
		return mTabName;
	}

	public void setTabName(String tabName) {
		mTabName = tabName;
	}

	public int getShowOrder() {
		return mShowOrder;
	}

	public void setShowOrder(int showOrder) {
		mShowOrder = showOrder;
	}

	@Column(name="ParentTabId",nullable=false,columnDefinition="int default -1")
	public int getParentTabId() {
		return mParentTabId;
	}

	public void setParentTabId(int pTabId) {
		mParentTabId = pTabId;
	}

	public String getDefaultUrl() {
		return mDefaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		mDefaultUrl = defaultUrl;
	}

	public int getTabType() {
		return mTabType;
	}

	public void setTabType(int tabType) {
		mTabType = tabType;
	}

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
		// TODO Auto-generated method stub
		return "sys.etab";
	}

	@Override
	public String toString() {
		return "SysTab [mTabName=" + mTabName + "]";
	}
	
	@Column(name="LevelDepth",nullable=false,columnDefinition="int default -1")
	public int getLevelDepth() {
		return mLevelDepth;
	}

	public void setLevelDepth(int levelDepth) {
		mLevelDepth = levelDepth;
	}
}
