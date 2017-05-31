
package com.fsmflying;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fsmflying.helpers.DBUtils;
import com.fsmflying.helpers.Helper;
import com.fsmflying.sys.dm.SysCompany;
import com.fsmflying.sys.dm.SysDepartment;
import com.fsmflying.sys.dm.SysEmployee;
import com.fsmflying.sys.dm.SysUser;

public class ExampleDbHelper {
	
	private static HashMap<String, List<?>> mListMap = new HashMap<String, List<?>>();
	
	private static Document mDocument = null;

	@SuppressWarnings("unchecked")
	public static List<SysUser> getUserList() {
		if (!mListMap.containsKey("SysUser")) {
			List<SysUser> userList = null;
			ResultSet rs = null;
			try {
				rs = DBUtils.getResultSet("Select * From Sys_Users");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			userList = new ArrayList<SysUser>();
			SysUser model = null;
			try {
				while (rs.next()) {
					model = new SysUser();
					model.setUserId(rs.getInt("UserId"));
					model.setUsername(rs.getString("Username"));
					model.setUserPwd(rs.getString("Userpwd"));
					model.setNickname(rs.getString("Nickname"));
					model.setIpPolicy(rs.getInt("IPPolicy"));

					model.setIpAddress(rs.getString("IPAddress"));
					model.setLastLoginTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("LastLoginTime")));
					model.setStatus(rs.getInt("DisabledPolicy"));
					model.setDisabledMinutes(rs.getInt("DisabledMinutes"));
					model.setDisabledTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DisabledTime")));

					model.setRegisterTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("RegisterTime")));
					model.setPwdPromptQuestion(rs
							.getString("PWDPromptQuestion"));
					model.setPwdProtectQuestion(rs
							.getString("PWDProtectQuestion"));
					model.setPwdProtectAnswer(rs.getString("PWDProtectAnswer"));
					model.setEmail(rs.getString("PWDResetEmail"));

					model.setDbDeleted(rs.getInt("DbDeleted"));
					model.setDbCreateBy(rs.getInt("DbCreateBy"));
					model.setDbCreateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbCreateTime")));
					model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
					model.setDbLastUpdateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbLastUpdateTime")));
					userList.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mListMap.put("SysUser", userList);
		}
		return (List<SysUser>) mListMap.get("SysUser");
	}
	
	public static boolean Login(String username, String password) {
		List<SysUser> list = getUserList();
		for (SysUser user : list) {
			if (user.getUsername().equals(username)
					&& user.getUserPwd().equals(Helper.getMD5(password)))
				return true;
		}
		return false;
	}

	public static Document getUserDocument() {
		List<SysUser> userList = getUserList();
		try {
			mDocument = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Element root = mDocument.createElement("Users");
		mDocument.appendChild(root);

		for (SysUser user : userList) {
			Element node = convert(mDocument, user);
			root.appendChild(node);
		}
		return mDocument;
	}

	public static Element convert(Document document, Object obj) {
		Class<?> cls = obj.getClass();

		BeanInfo binfo = null;
		try {
			binfo = java.beans.Introspector.getBeanInfo(cls, Object.class);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		Element node = document.createElement(cls.getSimpleName());

		PropertyDescriptor[] properties = binfo.getPropertyDescriptors();
		try {
			for (PropertyDescriptor p : properties) {
				Element pNode = document.createElement(p.getName());
				Object pValue = p.getReadMethod().invoke(obj);
				pNode.setTextContent(pValue.toString());
				node.appendChild(pNode);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return node;
	}

	@SuppressWarnings("unchecked")
	public static List<SysCompany> getCompanyList() {
		if (!mListMap.containsKey("SysCompany")) {
			List<SysCompany> list = null;
			ResultSet rs = null;
			try {
				rs = DBUtils.getResultSet("Select * From Sys_Companys");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			list = new ArrayList<SysCompany>();
			SysCompany model = null;
			try {
				while (rs.next()) {
					model = new SysCompany();
					model.setCompanyId(rs.getInt("CompanyId"));
					model.setParentCompanyId(rs.getInt("ParentCompanyId"));
					model.setCompanyNo(rs.getString("CompanyNo"));
					model.setAutoNo(rs.getString("AutoNo"));
					model.setShortName(rs.getString("ShortName"));

					model.setFullName(rs.getString("FullName"));
					model.setRegisterationDate(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("RegisterationDate")));
					model.setShowOrder(rs.getInt("ShowOrder"));
					model.setContactPerson(rs.getString("ContactPerson"));
					model.setContactAddress(rs.getString("ContactAddress"));

					model.setContactPhone(rs.getString("ContactPhone"));
//					model.setContactMPhone(rs.getString("ContactMPhone"));
					model.setContactFax(rs.getString("ContactFax"));
					model.setContactEmail(rs.getString("ContactEmail"));
					model.setContactPostalCode(rs
							.getString("ContactPostalCode"));
					model.setScopeOfBusiness(rs.getString("ScopeOfBusiness"));

					model.setFlag(rs.getInt("Flag"));
					model.setMemo(rs.getString("Memo"));
						
					model.setDbDeleted(rs.getInt("DbDeleted"));
					model.setDbCreateBy(rs.getInt("DbCreateBy"));
					model.setDbCreateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbCreateTime")));
					model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
					model.setDbLastUpdateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbLastUpdateTime")));
					list.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mListMap.put("SysCompany", list);
		}
		return (List<SysCompany>) mListMap.get("SysCompany");
	}

	@SuppressWarnings("unchecked")
	public static List<SysDepartment> getDepartmentList() {
		if (!mListMap.containsKey("SysDepartment")) {
			List<SysDepartment> list = null;
			ResultSet rs = null;
			try {
				rs = DBUtils.getResultSet("Select * From Sys_Departments");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			list = new ArrayList<SysDepartment>();
			SysDepartment model = null;
			try {
				while (rs.next()) {
					model = new SysDepartment();
					model.setDeptId(rs.getInt("DeptId"));
					model.setParentDeptId(rs.getInt("PDeptId"));
//					model.setcom(rs.getInt("CompanyId"));
					model.setDeptName(rs.getString("DeptName"));
					model.setDeptNo(rs.getString("DeptNo"));
					model.setLevelDepth(rs.getInt("LevelDepth"));
					model.setAutoNo(rs.getString("AutoNo"));
					model.setShowOrder(rs.getInt("ShowOrder"));
					model.setIsLeaf(rs.getInt("IsLeaf"));
					model.setFlag(rs.getInt("Flag"));

					model.setDbDeleted(rs.getInt("DbDeleted"));
					model.setDbCreateBy(rs.getInt("DbCreateBy"));
					model.setDbCreateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbCreateTime")));
					model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
					model.setDbLastUpdateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbLastUpdateTime")));
					list.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mListMap.put("SysDepartment", list);
		}
		return (List<SysDepartment>) mListMap.get("SysDepartment");
	}

	@SuppressWarnings("unchecked")
	public static List<SysEmployee> getEmployeeList() {
		if (!mListMap.containsKey("SysEmployee")) {
			List<SysEmployee> list = null;
			ResultSet rs = null;
			try {
				rs = DBUtils.getResultSet("Select * From Sys_Employees");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			list = new ArrayList<SysEmployee>();
			SysEmployee model = null;
			try {
				while (rs.next()) {
					model = new SysEmployee();
					model.setEmplId(rs.getInt("EmplId"));
					model.setCompanyId(rs.getInt("CompanyId"));
					model.setEmplNo(rs.getString("EmplNo"));
					model.setEmplName_CN(rs.getString("EmplName_CN"));
					model.setEmplName_EN(rs.getString("EmplName_EN"));

					model.setSex(rs.getInt("Sex"));
					model.setBirthDate(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("Birthdate")));
					model.setNativePlace(rs.getString("NativePlace"));
					model.setCardId(rs.getString("CardId"));

					model.setContactAddress(rs.getString("ContactAddress"));

					model.setContactPhone(rs.getString("ContactPhone"));

					model.setContactMPhone(rs.getString("ContactMPhone"));
					model.setContactFax(rs.getString("ContactFax"));
					model.setContactEmail(rs.getString("ContactEmail"));

					model.setPositionId(rs.getInt("PositionId"));
					model.setPositionName(rs.getString("PositionName"));
					model.setPositionTitle(rs.getString("TitleName"));

					model.setDeptId(rs.getInt("DeptId"));
					model.setShowOrder(rs.getInt("ShowOrder"));
					model.setAutoNo(rs.getString("AutoNo"));

					model.setFlag(rs.getInt("Flag"));

					model.setField001(rs.getString("Field001"));
					model.setField002(rs.getString("Field002"));
					model.setField003(rs.getString("Field003"));
					model.setField004(rs.getString("Field004"));
					model.setField005(rs.getString("Field005"));

					model.setDbDeleted(rs.getInt("DbDeleted"));
					model.setDbCreateBy(rs.getInt("DbCreateBy"));
					model.setDbCreateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbCreateTime")));
					model.setDbLastUpdateBy(rs.getInt("DbLastUpdateBy"));
					model.setDbLastUpdateTime(com.fsmflying.helpers.Helper
							.parseDate(rs.getString("DbLastUpdateTime")));
					list.add(model);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mListMap.put("SysEmployee", list);
		}
		return (List<SysEmployee>) mListMap.get("SysEmployee");
	}
}
