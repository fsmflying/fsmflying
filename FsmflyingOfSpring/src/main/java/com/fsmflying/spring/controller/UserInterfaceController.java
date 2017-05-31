package com.fsmflying.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class UserInterfaceController {
	
	@RequestMapping("/account/login")
	public String goToLoginPage01()
	{
		return "login";
	}
	
	@RequestMapping("/user/login")
	public String goToLoginPage02()
	{
		return "login";
	}
	
	@RequestMapping("/sysadmin/user/add")
	public String goToAddPageOfSysUser()
	{
		return "sysadmin/userAdd";
	}
	
	@RequestMapping("/sysadmin/user/edit")
	public String goToEditPageOfSysUser()
	{
		return "sysadmin/userEdit";
	}
	
	@RequestMapping("/sysadmin/user/list")
	public String goToListPageOfSysUser()
	{
		return "sysadmin/userList";
	}
	
	@RequestMapping("/sysadmin/company/add")
	public String goToAddPageOfSysCompany()
	{
		return "sysadmin/companyAdd";
	}
	
	@RequestMapping("/sysadmin/company/edit")
	public String goToEditPageOfSysCompany()
	{
		return "sysadmin/companyEdit";
	}
	
	@RequestMapping("/sysadmin/company/list")
	public String goToListPageOfSysCompany()
	{
		return "sysadmin/companyList";
	}
	
	@RequestMapping("/sysadmin/department/add")
	public String goToAddPageOfSysDepartment()
	{
		return "sysadmin/departmentAdd";
	}
	
	@RequestMapping("/sysadmin/department/edit")
	public String goToEditPageOfSysDepartment()
	{
		return "sysadmin/departmentEdit";
	}
	
	@RequestMapping("/sysadmin/department/list")
	public String goToListPageOfSysDepartment()
	{
		return "sysadmin/departmentList";
	}
	
	@RequestMapping("/sysadmin/role/add")
	public String goToAddPageOfSysRole()
	{
		return "sysadmin/roleAdd";
	}
	
	@RequestMapping("/sysadmin/role/edit")
	public String goToEditPageOfSysRole()
	{
		return "sysadmin/roleEdit";
	}
	
	@RequestMapping("/sysadmin/role/list")
	public String goToListPageOfSysRole()
	{
		return "sysadmin/roleList";
	}
	
	@RequestMapping("/sysadmin/tab/add")
	public String goToAddPageOfSysTab()
	{
		return "sysadmin/tabAdd";
	}
	
	@RequestMapping("/sysadmin/tab/edit")
	public String goToEditPageOfSysTab()
	{
		return "sysadmin/tabEdit";
	}
	
	@RequestMapping("/sysadmin/tab/list")
	public String goToListPageOfSysTab()
	{
		return "sysadmin/tabList";
	}
	
	@RequestMapping("/sysadmin/menu/add")
	public String goToAddPageOfSysMenu()
	{
		return "sysadmin/menuAdd";
	}
	
	@RequestMapping("/sysadmin/menu/edit")
	public String goToEditPageOfSysMenu()
	{
		return "sysadmin/menuEdit";
	}
	
	@RequestMapping("/sysadmin/menu/list")
	public String goToListPageOfSysMenu()
	{
		return "sysadmin/menuList";
	}
	
	@RequestMapping("/sysadmin/funcPoint/add")
	public String goToAddPageOfSysFuncPoint()
	{
		return "sysadmin/funcPointAdd";
	}
	
	@RequestMapping("/sysadmin/funcPoint/edit")
	public String goToEditPageOfSysFuncPoint()
	{
		return "sysadmin/funcPointEdit";
	}
	
	@RequestMapping("/sysadmin/funcPoint/list")
	public String goToListPageOfFuncPoint()
	{
		return "sysadmin/funcPointList";
	}
	
	@RequestMapping("/sysadmin/dataAuth/add")
	public String goToAddPageOfSysDataAuth()
	{
		return "sysadmin/dataAuthAdd";
	}
	
	@RequestMapping("/sysadmin/dataAuth/edit")
	public String goToEditPageOfSysDataAuth()
	{
		return "sysadmin/dataAuthEdit";
	}
	
	@RequestMapping("/sysadmin/dataAuth/list")
	public String goToListPageOfSysDataAuth()
	{
		return "sysadmin/dataAuthList";
	}
	
	@RequestMapping("/sysadmin/dataAuthItem/add")
	public String goToAddPageOfSysDataAuthItem()
	{
		return "sysadmin/dataAuthItemAdd";
	}
	
	@RequestMapping("/sysadmin/dataAuthItem/edit")
	public String goToEditPageOfSysDataAuthItem()
	{
		return "sysadmin/dataAuthItemEdit";
	}
	
	@RequestMapping("/sysadmin/dataAuthItem/list")
	public String goToListPageOfSysDataAuthItem()
	{
		return "sysadmin/dataAuthItemList";
	}
	
	@RequestMapping("/sysadmin/dictDir/add")
	public String goToAddPageOfSysDictDir()
	{
		return "sysadmin/dictDirAdd";
	}
	
	@RequestMapping("/sysadmin/dictDir/edit")
	public String goToEditPageOfSysDictDir()
	{
		return "sysadmin/dictDirEdit";
	}
	
	@RequestMapping("/sysadmin/dictDir/list")
	public String goToListPageOfSysDictDir()
	{
		return "sysadmin/dictDirList";
	}
	
	@RequestMapping("/sysadmin/dictItem/add")
	public String goToAddPageOfSysDictItem()
	{
		return "sysadmin/dictItemAdd";
	}
	
	@RequestMapping("/sysadmin/dictItem/edit")
	public String goToEditPageOfSysDictItem()
	{
		return "sysadmin/dictItemEdit";
	}
	
	@RequestMapping("/sysadmin/dictItem/list")
	public String goToListPageOfSysDictItem()
	{
		return "sysadmin/dictItemList";
	}
	
	@RequestMapping("/sysadmin/config/add")
	public String goToAddPageOfSysConfig()
	{
		return "sysadmin/configAdd";
	}
	
	@RequestMapping("/sysadmin/config/edit")
	public String goToEditPageOfSysConfig()
	{
		return "sysadmin/configEdit";
	}
	
	@RequestMapping("/sysadmin/config/list")
	public String goToListPageOfSysConfig()
	{
		return "sysadmin/configList";
	}
	
	
}
