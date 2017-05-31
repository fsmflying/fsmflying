package com.fsmflying.sys.service;

public interface ISequenceService {


	int generateNextIdByDefault();


	int generateNextId(String keyName);


	int generateNextId(String keyName, int increment);


	int[] generateNextIds(int generateCount, String keyName, int increment);


	int[] generateNextIds(int generateCount, String keyName);


	int[] generateNextIds(int generateCount);


	int[] generateNextIds(int generateCount, int increment);

}