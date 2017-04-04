package com.fsmflying.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.fsmflying.mybatis.dao.impl.SystemManageDaoImpl;
import com.fsmflying.mybatis.mapper.SysMapper;
import com.fsmflying.sys.dm.SysDictDir;
import com.fsmflying.util.TwoIntegerTuple;
import com.fsmflying.util.TwoTuple;
import com.google.gson.Gson;

public class ConsoleApp {
	static SqlSessionFactory sqlSessionFactory;
	static Gson gson = new Gson();

	public static void before() {
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

	public static void main(String[] args) {
		before();
		SqlSession session = sqlSessionFactory.openSession();
		SysMapper mapper = session.getMapper(SysMapper.class);
//		SystemManageDaoImpl impl = new SystemManageDaoImpl();
//		impl.setSessionFactory(sqlSessionFactory);
//		
//		List<TwoTuple<Integer,Integer>> list = impl.getTabMenuTuples();
//		for (TwoTuple<Integer,Integer> e : list)
//			System.out.println(e);
		
		
		List<SysDictDir> dirs = mapper.getDictDirs();
		System.out.println(gson.toJson(dirs));
	}
}
