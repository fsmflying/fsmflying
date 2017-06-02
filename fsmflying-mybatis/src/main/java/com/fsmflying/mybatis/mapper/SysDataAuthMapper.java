package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysDataAuth;

public interface SysDataAuthMapper {
	
	//Insert("insert into sys_dataauths(AuthId,AuthName,KeyCode,Memo,Disabled,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) values(#{authId},#{authName},#{keyCode},#{memo},#{disabled},#{dbDeleted},#{dbCreateBy},#{dbCreateTime},#{dbLastUpdateBy},#{dbLastUpdateTime})")
	boolean add(SysDataAuth model);

	//@Update("update sys_dataauths set AuthName=#{authName},KeyCode=#{keyCode},Memo=#{memo},Disabled=#{disabled},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateBy=#{dbLastUpdateBy},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and AuthId=#{authId}")
	boolean save(SysDataAuth model);

	//@Delete("delete from Sys_DataAuths where 1=1 and authId=#{authId}")
	boolean delete(int authId);

	//@Select("select * from Sys_DataAuths where 1=1 and authId=#{authId}")
	SysDataAuth get(int authId);

	//@Select("select * from Sys_DataAuths")
	List<SysDataAuth> getAllList();
/*
	List<SysDataAuth> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysDataAuth> list);
	
	boolean addList(List<SysDataAuth> list);
*/
}