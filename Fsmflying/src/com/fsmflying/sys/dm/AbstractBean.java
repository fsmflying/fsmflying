package com.fsmflying.sys.dm;

import java.util.Date;


public abstract class AbstractBean {
	
	private int 	mDbDeleted =  0;
	private int 	mDbCreateBy = -1;
	private Date 	mDbCreateTime = new Date();
	private	int		mDbLastUpdateBy = -1;
	private Date	mDbLastUpdateTime = new Date();
	
	/**
	 * 创建一个新的实例，这个实例的属性设置了默认值[DbDeleted=0][DbCreateBy=-1][DbCreateTime=now][DbLastUpdateBy=-1][DbLastUpdateTime=now]
	 */
	public AbstractBean() {
		super();
	}
	
	/**
	 * @return 相应记录在数据库存的逻辑删除状态<br/>[0：默认值，逻辑上在数据库中存在，在正常的业务中可以]<br/>[1：逻辑上在数据库中已经被删除，在正常的业务中已经不会再使用]
	 * 
	 */
	public int getDbDeleted() {
		return mDbDeleted;
	}

	/**
	 * @param dbDeleted 相应记录在数据库存的逻辑删除状态<br/>[0：默认值，逻辑上在数据库中存在，在正常的业务中可以]<br/>[1：逻辑上在数据库中已经被删除，在正常的业务中已经不会再使用]
	 */
	public void setDbDeleted(int dbDeleted) {
		mDbDeleted = dbDeleted;
	}

	/**
	 * @return 创建相应记录的用户ID，默认值为-1，表示没有设置记录创建者ID
	 */
	public int getDbCreateBy() {
		return mDbCreateBy;
	}

	/**
	 * @param dbCreateBy 创建相应记录的用户ID，默认值为-1，表示没有设置记录创建者ID
	 */
	public void setDbCreateBy(int dbCreateBy) {
		mDbCreateBy = dbCreateBy;
	}

	/**
	 * @return 创建相应记录的时间,默认为new Date()
	 */
	public Date getDbCreateTime() {
		return mDbCreateTime;
	}

	/**
	 * @param dbCreateTime 创建相应记录的时间,默认为new Date()
	 */
	public void setDbCreateTime(Date dbCreateTime) {
		mDbCreateTime = dbCreateTime;
	}

	/**
	 * @return 最后更新数据库相应记录的用户ID，默认值为-1，表示没有记录最后更新数据库记录的用户ID
	 */
	public int getDbLastUpdateBy() {
		return mDbLastUpdateBy;
	}

	/**
	 * @param dbLastUpdateBy 最后更新数据库相应记录的用户ID，默认值为-1，表示没有记录最后更新数据库记录的用户ID
	 */
	public void setDbLastUpdateBy(int dbLastUpdateBy) {
		mDbLastUpdateBy = dbLastUpdateBy;
	}

	/**
	 * @return  最后更新相应数据库记录的时间,默认为new Date()
	 */
	public Date getDbLastUpdateTime() {
		return mDbLastUpdateTime;
	}

	/**
	 * @param dbLastUpdateTime 最后更新相应数据库记录的时间,默认为new Date()
	 */
	public void setDbLastUpdateTime(Date dbLastUpdateTime) {
		mDbLastUpdateTime = dbLastUpdateTime;
	}

	public abstract String getTypeUnique();

	
}
