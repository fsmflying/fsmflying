package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysDataAuthItem;

public interface SysDataAuthItemMapper {
	
	//Insert("insert into sys_dataauthitems(AuthItemId,AuthId,AuthItemName,TransferCode,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime,DataAuthId) values(#{authItemId},#{authId},#{authItemName},#{transferCode},#{memo},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime},#{dataAuthId})")
	boolean add(SysDataAuthItem model);

	//@Update("update sys_dataauthitems set AuthId=#{authId},AuthItemName=#{authItemName},TransferCode=#{transferCode},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime},DataAuthId=#{dataAuthId} where 1=1 and AuthItemId=#{authItemId}")
	boolean save(SysDataAuthItem model);

	//@Delete("delete from Sys_DataAuthItems where 1=1 and authItemId=#{authItemId}")
	boolean delete(int authItemId);

	//@Select("select * from Sys_DataAuthItems where 1=1 and authItemId=#{authItemId}")
	SysDataAuthItem get(int authItemId);

	//@Select("select * from Sys_DataAuthItems")
	List<SysDataAuthItem> getAllList();
/*
	List<SysDataAuthItem> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysDataAuthItem> list);
	
	boolean addList(List<SysDataAuthItem> list);
*/
}