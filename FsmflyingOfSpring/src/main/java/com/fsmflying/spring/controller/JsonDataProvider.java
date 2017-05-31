package com.fsmflying.spring.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsmflying.http.HttpJsonResult;
import com.fsmflying.spring.auth.AuthHelper;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.service.ISystemManageService;
import com.fsmflying.util.TwoTuple;

@RestController
@RequestMapping("/json")
public class JsonDataProvider {

	@Resource
	ISystemManageService systemManageService;

	@RequestMapping("/getUserTabs")
	@JsonView
	public HttpJsonResult getUserTabs(HttpServletRequest request) {
		HttpJsonResult jsonResult = new HttpJsonResult();
		int userId = AuthHelper.getUserId(request);
		// System.out.println(userId);
		if (userId != -1) {
			jsonResult.setResult(1);
			jsonResult.setRows(systemManageService.getUserTabs(userId));
		} else {
			jsonResult.setResult(0);
			jsonResult.setMessage("未能识别用户信息!");
		}
		return jsonResult;
	}

	@RequestMapping("/getTreeTabMenus")
	@JsonView
	public HttpJsonResult getTreeTabMenus(HttpServletRequest request) {
		HttpJsonResult jsonResult = new HttpJsonResult();

		int userId = AuthHelper.getUserId(request);
		if(systemManageService==null)
		{
			System.out.println(systemManageService);
			return jsonResult;
		}
		if (userId != -1) {
			List<SysTab> tabs = systemManageService.getUserTabs(userId);
			List<SysMenu> menus = systemManageService.getUserMenus(userId);
			List<TwoTuple<Integer, Integer>> pairs = systemManageService.getTabMenuTuples();
			Map<Integer, HashSet<SysMenu>> mapTabs = new HashMap<Integer, HashSet<SysMenu>>();
			for (SysTab tab : tabs) {
				mapTabs.put(tab.getTabId(), new HashSet<SysMenu>());
			}

			for (SysMenu menu : menus) {
				int tabId = -1;
				for (TwoTuple<Integer, Integer> pair : pairs) {
					if (menu.getMenuId() == pair.getSecond()) {
						tabId = pair.getFirst();
						break;
					}
				}
				if (mapTabs.containsKey(tabId)) {
					mapTabs.get(tabId).add(menu);
				}
			}
			for (SysTab tab : tabs) {
				tab.setMenus(mapTabs.get(tab.getTabId()));
			}
			jsonResult.setResult(1);
			jsonResult.setRows(tabs);
		} else {
			jsonResult.setResult(0);
			jsonResult.setMessage("未能识别用户信息!");
		}
		return jsonResult;
	}

	@RequestMapping("/getUserMenus")
	@JsonView
	public HttpJsonResult getUserMenus(HttpServletRequest request) {
		HttpJsonResult jsonResult = new HttpJsonResult();
		int userId = AuthHelper.getUserId(request);
		// System.out.println(userId);
		if (userId != -1) {
			jsonResult.setResult(1);
			jsonResult.setRows(systemManageService.getUserMenus(userId));
		} else {
			jsonResult.setResult(0);
			jsonResult.setMessage("未能识别用户信息!");
		}
		return jsonResult;
	}
}
