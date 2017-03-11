package com.fsmflying.spring.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

//import org.springframework

public class SystemManageDaoImpl implements SystemManageDao {

	JdbcTemplate mJdbcTemplate = new JdbcTemplate();

	@Override
	public void setDataSource(DataSource ds) {
		this.mJdbcTemplate.setDataSource(ds);
	}

	@Override
	public boolean add(SysUser model) {
		this.mJdbcTemplate
				.update("insert into sys_users(UserId,Username,UserPwd,Nickname,IPPolicy,IPAddress,RegisterTime,LastLoginTime,"
						+ "Status,DisabledTime,DisabledMinutes,PwdPromptQuestion,PwdProtectQuestion,PwdProtectAnswer,"
						+ "Email,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						model.getUserId(), model.getUsername(),
						model.getUserPwd(), model.getNickname(),
						model.getIPPolicy(), model.getIPAddress(),
						model.getRegisterTime(), model.getLastLoginTime(),
						model.getStatus(), model.getDisabledTime(),
						model.getDisabledMinutes(),
						model.getPwdPromptQuestion(),
						model.getPwdProtectQuestion(),
						model.getPwdProtectAnswer(), model.getEmail(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateBy(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysUser model) {

		this.mJdbcTemplate
				.update("update sys_users set Username=?,UserPwd=?,Nickname=?,IPPolicy=?,IPAddress=?,RegisterTime=?,"
						+ "LastLoginTime=?,Status=?,DisabledTime=?,DisabledMinutes=?,PwdPromptQuestion=?,"
						+ "PwdProtectQuestion=?,PwdProtectAnswer=?,Email=?,DbDeleted=?,DbCreateBy=?,"
						+ "DbCreateTime=?,DbLastUpdateBy=?,DbLastUpdateTime=? "
						+ "where 1=1 and UserId=?", model.getUsername(),
						model.getUserPwd(), model.getNickname(),
						model.getIPPolicy(), model.getIPAddress(),
						model.getRegisterTime(), model.getLastLoginTime(),
						model.getStatus(), model.getDisabledTime(),
						model.getDisabledMinutes(),
						model.getPwdPromptQuestion(),
						model.getPwdProtectQuestion(),
						model.getPwdProtectAnswer(), model.getEmail(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateBy(),
						model.getDbLastUpdateTime(), model.getUserId()

				);
		return true;
		// return false;
	}

	@Override
	public boolean deleteModelOfSysUser(int userId) {
		this.mJdbcTemplate.update(
				"delete from  sys_users where  1=1 and UserId=?", userId);
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
			model.setRegisterTime(rs.getDate("RegisterTime"));
			model.setLastLoginTime(rs.getDate("LastLoginTime"));
			model.setStatus(rs.getInt("Status"));
			model.setDisabledTime(rs.getDate("DisabledTime"));
			model.setDisabledMinutes(rs.getInt("DisabledMinutes"));
			model.setPwdPromptQuestion(rs.getString("PwdPromptQuestion"));
			model.setPwdProtectQuestion(rs.getString("PwdProtectQuestion"));
			model.setPwdProtectAnswer(rs.getString("PwdProtectAnswer"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysUser getModelOfSysUser(int userId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_users where  1=1 and UserId=?",
				new Object[] { userId }, new SysUserMapper());
	}
	
	@Override
	public SysUser getModelOfSysUser(String username) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_users where  1=1 and Username=?",
				new Object[] { username }, new SysUserMapper());
	}

	@Override
	public List<SysUser> getListOfSysUser() {
		return this.mJdbcTemplate.query("select * from sys_users",
				new SysUserMapper());
	}

	@Override
	public boolean add(SysRole model) {
		this.mJdbcTemplate
				.update("insert into sys_roles(RoleId,RoleName,KeyCode,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?)",
						model.getRoleId(), model.getRoleName(),
						model.getKeyCode(), model.getMemo(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysRole model) {
		this.mJdbcTemplate
				.update("update sys_roles set RoleName=?,KeyCode=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and RoleId=?",
						model.getRoleName(), model.getKeyCode(),
						model.getMemo(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime(),
						model.getRoleId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysRole(int roleId) {
		this.mJdbcTemplate.update(
				"delete from  sys_roles where  1=1 and RoleId=?", roleId);
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysRole getModelOfSysRole(int roleId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_roles where  1=1 and RoleId=?",
				new Object[] { roleId }, new SysRoleMapper());
	}

	@Override
	public List<SysRole> getListOfSysRole() {
		return this.mJdbcTemplate.query("select * from sys_roles",
				new SysRoleMapper());
	}

	@Override
	public boolean add(SysCompany model) {
		this.mJdbcTemplate
				.update("insert into sys_companys(CompanyId,PCompanyId,CompanyNo,AutoNo,ShortName,FullName,RegisterationDate,ShowOrder,"
						+ "Contacts,ContactAddress,ContactPhone,ContactMPhone,ContactFax,ContactEmail,ContactPostalCode,"
						+ "BusinessScope,Flag,Memo,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						model.getCompanyId(),
						model.getParentCompanyId(),
						model.getCompanyNo(),
						model.getAutoNo(),
						model.getShortName(),
						model.getFullName(),
						model.getRegisterationDate(),
						model.getShowOrder(),
						model.getContactPerson(),
						model.getContactAddress(),
						model.getContactPhone(), // model.getContactMPhone(),
						model.getContactFax(), model.getContactEmail(),
						model.getContactPostalCode(),
						model.getScopeOfBusiness(), model.getFlag(),model.getMemo(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateBy(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysCompany model) {
		this.mJdbcTemplate
				.update("update sys_companys set ParentCompanyId=?,CompanyNo=?,AutoNo=?,ShortName=?,FullName=?,RegisterationDate=?,ShowOrder=?,"
						+ "ContactPerson=?,ContactAddress=?,ContactPhone=?,ContactFax=?,ContactEmail=?,ContactPostalCode=?,"
						+ "ScopeOfBusiness=?,Flag=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbCreateTime=?,DbLastUpdateBy=?,DbLastUpdateTime=? "
						+ "where 1=1 and CompanyId=?",
						model.getParentCompanyId(), model.getCompanyNo(),
						model.getAutoNo(), model.getShortName(),
						model.getFullName(), model.getRegisterationDate(),
						model.getShowOrder(), model.getContactPerson(),
						model.getContactAddress(), model.getContactPhone(),
						/* model.getContactMPhone(), */model.getContactFax(),
						model.getContactEmail(), model.getContactPostalCode(),
						model.getScopeOfBusiness(), model.getFlag(),
						model.getMemo(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateBy(), model.getDbLastUpdateTime(),
						model.getCompanyId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysCompany(int companyId) {
		this.mJdbcTemplate.update(
				"delete from  sys_companys where  1=1 and CompanyId=?",
				companyId);
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
			model.setContactPerson(rs.getString("Contacts"));
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysCompany getModelOfSysCompany(int companyId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_companys where  1=1 and CompanyId=?",
				new Object[] { companyId }, new SysCompanyMapper());
	}

	@Override
	public List<SysCompany> getListOfSysCompany() {
		return this.mJdbcTemplate.query("select * from sys_companys",
				new SysCompanyMapper());
	}

	@Override
	public boolean add(SysDepartment model) {
		this.mJdbcTemplate
				.update("insert into sys_departments(DeptId,PDeptId,CompanyId,DeptName,DeptNo,LevelDepth,AutoNo,ShowOrder,IsLeaf,Flag,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						model.getDeptId(), model.getParentDeptId(),
						model.getCompanyId(), model.getDeptName(),
						model.getDeptNo(), model.getLevelDepth(),
						model.getAutoNo(), model.getShowOrder(),
						model.getIsLeaf(), model.getFlag(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateBy(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysDepartment model) {
		this.mJdbcTemplate
				.update("update sys_departments set PDeptId=?,CompanyId=?,DeptName=?,DeptNo=?,LevelDepth=?,AutoNo=?,ShowOrder=?,IsLeaf=?,Flag=?,DbDeleted=?,DbCreateBy=?,DbCreateTime=?,DbLastUpdateBy=?,DbLastUpdateTime=? where 1=1 and DeptId=?",
						model.getParentDeptId(), model.getCompanyId(),
						model.getDeptName(), model.getDeptNo(),
						model.getLevelDepth(), model.getAutoNo(),
						model.getShowOrder(), model.getIsLeaf(),
						model.getFlag(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateBy(), model.getDbLastUpdateTime(),
						model.getDeptId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDepartment(int deptId) {
		this.mJdbcTemplate.update(
				"delete from  sys_departments where  1=1 and DeptId=?", deptId);
		return true;
	}

	class SysDepartmentMapper implements RowMapper<SysDepartment> {
		public SysDepartment mapRow(ResultSet rs, int rowNum)
				throws SQLException {
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDepartment getModelOfSysDepartment(int deptId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_departments where  1=1 and DeptId=?",
				new Object[] { deptId }, new SysDepartmentMapper());
	}

	@Override
	public List<SysDepartment> getListOfSysDepartment() {
		return this.mJdbcTemplate.query("select * from sys_departments",
				new SysDepartmentMapper());
	}

	@Override
	public boolean add(SysEmployee model) {
		this.mJdbcTemplate
				.update("insert into sys_employees(EmplId,CompanyId,EmplNo,EmplName_CN,EmplName_EN,Sex,Birthdate,NativePlace,CardId,ContactPhone,ContactMPhone,ContactAddress,ContactFax,ContactEmail,PositionId,PositionName,TitleName,ShowOrder,AutoNo,DeptId,Field001,Field002,Field003,Field004,Field005,Flag,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						model.getEmplId(), model.getCompanyId(),
						model.getEmplNo(), model.getEmplName_CN(),
						model.getEmplName_EN(), model.getSex(),
						model.getBirthdate(), model.getNativePlace(),
						model.getCardId(), model.getContactPhone(),
						model.getContactMPhone(), model.getContactAddress(),
						model.getContactFax(), model.getContactEmail(),
						model.getPositionId(), model.getPositionName(),
						model.getTitleName(), model.getShowOrder(),
						model.getAutoNo(), model.getDeptId(),
						model.getField001(), model.getField002(),
						model.getField003(), model.getField004(),
						model.getField005(), model.getFlag(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysEmployee model) {
		this.mJdbcTemplate
				.update("update sys_employees set CompanyId=?,EmplNo=?,EmplName_CN=?,EmplName_EN=?,Sex=?,Birthdate=?,NativePlace=?,CardId=?,ContactPhone=?,ContactMPhone=?,ContactAddress=?,ContactFax=?,ContactEmail=?,PositionId=?,PositionName=?,TitleName=?,ShowOrder=?,AutoNo=?,DeptId=?,Field001=?,Field002=?,Field003=?,Field004=?,Field005=?,Flag=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and EmplId=?",
						model.getCompanyId(), model.getEmplNo(),
						model.getEmplName_CN(), model.getEmplName_EN(),
						model.getSex(), model.getBirthdate(),
						model.getNativePlace(), model.getCardId(),
						model.getContactPhone(), model.getContactMPhone(),
						model.getContactAddress(), model.getContactFax(),
						model.getContactEmail(), model.getPositionId(),
						model.getPositionName(), model.getTitleName(),
						model.getShowOrder(), model.getAutoNo(),
						model.getDeptId(), model.getField001(),
						model.getField002(), model.getField003(),
						model.getField004(), model.getField005(),
						model.getFlag(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime(),
						model.getEmplId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysEmployee(int emplId) {
		this.mJdbcTemplate.update(
				"delete from  sys_employees where  1=1 and EmplId=?", emplId);
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysEmployee getModelOfSysEmployee(int emplId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_employees where  1=1 and EmplId=?",
				new Object[] { emplId }, new SysEmployeeMapper());
	}

	@Override
	public List<SysEmployee> getListOfSysEmployee() {
		return this.mJdbcTemplate.query("select * from sys_employees",
				new SysEmployeeMapper());
	}

	@Override
	public boolean add(SysTab model) {

		return false;
	}

	@Override
	public boolean save(SysTab model) {

		return false;
	}

	@Override
	public boolean deleteModelOfSysTab(int id) {

		return false;
	}

	@Override
	public SysTab getModelOfSysTab(int id) {

		return null;
	}

	@Override
	public List<SysTab> getListOfSysTab() {

		return null;
	}

	@Override
	public boolean add(SysMenu model) {

		return false;
	}

	@Override
	public boolean save(SysMenu model) {

		return false;
	}

	@Override
	public boolean deleteModelOfSysMenu(int id) {

		return false;
	}

	@Override
	public SysMenu getModelOfSysMenu(int id) {

		return null;
	}

	@Override
	public List<SysMenu> getListOfSysMenu() {

		return null;
	}

	@Override
	public boolean add(SysFuncPoint model) {

		return false;
	}

	@Override
	public boolean save(SysFuncPoint model) {

		return false;
	}

	@Override
	public boolean deleteModelOfSysFuncPoint(int id) {

		return false;
	}

	@Override
	public SysFuncPoint getModelOfSysFuncPoint(int id) {

		return null;
	}

	@Override
	public List<SysFuncPoint> getListOfSysFuncPoint() {

		return null;
	}

	@Override
	public boolean add(SysConfig model) {
		this.mJdbcTemplate
				.update("insert into sys_configs(ConfigId,ConfigName,ConfigKey,ConfigValue,Memo,PConfigId,Disabled,LevelDepth,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
						model.getConfigId(), model.getConfigName(),
						model.getConfigKey(), model.getConfigValue(),
						model.getMemo(), model.getPConfigId(),
						model.getDisabled(), model.getLevelDepth(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysConfig model) {
		this.mJdbcTemplate
				.update("update sys_configs set ConfigName=?,ConfigKey=?,ConfigValue=?,Memo=?,PConfigId=?,Disabled=?,LevelDepth=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and ConfigId=?",
						model.getConfigName(), model.getConfigKey(),
						model.getConfigValue(), model.getMemo(),
						model.getPConfigId(), model.getDisabled(),
						model.getLevelDepth(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime(),
						model.getConfigId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysConfig(int configId) {
		this.mJdbcTemplate.update(
				"delete from  sys_configs where  1=1 and ConfigId=?", configId);
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
			model.setPConfigId(rs.getInt("PConfigId"));
			model.setDisabled(rs.getInt("Disabled"));
			model.setLevelDepth(rs.getInt("LevelDepth"));
			model.setDbDeleted(rs.getInt("DbDeleted"));
			model.setDbCreateBy(rs.getInt("DbCreateBy"));
			model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysConfig getModelOfSysConfig(int configId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_configs where  1=1 and ConfigId=?",
				new Object[] { configId }, new SysConfigMapper());
	}

	@Override
	public List<SysConfig> getListOfSysConfig() {
		return this.mJdbcTemplate.query("select * from sys_configs",
				new SysConfigMapper());
	}

	@Override
	public boolean add(SysCustomPage model) {
		this.mJdbcTemplate
				.update("insert into sys_custompages(CustomPageId,CustomPageName,AppName,SourceUrl,DestinationUrl,TranslationType,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?)",
						model.getCustomPageId(), model.getCustomPageName(),
						model.getAppName(), model.getSourceUrl(),
						model.getDestinationUrl(), model.getTranslationType(),
						model.getMemo(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysCustomPage model) {
		this.mJdbcTemplate
				.update("update sys_custompages set CustomPageName=?,AppName=?,SourceUrl=?,DestinationUrl=?,TranslationType=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and CustomPageId=?",
						model.getCustomPageName(), model.getAppName(),
						model.getSourceUrl(), model.getDestinationUrl(),
						model.getTranslationType(), model.getMemo(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime(), model.getCustomPageId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysCustomPage(int customPageId) {
		this.mJdbcTemplate.update(
				"delete from  sys_custompages where  1=1 and CustomPageId=?",
				customPageId);
		return true;
	}

	class SysCustomPageMapper implements RowMapper<SysCustomPage> {
		public SysCustomPage mapRow(ResultSet rs, int rowNum)
				throws SQLException {
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysCustomPage getModelOfSysCustomPage(int customPageId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_custompages where  1=1 and CustomPageId=?",
				new Object[] { customPageId }, new SysCustomPageMapper());
	}

	@Override
	public List<SysCustomPage> getListOfSysCustomPage() {
		return this.mJdbcTemplate.query("select * from sys_custompages",
				new SysCustomPageMapper());
	}

	@Override
	public boolean add(SysDataAuth model) {
		this.mJdbcTemplate
				.update("insert into sys_dataauths(AuthId,KeyCode,AuthName,Memo,Disabled,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?)",
						model.getAuthId(), model.getKeyCode(),
						model.getAuthName(), model.getMemo(),
						model.getDisabled(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysDataAuth model) {
		this.mJdbcTemplate
				.update("update sys_dataauths set KeyCode=?,AuthName=?,Memo=?,Disabled=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and AuthId=?",
						model.getKeyCode(), model.getAuthName(),
						model.getMemo(), model.getDisabled(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime(), model.getAuthId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDataAuth(int authId) {
		this.mJdbcTemplate.update(
				"delete from  sys_dataauths where  1=1 and AuthId=?", authId);
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDataAuth getModelOfSysDataAuth(int authId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_dataauths where  1=1 and AuthId=?",
				new Object[] { authId }, new SysDataAuthMapper());
	}

	@Override
	public List<SysDataAuth> getListOfSysDataAuth() {
		return this.mJdbcTemplate.query("select * from sys_dataauths",
				new SysDataAuthMapper());
	}

	@Override
	public boolean add(SysDataAuthItem model) {
		this.mJdbcTemplate
				.update("insert into sys_dataauthitems(AuthItemId,AuthId,AuthItemName,TransferCode,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) "
						+ "values(?,?,?,?,?,?,?,?,?,?)", model.getAuthItemId(),
						model.getAuthId(), model.getAuthItemName(),
						model.getTransferCode(), model.getMemo(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysDataAuthItem model) {
		this.mJdbcTemplate
				.update("update sys_dataauthitems set AuthId=?,AuthItemName=?,TransferCode=?,Memo=?,"
						+ "DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=?"
						+ " where 1=1 and AuthItemId=?", model.getAuthId(),
						model.getAuthItemName(), model.getTransferCode(),
						model.getMemo(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime(),
						model.getAuthItemId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDataAuthItem(int authItemId) {
		this.mJdbcTemplate.update(
				"delete from  sys_dataauthitems where  1=1 and AuthItemId=?",
				authItemId);
		return true;
	}

	class SysDataAuthItemMapper implements RowMapper<SysDataAuthItem> {
		public SysDataAuthItem mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			SysDataAuthItem model = new SysDataAuthItem();
			model.setAuthItemId(rs.getInt("AuthItemId"));
			model.setAuthId(rs.getInt("AuthId"));
			model.setAuthItemName(rs.getString("AuthItemName"));
			model.setTransferCode(rs.getString("TransferCode"));
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
	public SysDataAuthItem getModelOfSysDataAuthItem(int authItemId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_dataauthitems where  1=1 and AuthItemId=?",
				new Object[] { authItemId }, new SysDataAuthItemMapper());
	}

	@Override
	public List<SysDataAuthItem> getListOfSysDataAuthItem() {
		return this.mJdbcTemplate.query("select * from sys_dataauthitems",
				new SysDataAuthItemMapper());
	}

	@Override
	public boolean add(SysDictDir model) {
		this.mJdbcTemplate
				.update("insert into sys_dictdirs(DirId,KeyCode,DirName,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?)",
						model.getDirId(), model.getKeyCode(),
						model.getDirName(), model.getMemo(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysDictDir model) {
		this.mJdbcTemplate
				.update("update sys_dictdirs set KeyCode=?,DirName=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and DirId=?",
						model.getKeyCode(), model.getDirName(),
						model.getMemo(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime(),
						model.getDirId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDictDir(int dirId) {
		this.mJdbcTemplate.update(
				"delete from  sys_dictdirs where  1=1 and DirId=?", dirId);
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDictDir getModelOfSysDictDir(int dirId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_dictdirs where  1=1 and DirId=?",
				new Object[] { dirId }, new SysDictDirMapper());
	}

	@Override
	public List<SysDictDir> getListOfSysDictDir() {
		return this.mJdbcTemplate.query("select * from sys_dictdirs",
				new SysDictDirMapper());
	}

	@Override
	public boolean add(SysDictItem model) {
		this.mJdbcTemplate
				.update("insert into sys_dictitems(ItemId,ItemValue,ItemName,DirId,ShowOrder,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?)",
						model.getItemId(), model.getItemValue(),
						model.getItemName(), model.getDirId(),
						model.getShowOrder(), model.getMemo(),
						model.getDbDeleted(), model.getDbCreateBy(),
						model.getDbLastUpdateBy(), model.getDbCreateTime(),
						model.getDbLastUpdateTime()

				);
		return true;
	}

	@Override
	public boolean save(SysDictItem model) {
		this.mJdbcTemplate
				.update("update sys_dictitems set ItemValue=?,ItemName=?,DirId=?,ShowOrder=?,Memo=?,DbDeleted=?,DbCreateBy=?,DbLastUpdateBy=?,DbCreateTime=?,DbLastUpdateTime=? where 1=1 and ItemId=?",
						model.getItemValue(), model.getItemName(),
						model.getDirId(), model.getShowOrder(),
						model.getMemo(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbLastUpdateBy(),
						model.getDbCreateTime(), model.getDbLastUpdateTime(),
						model.getItemId()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysDictItem(int itemId) {
		this.mJdbcTemplate.update(
				"delete from  sys_dictitems where  1=1 and ItemId=?", itemId);
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));
			model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));

			return model;
		}
	}

	@Override
	public SysDictItem getModelOfSysDictItem(int itemId) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_dictitems where  1=1 and ItemId=?",
				new Object[] { itemId }, new SysDictItemMapper());
	}

	@Override
	public List<SysDictItem> getListOfSysDictItem() {
		return this.mJdbcTemplate.query("select * from sys_dictitems",
				new SysDictItemMapper());
	}

	@Override
	public boolean add(SysSequence model) {
		this.mJdbcTemplate
				.update("insert into sys_sequences(KeyName,NextValue,GeneratedTime) values(?,?,?)",
						model.getKeyName(), model.getNextValue(),
						model.getGeneratedTime()

				);
		return true;
	}

	@Override
	public boolean save(SysSequence model) {
		this.mJdbcTemplate
				.update("update sys_sequences set NextValue=?,GeneratedTime=? where 1=1 and KeyName=?",
						model.getNextValue(), model.getGeneratedTime(),
						model.getKeyName()

				);
		return true;
	}

	@Override
	public boolean deleteModelOfSysSequence(String keyName) {
		this.mJdbcTemplate.update(
				"delete from  sys_sequences where  1=1 and KeyName=?", keyName);
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
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_sequences where  1=1 and KeyName=?",
				new Object[] { keyName }, new SysSequenceMapper());
	}

	@Override
	public List<SysSequence> getListOfSysSequence() {
		return this.mJdbcTemplate.query("select * from sys_sequences",
				new SysSequenceMapper());
	}

	@Override
	public boolean add(SysFile model) {
		this.mJdbcTemplate
				.update("insert into sys_files(fileId,fileName,fileExtName,fileGroupId,fileAddress,fileLength,fileType,memo,dbDeleted,dbCreateBy,dbCreateTime)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?)", model.getFileId(),
						model.getFileName(), model.getFileExtName(),
						model.getFileGroupId(), model.getFileAddress(),
						model.getFileLength(), model.getFileType(),
						model.getMemo(), model.getDbDeleted(),
						model.getDbCreateBy(), model.getDbCreateTime());
		return true;
	}

	@Override
	public boolean save(SysFile model) {
		this.mJdbcTemplate
				.update("update sys_files set fileName=?,fileExtName=?,fileGroupId=?,fileAddress=?,fileLength=?,fileType=?,memo=?,dbDeleted=?"
						+ " where fileId=?", model.getFileName(),
						model.getFileExtName(), model.getFileGroupId(),
						model.getFileAddress(), model.getFileLength(),
						model.getFileType(), model.getMemo(),
						model.getDbDeleted(), model.getFileId());
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
			model.setDbCreateTime(rs.getDate("DbCreateTime"));

			return model;
		}
	}

	@Override
	public SysFile getModelOfSysFile(int id) {
		return this.mJdbcTemplate.queryForObject(
				"select * from sys_files where fileId=?", new Object[] { id },
				new SysFileMapper());
	}

	@Override
	public List<SysFile> getListOfSysFile() {
		return this.mJdbcTemplate.query("select * from sys_files",
				new SysFileMapper());
	}

	@Override
	public boolean add(SysIOTemplate model) {
		// this.mJdbcTemplate
		// .update("insert into sys_iotemplates(TemplateId,TemplateName,TemplateCode,Title,MainFirstIndex,DetailFirstIndex,TableName,Description,Version,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
		//
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
		// .update("insert into sys_files(fileId,fileName,fileExtName,fileAddress,fileLength,fileType,dbDeleted,dbCreateBy,dbCreateTime)"
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

}
