package com.fsmflying.sys.dal;

//import java.sql.ResultSet;
import java.util.List;

import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysUser;

public interface SystemManager {
	
	boolean addSysUser(SysUser user);
	boolean updateSysUser(SysUser user);
	boolean deleteSysUserById(int id);
	List<SysUser> getListOfSysUser(boolean isLoading,String whereSql,List<SqlParameter<?>> params,int pageSize,int pageIndex);
	List<SysUser> getListOfSysUser(String whereSql);
	
}
