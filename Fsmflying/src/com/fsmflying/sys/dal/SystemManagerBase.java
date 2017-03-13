package com.fsmflying.sys.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysDataAuth;
import com.fsmflying.sys.dm.SysMenu;
import com.fsmflying.sys.dm.SysUser;

public class SystemManagerBase implements SystemManager {

	public String getFieldNamesOfSysUser() {
		return "UserId,Username,Password,Nickname,IPPolicy,"
				+ "IPAddress,LastLoginTime,DisabledPolicy,DisabledMinutes,DisabledTime,"
				+ "RegisterTime,PWDPromptQuestion,PWDProtectQuestion,PWDProtectAnswer,PWDResetEmail,"
				+ "DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime";
	}

	public String getFieldNamesOfSysCompany() {
		return "CompanyId,PCompanyId,CompanyNo,AutoNo,ShortName,"
				+ "FullName,EstablishedDate,ShowOrder,Contacts,ContactAddress,"
				+ "ContactPhone,ContactMPhone,ContactFax,ContactEmail,ContactPostalCode,"
				+ "BusinessScope,Flag,DbDeleted,DbCreateBy,DbCreateTime,"
				+ "DbLastUpdateBy,DbLastUpdateTime";
	}

	public String getFieldNamesOfSysDepartment() {
		return "DeptId,DeptNo,DeptName,Depth,PDeptId,CompanyId,AutoNo,ShowOrder,"
				+ "DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime,"
				+ "IsLeaf";

	}

	public String getFieldNamesOfSysEmployee() {
		return "";
	}

	public String getFieldNamesOfSysTab() {
		return "";
	}

	public String getFieldNamesOfSysMenu() {
		return "";
	}

	public String getFieldNamesOfSysFuncPoint() {
		return "FuncPointId,FuncPointName,FuncPointCode,ShowOrder,Memo,"
				+ "DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime";
	}

	public String getFieldNamesOfSysDataAuth() {
		return "DataAuthId,DataAuthKeyCode,DataAuthName,Memo,Disabled,"
				+ "DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime";
	}

	public String getFieldNamesOfSysDataAuthItem() {
		return "";
	}

	@Override
	public boolean addSysUser(SysUser user) {
		// TODO Auto-generated method stub
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
		return false;
	}

	@Override
	public List<SysUser> getListOfSysUser(boolean isLoading, String whereSql,
			List<SqlParameter<?>> params, int pageSize, int pageIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysUser> getListOfSysUser(String whereSql) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 从指定的数据集读取一个对象
	 * 
	 * @param rs
	 *            从数据库中提取的数据集
	 * @param isChecked
	 *            是否调用rs.next()进行游标移动，如果已经在外部移动了，就不需要再在此方法内部再移动游标
	 * @return　一个SysDataAuth对象
	 */
	protected SysDataAuth ReadForSysDataAuth(ResultSet rs, boolean isChecked) {
		SysDataAuth model = null;

		if (isChecked)
			try {
				isChecked = rs != null && rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		else
			isChecked = true;
		try {
			if (isChecked) {
				model = new SysDataAuth();
				model.setAuthId(rs.getInt("DataAuthId"));
				model.setKeyCode(rs.getString("DataAuthKeyCode"));
				model.setAuthName(rs.getString("DataAuthName"));
				model.setMemo(rs.getString("Memo"));
				model.setDisabled(rs.getInt("Disabled"));
				model.setDbDeleted(rs.getInt("DbDeleted"));
				model.setDbCreateBy(rs.getInt("DbCreateBy"));
				model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
				model.setDbCreateTime(rs.getDate("DbCreateTime"));
				model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;
	}

	/**
	 * 从指定的数据集读取一个对象
	 * 
	 * @param rs
	 *            从数据库中提取的数据集
	 * @param requireMoveCursor
	 *            是否调用rs.next()进行游标移动，如果已经在外部移动了，就不需要再在此方法内部再移动游标
	 * @return　一个SysDataAuth对象
	 */
	protected SysMenu ReadForSysMenu(ResultSet rs, boolean requireMoveCursor) {
		SysMenu model = null;
		try {
			if (rs == null)
				throw new IllegalArgumentException();
			boolean readOk = true;
			if (requireMoveCursor)
				readOk = rs.next();
			if (readOk) {
				model = new SysMenu();
				model.setMenuId(rs.getInt("MenuId"));
				model.setParentMenuId(rs.getInt("PMenuId"));
				model.setMenuName(rs.getString("MenuName"));
				model.setMenuType(rs.getInt("MenuType"));
				model.setShowOrder(rs.getInt("ShowOrder"));
				model.setMemo(rs.getString("Memo"));
				model.setDefaultUrl(rs.getString("DefaultUrl"));
				model.setLevelDepth(rs.getInt("LevelDepth"));
				model.setDbDeleted(rs.getInt("DbDeleted"));
				model.setDbCreateBy(rs.getInt("DbCreateBy"));
				model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
				model.setDbCreateTime(rs.getDate("DbCreateTime"));
				model.setDbLastUpdateTime(rs.getDate("DbLastUpdateTime"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;
	}
}
