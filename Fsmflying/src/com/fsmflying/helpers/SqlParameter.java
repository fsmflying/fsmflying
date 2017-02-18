package com.fsmflying.helpers;

public class SqlParameter<T> {
	private String 	mName;
	private T		mValue;
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}
	public T getValue() {
		return mValue;
	}
	public void setValue(T mValue) {
		this.mValue = mValue;
	}
	public SqlParameter(String mName, T mValue) {
		super();
		this.mName = mName;
		this.mValue = mValue;
	}
	@Override
	public String toString() {
		return "SqlParameter<"+(mValue!=null?mValue.getClass().getCanonicalName():null)+"> [getName()=" + getName() + ", getValue()="
				+ getValue() + "]";
	}

	
}
