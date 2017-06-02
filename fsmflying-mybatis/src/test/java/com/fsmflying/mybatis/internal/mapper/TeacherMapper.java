package com.fsmflying.mybatis.internal.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.mybatis.internal.Teacher;

public interface TeacherMapper {
	
	@Insert("insert into teachers(id,name,sex,birthDate) values(#{id},#{name},#{sex},#{birthDate})")
	boolean add(Teacher model);

	@Update("update teachers set name=#{name},sex=#{sex},birthDate=#{birthDate} where 1=1 and id=#{id}")
	boolean save(Teacher model);

	@Delete("delete from teachers where 1=1 and id=#{id}")
	boolean delete(int id);

	@Select("select * from teachers where 1=1 and id=#{id}")
	Teacher get(int id);

	@Select("select * from teachers")
	List<Teacher> getAllList();
/*
	List<Teacher> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<Teacher> list);
	
	boolean addList(List<Teacher> list);
*/
}