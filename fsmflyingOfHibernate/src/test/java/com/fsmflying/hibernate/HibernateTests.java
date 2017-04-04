package com.fsmflying.hibernate;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.service.IConfigService;
import com.fsmflying.sys.service.ILogService;
import com.fsmflying.sys.service.ISequenceService;
import com.fsmflying.sys.service.ISystemManageService;
import com.fsmflying.util.TwoTuple;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HibernateTests {

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
		ctx = new ClassPathXmlApplicationContext(new String[] { "springJtaContextConfig.xml" });
		
//		ILogService 
		ils = (ILogService)ctx.getBean("logService");
		
//		ISystemManageService 
		isms = (ISystemManageService)ctx.getBean("systemManageService");
		
//		IConfigService 
		ics = (IConfigService)ctx.getBean("configService");
		
		iss = (ISequenceService)ctx.getBean("sequenceService");
		
		sessionFactory = (SessionFactory)ctx.getBean("mySessionFactory");
	}

	@After
	public void after() {
		ctx.close();
	}
	
	@Test
	public void test_01()
	{
		System.out.println("**test_01****************************************");
		System.out.println("--getModelOfSysUser------------------------------");
		System.out.println(gson.toJson(isms.getModelOfSysUser(2)));
//		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
//		SysUser model = (SysUser)session.createQuery("from SysUser e where e.userId=:userId")
//				.setParameter("userId", 2).uniqueResult();
//		System.out.println(model);
//		session.getTransaction().commit();
	}
	
	@Test
	public void test_02()
	{
		System.out.println("**test_02****************************************");
		List<TwoTuple<Integer,Integer>> pairs = isms.getTabMenuTuples();
		System.out.println(gson.toJson(pairs));
		
//		System.out.println("--getUserRoles-----------------------------------");
//		List<SysRole> roles = isms.getUserRoles(2);
//		System.out.println(gson.toJson(roles));
		
//		System.out.println("--getUserTabs-----------------------------------");
//		List<SysTab> tabs = isms.getUserTabs(2);
//		gson.toJson(tabs);
		
//		System.out.println("--getModelOfSysUser------------------------------");
//		System.out.println(gson.toJson(isms.getModelOfSysUser(2)));
		
//		System.out.println("--getModelOfSysCompany------------------------------");
//		System.out.println(gson.toJson(isms.getModelOfSysCompany(1)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test_03(){
		
//		Session session = sessionFactory.getCurrentSession();
//		session.beginTransaction();
//		List<Object[]> listResults = (List<Object[]>) session
//				.createSQLQuery(
//						"select distinct r.tabId,r.menuId from sys_rtabmenu r inner join sys_tabs t on r.tabId=t.tabId"
//						+" inner join sys_menus m on m.menuId=r.menuId where t.dbdeleted=0 and m.dbdeleted=0")
//				.list();
//		for(Object[] arr:listResults)
//		{
//			System.out.println(arr[0]+"|"+arr[1]);
//		}
//		session.getTransaction().commit();
		
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<SysRole> list = (List<SysRole>) session
				.createSQLQuery(
						"select t.* from sys_roles t inner join sys_ruserrole r on t.roleId=r.roleId where r.userId=:userId and dbdeleted=0")
				.addEntity(SysRole.class)
				.setParameter("userId", 2)
				.list();
//		Hibernate.initialize(list);
		System.out.println(gson.toJson(list));
		session.getTransaction().commit();
		
		
	}
}
