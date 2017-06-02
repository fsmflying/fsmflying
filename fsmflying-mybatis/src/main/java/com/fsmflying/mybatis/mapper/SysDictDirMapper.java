package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysDictDir;

public interface SysDictDirMapper {
	
	//Insert("insert into sys_dictdirs(DirId,DirName,KeyCode,Memo,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime,DictDirId) values(#{dirId},#{dirName},#{keyCode},#{memo},#{dbDeleted},#{dbCreateBy},#{dbCreateTime},#{dbLastUpdateBy},#{dbLastUpdateTime},#{dictDirId})")
	boolean add(SysDictDir model);

	//@Update("update sys_dictdirs set DirName=#{dirName},KeyCode=#{keyCode},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateBy=#{dbLastUpdateBy},DbLastUpdateTime=#{dbLastUpdateTime},DictDirId=#{dictDirId} where 1=1 and DirId=#{dirId}")
	boolean save(SysDictDir model);

	//@Delete("delete from Sys_DictDirs where 1=1 and dirId=#{dirId}")
	boolean delete(int dirId);

	//@Select("select * from Sys_DictDirs where 1=1 and dirId=#{dirId}")
	SysDictDir get(int dirId);

	//@Select("select * from Sys_DictDirs")
	List<SysDictDir> getAllList();
/*
	List<SysDictDir> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysDictDir> list);
	
	boolean addList(List<SysDictDir> list);
*/
}