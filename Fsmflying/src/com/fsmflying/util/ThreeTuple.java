package com.fsmflying.util;

public class ThreeTuple<TFirst, TSecond, TThird> extends
		TwoTuple<TFirst, TSecond> {
	private TThird mThird;

	
	public ThreeTuple() {
		super();
	}

	public ThreeTuple(TFirst first, TSecond second, TThird mThird) {
		super(first, second);
		this.mThird = mThird;
	}

	public TThird getThird() {
		return mThird;
	}

	public void setThird(TThird mThird) {
		this.mThird = mThird;
	}
	
}
