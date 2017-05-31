package com.fsmflying.sys.dm.helper;

import java.util.List;

public class UserPermissions {
	private int userId;
	private List<Integer> roles;
	private List<Integer> tabs;
	private List<Integer> menus;
	private List<Integer> funcPoints;
	private List<Integer> dataAuths;
	private List<Integer> dataAuthItems;
	
	public UserPermissions() {
		super();
	}
	
	public UserPermissions(int userId) {
		super();
		this.userId = userId;
	}

	public UserPermissions(int userId,List<Integer> roles, List<Integer> tabs, List<Integer> menus, List<Integer> funcPoints,
			List<Integer> dataAuths, List<Integer> dataAuthItems) {
		super();
		this.userId = userId;
		this.roles = roles;
		this.tabs = tabs;
		this.menus = menus;
		this.funcPoints = funcPoints;
		this.dataAuths = dataAuths;
		this.dataAuthItems = dataAuthItems;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Integer> getRoles() {
		return roles;
	}
	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}
	public List<Integer> getTabs() {
		return tabs;
	}
	public void setTabs(List<Integer> tabs) {
		this.tabs = tabs;
	}
	public List<Integer> getMenus() {
		return menus;
	}
	public void setMenus(List<Integer> menus) {
		this.menus = menus;
	}
	public List<Integer> getFuncPoints() {
		return funcPoints;
	}
	public void setFuncPoints(List<Integer> funcPoints) {
		this.funcPoints = funcPoints;
	}
	public List<Integer> getDataAuths() {
		return dataAuths;
	}
	public void setDataAuths(List<Integer> dataAuths) {
		this.dataAuths = dataAuths;
	}
	public List<Integer> getDataAuthItems() {
		return dataAuthItems;
	}
	public void setDataAuthItems(List<Integer> dataAuthItems) {
		this.dataAuthItems = dataAuthItems;
	}
	
}
