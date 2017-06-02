package com.fsmflying.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
//import org.apache.ibatis.annotations.Select;

import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysDictDir;
import com.fsmflying.sys.dm.SysDictItem;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysTab;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.util.IntegerStringTuple;
import com.fsmflying.util.TwoIntegerTuple;
//import com.fsmflying.util.TwoTuple;
//import com.fsmflying.util.TwoObjectTuple;

@CacheNamespace(readWrite = false)
public interface SysMapper {

//	@Select("select * from sys_users where username=#{username}")
	public SysUser getModelOfSysUser(String username);

//	@Select("select b.* from sys_ruserrole r" + " inner join sys_roles b on r.roleId=b.roleId"
//			+ " where b.dbDeleted=0 and a.userId=#{userId}")
	public List<SysRole> getUserRoles(int userId);

//	@Select("select t.* from sys_tabs t inner join (" + " select distinct tabid from sys_ruserrole a"
//			+ " inner join sys_roles b on a.roleid=b.roleid" + " inner join sys_rroletab c on a.roleid=c.roleid"
//			+ " where b.dbdeleted=0 and a.userid=#{userId}" + ") r on t.tabid=r.tabid where dbdeleted=0")
	public List<SysTab> getUserTabs(int userId);

//	@Select("select t.* from sys_menus t inner join ("
//			+ " select distinct menuid from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
//			+ " inner join sys_rrolemenu c on a.roleid=c.roleid" + " where b.dbdeleted=0 and a.userid=#{userId}"
//			+ ") r on t.menuid=r.menuid where dbdeleted=0")
	public List<SysMenu> getUserMenus(int userId);
	
	
//	@Select("select t.* from sys_funcpoints t inner join ("
//						+ " select distinct funcPointId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
//						+ " inner join sys_rrolefuncpoint c on a.roleid=c.roleid"
//						+ " where b.dbdeleted=0 and a.userid=#{userid}"
//						+ ") r on t.funcPointId=r.funcPointId where dbdeleted=0")
	public List<SysFuncPoint> getUserFuncPoints(int userId);
	
//	@Select("select t.* from sys_dataauths t inner join ("
//			+ " select distinct authId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
//			+ " inner join sys_rroledataauth c on a.roleid=c.roleid"
//			+ " where b.dbdeleted=0 and a.userid=#{userId}"
//			+ ") r on t.authId=r.authId where dbdeleted=0 and t.disabled=0")
	public List<SysDataAuth> getUserDataAuths(int userId);
	
//	@Select("select t.* from sys_dataauthitems t inner join ("
//			+ " select distinct authId from sys_ruserrole a inner join sys_roles b on a.roleid=b.roleid"
//			+ " inner join sys_rroledataauth c on a.roleid=c.roleid"
//			+ " where b.dbdeleted=0 and a.userid=#{userId}" + ") r on t.authItemId=r.authItemId"
//			+ " inner join sys_dataauths s on t.authId=s.authId"
//			+ " where t.dbdeleted=0 and s.dbdeleted=0 and s.disabled=0")
	public List<SysDataAuthItem> getUserDataAuthItems(int userId);
	
//	@Select("select t.* from sys_menus t"
//			+ " inner join sys_rtabmenu r on t.menuid=r.menuid"
//			+ " where dbdeleted=0 and r.tabId=#{tabId}")	
	public List<SysMenu> getTabMenus(int tabId);
	
	
//	@Select("select distinct r.tabId as first,r.menuId as second from sys_rtabmenu r"
//			+ " inner join sys_tabs t on r.tabId=t.tabId"
//			+ " inner join sys_menus m on m.menuId=r.menuId where t.dbdeleted=0 and m.dbdeleted=0")
	public List<TwoIntegerTuple> getTabMenuTuples();
	
	public List<SysDictItem> getItemsOfDir(String dirKeyCode);
	
	public List<SysDictDir> getDictDirs();
	
	public List<Integer> getTestIds();
	
	public List<IntegerStringTuple> getUserPermissioinIds(int userId);

}
