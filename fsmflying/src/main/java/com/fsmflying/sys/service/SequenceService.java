package com.fsmflying.sys.service;


import com.fsmflying.sys.dao.ISequenceDao;

/**
 * 序号生成功能相关的服务接口
 * @author FangMing
 *
 */
public interface SequenceService extends ISequenceService {
	
	
	/**
	 * 设置数据访问对象
	 * @param sequenceDao 数据访问对象
	 */
	public void setSequenceDao(ISequenceDao sequenceDao);
}
