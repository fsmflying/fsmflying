package com.fsmflying.spring.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.fsmflying.http.HttpJsonResult;
import com.fsmflying.spring.Spring4WebHelper;
import com.fsmflying.sys.service.IConfigService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Resource
	IConfigService configService;

	@RequestMapping("/getCtxInfo")
	@JsonView
	public HttpJsonResult getApplicationContextInfo(HttpServletRequest request) {
		HttpJsonResult httpJsonResult = new HttpJsonResult();
		WebApplicationContext ctx = Spring4WebHelper.getApplicationContext(request);
		System.out.println(ctx);
		if (ctx != null) {
			String[] beanNames = ctx.getBeanDefinitionNames();
			if (beanNames != null) {
				httpJsonResult.setData(new HashMap<String, Object>());
				httpJsonResult.getData().put("beanNames", beanNames);
				for (String name : beanNames) {
					httpJsonResult.getData().put(name, ctx.getBean(name).getClass().getCanonicalName());
				}
				httpJsonResult.setResult(1);
			}
		}
		return httpJsonResult;
	}

	@RequestMapping("/getConfigs")
	@JsonView
	public HttpJsonResult getConfigs(HttpServletRequest request) {
		HttpJsonResult httpJsonResult = new HttpJsonResult();
		httpJsonResult.setData(new HashMap<String, Object>());
		if (configService != null) {
			httpJsonResult.getData().put("AllConfigs", configService.getAllConfigs());
			httpJsonResult.setResult(1);
		}
		return httpJsonResult;
	}
}
