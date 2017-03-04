package com.fsmflying.sys.dm;

import java.util.Date;


public class SysSequence extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private String	mKeyName;
	private int		mNextValue;
	private Date	mGeneratedTime;
	
	public SysSequence() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return mKeyName;
	}

	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		mKeyName = keyName;
	}

	/**
	 * @return the nextValue
	 */
	public int getNextValue() {
		return mNextValue;
	}
	
	/**
	 * @param nextValue the nextValue to set
	 */
	public void setNextValue(int nextValue) {
		mNextValue = nextValue;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getGeneratedTime() {
		return mGeneratedTime;
	}
	
	/**
	 * @param generatedTime the lastUpdateTime to set
	 */
	public void setGeneratedTime(Date generatedTime) {
		mGeneratedTime = generatedTime;
	}






	@Override
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "esequence";
	}

}
