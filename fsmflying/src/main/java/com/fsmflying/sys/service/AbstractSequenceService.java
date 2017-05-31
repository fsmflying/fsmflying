package com.fsmflying.sys.service;

public abstract class AbstractSequenceService implements ISequenceService{

	ILogService logService;
	
	public void setLogService(ILogService logService)
	{
		this.logService = logService;
	}
	
	@Override
	public int generateNextIdByDefault() {
		return generateNextId("default");
	}

	@Override
	public int generateNextId(String keyName) {
		return generateNextId(keyName,1);
	}

	@Override
	public int[] generateNextIds(int generateCount, String keyName) {
		return generateNextIds(generateCount,keyName,1);
	}

	@Override
	public int[] generateNextIds(int generateCount) {
		return generateNextIds(generateCount,"default",1);
	}

	@Override
	public int[] generateNextIds(int generateCount, int increment) {
		return generateNextIds(generateCount,"default",increment);
	}

	
}
