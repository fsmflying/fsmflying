package com.fsmflying.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.fsmflying.sys.dao.SystemManageDao;
import com.fsmflying.sys.dm.SysArea;
import com.fsmflying.sys.dm.SysCompany;
import com.fsmflying.sys.dm.SysConfig;
import com.fsmflying.sys.dm.SysCustomPage;
import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysDataAuthItem;
import com.fsmflying.sys.dm.SysDepartment;
import com.fsmflying.sys.dm.SysDictDir;
import com.fsmflying.sys.dm.SysDictItem;
import com.fsmflying.sys.dm.SysEmployee;
import com.fsmflying.sys.dm.SysFile;
import com.fsmflying.sys.dm.SysFuncPoint;
import com.fsmflying.sys.dm.SysIOTemplate;
import com.fsmflying.sys.dm.SysIOTemplateColumn;
import com.fsmflying.sys.dm.SysIOTemplateField;
import com.fsmflying.sys.dm.SysLog;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysRole;
import com.fsmflying.sys.dm.SysSequence;
import com.fsmflying.sys.dm.SysTab;
import com.fsmflying.sys.dm.SysUser;
import com.fsmflying.sys.dm.helper.UserRightSet;
import com.fsmflying.util.TwoTuple;

//import org.springframework

public class SystemManageDaoImpl implements SystemManageDao {

	JdbcTemplate mJdbcTemplate = new JdbcTemplate();

	@Override
	public void setDataSource(DataSource ds) {
		this.mJdbcTemplate.setDataSource(ds);
	}

	@Override
	public boolean add(SysUser model) {
		this.mJdbcTemplate.update(
				"insert into sys_users(UserId,Username,UserPwd,Nickname,IPPolicy,IPAddress,RegisterTime,LastLoginTime,"
						+ "Status,DisabledTime,DisabledMinutes,PwdPromptQuestion,PwdProtectQuestion,PwdProtectAnswer,"
						+ "Email,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getUserId(), model.getUsername(), model.getUserPwd(), model.getNickname(), model.getIPPolicy(),
				model.getIPAddress(), model.getRegisterTime(), model.getLastLoginTime(), model.getStatus(),
				model.getDisabledTime(), model.getDisabledMinutes(), model.getPwdPromptQuestion(),
				model.getPwdProtectQuestion(), model.getPwdProtectAnswer(), model.getEmail(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbCreateTime(), model.getDbLastUpdateBy(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysUser model) {

		this.mJdbcTemplate.update(
				"update sys_users set Username=?,UserPwd=?,Nickname=?,IPPolicy=?,IPAddress=?,RegisterTime=?,"
						+ "LastLoginTime=?,Status=?,DisabledTime=?,DisabledMinutes=?,PwdPromptQuestion=?,"
						+ "PwdProtectQuestion=?,PwdProtectAnswer=?,Email=?,DbDeleted=?,DbCreateBy=?,"
						+ "DbCreateTime=?,DbLastUpdateBy=?,DbLastUpdateTime=? " + "where 1=1 and UserId=?",
				model.getUsername(), model.getUserPwd(), model.getNickname(), model.getIPPolicy(), model.getIPAddress(),
				model.getRegisterTime(), model.getLastLoginTime(), model.getStatus(), model.getDisabledTime(),
				model.getDisabledMinutes(), model.getPwdPromptQuestion(), model.getPwdProtectQuestion(),
				model.getPwdProtectAnswer(), model.getEmail(), model.getDbDeleted(), model.getDbCreateBy(),
				model.getDbCreateTime(), model.getDbLastUpdateBy(), model.getDbLastUpdateTime(), model.getUserId()

		);
		return true;
		// return false;
	}

	@Override
	public boolean deleteModelOfSysUser(int userId) {
		this.mJdbcTemplate.update("delete from  sys_users where  1=1 and UserId=?", userId);
		return true;
	}

	class SysUserMapper implements RowMapper<SysUser> {
		public SysUser mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysUser model = new SysUser();
			model.setUserId(rs.getInt("UserId"));
			model.setUsername(rs.getString("Username"));
			model.setUserPwd(rs.getString("UserPwd"));
			model.setNickname(rs.getString("Nickname"));
			model.setEmail(rs.getString("Email"));
			model.setIPPolicy(rs.getInt("IPPolicy"));
			model.setIPAddress(rs.getString("IPAddress"));
			model.setRegisterTime(rs.getTimestamp("RegisterTime"));
			model.setLastLoginTime(rs.getTimestamp("LastLoginTime"));
			model.setStatus(rs.getInt("Status"));
			model.setDisabledTime(rs.getTimestamp("DisabledTime"));
			model.setDisabledMinutes(rs.getInt("DisabledMinutes"));
			model.setPwdPromptQuestion(rs.getString("PwdPromptQuestion"));
			model.setPwdProtectQuestion(rs.getString("PwdProtectQuestion"));
			model.setPwdProtectAnswer(rs.getString("PwdProtectAnswer"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysUser getModelOfSysUser(int userId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_users where  1=1 and UserId=?",
				new Object[] { userId }, new SysUserMapper());
	}

	@Override
	public SysUser getModelOfSysUser(String username) {
		return this.mJdbcTemplate.queryForObject("select * from sys_users where  1=1 and Username=?",
				new Object[] { username }, new SysUserMapper());
	}

	@Override
	public List<SysUser> getListOfSysUser() {
		return this.mJdbcTemplate.query("select * from sys_users where dbdeleted=0", new SysUserMapper());
	}

	@Override
	public boolean add(SysRole model) {
		this.mJdbcTemplate.update(
				"insert into sys_roles(RoleId,RoleName,KeyCode,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?)",
				model.getRoleId(), model.getRoleName(), model.getKeyCode(), model.getMemo(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysRole model) {
		this.mJdbcTemplate.update(
				"update sys_roles set RoleName=?,KeyCode=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and RoleId=?",
				model.getRoleName(), model.getKeyCode(), model.getMemo(), model.getDbDeleted(), model.getDbCreateBy(),
				model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(), model.getRoleId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysRole(int roleId) {
		this.mJdbcTemplate.update("delete from  sys_roles where  1=1 and RoleId=?", roleId);
		return true;
	}

	class SysRoleMapper implements RowMapper<SysRole> {
		public SysRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysRole model = new SysRole();
			model.setRoleId(rs.getInt("RoleId"));
			model.setRoleName(rs.getString("RoleName"));
			model.setKeyCode(rs.getString("KeyCode"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysRole getModelOfSysRole(int roleId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_roles where  1=1 and RoleId=?",
				new Object[] { roleId }, new SysRoleMapper());
	}

	@Override
	public List<SysRole> getListOfSysRole() {
		return this.mJdbcTemplate.query("select * from sys_roles where dbdeleted=0", new SysRoleMapper());
	}

	@Override
	public boolean add(SysCompany model) {
		this.mJdbcTemplate.update(
				"insert into sys_companys(CompanyId,PCompanyId,CompanyNo,AutoNo,ShortName,FullName,RegisterationDate,ShowOrder,"
						+ "Contacts,ContactAddress,ContactPhone,ContactMPhone,ContactFax,ContactEmail,ContactPostalCode,"
						+ "BusinessScope,Flag,Memo,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getCompanyId(), model.getParentCompanyId(), model.getCompanyNo(), model.getAutoNo(),
				model.getShortName(), model.getFullName(), model.getRegisterationDate(), model.getShowOrder(),
				model.getContactPerson(), model.getContactAddress(), model.getContactPhone(), // model.getContactMPhone(),
				model.getContactFax(), model.getContactEmail(), model.getContactPostalCode(),
				model.getScopeOfBusiness(), model.getFlag(), model.getMemo(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbCreateTime(), model.getDbLastUpdateBy(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysCompany model) {
		this.mJdbcTemplate.update(
				"update sys_companys set ParentCompanyId=?,CompanyNo=?,AutoNo=?,ShortName=?,FullName=?,RegisterationDate=?,ShowOrder=?,"
						+ "ContactPerson=?,ContactAddress=?,ContactPhone=?,ContactFax=?,ContactEmail=?,ContactPostalCode=?,"
						+ "ScopeOfBusiness=?,Flag=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbCreateTime=?,DbLastUpdateBy=?,DbLastUpdateTime=? "
						+ "where 1=1 and CompanyId=?",
				model.getParentCompanyId(), model.getCompanyNo(), model.getAutoNo(), model.getShortName(),
				model.getFullName(), model.getRegisterationDate(), model.getShowOrder(), model.getContactPerson(),
				model.getContactAddress(), model.getContactPhone(),
				/* model.getContactMPhone(), */model.getContactFax(), model.getContactEmail(),
				model.getContactPostalCode(), model.getScopeOfBusiness(), model.getFlag(), model.getMemo(),
				model.getDbDeleted(), model.getDbCreateBy(), model.getDbCreateTime(), model.getDbLastUpdateBy(),
				model.getDbLastUpdateTime(), model.getCompanyId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysCompany(int companyId) {
		this.mJdbcTemplate.update("delete from  sys_companys where  1=1 and CompanyId=?", companyId);
		return true;
	}

	class SysCompanyMapper implements RowMapper<SysCompany> {
		public SysCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysCompany model = new SysCompany();
			model.setCompanyId(rs.getInt("CompanyId"));
			model.setParentCompanyId(rs.getInt("ParentCompanyId"));
			model.setCompanyNo(rs.getString("CompanyNo"));
			model.setAutoNo(rs.getString("AutoNo"));
			model.setShortName(rs.getString("ShortName"));
			model.setFullName(rs.getString("FullName"));
			model.setRegisterationDate(rs.getDate("RegisterationDate"));
			model.setShowOrder(rs.getInt("ShowOrder"));
			model.setContactPerson(rs.getString("ContactPerson"));
			model.setContactAddress(rs.getString("ContactAddress"));
			model.setContactPhone(rs.getString("ContactPhone"));
			// model.setContactMPhone(rs.getString("ContactMPhone"));
			model.setContactFax(rs.getString("ContactFax"));
			model.setContactEmail(rs.getString("ContactEmail"));
			model.setContactPostalCode(rs.getString("ContactPostalCode"));
			model.setScopeOfBusiness(rs.getString("ScopeOfBusiness"));
			model.setFlag(rs.getInt("Flag"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysCompany getModelOfSysCompany(int companyId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_companys where  1=1 and CompanyId=?",
				new Object[] { companyId }, new SysCompanyMapper());
	}

	@Override
	public List<SysCompany> getListOfSysCompany() {
		return this.mJdbcTemplate.query("select * from sys_companys where dbdeleted=0", new SysCompanyMapper());
	}

	@Override
	public boolean add(SysDepartment model) {
		this.mJdbcTemplate.update(
				"insert into sys_departments(DeptId,PDeptId,CompanyId,DeptName,DeptNo,LevelDepth,AutoNo,ShowOrder,IsLeaf,Flag,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getDeptId(), model.getParentDeptId(), model.getCompanyId(), model.getDeptName(),
				model.getDeptNo(), model.getLevelDepth(), model.getAutoNo(), model.getShowOrder(), model.getIsLeaf(),
				model.getFlag(), model.getDbDeleted(), model.getDbCreateBy(), model.getDbCreateTime(),
				model.getDbLastUpdateBy(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysDepartment model) {
		this.mJdbcTemplate.update(
				"update sys_departments set PDeptId=?,CompanyId=?,DeptName=?,DeptNo=?,LevelDepth=?,AutoNo=?,ShowOrder=?,IsLeaf=?,Flag=?,DbDeleted=?,DbCreateBy=?,DbCreateTime=?,DbLastUpdateBy=?,DbLastUpdateTime=? where 1=1 and DeptId=?",
				model.getParentDeptId(), model.getCompanyId(), model.getDeptName(), model.getDeptNo(),
				model.getLevelDepth(), model.getAutoNo(), model.getShowOrder(), model.getIsLeaf(), model.getFlag(),
				model.getDbDeleted(), model.getDbCreateBy(), model.getDbCreateTime(), model.getDbLastUpdateBy(),
				model.getDbLastUpdateTime(), model.getDeptId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDepartment(int deptId) {
		this.mJdbcTemplate.update("delete from  sys_departments where  1=1 and DeptId=?", deptId);
		return true;
	}

	class SysDepartmentMapper implements RowMapper<SysDepartment> {
		public SysDepartment mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysDepartment model = new SysDepartment();
			model.setDeptId(rs.getInt("DeptId"));
			model.setParentDeptId(rs.getInt("PDeptId"));
			model.setCompanyId(rs.getInt("CompanyId"));
			model.setDeptName(rs.getString("DeptName"));
			model.setDeptNo(rs.getString("DeptNo"));
			model.setLevelDepth(rs.getInt("LevelDepth"));
			model.setAutoNo(rs.getString("AutoNo"));
			model.setShowOrder(rs.getInt("ShowOrder"));
			model.setIsLeaf(rs.getInt("IsLeaf"));
			model.setFlag(rs.getInt("Flag"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDepartment getModelOfSysDepartment(int deptId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_departments where  1=1 and DeptId=?",
				new Object[] { deptId }, new SysDepartmentMapper());
	}

	@Override
	public List<SysDepartment> getListOfSysDepartment() {
		return this.mJdbcTemplate.query("select * from sys_departments where dbdeleted=0", new SysDepartmentMapper());
	}

	@Override
	public boolean add(SysEmployee model) {
		this.mJdbcTemplate.update(
				"insert into sys_employees(EmplId,CompanyId,EmplNo,EmplName_CN,EmplName_EN,Sex,Birthdate,NativePlace,CardId,ContactPhone,ContactMPhone,ContactAddress,ContactFax,ContactEmail,PositionId,PositionName,TitleName,ShowOrder,AutoNo,DeptId,Field001,Field002,Field003,Field004,Field005,Flag,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getEmplId(), model.getCompanyId(), model.getEmplNo(), model.getEmplName_CN(),
				model.getEmplName_EN(), model.getSex(), model.getBirthdate(), model.getNativePlace(), model.getCardId(),
				model.getContactPhone(), model.getContactMPhone(), model.getContactAddress(), model.getContactFax(),
				model.getContactEmail(), model.getPositionId(), model.getPositionName(), model.getTitleName(),
				model.getShowOrder(), model.getAutoNo(), model.getDeptId(), model.getField001(), model.getField002(),
				model.getField003(), model.getField004(), model.getField005(), model.getFlag(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysEmployee model) {
		this.mJdbcTemplate.update(
				"update sys_employees set CompanyId=?,EmplNo=?,EmplName_CN=?,EmplName_EN=?,Sex=?,Birthdate=?,NativePlace=?,CardId=?,ContactPhone=?,ContactMPhone=?,ContactAddress=?,ContactFax=?,ContactEmail=?,PositionId=?,PositionName=?,TitleName=?,ShowOrder=?,AutoNo=?,DeptId=?,Field001=?,Field002=?,Field003=?,Field004=?,Field005=?,Flag=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and EmplId=?",
				model.getCompanyId(), model.getEmplNo(), model.getEmplName_CN(), model.getEmplName_EN(), model.getSex(),
				model.getBirthdate(), model.getNativePlace(), model.getCardId(), model.getContactPhone(),
				model.getContactMPhone(), model.getContactAddress(), model.getContactFax(), model.getContactEmail(),
				model.getPositionId(), model.getPositionName(), model.getTitleName(), model.getShowOrder(),
				model.getAutoNo(), model.getDeptId(), model.getField001(), model.getField002(), model.getField003(),
				model.getField004(), model.getField005(), model.getFlag(), model.getDbDeleted(), model.getDbCreateBy(),
				model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(), model.getEmplId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysEmployee(int emplId) {
		this.mJdbcTemplate.update("delete from  sys_employees where  1=1 and EmplId=?", emplId);
		return true;
	}

	class SysEmployeeMapper implements RowMapper<SysEmployee> {
		public SysEmployee mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysEmployee model = new SysEmployee();
			model.setEmplId(rs.getInt("EmplId"));
			model.setCompanyId(rs.getInt("CompanyId"));
			model.setEmplNo(rs.getString("EmplNo"));
			model.setEmplName_CN(rs.getString("EmplName_CN"));
			model.setEmplName_EN(rs.getString("EmplName_EN"));
			model.setSex(rs.getInt("Sex"));
			model.setBirthdate(rs.getDate("Birthdate"));
			model.setNativePlace(rs.getString("NativePlace"));
			model.setCardId(rs.getString("CardId"));
			model.setContactPhone(rs.getString("ContactPhone"));
			model.setContactMPhone(rs.getString("ContactMPhone"));
			model.setContactAddress(rs.getString("ContactAddress"));
			model.setContactFax(rs.getString("ContactFax"));
			model.setContactEmail(rs.getString("ContactEmail"));
			model.setPositionId(rs.getInt("PositionId"));
			model.setPositionName(rs.getString("PositionName"));
			model.setTitleName(rs.getString("TitleName"));
			model.setShowOrder(rs.getInt("ShowOrder"));
			model.setAutoNo(rs.getString("AutoNo"));
			model.setDeptId(rs.getInt("DeptId"));
			model.setField001(rs.getString("Field001"));
			model.setField002(rs.getString("Field002"));
			model.setField003(rs.getString("Field003"));
			model.setField004(rs.getString("Field004"));
			model.setField005(rs.getString("Field005"));
			model.setFlag(rs.getInt("Flag"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysEmployee getModelOfSysEmployee(int emplId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_employees where  1=1 and EmplId=?",
				new Object[] { emplId }, new SysEmployeeMapper());
	}

	@Override
	public List<SysEmployee> getListOfSysEmployee() {
		return this.mJdbcTemplate.query("select * from sys_employees where dbdeleted=0", new SysEmployeeMapper());
	}

	@Override
	public boolean add(SysTab model) {
		this.mJdbcTemplate.update(
				"insert into sys_tabs(TabId,TabName,ParentTabId,ShowOrder,DefaultUrl,TabType,LevelDepth,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getTabId(), model.getTabName(), model.getParentTabId(), model.getShowOrder(),
				model.getDefaultUrl(), model.getTabType(), model.getLevelDepth(), model.getMemo(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysTab model) {
		this.mJdbcTemplate.update(
				"update sys_tabs set TabName=?,ParentTabId=?,ShowOrder=?,DefaultUrl=?,TabType=?,LevelDepth=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and TabId=?",
				model.getTabName(), model.getParentTabId(), model.getShowOrder(), model.getDefaultUrl(),
				model.getTabType(), model.getLevelDepth(), model.getMemo(), model.getDbDeleted(), model.getDbCreateBy(),
				model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(), model.getTabId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysTab(int tabId) {
		this.mJdbcTemplate.update("delete from  sys_tabs where  1=1 and TabId=?", tabId);
		return true;
	}

	class SysTabMapper implements RowMapper<SysTab> {
		public SysTab mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysTab model = new SysTab();
			model.setTabId(rs.getInt("TabId"));
			model.setTabName(rs.getString("TabName"));
			model.setParentTabId(rs.getInt("ParentTabId"));
			model.setShowOrder(rs.getInt("ShowOrder"));
			model.setDefaultUrl(rs.getString("DefaultUrl"));
			model.setTabType(rs.getInt("TabType"));
			model.setLevelDepth(rs.getInt("LevelDepth"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysTab getModelOfSysTab(int tabId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_tabs where  1=1 and TabId=?",
				new Object[] { tabId }, new SysTabMapper());
	}

	@Override
	public List<SysTab> getListOfSysTab() {
		return this.mJdbcTemplate.query("select * from sys_tabs where dbdeleted=0", new SysTabMapper());
	}

	@Override
	public boolean add(SysMenu model) {
		this.mJdbcTemplate.update(
				"insert into sys_menus(MenuId,ParentMenuId,MenuName,DefaultUrl,MenuType,ShowOrder,LevelDepth,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getMenuId(), model.getParentMenuId(), model.getMenuName(), model.getDefaultUrl(),
				model.getMenuType(), model.getShowOrder(), model.getLevelDepth(), model.getMemo(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysMenu model) {
		this.mJdbcTemplate.update(
				"update sys_menus set ParentMenuId=?,MenuName=?,DefaultUrl=?,MenuType=?,ShowOrder=?,LevelDepth=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and MenuId=?",
				model.getParentMenuId(), model.getMenuName(), model.getDefaultUrl(), model.getMenuType(),
				model.getShowOrder(), model.getLevelDepth(), model.getMemo(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(),
				model.getMenuId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysMenu(int menuId) {
		this.mJdbcTemplate.update("delete from  sys_menus where  1=1 and MenuId=?", menuId);
		return true;
	}

	class SysMenuMapper implements RowMapper<SysMenu> {
		public SysMenu mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysMenu model = new SysMenu();
			model.setMenuId(rs.getInt("MenuId"));
			model.setParentMenuId(rs.getInt("ParentMenuId"));
			model.setMenuName(rs.getString("MenuName"));
			model.setDefaultUrl(rs.getString("DefaultUrl"));
			model.setMenuType(rs.getInt("MenuType"));
			model.setShowOrder(rs.getInt("ShowOrder"));
			model.setLevelDepth(rs.getInt("LevelDepth"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysMenu getModelOfSysMenu(int menuId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_menus where  1=1 and MenuId=?",
				new Object[] { menuId }, new SysMenuMapper());
	}

	@Override
	public List<SysMenu> getListOfSysMenu() {
		return this.mJdbcTemplate.query("select * from sys_menus where dbdeleted=0", new SysMenuMapper());
	}

	@Override
	public boolean add(SysFuncPoint model) {
		this.mJdbcTemplate.update(
				"insert into sys_funcpoints(FuncPointId,FuncPointName,KeyCode,ShowOrder,MenuId,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?)",
				model.getFuncPointId(), model.getFuncPointName(), model.getKeyCode(), model.getShowOrder(),
				model.getMenuId(), model.getMemo(), model.getDbDeleted(), model.getDbCreateBy(),
				model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysFuncPoint model) {
		this.mJdbcTemplate.update(
				"update sys_funcpoints set FuncPointName=?,KeyCode=?,ShowOrder=?,MenuId=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and FuncPointId=?",
				model.getFuncPointName(), model.getKeyCode(), model.getShowOrder(), model.getMenuId(), model.getMemo(),
				model.getDbDeleted(), model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(),
				model.getDbLastUpdateTime(), model.getFuncPointId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysFuncPoint(int funcPointId) {
		this.mJdbcTemplate.update("delete from  sys_funcpoints where  1=1 and FuncPointId=?", funcPointId);
		return true;
	}

	class SysFuncPointMapper implements RowMapper<SysFuncPoint> {
		public SysFuncPoint mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysFuncPoint model = new SysFuncPoint();
			model.setFuncPointId(rs.getInt("FuncPointId"));
			model.setFuncPointName(rs.getString("FuncPointName"));
			model.setKeyCode(rs.getString("KeyCode"));
			model.setShowOrder(rs.getInt("ShowOrder"));
			model.setMenuId(rs.getInt("MenuId"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysFuncPoint getModelOfSysFuncPoint(int funcPointId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_funcpoints where  1=1 and FuncPointId=?",
				new Object[] { funcPointId }, new SysFuncPointMapper());
	}

	@Override
	public List<SysFuncPoint> getListOfSysFuncPoint() {
		return this.mJdbcTemplate.query("select * from sys_funcpoints where dbdeleted=0", new SysFuncPointMapper());
	}

	@Override
	public boolean add(SysConfig model) {
		this.mJdbcTemplate.update(
				"insert into sys_configs(ConfigId,ConfigName,ConfigKey,ConfigValue,Memo,ParentConfigId,Disabled,LevelDepth,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getConfigId(), model.getConfigName(), model.getConfigKey(), model.getConfigValue(),
				model.getMemo(), model.getParentConfigId(), model.getDisabled(), model.getLevelDepth(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysConfig model) {
		this.mJdbcTemplate.update(
				"update sys_configs set ConfigName=?,ConfigKey=?,ConfigValue=?,Memo=?,ParentConfigId=?,Disabled=?,LevelDepth=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and ConfigId=?",
				model.getConfigName(), model.getConfigKey(), model.getConfigValue(), model.getMemo(),
				model.getParentConfigId(), model.getDisabled(), model.getLevelDepth(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(),
				model.getConfigId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysConfig(int configId) {
		this.mJdbcTemplate.update("delete from  sys_configs where  1=1 and ConfigId=?", configId);
		return true;
	}

	class SysConfigMapper implements RowMapper<SysConfig> {
		public SysConfig mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysConfig model = new SysConfig();
			model.setConfigId(rs.getInt("ConfigId"));
			model.setConfigName(rs.getString("ConfigName"));
			model.setConfigKey(rs.getString("ConfigKey"));
			model.setConfigValue(rs.getString("ConfigValue"));
			model.setMemo(rs.getString("Memo"));
			model.setParentConfigId(rs.getInt("ParentConfigId"));
			model.setDisabled(rs.getInt("Disabled"));
			model.setLevelDepth(rs.getInt("LevelDepth"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysConfig getModelOfSysConfig(int configId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_configs where  1=1 and ConfigId=?",
				new Object[] { configId }, new SysConfigMapper());
	}

	@Override
	public List<SysConfig> getListOfSysConfig() {
		return this.mJdbcTemplate.query("select * from sys_configs where dbdeleted=0", new SysConfigMapper());
	}

	@Override
	public boolean add(SysCustomPage model) {
		this.mJdbcTemplate.update(
				"insert into sys_custompages(CustomPageId,CustomPageName,AppName,SourceUrl,DestinationUrl,TranslationType,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?)",
				model.getCustomPageId(), model.getCustomPageName(), model.getAppName(), model.getSourceUrl(),
				model.getDestinationUrl(), model.getTranslationType(), model.getMemo(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysCustomPage model) {
		this.mJdbcTemplate.update(
				"update sys_custompages set CustomPageName=?,AppName=?,SourceUrl=?,DestinationUrl=?,TranslationType=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and CustomPageId=?",
				model.getCustomPageName(), model.getAppName(), model.getSourceUrl(), model.getDestinationUrl(),
				model.getTranslationType(), model.getMemo(), model.getDbDeleted(), model.getDbCreateBy(),
				model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(), model.getCustomPageId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysCustomPage(int customPageId) {
		this.mJdbcTemplate.update("delete from  sys_custompages where  1=1 and CustomPageId=?", customPageId);
		return true;
	}

	class SysCustomPageMapper implements RowMapper<SysCustomPage> {
		public SysCustomPage mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysCustomPage model = new SysCustomPage();
			model.setCustomPageId(rs.getInt("CustomPageId"));
			model.setCustomPageName(rs.getString("CustomPageName"));
			model.setAppName(rs.getString("AppName"));
			model.setSourceUrl(rs.getString("SourceUrl"));
			model.setDestinationUrl(rs.getString("DestinationUrl"));
			model.setTranslationType(rs.getInt("TranslationType"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysCustomPage getModelOfSysCustomPage(int customPageId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_custompages where  1=1 and CustomPageId=?",
				new Object[] { customPageId }, new SysCustomPageMapper());
	}

	@Override
	public List<SysCustomPage> getListOfSysCustomPage() {
		return this.mJdbcTemplate.query("select * from sys_custompages where dbdeleted=0", new SysCustomPageMapper());
	}

	@Override
	public boolean add(SysDataAuth model) {
		this.mJdbcTemplate.update(
				"insert into sys_dataauths(AuthId,KeyCode,AuthName,Memo,Disabled,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?)",
				model.getAuthId(), model.getKeyCode(), model.getAuthName(), model.getMemo(), model.getDisabled(),
				model.getDbDeleted(), model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(),
				model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysDataAuth model) {
		this.mJdbcTemplate.update(
				"update sys_dataauths set KeyCode=?,AuthName=?,Memo=?,Disabled=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and AuthId=?",
				model.getKeyCode(), model.getAuthName(), model.getMemo(), model.getDisabled(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(),
				model.getAuthId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDataAuth(int authId) {
		this.mJdbcTemplate.update("delete from  sys_dataauths where  1=1 and AuthId=?", authId);
		return true;
	}

	class SysDataAuthMapper implements RowMapper<SysDataAuth> {
		public SysDataAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysDataAuth model = new SysDataAuth();
			model.setAuthId(rs.getInt("AuthId"));
			model.setKeyCode(rs.getString("KeyCode"));
			model.setAuthName(rs.getString("AuthName"));
			model.setMemo(rs.getString("Memo"));
			model.setDisabled(rs.getInt("Disabled"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDataAuth getModelOfSysDataAuth(int authId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_dataauths where  1=1 and AuthId=?",
				new Object[] { authId }, new SysDataAuthMapper());
	}

	@Override
	public List<SysDataAuth> getListOfSysDataAuth() {
		return this.mJdbcTemplate.query("select * from sys_dataauths where dbdeleted=0", new SysDataAuthMapper());
	}

	@Override
	public boolean add(SysDataAuthItem model) {
		this.mJdbcTemplate.update(
				"insert into sys_dataauthitems(AuthItemId,AuthId,AuthItemName,TransferCode,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) "
						+ "values(?,?,?,?,?,?,?,?,?,?)",
				model.getAuthItemId(), model.getAuthId(), model.getAuthItemName(), model.getTransferCode(),
				model.getMemo(), model.getDbDeleted(), model.getDbCreateBy(), model.getDbLastUpdateBy(),
				model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysDataAuthItem model) {
		this.mJdbcTemplate.update(
				"update sys_dataauthitems set AuthId=?,AuthItemName=?,TransferCode=?,Memo=?,"
						+ "DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=?"
						+ " where 1=1 and AuthItemId=?",
				model.getAuthId(), model.getAuthItemName(), model.getTransferCode(), model.getMemo(),
				model.getDbDeleted(), model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(),
				model.getDbLastUpdateTime(), model.getAuthItemId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDataAuthItem(int authItemId) {
		this.mJdbcTemplate.update("delete from  sys_dataauthitems where  1=1 and AuthItemId=?", authItemId);
		return true;
	}

	class SysDataAuthItemMapper implements RowMapper<SysDataAuthItem> {
		public SysDataAuthItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysDataAuthItem model = new SysDataAuthItem();
			model.setAuthItemId(rs.getInt("AuthItemId"));
			model.setAuthId(rs.getInt("AuthId"));
			model.setAuthItemName(rs.getString("AuthItemName"));
			model.setTransferCode(rs.getString("TransferCode"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDataAuthItem getModelOfSysDataAuthItem(int authItemId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_dataauthitems where  1=1 and AuthItemId=?",
				new Object[] { authItemId }, new SysDataAuthItemMapper());
	}

	@Override
	public List<SysDataAuthItem> getListOfSysDataAuthItem() {
		return this.mJdbcTemplate.query("select * from sys_dataauthitems where dbdeleted=0", new SysDataAuthItemMapper());
	}

	@Override
	public boolean add(SysDictDir model) {
		this.mJdbcTemplate.update(
				"insert into sys_dictdirs(DirId,KeyCode,DirName,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?)",
				model.getDirId(), model.getKeyCode(), model.getDirName(), model.getMemo(), model.getDbDeleted(),
				model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysDictDir model) {
		this.mJdbcTemplate.update(
				"update sys_dictdirs set KeyCode=?,DirName=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and DirId=?",
				model.getKeyCode(), model.getDirName(), model.getMemo(), model.getDbDeleted(), model.getDbCreateBy(),
				model.getDbLastUpdateBy(), model.getDbCreateTime(), model.getDbLastUpdateTime(), model.getDirId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDictDir(int dirId) {
		this.mJdbcTemplate.update("delete from  sys_dictdirs where  1=1 and DirId=?", dirId);
		return true;
	}

	class SysDictDirMapper implements RowMapper<SysDictDir> {
		public SysDictDir mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysDictDir model = new SysDictDir();
			model.setDirId(rs.getInt("DirId"));
			model.setKeyCode(rs.getString("KeyCode"));
			model.setDirName(rs.getString("DirName"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDictDir getModelOfSysDictDir(int dirId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_dictdirs where  1=1 and DirId=?",
				new Object[] { dirId }, new SysDictDirMapper());
	}

	@Override
	public List<SysDictDir> getListOfSysDictDir() {
		return this.mJdbcTemplate.query("select * from sys_dictdirs", new SysDictDirMapper());
	}

	@Override
	public boolean add(SysDictItem model) {
		this.mJdbcTemplate.update(
				"insert into sys_dictitems(ItemId,ItemValue,ItemName,DirId,ShowOrder,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?)",
				model.getItemId(), model.getItemValue(), model.getItemName(), model.getDirId(), model.getShowOrder(),
				model.getMemo(), model.getDbDeleted(), model.getDbCreateBy(), model.getDbLastUpdateBy(),
				model.getDbCreateTime(), model.getDbLastUpdateTime()

		);
		return true;
	}

	@Override
	public boolean save(SysDictItem model) {
		this.mJdbcTemplate.update(
				"update sys_dictitems set ItemValue=?,ItemName=?,DirId=?,ShowOrder=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and ItemId=?",
				model.getItemValue(), model.getItemName(), model.getDirId(), model.getShowOrder(), model.getMemo(),
				model.getDbDeleted(), model.getDbCreateBy(), model.getDbLastUpdateBy(), model.getDbCreateTime(),
				model.getDbLastUpdateTime(), model.getItemId()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDictItem(int itemId) {
		this.mJdbcTemplate.update("delete from  sys_dictitems where  1=1 and ItemId=?", itemId);
		return true;
	}

	class SysDictItemMapper implements RowMapper<SysDictItem> {
		public SysDictItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysDictItem model = new SysDictItem();
			model.setItemId(rs.getInt("ItemId"));
			model.setItemValue(rs.getString("ItemValue"));
			model.setItemName(rs.getString("ItemName"));
			model.setDirId(rs.getInt("DirId"));
			model.setShowOrder(rs.getInt("ShowOrder"));
			model.setMemo(rs.getString("Memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getTimestamp("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDictItem getModelOfSysDictItem(int itemId) {
		return this.mJdbcTemplate.queryForObject("select * from sys_dictitems where  1=1 and ItemId=?",
				new Object[] { itemId }, new SysDictItemMapper());
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem() {
		return this.mJdbcTemplate.query("select * from sys_dictitems", new SysDictItemMapper());
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem(String dirKeyCode) {
		return this.mJdbcTemplate.query(
				"select a.* from sys_dictitems a " + "inner join sys_dictdirs b on a.dirid=b.dirid "
						+ "where 1=1 and a.dbdeleted=0 and b.dbdeleted=0 and lower(b.keycode)=lower(?)",
				new Object[] { dirKeyCode }, new SysDictItemMapper());
	}

	@Override
	public boolean add(SysSequence model) {
		this.mJdbcTemplate.update("insert into sys_sequences(KeyName,NextValue,GeneratedTime) values(?,?,?)",
				model.getKeyName(), model.getNextValue(), model.getGeneratedTime()

		);
		return true;
	}

	@Override
	public boolean save(SysSequence model) {
		this.mJdbcTemplate.update("update sys_sequences set NextValue=?,GeneratedTime=? where 1=1 and KeyName=?",
				model.getNextValue(), model.getGeneratedTime(), model.getKeyName()

		);
		return true;
	}

	@Override
	public boolean deleteModelOfSysSequence(String keyName) {
		this.mJdbcTemplate.update("delete from  sys_sequences where  1=1 and KeyName=?", keyName);
		return true;
	}

	class SysSequenceMapper implements RowMapper<SysSequence> {
		public SysSequence mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysSequence model = new SysSequence();
			model.setKeyName(rs.getString("KeyName"));
			model.setNextValue(rs.getInt("NextValue"));
			model.setGeneratedTime(rs.getDate("GeneratedTime"));

			return model;
		}
	}

	@Override
	public SysSequence getModelOfSysSequence(String keyName) {
		return this.mJdbcTemplate.queryForObject("select * from sys_sequences where  1=1 and KeyName=?",
				new Object[] { keyName }, new SysSequenceMapper());
	}

	@Override
	public List<SysSequence> getListOfSysSequence() {
		return this.mJdbcTemplate.query("select * from sys_sequences", new SysSequenceMapper());
	}

	@Override
	public boolean add(SysFile model) {
		this.mJdbcTemplate.update(
				"insert into sys_files(fileId,fileName,fileExtName,fileGroupId,fileAddress,fileLength,fileType,memo,dbDeleted,dbCreateBy,dbCreateTime)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?)",
				model.getFileId(), model.getFileName(), model.getFileExtName(), model.getFileGroupId(),
				model.getFileAddress(), model.getFileLength(), model.getFileType(), model.getMemo(),
				model.getDbDeleted(), model.getDbCreateBy(), model.getDbCreateTime());
		return true;
	}

	@Override
	public boolean save(SysFile model) {
		this.mJdbcTemplate.update(
				"update sys_files set fileName=?,fileExtName=?,fileGroupId=?,fileAddress=?,fileLength=?,fileType=?,memo=?,dbDeleted=?"
						+ " where fileId=?",
				model.getFileName(), model.getFileExtName(), model.getFileGroupId(), model.getFileAddress(),
				model.getFileLength(), model.getFileType(), model.getMemo(), model.getDbDeleted(), model.getFileId());
		return true;
	}

	@Override
	public boolean deleteModelOfSysFile(int id) {
		this.mJdbcTemplate.update("delete from sys_files where fileId=?", id);
		return true;
	}

	class SysFileMapper implements RowMapper<SysFile> {
		public SysFile mapRow(ResultSet rs, int rowNum) throws SQLException {
			SysFile model = new SysFile();
			model.setFileId(rs.getInt("fileId"));
			model.setFileName(rs.getString("fileName"));
			model.setFileExtName(rs.getString("fileExtName"));
			model.setFileGroupId(rs.getInt("fileGroupId"));
			model.setFileAddress(rs.getString("fileAddress"));
			model.setFileLength(rs.getInt("fileLength"));
			model.setFileType(rs.getInt("fileType"));
			model.setMemo(rs.getString("memo"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbDeleted"));
			model.setDbCreateTime(rs.getTimestamp("DbCreateTime"));

			return model;
		}
	}

	@Override
	public SysFile getModelOfSysFile(int id) {
		return this.mJdbcTemplate.queryForObject("select * from sys_files where fileId=?", new Object[] { id },
				new SysFileMapper());
	}

	@Override
	public List<SysFile> getListOfSysFile() {
		return this.mJdbcTemplate.query("select * from sys_files where dbdeleted=0", new SysFileMapper());
	}

	@Override
	public boolean add(SysIOTemplate model) {
		// this.mJdbcTemplate
		// .update("insert into
		// sys_iotemplates(TemplateId,TemplateName,TemplateCode,Title,MainFirstIndex,DetailFirstIndex,TableName,Description,Version,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime)
		// values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
		// model.getTemplateId(),
		// model.getTemplateName(),
		// model.getTemplateCode(),
		// model.getTitle(),
		// model.getMainFirstIndex(),
		// model.getDetailFirstIndex(),
		// model.getTableName(),
		// model.getDescription(),
		// model.getVersion(),
		// model.getDbDeleted(),
		// model.getDbCreateBy(),
		// model.getDbCreateTime(),
		// model.getDbLastUpdateBy(),
		// model.getDbLastUpdateTime()
		// );
		// return true;
		return false;
	}

	@Override
	public boolean save(SysIOTemplate model) {

		return false;
	}

	@Override
	public boolean deleteModelOfSysIOTemplate(int id) {

		return false;
	}

	@Override
	public SysIOTemplate getModelOfSysIOTemplate(int id) {

		return null;
	}

	@Override
	public List<SysIOTemplate> getListOfSysIOTemplate() {

		return null;
	}

	@Override
	public boolean add(SysIOTemplateColumn model) {

		return false;
	}

	@Override
	public boolean save(SysIOTemplateColumn model) {

		return false;
	}

	@Override
	public boolean deleteModelOfSysIOTemplateColumn(int id) {

		return false;
	}

	@Override
	public SysIOTemplateColumn getModelOfSysIOTemplateColumn(int id) {

		return null;
	}

	@Override
	public List<SysIOTemplateColumn> getListOfSysIOTemplateColumn() {

		return null;
	}

	@Override
	public boolean add(SysIOTemplateField model) {

		return false;
	}

	@Override
	public boolean save(SysIOTemplateField model) {

		return false;
	}

	@Override
	public boolean deleteModelOfSysIOTemplateField(int id) {

		return false;
	}

	@Override
	public SysIOTemplateField getModelOfSysIOTemplateField(int id) {

		return null;
	}

	@Override
	public List<SysIOTemplateField> getListOfSysIOTemplateField() {

		return null;
	}

	@Override
	public boolean add(SysLog model) {

		return false;
	}

	@Override
	public boolean save(SysLog model) {

		return false;
	}

	@Override
	public boolean deleteModelOfSysLog(int id) {

		return false;
	}

	@Override
	public SysLog getModelOfSysLog(int id) {
		return null;
	}

	@Override
	public List<SysLog> getListOfSysLog() {
		return null;
	}

	@Override
	public boolean add(SysArea model) {
		// this.mJdbcTemplate
		// .update("insert into
		// sys_files(fileId,fileName,fileExtName,fileAddress,fileLength,fileType,dbDeleted,dbCreateBy,dbCreateTime)"
		// + " values(?,?,?,?,?,?,?,?,?)", model.getFileId(),
		// model.getFileName(), model.getFileExtName(),
		// model.getFileAddress(), model.getFileLength(),
		// model.getFileType(), model.getDbDeleted(),
		// model.getDbCreateBy(), model.getDbCreateTime());
		return false;
	}

	@Override
	public boolean save(SysArea model) {
		return false;
	}

	@Override
	public boolean deleteModelOfSysArea(int id) {
		return false;
	}

	@Override
	public SysArea getModelOfSysArea(int id) {
		return null;
	}

	@Override
	public List<SysArea> getListOfSysArea() {
		return null;
	}

	@Override
	public List<SysRole> getUserRoles(int userId) {
		return this.mJdbcTemplate.query(
				"select b.* from sys_ruserrole a"
						+ " inner join sys_roles b on a.roleid=b.roleid where b.dbdeleted=0 and a.userid=?",
				new Object[] { userId }, new SysRoleMapper());
	}

	@Override
	public List<SysTab> getUserTabs(int userId) {
		// System.out.println("getUserTabs:userId="+userId);
		return this.mJdbcTemplate.query(
				"select d.* from (select distinct c.tabid from sys_ruserrole a"
						+ " inner join sys_roles b on a.roleid=b.roleid inner join sys_rroletab c on c.roleid=b.roleid"
						+ " where a.userid=? and b.dbdeleted=0) a inner join sys_tabs d on a.tabid=d.tabid"
						+ " where 1=1  and d.dbdeleted=0 order by d.showorder",
				new Object[] { userId }, new SysTabMapper());
	}

	@Override
	public List<SysMenu> getUserMenus(int userId) {
		return this.mJdbcTemplate.query(
				"select d.* from (select distinct c.menuid from sys_ruserrole a"
						+ "	inner join sys_roles b on a.roleid=b.roleid inner join sys_rrolemenu c on c.roleid=b.roleid"
						+ "	where a.userid=? and b.dbdeleted=0) a inner join sys_menus d on a.menuid=d.menuid"
						+ " where 1=1  and d.dbdeleted=0 order by d.showorder",
				new Object[] { userId }, new SysMenuMapper());
	}

	@Override
	public List<SysFuncPoint> getUserFuncPoints(int userId) {
		return this.mJdbcTemplate.query(
				"select d.* from (select distinct c.funcpointid from sys_ruserrole a"
						+ "	inner join sys_roles b on a.roleid=b.roleid"
						+ "	inner join sys_rrolefuncpoint c on c.roleid=b.roleid where a.userid=? and b.dbdeleted=0"
						+ " ) a inner join sys_funcpoints d on a.funcpointid=d.funcpointid"
						+ " where 1=1  and d.dbdeleted=0 order by d.showorder",
				new Object[] { userId }, new SysFuncPointMapper());
	}

	@Override
	public List<SysDataAuth> getUserDataAuths(int userId) {
		return this.mJdbcTemplate.query(
				"select d.* from (select distinct c.authid from sys_ruserrole a"
						+ "	inner join sys_roles b on a.roleid=b.roleid"
						+ "	inner join sys_rroledataauth c on c.roleid=b.roleid" + " where a.userid=? and b.dbdeleted=0"
						+ " ) a inner join sys_dataauths d on a.authid=d.authid" + " where 1=1  and d.dbdeleted=0",
				new Object[] { userId }, new SysDataAuthMapper());
	}

	@Override
	public List<SysDataAuthItem> getUserDataAuthItems(int userId) {
		return this.mJdbcTemplate.query("select d.* from (select distinct c.authitemid from sys_ruserrole a"
				+ "	inner join sys_roles b on a.roleid=b.roleid"
				+ "	inner join sys_rroledataauthitem c on c.roleid=b.roleid where a.userid=? and b.dbdeleted=0"
				+ " ) a inner join sys_dataauthitems d on a.authitemid=d.authitemid" + " where 1=1  and d.dbdeleted=0",
				new Object[] { userId }, new SysDataAuthItemMapper());
	}

	@Override
	public List<SysMenu> getTabMenus(int tabId) {
		return this.mJdbcTemplate.query(
				"select a.* from sys_menus a inner join sys_rtabmenu b on a.menuid=b.menuid where b.tabid=?",
				new Object[] { tabId }, new SysMenuMapper());
	}

	@Override
	public UserRightSet getUserRightSet(int userId) {
		UserRightSet userRightSet = new UserRightSet();
		List<Integer> ids = new ArrayList<Integer>();

		List<SysRole> roles = this.getUserRoles(userId);
		for (SysRole e : roles)
			ids.add(e.getRoleId());
		userRightSet.setRoles(ids);

		ids.clear();
		List<SysMenu> menus = this.getUserMenus(userId);
		for (SysMenu e : menus)
			ids.add(e.getMenuId());
		userRightSet.setMenus(ids);

		ids.clear();
		List<SysFuncPoint> funcPoints = this.getUserFuncPoints(userId);
		for (SysFuncPoint e : funcPoints)
			ids.add(e.getFuncPointId());
		userRightSet.setFuncPoints(ids);

		ids.clear();
		List<SysDataAuth> dataAuths = this.getUserDataAuths(userId);
		for (SysDataAuth e : dataAuths)
			ids.add(e.getAuthId());
		userRightSet.setDataAuths(ids);

		ids.clear();
		List<SysDataAuthItem> dataAuthItems = this.getUserDataAuthItems(userId);
		for (SysDataAuthItem e : dataAuthItems)
			ids.add(e.getAuthItemId());
		userRightSet.setDataAuthItems(ids);

		return userRightSet;
	}

	class SysTabMenuMapper implements RowMapper<TwoTuple<Integer, Integer>> {
		public TwoTuple<Integer, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
			TwoTuple<Integer, Integer> model = new TwoTuple<Integer, Integer>();
			model.setFirst(rs.getInt("TabId"));
			model.setSecond(rs.getInt("MenuId"));
			return model;
		}
	}

	class TwoIntegerTupleMapper implements RowMapper<TwoTuple<Integer, Integer>> {
		private String firstColumnName;
		private String secondColumnName;

		public TwoIntegerTupleMapper(String firstColumnName, String secondColumnName) {
			super();
			this.firstColumnName = firstColumnName;
			this.secondColumnName = secondColumnName;
		}

		public TwoTuple<Integer, Integer> mapRow(ResultSet rs, int rowNum) throws SQLException {
			TwoTuple<Integer, Integer> model = new TwoTuple<Integer, Integer>();
			model.setFirst(rs.getInt(firstColumnName));
			model.setSecond(rs.getInt(secondColumnName));
			return model;
		}
	}
	
	//class SingleIntegerColumnMapper implements RowMapper<K>
	
	
//	class IntegerListMapMapper implements RowMapper<HashMap<Integer, List<Integer>>> {
//		private String firstColumnName;
//		private String secondColumnName;
//
//		public IntegerListMapMapper(String firstColumnName, String secondColumnName) {
//			super();
//			this.firstColumnName = firstColumnName;
//			this.secondColumnName = secondColumnName;
//		}
//
//		public HashMap<Integer, List<Integer>> mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Map<Integer, List<Integer>> model = new HashMap<Integer, List<Integer>>();
//			model.setFirst(rs.getInt(firstColumnName));
//			model.setSecond(rs.getInt(secondColumnName));
//			return model;
//		}
//	}
	
	

	// class TwoTupleMapper<K, V> implements RowMapper<Map<K, List<V>>> {
	//
	// private RowMapper<K> keyMapper;
	// private RowMapper<V> valueMapper;
	//
	// public TwoTupleMapper(RowMapper<K> keyMapper, RowMapper<V> valueMapper) {
	// super();
	// this.keyMapper = keyMapper;
	// this.valueMapper = valueMapper;
	// }
	//
	// public Map<K, List<V>> mapRow(ResultSet rs, int rowNum) throws
	// SQLException {
	// Map<K, List<V>> model = new HashMap<K, List<V>>();
	// K key = keyMapper.mapRow(rs, rowNum);
	// V value = valueMapper.mapRow(rs, rowNum);
	// if (!model.containsKey(key)) {
	// model.put(key, new ArrayList<V>());
	// }
	// model.get(key).add(value);
	// return model;
	// }
	// }

	// class TwoTupleMapper implements RowMapper<TwoTuple<Integer,Integer>> {
	// private String firstColumnName;
	// private String secondColumnName;
	//
	// public TwoTupleMapper(String firstColumnName, String secondColumnName) {
	// super();
	// this.firstColumnName = firstColumnName;
	// this.secondColumnName = secondColumnName;
	// }
	//
	// public TwoTuple<Integer,Integer> mapRow(ResultSet rs, int rowNum) throws
	// SQLException {
	// TwoTuple<Integer,Integer> model = new TwoTuple<Integer,Integer>();
	// model.setFirst(rs.getInt(firstColumnName));
	// model.setSecond(rs.getInt(secondColumnName));
	// return model;
	// }
	// }

	@Override
	public List<TwoTuple<Integer, Integer>> getTabMenuTuples() {
		return this.mJdbcTemplate.query(
				"select r.tabid,r.menuid from sys_rtabmenu r" + " inner join sys_tabs t on r.tabid = t.tabid"
						+ " inner join sys_menus m on r.menuid=m.menuid" + " where t.dbdeleted=0 and m.dbdeleted=0",
				new TwoIntegerTupleMapper("tabid", "menuid"));
	}

	// @Override
	// public Map<SysTab, List<SysMenu>> getUserTabMenus(int userId) {
	// Map<SysTab, List<SysMenu>> maps = new HashMap<SysTab, List<SysMenu>>();
	// List<SysTab> tabs = this.getUserTabs(userId);
	// List<SysMenu> menus = this.getUserMenus(userId);
	// for(SysTab tab : tabs)
	// {
	// if(!maps.containsKey(tab))
	// }
	//
	// return null;
	// }

}
