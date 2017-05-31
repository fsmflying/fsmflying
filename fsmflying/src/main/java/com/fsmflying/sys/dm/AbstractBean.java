package com.fsmflying.sys.dm;

import java.util.Date;


public abstract class AbstractBean {
	
	private int 	mDbDeleted =  0;
	private int 	mDbCreateBy = -1;
	private Date 	mDbCreateTime = new Date();
	private	int		mDbLastUpdateBy = -1;
	private Date	mDbLastUpdateTime = new Date();
	public AbstractBean() {
		super();
	}
	
	public int getDbDeleted() {
		return mDbDeleted;
	}

	public void setDbDeleted(int dbDeleted) {
		mDbDeleted = dbDeleted;
	}
	public int getDbCreateBy() {
		return mDbCreateBy;
	}

	public void setDbCreateBy(int dbCreateBy) {
		mDbCreateBy = dbCreateBy;
	}

	public Date getDbCreateTime() {
		return mDbCreateTime;
	}

	public void setDbCreateTime(Date dbCreateTime) {
		mDbCreateTime = dbCreateTime;
	}

	public int getDbLastUpdateBy() {
		return mDbLastUpdateBy;
	}

	public void setDbLastUpdateBy(int dbLastUpdateBy) {
		mDbLastUpdateBy = dbLastUpdateBy;
	}

	public Date getDbLastUpdateTime() {
		return mDbLastUpdateTime;
	}

	public void setDbLastUpdateTime(Date dbLastUpdateTime) {
		mDbLastUpdateTime = dbLastUpdateTime;
	}

	public abstract String getTypeUnique();

	
}
