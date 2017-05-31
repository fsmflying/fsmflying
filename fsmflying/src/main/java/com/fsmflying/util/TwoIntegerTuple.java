package com.fsmflying.util;

import javax.persistence.Entity;

@Entity
public class TwoIntegerTuple extends TwoTuple<Integer, Integer> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwoIntegerTuple() {
		super();
	}

	public TwoIntegerTuple(Integer first, Integer second) {
		super(first, second);
	}
}
