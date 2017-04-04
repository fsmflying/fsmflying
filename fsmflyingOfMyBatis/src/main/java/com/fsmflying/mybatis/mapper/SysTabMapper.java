package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysTab;

public interface SysTabMapper {
	
	//Insert("insert into sys_tabs(TabId,TabName,ParentTabId,ShowOrder,DefaultUrl,TabType,LevelDepth,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(#{tabId},#{tabName},#{parentTabId},#{showOrder},#{defaultUrl},#{tabType},#{levelDepth},#{memo},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime})")
	boolean add(SysTab model);

	//@Update("update sys_tabs set TabName=#{tabName},ParentTabId=#{parentTabId},ShowOrder=#{showOrder},DefaultUrl=#{defaultUrl},TabType=#{tabType},LevelDepth=#{levelDepth},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and TabId=#{tabId}")
	boolean save(SysTab model);

	//@Delete("delete from Sys_Tabs where 1=1 and tabId=#{tabId}")
	boolean delete(int tabId);

	//@Select("select * from Sys_Tabs where 1=1 and tabId=#{tabId}")
	SysTab get(int tabId);

	//@Select("select * from Sys_Tabs")
	List<SysTab> getAllList();
/*
	List<SysTab> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysTab> list);
	
	boolean addList(List<SysTab> list);
*/
}