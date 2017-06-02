package com.fsmflying.mybatis;

import java.io.IOException;
import java.io.InputStream;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;

import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import com.fsmflying.mybatis.dao.impl.SystemManageDaoImpl;
//import com.fsmflying.mybatis.mapper.SysCompanyMapper;
//import com.fsmflying.mybatis.mapper.SysDepartmentMapper;
//import com.fsmflying.mybatis.mapper.SysEmployeeMapper;
//import com.fsmflying.mybatis.mapper.SysMapper;
//import com.fsmflying.mybatis.mapper.SysRoleMapper;
//import com.fsmflying.mybatis.mapper.SysSequenceMapper;
//import com.fsmflying.mybatis.mapper.SysTestMapper;
//import com.fsmflying.mybatis.mapper.SysUserMapper;
//import com.fsmflying.sys.dm.SysCompany;
//import com.fsmflying.sys.dm.SysDepartment;
//import com.fsmflying.sys.dm.SysDictDir;
//import com.fsmflying.sys.dm.SysDictItem;
//import com.fsmflying.sys.dm.SysEmployee;
//import com.fsmflying.sys.dm.SysRole;
//import com.fsmflying.sys.dm.SysSequence;
//import com.fsmflying.sys.dm.SysUser;
//import com.fsmflying.util.IntegerStringTuple;
//import com.fsmflying.util.TwoIntegerTuple;
//import com.fsmflying.util.TwoObjectTuple;
//import com.fsmflying.util.TwoTuple;
import com.google.gson.Gson;

public class MybatisTestsUsingAnnotation {
	SqlSessionFactory sqlSessionFactory;
	Gson gson = new Gson();

	@Before
	public void before() {
		String resource = "mybatis-config2.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "common_sysadmin");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
		// TransactionFactory transactionFactory = new JdbcTransactionFactory();
		// Environment environment = new Environment("development",
		// transactionFactory, dataSource);
		// Configuration configuration = new Configuration(environment);
		// configuration.addMapper(SysUser.class);
		// SqlSessionFactory sqlSessionFactory = new
		// SqlSessionFactoryBuilder().build(configuration);
	}

	@After
	public void after() {

	}

	@Test
	public void test01Initial()
	{
//		SysTestMapper mapper = this.sqlSessionFactory.openSession().getMapper(SysTestMapper.class);
		
	}
}
