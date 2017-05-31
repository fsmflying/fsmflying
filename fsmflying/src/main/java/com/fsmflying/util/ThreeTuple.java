package com.fsmflying.util;

public class ThreeTuple<TFirst, TSecond, TThird> extends
		TwoTuple<TFirst, TSecond> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
