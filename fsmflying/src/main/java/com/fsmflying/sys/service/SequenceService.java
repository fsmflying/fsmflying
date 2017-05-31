package com.fsmflying.sys.service;


import com.fsmflying.sys.dao.ISequenceDao;


public interface SequenceService extends ISequenceService {
	
	
	
	public void setSequenceDao(ISequenceDao sequenceDao);
	
}
