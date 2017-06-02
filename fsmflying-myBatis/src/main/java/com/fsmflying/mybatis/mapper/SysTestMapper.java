package com.fsmflying.mybatis.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysEmployee;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysUser;

public interface SysTestMapper {

	boolean add(SysUser model);

	boolean save(SysUser model);

	boolean delete(Integer id);

	//@Select("select * from sys_users where userId=#{userId}")
	SysUser get(Integer id);
	
	SysUser getByUsername(String username);
	
	HashMap<String,Object> getForMap01(Integer id);

	List<SysUser> getAllList();

	List<SysUser> getListByProperties(List<SqlParameter<?>> parameters);

	SysUser get(String username);
	
	Map<String,Object> getCustomUser01(int id);
	
	Map<String,Object> getCustomUser02(int id);
	
	SysUser getCustomUser03(int id);
	
	SysEmployee getEmployee(int id);
	
	List<SysUser> getCustomUser04();
	
	List<SysUser> getUserWithRoles();
	
	List<SysUser> getUserWithRoles02();
	
	List<SysUser> getUserWithEmployee();
	
	List<SysUser> getUserWithEmployee02();
	
	List<SysRole> getRoles(int id);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysUser> list);

	boolean addList(List<SysUser> list);

}
