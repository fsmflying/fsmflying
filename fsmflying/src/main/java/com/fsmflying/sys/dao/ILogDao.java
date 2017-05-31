package com.fsmflying.sys.dao;

import java.util.List;

import com.fsmflying.sys.dm.SysLog;

public interface ILogDao {
	
	public boolean add(SysLog model);

	public boolean save(SysLog model);

	public boolean deleteModelOfSysLog(int id);

	public SysLog getModelOfSysLog(int id);

	public List<SysLog> getListOfSysLog();
}
