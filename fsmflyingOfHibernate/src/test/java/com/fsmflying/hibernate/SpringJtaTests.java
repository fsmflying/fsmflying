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

public class SpringJtaTests {
	AbstractApplicationContext ctx = null;

	SessionFactory sessionFactory = null;

	ILogService logService = null;

	ISystemManageService isms = null;
	
	ISequenceService iss=null;

	IConfigService ics = null;

	Gson gson = new Gson();

	@Before
	public void before() {
		// TODO Auto-generated method stub
		ctx = new ClassPathXmlApplicationContext(new String[] { "springJtaContextConfig.xml" });

		// ILogService
		logService = (ILogService) ctx.getBean("logService");

		// ISystemManageService
		isms = (ISystemManageService) ctx.getBean("systemManageService");
		
		//I
		iss = (ISequenceService) ctx.getBean("sequenceService");

		// IConfigService
		ics = (IConfigService) ctx.getBean("configService");

//		sessionFactory = (SessionFactory) ctx.getBean("mySessionFactory");
	}

	@After
	public void after() {
		ctx.close();
	}

	@Test
	public void test_01() {
		
	}
}
