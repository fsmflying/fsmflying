package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysDictItem;

public interface SysDictItemMapper {
	
	//Insert("insert into sys_dictitems(ItemId,DirId,ItemName,ItemValue,IsDefault,ShowOrder,Memo,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime,DisplayName) values(#{itemId},#{dirId},#{itemName},#{itemValue},#{isDefault},#{showOrder},#{memo},#{dbDeleted},#{dbCreateBy},#{dbCreateTime},#{dbLastUpdateBy},#{dbLastUpdateTime},#{displayName})")
	boolean add(SysDictItem model);

	//@Update("update sys_dictitems set DirId=#{dirId},ItemName=#{itemName},ItemValue=#{itemValue},IsDefault=#{isDefault},ShowOrder=#{showOrder},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateBy=#{dbLastUpdateBy},DbLastUpdateTime=#{dbLastUpdateTime},DisplayName=#{displayName} where 1=1 and ItemId=#{itemId}")
	boolean save(SysDictItem model);

	//@Delete("delete from Sys_DictItems where 1=1 and itemId=#{itemId}")
	boolean delete(int itemId);

	//@Select("select * from Sys_DictItems where 1=1 and itemId=#{itemId}")
	SysDictItem get(int itemId);

	//@Select("select * from Sys_DictItems")
	List<SysDictItem> getAllList();
/*
	List<SysDictItem> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysDictItem> list);
	
	boolean addList(List<SysDictItem> list);
*/
}