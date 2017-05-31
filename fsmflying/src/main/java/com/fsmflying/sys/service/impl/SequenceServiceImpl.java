package com.fsmflying.sys.service.impl;

import javax.annotation.Resource;

import com.fsmflying.sys.dao.ISequenceDao;
import com.fsmflying.sys.service.AbstractSequenceService;
import com.fsmflying.sys.service.SequenceService;

public class SequenceServiceImpl extends AbstractSequenceService implements SequenceService{

	ISequenceDao sequenceDao;

	@Resource
	@Override
	public void setSequenceDao(ISequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}
	
	@Override
	public int generateNextId(String keyName, int increment) {
		return sequenceDao.generateNextId(keyName, increment);
	}

	@Override
	public int[] generateNextIds(int generateCount, String keyName, int increment) {
		return sequenceDao.generateNextIds(generateCount, keyName, increment);
	}

}
