package com.fsmflying.sys.dm;

import java.util.Date;


public abstract class AbstractBean {
	
	private int 	mDbDeleted =  0;
	private int 	mDbCreateBy = -1;
	private Date 	mDbCreateTime = new Date();
	private	int		mDbLastUpdateBy = -1;
	private Date	mDbLastUpdateTime = new Date();
	
	/**
	 * ����һ���µ�ʵ�������ʵ��������������Ĭ��ֵ[DbDeleted=0][DbCreateBy=-1][DbCreateTime=now][DbLastUpdateBy=-1][DbLastUpdateTime=now]
	 */
	public AbstractBean() {
		super();
	}
	
	/**
	 * @return ��Ӧ��¼�����ݿ����߼�ɾ��״̬<br/>[0��Ĭ��ֵ���߼��������ݿ��д��ڣ���������ҵ���п���]<br/>[1���߼��������ݿ����Ѿ���ɾ������������ҵ�����Ѿ�������ʹ��]
	 * 
	 */
	public int getDbDeleted() {
		return mDbDeleted;
	}

	/**
	 * @param dbDeleted ��Ӧ��¼�����ݿ����߼�ɾ��״̬<br/>[0��Ĭ��ֵ���߼��������ݿ��д��ڣ���������ҵ���п���]<br/>[1���߼��������ݿ����Ѿ���ɾ������������ҵ�����Ѿ�������ʹ��]
	 */
	public void setDbDeleted(int dbDeleted) {
		mDbDeleted = dbDeleted;
	}

	/**
	 * @return ������Ӧ��¼���û�ID��Ĭ��ֵΪ-1����ʾû�����ü�¼������ID
	 */
	public int getDbCreateBy() {
		return mDbCreateBy;
	}

	/**
	 * @param dbCreateBy ������Ӧ��¼���û�ID��Ĭ��ֵΪ-1����ʾû�����ü�¼������ID
	 */
	public void setDbCreateBy(int dbCreateBy) {
		mDbCreateBy = dbCreateBy;
	}

	/**
	 * @return ������Ӧ��¼��ʱ��,Ĭ��Ϊnew Date()
	 */
	public Date getDbCreateTime() {
		return mDbCreateTime;
	}

	/**
	 * @param dbCreateTime ������Ӧ��¼��ʱ��,Ĭ��Ϊnew Date()
	 */
	public void setDbCreateTime(Date dbCreateTime) {
		mDbCreateTime = dbCreateTime;
	}

	/**
	 * @return ���������ݿ���Ӧ��¼���û�ID��Ĭ��ֵΪ-1����ʾû�м�¼���������ݿ��¼���û�ID
	 */
	public int getDbLastUpdateBy() {
		return mDbLastUpdateBy;
	}

	/**
	 * @param dbLastUpdateBy ���������ݿ���Ӧ��¼���û�ID��Ĭ��ֵΪ-1����ʾû�м�¼���������ݿ��¼���û�ID
	 */
	public void setDbLastUpdateBy(int dbLastUpdateBy) {
		mDbLastUpdateBy = dbLastUpdateBy;
	}

	/**
	 * @return  ��������Ӧ���ݿ��¼��ʱ��,Ĭ��Ϊnew Date()
	 */
	public Date getDbLastUpdateTime() {
		return mDbLastUpdateTime;
	}

	/**
	 * @param dbLastUpdateTime ��������Ӧ���ݿ��¼��ʱ��,Ĭ��Ϊnew Date()
	 */
	public void setDbLastUpdateTime(Date dbLastUpdateTime) {
		mDbLastUpdateTime = dbLastUpdateTime;
	}

	public abstract String getTypeUnique();

	
}
