package com.fsmflying.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsmflying.mybatis.internal.Student;
import com.fsmflying.mybatis.internal.Subject;
import com.fsmflying.mybatis.internal.Teacher;
import com.fsmflying.mybatis.internal.Course;
import com.fsmflying.mybatis.internal.mapper.InternalMapper;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

public class MybatisEducationTests {

	SqlSessionFactory sqlSessionFactory;
	Gson gson = new Gson();

	@Before
	public void before() {
		String resource = "mybatis-config3.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "common_sysadmin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void after() {

	}

	@Test
	public void test01Initial() {
		SqlSession session = this.sqlSessionFactory.openSession();
		InternalMapper mapper = session.getMapper(InternalMapper.class);

		mapper.deleteAllCourses();
		mapper.deleteAllTeachers();
		mapper.deleteAllStudents();
		mapper.deleteAllSubjects();

		System.out.println("-----------------------------------------");

		mapper.addSubject(
				new Subject("微积分", "微积分（Calculus）是高等数学中研究函数的微分（Differentiation）、积分(Integration)以及有关概念和应用的数学分支。"));
		mapper.addSubject(new Subject("线性代数", "线性代数是数学的一个分支，它的研究对象是向量，向量空间（或称线性空间），线性变换和有限维的线性方程组"));
		mapper.addSubject(new Subject("离散数学", "离散数学(Discrete mathematics)是研究离散量的结构及其相互关系的数学学科，是现代数学的一个重要分支。"));
		mapper.addSubject(
				new Subject("微机原理", "《微机原理》是一门专业基础课程，它的主要内容包括微型计算机体系结构、8086微处理器和指令系统、汇编语言设计以及微型计算机各个组成部分介绍等内容。"));
		mapper.addSubject(new Subject("C语言",
				"C语言是一门通用计算机编程语言，应用广泛。C语言的设计目标是提供一种能以简易的方式编译、处理低级存储器、产生少量的机器码以及不需要任何运行环境支持便能运行的编程语言。"));
		mapper.addSubject(new Subject("计算机操作系统", "介绍了现代计算机操作系统的基本概念、原理和实现方法。"));
		mapper.addSubject(new Subject("数据结构", "数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。"));
		mapper.addSubject(new Subject("数据库系统原理", "数据库系统原理"));
		mapper.addSubject(new Subject("软件工程", "软件工程"));
		mapper.addSubject(new Subject("计算机网络", "计算机网络"));

		Calendar calendar = Calendar.getInstance();

		calendar.set(1980, 3, 29);
		mapper.addStudent(new Student("FangMing", 0, calendar.getTime()));
		calendar.set(1981, 3, 24);
		mapper.addStudent(new Student("WangXiaoJuan", 1, calendar.getTime()));
		calendar.set(1981, 12, 27);
		mapper.addStudent(new Student("YanXia", 1, calendar.getTime()));
		calendar.set(1981, 7, 01);
		mapper.addStudent(new Student("ZhangYang", 1, calendar.getTime()));
		calendar.set(1983, 11, 12);
		mapper.addStudent(new Student("Amanda", 1, calendar.getTime()));

		calendar.set(1961, 5, 23);
		mapper.addTeacher(new Teacher("苏徳矿", 0, calendar.getTime()));
		calendar.set(1968, 3, 12);
		mapper.addTeacher(new Teacher("翁恺", 0, calendar.getTime()));
		calendar.set(1972, 9, 4);
		mapper.addTeacher(new Teacher("彭笑刚", 0, calendar.getTime()));
		calendar.set(1967, 12, 13);
		mapper.addTeacher(new Teacher("蔡天新", 0, calendar.getTime()));
		calendar.set(1971, 8, 21);
		mapper.addTeacher(new Teacher("吴嘉", 1, calendar.getTime()));
		
		
		List<Student> students = mapper.getAllStudents();
		List<Teacher> teachers = mapper.getAllTeachers();
		List<Subject> subjects = mapper.getAllSubjects();
		List<Course> courses = new ArrayList<Course>();
		
		calendar.set(2004,9,20);
		courses.add(new Course("微积分",1,1,calendar.getTime(),"2004级计算机应用专业一班"));
		calendar.set(2004,9,20);
		courses.add(new Course("C语言",1,5,calendar.getTime(),"2004级计算机应用专业一班"));
		calendar.set(2004,9,20);
		courses.add(new Course("微积分",1,1,calendar.getTime(),"2004级计算机应用专业二班"));
		calendar.set(2004,9,20);
		courses.add(new Course("C语言",1,5,calendar.getTime(),"2004级计算机应用专业一班"));
		calendar.set(2005,9,6);
		courses.add(new Course("线性代数",1,2,calendar.getTime(),"2004级计算机应用专业一班"));
		calendar.set(2005,9,6);
		courses.add(new Course("微机原理",1,3,calendar.getTime(),"2004级计算机应用专业一班"));
		calendar.set(2005,9,6);
		courses.add(new Course("线性代数",1,2,calendar.getTime(),"2004级计算机应用专业二班"));
		calendar.set(2005,9,6);
		courses.add(new Course("微机原理",1,3,calendar.getTime(),"2004级计算机应用专业一班"));

		session.commit();
	}

	@Test
	public void test02CreateTables() {
		SqlSession session = this.sqlSessionFactory.openSession();
		InternalMapper mapper = session.getMapper(InternalMapper.class);
		mapper.createTables();
		// DatabaseMetaData.getDatabaseProductName
		session.commit();
	}

	@Test
	public void test03ExistsTable() {
		SqlSession session = this.sqlSessionFactory.openSession();
		InternalMapper mapper = session.getMapper(InternalMapper.class);
		Map<String, Integer> map01 = mapper.existsTable("courses");

		boolean isExists = false;

		if (map01 != null && map01.containsKey("count")) {
			isExists = ((int) map01.get("count")) == 1 ? true : false;
		}
		System.out.println("existsTable:"+isExists);
		System.out.println(gson.toJson(map01));
		session.commit();
		//DatabaseIdProvider provider = null;
		LanguageDriver ld=null;
	}
	
	@Test
	public void test04GetByModel() {
		SqlSession session = this.sqlSessionFactory.openSession();
		InternalMapper mapper = session.getMapper(InternalMapper.class);
		Student conditions = new Student(Integer.MIN_VALUE,"FangMing",Integer.MIN_VALUE,null);
		List<Student> list = mapper.getAllStudentsByProperties(conditions);
		for(Student e:list)
			System.out.println(e);
		
	}
	
	@Test
	public void test05GetByProperties() {
		SqlSession session = this.sqlSessionFactory.openSession();
		InternalMapper mapper = session.getMapper(InternalMapper.class);
		Map<String, Object> conditions = new HashMap<String,Object>();
		conditions.put("sex",1);
		List<Student> list = mapper.getAllStudentsByProperties02(conditions);
		for(Student e:list)
			System.out.println(e);

	}
	
	@Test
	public void test06Foreach() {
		SqlSession session = this.sqlSessionFactory.openSession();
		InternalMapper mapper = session.getMapper(InternalMapper.class);
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		List<Student> students = mapper.getAllStudentsByIds(list);
		for(Student e:students)
			System.out.println(e);

	}
}
