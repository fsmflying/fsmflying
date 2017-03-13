package com.fsmflying.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsmflying.http.HttpJsonResult;
import com.fsmflying.spring.auth.AuthHelper;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.service.SystemManageService;
import com.fsmflying.util.TwoTuple;


@RestController
@RequestMapping("/json")
public class JsonDataProvider {

	@Resource
	SystemManageService systemManageService;
	
	@RequestMapping("getUserTabs")
	public HttpJsonResult getUserTabs(HttpServletRequest request)
	{
		HttpJsonResult jsonResult = new HttpJsonResult();
		int userId = AuthHelper.getUserId(request);
//		System.out.println(userId);
		if (userId != -1) {
			jsonResult.setResult(1);
			jsonResult.setRows(systemManageService.getUserTabs(userId));
		}
		else{
			jsonResult.setResult(0);
			jsonResult.setMessage("未能识别用户信息!");
		}
		return jsonResult;
	}
	
	@RequestMapping("/getTreeTabMenus")
	public HttpJsonResult getTreeTabMenus(HttpServletRequest request)
	{
		HttpJsonResult jsonResult = new HttpJsonResult();
		
		int userId = AuthHelper.getUserId(request);
		List<SysTab> tabs = systemManageService.getUserTabs(userId);
		List<SysMenu> menus = systemManageService.getUserMenus(userId);
		List<TwoTuple<Integer,Integer>> pairs = systemManageService.getUserTabMenus();
		
		Map<Integer,HashSet<SysMenu>> mapTabs = new HashMap<Integer,HashSet<SysMenu>>();
		
		for(SysTab tab:tabs)
		{			
			mapTabs.put(tab.getTabId(), new HashSet<SysMenu>());
		}
		
		for(SysMenu menu:menus)
		{	
			int tabId = -1;
			for(TwoTuple<Integer,Integer> pair:pairs)
			{
				if(menu.getMenuId()==pair.getSecond())
				{
					tabId=pair.getFirst();
					break;
				}
			}
			
			if(mapTabs.containsKey(tabId))
			{
				mapTabs.get(tabId).add(menu);
			}
		}
		
		for(SysTab tab:tabs)
		{			
			tab.setMenus(mapTabs.get(tab.getTabId()));
		}

		if (userId != -1) {
			jsonResult.setResult(1);
			jsonResult.setRows(tabs);
		}
		else{
			jsonResult.setResult(0);
			jsonResult.setMessage("未能识别用户信息!");
		}
		return jsonResult;
	}
	
	@RequestMapping("getUserMenus")
	public HttpJsonResult getUserMenus(HttpServletRequest request)
	{
		HttpJsonResult jsonResult = new HttpJsonResult();
		int userId = AuthHelper.getUserId(request);
//		System.out.println(userId);
		if (userId != -1) {
			jsonResult.setResult(1);
			jsonResult.setRows(systemManageService.getUserMenus(userId));
		}
		else{
			jsonResult.setResult(0);
			jsonResult.setMessage("未能识别用户信息!");
		}
		return jsonResult;
	}
}
