package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysCustomPage;

public interface SysCustomPageMapper {
	
	//Insert("insert into sys_custompages(CustomPageId,CustomPageName,AppName,SourceUrl,DestinationUrl,TranslationType,Memo,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime,SysName) values(#{customPageId},#{customPageName},#{appName},#{sourceUrl},#{destinationUrl},#{translationType},#{memo},#{dbDeleted},#{dbCreateBy},#{dbCreateTime},#{dbLastUpdateBy},#{dbLastUpdateTime},#{sysName})")
	boolean add(SysCustomPage model);

	//@Update("update sys_custompages set CustomPageName=#{customPageName},AppName=#{appName},SourceUrl=#{sourceUrl},DestinationUrl=#{destinationUrl},TranslationType=#{translationType},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateBy=#{dbLastUpdateBy},DbLastUpdateTime=#{dbLastUpdateTime},SysName=#{sysName} where 1=1 and CustomPageId=#{customPageId}")
	boolean save(SysCustomPage model);

	//@Delete("delete from Sys_CustomPages where 1=1 and customPageId=#{customPageId}")
	boolean delete(int customPageId);

	//@Select("select * from Sys_CustomPages where 1=1 and customPageId=#{customPageId}")
	SysCustomPage get(int customPageId);

	//@Select("select * from Sys_CustomPages")
	List<SysCustomPage> getAllList();
/*
	List<SysCustomPage> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysCustomPage> list);
	
	boolean addList(List<SysCustomPage> list);
*/
}