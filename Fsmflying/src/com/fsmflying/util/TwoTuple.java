package com.fsmflying.util;

public class TwoTuple<TFirst, TSecond> {
	private TFirst 	mFirst;
	private TSecond mSecond;
	public TwoTuple(TFirst first, TSecond second) {
		super();
		this.mFirst = first;
		this.mSecond = second;
	}
	public TwoTuple() {
		super();
	}
	public TFirst getFirst() {
		return mFirst;
	}
	public void setFirst(TFirst mFirst) {
		this.mFirst = mFirst;
	}
	public TSecond getSecond() {
		return mSecond;
	}
	public void setSecond(TSecond mSecond) {
		this.mSecond = mSecond;
	}
	@Override
	public String toString() {
		return "TwoTuple {First=" + mFirst + ", Second=" + mSecond + "}";
	}
	
	
}
