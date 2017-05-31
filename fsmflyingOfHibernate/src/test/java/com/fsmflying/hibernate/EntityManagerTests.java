package com.fsmflying.hibernate;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.fsmflying.sys.service.IConfigService;
import com.fsmflying.sys.service.ILogService;
import com.fsmflying.sys.service.ISequenceService;
import com.fsmflying.sys.service.ISystemManageService;
import com.google.gson.Gson;

public class EntityManagerTests {
	
AbstractApplicationContext ctx = null;
	
	SessionFactory sessionFactory = null;
	
	ILogService ils = null;
	
	ISystemManageService isms = null;
	
	IConfigService ics = null;
	
	ISequenceService iss = null;
	
	Gson gson = new Gson();
	
	@Before
	public void before() {
		// TODO Auto-generated method stub
		ctx = new ClassPathXmlApplicationContext(new String[] { "springJpaContextConfig.xml" });
		
//		ILogService 
		ils = (ILogService)ctx.getBean("logService");
		
//		ISystemManageService 
		isms = (ISystemManageService)ctx.getBean("systemManageService");
		
//		IConfigService 
		ics = (IConfigService)ctx.getBean("configService");
		
		iss = (ISequenceService)ctx.getBean("sequenceService");
		
//		sessionFactory = (SessionFactory)ctx.getBean("mySessionFactory");
	}

	@After
	public void after() {
		ctx.close();
	}
	
	
	@Test
	public void test01()
	{
		System.out.println(gson.toJson(isms.getModelOfSysUser(2)));
	}
}
