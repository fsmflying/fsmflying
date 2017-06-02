package com.fsmflying.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
//import java.util.Map;
//import java.util.Set;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fsmflying.mybatis.dao.impl.SystemManageDaoImpl;
import com.fsmflying.mybatis.mapper.SysCompanyMapper;
import com.fsmflying.mybatis.mapper.SysDepartmentMapper;
import com.fsmflying.mybatis.mapper.SysEmployeeMapper;
import com.fsmflying.mybatis.mapper.SysMapper;
import com.fsmflying.mybatis.mapper.SysRoleMapper;
import com.fsmflying.mybatis.mapper.SysSequenceMapper;
import com.fsmflying.mybatis.mapper.SysTestMapper;
import com.fsmflying.mybatis.mapper.SysUserMapper;
import com.fsmflying.sys.dm.SysCompany;
import com.fsmflying.sys.dm.SysDepartment;
import com.fsmflying.sys.dm.SysDictDir;
import com.fsmflying.sys.dm.SysEmployee;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysSequence;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.util.IntegerStringTuple;
import com.fsmflying.util.TwoTuple;
import com.google.gson.Gson;

public class MybatisTests {
	SqlSessionFactory sqlSessionFactory;
	Gson gson = new Gson();

	@Before
	public void before() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, "common_sysadmin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	public void test01SysUser() {
		// System.out.println(sqlSessionFactory);
		this.sqlSessionFactory.getConfiguration();
		SqlSession session = this.sqlSessionFactory.openSession();
		SysUserMapper mapper = session.getMapper(SysUserMapper.class);
		SysUser model = mapper.get(2);
		System.out.println(model);
		SysUser model02 = mapper.getByUsername("sysop");
		System.out.println(model02);

		System.out.println("--getList()-------------");
		List<SysUser> list = mapper.getAllList();
		for (SysUser e : list) {
			System.out.println(e);
			break;
		}

	}

	@Test
	public void test02SysRole() {
		// System.out.println(sqlSessionFactory);
		this.sqlSessionFactory.getConfiguration();
		SqlSession session = this.sqlSessionFactory.openSession();
		SysRoleMapper mapper = session.getMapper(SysRoleMapper.class);
		SysRole model = mapper.get(2);
		System.out.println(model);

		System.out.println("--getList()-------------");
		List<SysRole> list = mapper.getAllList();
		for (SysRole e : list) {
			System.out.println(e);
			break;
		}

	}

	@Test
	public void test03SysCompany() {
		// System.out.println(sqlSessionFactory);
		this.sqlSessionFactory.getConfiguration();
		SqlSession session = this.sqlSessionFactory.openSession();
		SysCompanyMapper mapper = session.getMapper(SysCompanyMapper.class);
		SysCompany model = mapper.get(2);
		System.out.println(model);

		System.out.println("--getList()-------------");
		List<SysCompany> list = mapper.getAllList();
		for (SysCompany e : list) {
			System.out.println(e);
			break;
		}

	}

	@Test
	public void test04Others() {
		// System.out.println(sqlSessionFactory);
		this.sqlSessionFactory.getConfiguration();
		SqlSession session = this.sqlSessionFactory.openSession();

		// SysEmployee
		List<SysDepartment> deptlist = session.getMapper(SysDepartmentMapper.class).getAllList();
		for (SysDepartment e : deptlist) {
			System.out.println(e);
			break;
		}

		List<SysEmployee> employeeList = session.getMapper(SysEmployeeMapper.class).getAllList();
		for (SysEmployee e : employeeList) {
			System.out.println(e);
			break;
		}
	}

	@Test
	public void test05Test() {
		// System.out.println(sqlSessionFactory);
		this.sqlSessionFactory.getConfiguration();
		SqlSession session = this.sqlSessionFactory.openSession();

		SysTestMapper mapper = session.getMapper(SysTestMapper.class);

		// SysUser user01 = mapper.get(49);
		// System.out.println(user01);

		// 返回hashmap结果,键为表中的列名，值为查询到相应的列的值
		// Map<String, Object> map01 = mapper.getForMap01(49);
		// Set<String> keys = map01.keySet();
		// System.out.println("--------------------------------------------------------");
		// for(String i:keys)
		// System.out.println(i);
		// System.out.println("--------------------------------------------------------");
		// System.out.println(gson.toJson(map01));
		// System.out.println("--------------------------------------------------------");
		// /**/
		// if (map01 != null && map01.containsKey("Username")) {
		// System.out.println("Username=" + map01.get("Username"));
		// }
		//
		// System.out.println("--getCustomUser01------------------------------------------------------");
		// Map<String,Object> obj01 = mapper.getCustomUser01(2);
		// System.out.println(gson.toJson(obj01));
		//
		// System.out.println("--getCustomUser02--自定义列名1,并将查询结果以Map<String,Object>返回---------------");
		// Map<String,Object> obj02 = mapper.getCustomUser02(2);
		// System.out.println(gson.toJson(obj02));
		//
		// System.out.println("--getCustomUser03--自定义列名2----------------------------------------------------");
		// SysUser obj03 = mapper.getCustomUser03(49);
		// System.out.println(gson.toJson(obj03));
		//
		// System.out.println("--getCustomUser04--查询部分属性----------------------------------------------------");
		// List<SysUser> obj04 = mapper.getCustomUser04();
		// System.out.println(gson.toJson(obj04));
		//
		//
		// System.out.println("--getUserWithRoles-关联查询-----------------------------------------------------");
		// List<SysUser> users01 = mapper.getUserWithRoles();
		// System.out.println(gson.toJson(users01));

		// System.out.println("--getRoles-查询用户的所有角色，一对多关联查询-----------------------------------------------------");
		// List<SysRole> roles01 = mapper.getRoles(2);
		// System.out.println(gson.toJson(roles01));

		// System.out.println("--getUserWithRoles02-一对多嵌套查询-----------------------------------------------------");
		// List<SysUser> users02 = mapper.getUserWithRoles02();
		// System.out.println(gson.toJson(users02));

		// System.out.println("--getUserWithEmployee-一对一关联查询--association---------------------------------------------------");
		// List<SysUser> users04 = mapper.getUserWithEmployee();
		// System.out.println(gson.toJson(users04));

		System.out.println(
				"--getUserWithEmployee02-关联查询--使用collection关联---------------------------------------------------");
		List<SysUser> users05 = mapper.getUserWithEmployee02();
		System.out.println(gson.toJson(users05));

	}

	@Test
	public void test06TestUsingKey() {
		// System.out.println(sqlSessionFactory);
		this.sqlSessionFactory.getConfiguration();
		SqlSession session = this.sqlSessionFactory.openSession();

		String stmtKey01 = "com.fsmflying.mybatis.mapper.SysTestMapper.get";
		SysUser user01 = (SysUser) session.selectOne(stmtKey01, 49);
		System.out.println(user01);

	}

	@Test
	public void test07Sequence() {
		// System.out.println(sqlSessionFactory);
		// this.sqlSessionFactory.getConfiguration();

		String keyName = "test";
		int increment = 1;

		
		SqlSession session = this.sqlSessionFactory.openSession();
		SysSequenceMapper mapper = session.getMapper(SysSequenceMapper.class);
		SysSequence model = mapper.getSequence(keyName);
		
		System.out.println(model);
		
		Date generatedTime = Calendar.getInstance().getTime();
		int value = -1;
		if (model != null) {
			value = model.getNextValue();
			model.setNextValue(value + increment);
			model.setGeneratedTime(generatedTime);
			mapper.saveSequence(model);
			mapper.addSequenceHistory(new SysSequence(keyName, value + increment, generatedTime));

		} else {
			value = 1;
			model = new SysSequence(keyName, value + increment, generatedTime);
			mapper.addSequence(new SysSequence(keyName, value + increment, generatedTime));
			mapper.addSequenceHistory(new SysSequence(keyName, value, generatedTime));
			mapper.addSequenceHistory(new SysSequence(keyName, value + increment, generatedTime));
		}
		session.commit();
		
		System.out.println("nextValue:"+value);
		/**/

	}
	
	public void test08SysMapper(){
//		SqlSession session = this.sqlSessionFactory.openSession();
//		SysMapper mapper = session.getMapper(SysMapper.class);
//		List<TwoIntegerTuple> list = mapper.getTabMenuTuples();
//		for (TwoIntegerTuple e : list)
//			System.out.println(e);
		
//		SqlSession session = sqlSessionFactory.openSession();
//		SysMapper mapper = session.getMapper(SysMapper.class);
		SystemManageDaoImpl impl = new SystemManageDaoImpl();
		impl.setSessionFactory(sqlSessionFactory);
		
		List<TwoTuple<Integer,Integer>> list = impl.getTabMenuTuples();
		for (TwoTuple<Integer,Integer> e : list)
			System.out.println(e);
	}
	
	@Test
	public void test09SysMapper(){
		SqlSession session = sqlSessionFactory.openSession();
		SysMapper mapper = session.getMapper(SysMapper.class);
//		List<SysDictItem> items = mapper.getItemsOfDir("");
		List<SysDictDir> dirs = mapper.getDictDirs();
		System.out.println(gson.toJson(dirs));
	}
	
	@Test
	public void test10TestMapperIds()
	{
		SqlSession session = sqlSessionFactory.openSession();
		SysMapper mapper = session.getMapper(SysMapper.class);
//		List<SysDictItem> items = mapper.getItemsOfDir("");
		List<Integer> list = mapper.getTestIds();
		System.out.println(gson.toJson(list));
	}
	
	@Test
	public void test11GetUserPermissionIds()
	{
		SqlSession session = sqlSessionFactory.openSession();
		SysMapper mapper = session.getMapper(SysMapper.class);
//		List<SysDictItem> items = mapper.getItemsOfDir("");
		List<IntegerStringTuple> list = mapper.getUserPermissioinIds(2);
		System.out.println(gson.toJson(list));
	}
}
