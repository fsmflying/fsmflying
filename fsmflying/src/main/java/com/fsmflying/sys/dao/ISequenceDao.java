package com.fsmflying.sys.dao;

public interface ISequenceDao {


	int generateNextId(String keyName, int increment);


	int[] generateNextIds(int generateCount, String keyName, int increment);

}