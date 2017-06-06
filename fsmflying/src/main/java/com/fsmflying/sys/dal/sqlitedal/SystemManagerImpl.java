package com.fsmflying.sys.dal.sqlitedal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fsmflying.helpers.DBUtils;
import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dal.SystemManager;
import com.fsmflying.sys.dm.SysCompany;
import com.fsmflying.sys.dm.SysUser;

public class SystemManagerImpl implements SystemManager {

	private static final String SysUserFieldNames = "UserId,Username,Password,Nickname,IPPolicy,"
			+ "IPAddress,LastLoginTime,DisabledPolicy,DisabledMinutes,DisabledTime,"
			+ "RegisterTime,PWDPromptQuestion,PWDProtectQuestion,PWDProtectAnswer,PWDResetEmail,"
			+ "DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime";

	static {
		DBUtils.RegisterTable("Sys_Users", SysUserFieldNames);
	}

	public String getFieldNamesOfSysUser() {
		return "UserId,Username,Password,Nickname,IPPolicy,"
				+ "IPAddress,LastLoginTime,DisabledPolicy,DisabledMinutes,DisabledTime,"
				+ "RegisterTime,PWDPromptQuestion,PWDProtectQuestion,PWDProtectAnswer,PWDResetEmail,"
				+ "DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime";
	}

	@Override
	public boolean addSysUser(SysUser user) {
		return false;
	}

	@Override
	public boolean updateSysUser(SysUser user) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean deleteSysUserById(int id) {
		// TODO Auto-generated method stub

		// String sql = DBUtils.getInsertSql("Sys_Users");

		return false;
	}

	@Override
	public List<SysUser> getListOfSysUser(String whereSql) {
		// TODO Auto-generated method stub
		return null;
	}

	protected SysUser readForSysUser(ResultSet rs, boolean requireMoveCursor) {
		SysUser e = null;
		if (rs == null)
			throw new IllegalArgumentException("");
		try {
			boolean readOk = true;
			if (requireMoveCursor)
				readOk = rs.next();
			if (readOk) {
				e = new SysUser();
				e.setUserId(rs.getInt("UserId"));
				e.setUsername(rs.getString("Userpwd"));
				e.setUserPwd(rs.getString("Password"));
				e.setNickname(rs.getString("Nickname"));
				e.setIpPolicy(rs.getInt("IPPolicy"));

				e.setIpAddress(rs.getString("IPAddress"));
				e.setLastLoginTime(rs.getDate("LastLoginTime"));
				e.setStatus(rs.getInt("DisabledPolicy"));
				e.setDisabledMinutes(rs.getInt("DisabledMinutes"));
				e.setDisabledTime(rs.getDate("DisabledTime"));

				e.setRegisterTime(rs.getDate("RegisterTime"));
				e.setPwdPromptQuestion(rs.getString("PWDPrompt_Question"));
				e.setPwdProtectQuestion(rs.getString("PWDProtectQuestion"));
				e.setPwdProtectAnswer(rs.getString("PWDProtectAnswer"));
				e.setEmail(rs.getString("PWDResetEmail"));

				e.setDbDeleted(rs.getInt("DbDeleted"));
				e.setDbCreateBy(rs.getInt("DbCreateBy"));
				e.setDbCreateTime(rs.getDate("DbCreateTime"));
				e.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
				e.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return e;
	}

	@Override
	public List<SysUser> getListOfSysUser(boolean isLoading, String whereSql,
			List<SqlParameter<?>> params, int pageSize, int pageIndex) {
		final String sql = String.format("Select %s From Sys_Users where %s",
				SysUserFieldNames, whereSql);
		ArrayList<SysUser> list = new ArrayList<SysUser>();
		try {
			ResultSet rs = DBUtils.getResultSet(sql);
			while (rs.next()) {
				list.add(readForSysUser(rs, false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return list;
	}

	public String getFieldNamesOfCompany() {
		return "CompanyId,PCompanyId,CompanyNo,AutoNo,ShortName,"
				+ "FullName,EstablishedDate,ShowOrder,Contacts,ContactAddress,"
				+ "ContactPhone,ContactMPhone,ContactFax,ContactEmail,ContactPostalCode,"
				+ "BusinessScope,Flag,DbDeleted,DbCreateBy,DbCreateTime,"
				+ "DbLastUpdateBy,DbLastUpdateTime";

	}

	/**
	 * CompanyId,PCompanyId,CompanyNo,AutoNo,ShortName,
	 * FullName,EstablishedDate,ShowOrder,Contacts,ContactAddress,
	 * ContactPhone,ContactMPhone,ContactFax,ContactEmail,ContactPostalCode,
	 * BusinessScope,Flag,DbDeleted,DbCreateBy,DbCreateTime,
	 * DbLastUpdateBy,DbLastUpdateTime
	 * 
	 * @param rs
	 * @param isChecked
	 * @return
	 */
	public SysCompany readForSysCompany(ResultSet rs, boolean isChecked) {
		SysCompany model = null;

		if (isChecked)
			try {
				isChecked = rs != null && rs.next();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		else
			isChecked = true;

		try {
			if (isChecked) {
				model = new SysCompany();
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
//				model.setContactMPhone(rs.getString("ContactMPhone"));
				model.setContactFax(rs.getString("ContactFax"));
				model.setContactEmail(rs.getString("ContactEmail"));
				model.setContactPostalCode(rs.getString("ContactPostalCode"));
				model.setScopeOfBusiness(rs.getString("ScopeOfBusiness"));
				model.setMemo(rs.getString("Memo"));
				model.setFlag(rs.getInt("Flag"));
				model.setDbDeleted(rs.getInt("DbDeleted"));
				model.setDbCreateBy(rs.getInt("DbCreateBy"));
				model.setDbCreateTime(rs.getDate("DbCreateTime"));
				model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
				model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return model;
	}
}
