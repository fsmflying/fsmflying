package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysMenu;

public interface SysMenuMapper {
	
	//Insert("insert into sys_menus(MenuId,ParentMenuId,MenuName,DefaultUrl,MenuType,ShowOrder,LevelDepth,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(#{menuId},#{parentMenuId},#{menuName},#{defaultUrl},#{menuType},#{showOrder},#{levelDepth},#{memo},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime})")
	boolean add(SysMenu model);

	//@Update("update sys_menus set ParentMenuId=#{parentMenuId},MenuName=#{menuName},DefaultUrl=#{defaultUrl},MenuType=#{menuType},ShowOrder=#{showOrder},LevelDepth=#{levelDepth},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and MenuId=#{menuId}")
	boolean save(SysMenu model);

	//@Delete("delete from Sys_Menus where 1=1 and menuId=#{menuId}")
	boolean delete(int menuId);

	//@Select("select * from Sys_Menus where 1=1 and menuId=#{menuId}")
	SysMenu get(int menuId);

	//@Select("select * from Sys_Menus")
	List<SysMenu> getAllList();
/*
	List<SysMenu> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysMenu> list);
	
	boolean addList(List<SysMenu> list);
*/
}