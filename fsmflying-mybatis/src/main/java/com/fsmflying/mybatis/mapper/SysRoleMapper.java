package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysRole;

public interface SysRoleMapper {
	
//	@Insert("insert into sys_roles(RoleId,RoleName,KeyCode,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(#{roleId},#{roleName},#{keyCode},#{memo},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime})")
	boolean add(SysRole model);

//	@Update("update sys_roles set RoleName=#{roleName},KeyCode=#{keyCode},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and RoleId=#{roleId}")
	boolean save(SysRole model);

//	@Delete("delete from Sys_Roles where 1=1 and roleId=#{roleId}")
	boolean delete(int roleId);

//	@Select("select * from Sys_Roles where 1=1 and roleId=#{roleId}")
	SysRole get(int roleId);

//	@Select("select * from Sys_Roles")
	List<SysRole> getAllList();
/*
	List<SysRole> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysRole> list);
	
	boolean addList(List<SysRole> list);
*/
}