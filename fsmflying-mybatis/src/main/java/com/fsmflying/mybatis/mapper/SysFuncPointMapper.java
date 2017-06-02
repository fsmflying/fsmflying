package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysFuncPoint;

public interface SysFuncPointMapper {
	
	//Insert("insert into sys_funcpoints(FuncPointId,FuncPointName,KeyCode,ShowOrder,MenuId,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(#{funcPointId},#{funcPointName},#{keyCode},#{showOrder},#{menuId},#{memo},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime})")
	boolean add(SysFuncPoint model);

	//@Update("update sys_funcpoints set FuncPointName=#{funcPointName},KeyCode=#{keyCode},ShowOrder=#{showOrder},MenuId=#{menuId},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and FuncPointId=#{funcPointId}")
	boolean save(SysFuncPoint model);

	//@Delete("delete from Sys_FuncPoints where 1=1 and funcPointId=#{funcPointId}")
	boolean delete(int funcPointId);

	//@Select("select * from Sys_FuncPoints where 1=1 and funcPointId=#{funcPointId}")
	SysFuncPoint get(int funcPointId);

	//@Select("select * from Sys_FuncPoints")
	List<SysFuncPoint> getAllList();
/*
	List<SysFuncPoint> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysFuncPoint> list);
	
	boolean addList(List<SysFuncPoint> list);
*/
}