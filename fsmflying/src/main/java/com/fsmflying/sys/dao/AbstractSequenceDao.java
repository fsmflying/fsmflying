package com.fsmflying.sys.dao;

public abstract class AbstractSequenceDao implements ISequenceDao {

	@Override
	public int generateNextId(String keyName, int increment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[] generateNextIds(int generateCount, String keyName, int increment) {
		// TODO Auto-generated method stub
		return null;
	}

}
