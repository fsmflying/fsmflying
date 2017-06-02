package com.fsmflying.mybatis.internal.mapper;

import java.util.List;
import java.util.Map;

import com.fsmflying.mybatis.internal.Course;
import com.fsmflying.mybatis.internal.Student;
import com.fsmflying.mybatis.internal.Subject;
import com.fsmflying.mybatis.internal.Teacher;

public interface InternalMapper {
	
	/* Student */
	
	public boolean addStudent(Student model);

	public boolean addStudents(List<Student> list);

	public Student getStudent(int id);
	
	public List<Student> getAllStudents();
	
	public boolean deleteAllStudents();
	
	public boolean deleteStudent(int id);

	public Student getStudentByName(String name);

	public List<Student> getStudentsByNames(List<String> names);
	
	public boolean addStudentIfNotExists(Student model);
	
	public List<Student> getAllStudentsByProperties(Student model);
	
	public List<Student> getAllStudentsByProperties02(Map<String,Object> properties);
	
	public List<Student> getAllStudentsByIds(List<Integer> list);

	/* Subject */

	boolean addSubject(Subject model);

	public boolean addSubjects(List<Subject> list);

	public Subject getSubject(int id);
	
	public List<Subject> getAllSubjects();
	
	public boolean deleteSubject(int id);

	public boolean deleteAllSubjects();
	
	/* Teacher */

	public boolean addTeacher(Teacher model);

	public boolean addTeachers(List<Teacher> list);
	
	public Teacher getTeacher(int id);
	
	public List<Teacher> getAllTeachers();

	public boolean deleteAllTeachers();
	
	public boolean deleteTeacher(int id);

	/* Course */
	
	public boolean addCourse(Course model);
	
	public boolean addCourses(List<Course> list);

	public Course getCourse(int id);
	
	public List<Course> getAllCourses();

	public boolean deleteAllCourses();
	
	public boolean deleteCourse(int id);
	
	/**/
	
	void createTables();
	
	Map<String,Integer> existsTable(String name);
	

}
