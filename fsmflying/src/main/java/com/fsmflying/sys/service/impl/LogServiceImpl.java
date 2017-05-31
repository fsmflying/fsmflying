package com.fsmflying.sys.service.impl;

import java.util.List;
import com.fsmflying.sys.dao.ILogDao;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.service.AbstractLogService;

public class LogServiceImpl extends AbstractLogService {

	private ILogDao logDao;

	public void setLogDao(ILogDao logDao) {
		this.logDao = logDao;
	}

	@Override
	public boolean add(SysLog model) {
		return this.logDao.add(model);
	}

	@Override
	public boolean save(SysLog model) {
		return this.logDao.save(model);
	}

	@Override
	public boolean deleteModelOfSysLog(int id) {
		return this.logDao.deleteModelOfSysLog(id);
	}

	@Override
	public SysLog getModelOfSysLog(int id) {
		return this.logDao.getModelOfSysLog(id);
	}

	@Override
	public List<SysLog> getListOfSysLog() {
		return this.logDao.getListOfSysLog();
	}

	
}
