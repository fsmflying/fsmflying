package com.fsmflying.mybatis.internal.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.mybatis.internal.Student;

public interface StudentMapper {
	
	@Insert("insert into students(id,name,sex,birthDate) values(#{id},#{name},#{sex},#{birthDate})")
	boolean add(Student model);

	@Update("update students set name=#{name},sex=#{sex},birthDate=#{birthDate} where 1=1 and id=#{id}")
	boolean save(Student model);

	@Delete("delete from students where 1=1 and id=#{id}")
	boolean delete(int id);

	@Select("select * from students where 1=1 and id=#{id}")
	Student get(int id);

	@Select("select * from students")
	List<Student> getAllList();
/*
	List<Student> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<Student> list);
	
	boolean addList(List<Student> list);
*/
}