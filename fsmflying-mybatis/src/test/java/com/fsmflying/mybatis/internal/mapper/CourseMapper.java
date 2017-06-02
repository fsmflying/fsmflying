package com.fsmflying.mybatis.internal.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.mybatis.internal.Course;

public interface CourseMapper {
	
	@Insert("insert into courses(id,name,teacherId,subjectId,startTime,memo) values(#{id},#{name},#{teacherId},#{subjectId},#{startTime},#{memo})")
	boolean add(Course model);

	@Update("update courses set name=#{name},teacherId=#{teacherId},subjectId=#{subjectId},startTime=#{startTime},memo=#{memo} where 1=1 and id=#{id}")
	boolean save(Course model);

	@Delete("delete from courses where 1=1 and id=#{id}")
	boolean delete(int id);

	@Select("select * from courses where 1=1 and id=#{id}")
	Course get(int id);

	@Select("select * from courses")
	List<Course> getAllList();
/*
	List<Course> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<Course> list);
	
	boolean addList(List<Course> list);
*/
}