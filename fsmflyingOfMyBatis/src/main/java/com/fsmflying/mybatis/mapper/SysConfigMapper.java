package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysConfig;

public interface SysConfigMapper {
	
	//Insert("insert into sys_configs(ConfigId,ParentConfigId,ConfigName,ConfigKey,ConfigValue,LevelDepth,Disabled,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(#{configId},#{parentConfigId},#{configName},#{configKey},#{configValue},#{levelDepth},#{disabled},#{memo},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime})")
	boolean add(SysConfig model);

	//@Update("update sys_configs set ParentConfigId=#{parentConfigId},ConfigName=#{configName},ConfigKey=#{configKey},ConfigValue=#{configValue},LevelDepth=#{levelDepth},Disabled=#{disabled},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and ConfigId=#{configId}")
	boolean save(SysConfig model);

	//@Delete("delete from Sys_Configs where 1=1 and configId=#{configId}")
	boolean delete(int configId);

	//@Select("select * from Sys_Configs where 1=1 and configId=#{configId}")
	SysConfig get(int configId);

	//@Select("select * from Sys_Configs")
	List<SysConfig> getAllList();
/*
	List<SysConfig> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysConfig> list);
	
	boolean addList(List<SysConfig> list);
*/
}