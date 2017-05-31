package com.fsmflying.util;

import javax.persistence.Entity;

@Entity
public class TwoIntegerTuple extends TwoTuple<Integer, Integer> {
	
	public TwoIntegerTuple() {
		super();
	}

	public TwoIntegerTuple(Integer first, Integer second) {
		super(first, second);
	}
}
