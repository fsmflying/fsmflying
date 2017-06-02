package com.fsmflying.spring;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SuppressWarnings({ "unchecked", "static-access" })
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context = null;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return context;
	}

	public static <T> T getBean(String beanName) {
		return (T) context.getBean(beanName);
	}

	public static String getMessage(String key) {
		return context.getMessage(key, null, Locale.getDefault());
	}

}
