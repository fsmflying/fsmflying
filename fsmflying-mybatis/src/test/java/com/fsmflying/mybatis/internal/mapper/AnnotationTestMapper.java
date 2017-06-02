package com.fsmflying.mybatis.internal.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.StatementType;
import com.fsmflying.mybatis.internal.Course;
import com.fsmflying.mybatis.internal.Student;
import com.fsmflying.mybatis.internal.Subject;

public interface AnnotationTestMapper {
	/* Student */
	@Insert("insert into students(id,name,sex,birthDate) values(#{id},#{name},#{sex},#{birthDate})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public boolean addStudent(Student model);

	@Insert("insert into students(id,name,sex,birthDate) values(#{id},#{name},#{sex},#{birthDate})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public boolean addStudents(List<Student> list);

	@Select("select * from students where id=#{id}")
	public Student getStudent(int id);

	@Select("select * from students where name=#{name}")
	public Student getStudentByName(String name);

	@Select("select * from students where name=#{name}")
	public List<Student> getStudents(List<String> name);
	
	@Delete(value = { "delete from students" })
	public boolean deleteAllStudents();
	
	@Delete(value = { "delete from students where id=#{id}" })
	public boolean deleteStudentByKey(int id);

	/* Subject */

	@Insert("insert into subjects(id,name,memo) values(#{id},#{name},#{memo})")
	@SelectKey(statement="call identity()", before = false, keyProperty = "id", resultType = int.class)
	boolean add(Subject model);

	@Select("select * from subjects where id=#{id}")
	public Subject getSubject(int id);

	public boolean addSubjects(List<Student> list);

	@Delete(value = { "delete from subjects" })
	public boolean deleteAllSubjects();
	
	@Delete(value = { "delete from subjects where id=#{id}" })
	public boolean deleteSubjectByKey(int id);
	
	/* Teacher */

	@Select("select * from teachers where id=#{id}")
	@SelectKey(statementType = StatementType.STATEMENT, statement = "select nextValue from sequences where name='teacher'", before = false, keyColumn = "nextValue", keyProperty = "id", resultType = int.class)
	public Subject getTeacher(int id);

	@Delete(value = { "delete from teachers" })
	public boolean deleteAllTeachers();
	
	@Delete(value = { "delete from teachers where id=#{id}" })
	public boolean deleteTeacherByKey(int id);

	/* Course */

	@Select("select * from courses where id=#{id}")
	public Course getCourse(int id);

	@Delete(value = { "delete from courses" })
	public boolean deleteAllCourses();
	
	@Delete(value = { "delete from courses where id=#{id}" })
	public boolean deleteCourseByKey(int id);
	
	@Update("create table if not exist sys_sequences(KeyName varchar(64) primary key not null,NextValue int(20) not null,GeneratedTime datetime not null)")
	void createTables();
}
