package com.fsmflying.sys.service;


import com.fsmflying.sys.dao.ISequenceDao;

/**
 * ������ɹ�����صķ���ӿ�
 * @author FangMing
 *
 */
public interface SequenceService extends ISequenceService {
	
	
	/**
	 * �������ݷ��ʶ���
	 * @param sequenceDao ���ݷ��ʶ���
	 */
	public void setSequenceDao(ISequenceDao sequenceDao);
}
