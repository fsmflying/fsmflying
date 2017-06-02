package com.fsmflying.spring.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration  
@ComponentScan(basePackages={"fsmflying.commonweb"})  
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter{
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new AuthInterceptor());
		//super.addInterceptors(registry);
	}
}
