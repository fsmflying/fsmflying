package com.fsmflying.util;

import java.io.Serializable;

public class TwoTuple<TFirst, TSecond> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TFirst mFirst;
	private TSecond mSecond;

	public TwoTuple() {
		super();
	}

	public TwoTuple(TFirst first, TSecond second) {
		super();
		this.mFirst = first;
		this.mSecond = second;
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
		return "TwoTuple {first=" + mFirst + ", second=" + mSecond + "}";
	}

}
