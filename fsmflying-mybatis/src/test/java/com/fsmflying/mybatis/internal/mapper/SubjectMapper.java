package com.fsmflying.mybatis.internal.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.mybatis.internal.Subject;

public interface SubjectMapper {
	
	@Insert("insert into subjects(id,name,memo) values(#{id},#{name},#{memo})")
	boolean add(Subject model);

	@Update("update subjects set name=#{name},memo=#{memo} where 1=1 and id=#{id}")
	boolean save(Subject model);

	@Delete("delete from subjects where 1=1 and id=#{id}")
	boolean delete(int id);

	@Select("select * from subjects where 1=1 and id=#{id}")
	Subject get(int id);

	@Select("select * from subjects")
	List<Subject> getAllList();
/*
	List<Subject> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<Subject> list);
	
	boolean addList(List<Subject> list);
*/
}