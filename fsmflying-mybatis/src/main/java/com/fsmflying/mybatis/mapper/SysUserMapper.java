package com.fsmflying.mybatis.mapper;

import java.util.List;

//import org.apache.ibatis.annotations.CacheNamespace;
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;

//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysUser;

//@CacheNamespace(readWrite = false)
public interface SysUserMapper {

//	@Insert("insert into sys_users(UserId,Username,UserPwd,Nickname,IpPolicy,IpAddress,RegisterTime,LastLoginTime,Status,DisabledTime,DisabledMinutes,PwdPromptQuestion,PwdProtectQuestion,PwdProtectAnswer,Email,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(#{userId},#{username},#{userPwd},#{nickname},#{ipPolicy},#{ipAddress},#{registerTime},#{lastLoginTime},#{status},#{disabledTime},#{disabledMinutes},#{pwdPromptQuestion},#{pwdProtectQuestion},#{pwdProtectAnswer},#{email},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime})")
	boolean add(SysUser model);

//	@Update("update sys_users set Username=#{username},UserPwd=#{userPwd},Nickname=#{nickname},IpPolicy=#{ipPolicy},IpAddress=#{ipAddress},RegisterTime=#{registerTime},LastLoginTime=#{lastLoginTime},Status=#{status},DisabledTime=#{disabledTime},DisabledMinutes=#{disabledMinutes},PwdPromptQuestion=#{pwdPromptQuestion},PwdProtectQuestion=#{pwdProtectQuestion},PwdProtectAnswer=#{pwdProtectAnswer},Email=#{email},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and UserId=#{userId}")
	boolean save(SysUser model);

//	@Delete("delete from Sys_Users where 1=1 and userId=#{userId}")
	boolean delete(Integer id);

//	@Select("select * from Sys_Users where 1=1 and userId=#{userId}")
	SysUser get(Integer id);
	
//	@Select("select * from Sys_Users where 1=1 and username=#{username}")
	SysUser getByUsername(String username);

//	@Select("select * from Sys_Users")
	List<SysUser> getAllList();

//	List<SysUser> getListByProperties(List<SqlParameter<?>> parameters);

////	@Select("select * from Sys_Users where 1=1 and username=#{username}")
//	SysUser get(String username);

//	boolean deleteByProperties(List<SqlParameter<?>> parameters);

//	boolean saveList(List<SysUser> list);

//	boolean addList(List<SysUser> list);

}
