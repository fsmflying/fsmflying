package com.fsmflying.sys.dm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Sys_Roles",
uniqueConstraints=@UniqueConstraint(columnNames = { "RoleCode" })
)
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.erole",
		initialValue=1
		
	)
public class SysRole extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private int mRoleId;
	private String mRoleName;
	private String mRoleCode;
	private String mMemo;
	
	private Set<SysUser> users = new HashSet<SysUser>();
//	private Set<SysTab> tabs = new HashSet<SysTab>();
//	private Set<SysDataAuth> dataAuths = new HashSet<SysDataAuth>();
//	private Set<SysDataAuthItem> dataAuthItems = new HashSet<SysDataAuthItem>();
//	private Set<SysFuncPoint> funcPoints = new HashSet<SysFuncPoint>();
//	private Set<SysMenu> menus = new HashSet<SysMenu>();
	
	public SysRole() {
		super();
	}
	@ManyToMany(targetEntity=SysUser.class)
	@JoinTable(name="Sys_RUserRole",
		joinColumns=@JoinColumn(name="RoleId",referencedColumnName="RoleId"),
		inverseJoinColumns=@JoinColumn(name="UserId",referencedColumnName="UserId")
	)
	public Set<SysUser> getUsers() {
		return users;
	}
	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="RoleId")
	public int getRoleId() {
		return mRoleId;
	}
	public void setRoleId(int roleId) {
		mRoleId = roleId;
	}
	@Column(name="RoleName")
	public String getRoleName() {
		return mRoleName;
	}
	public void setRoleName(String roleName) {
		mRoleName = roleName;
	}
	@Column(name="RoleCode")
	public String getRoleCode() {
		return mRoleCode;
	}
	public void setRoleCode(String roleCode) {
		mRoleCode = roleCode;
	}
	@Column(name="Memo")
	public String getMemo() {
		return mMemo;
	}
	public void setMemo(String memo) {
		mMemo = memo;
	}
	
	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.erole";
	}
	
	@Override
	@Column(name="DbDeleted")
	public int getDbDeleted() {
		// TODO Auto-generated method stub
		return super.getDbDeleted();
	}

	@Override
	@Column(name="DbCreateBy")
	public int getDbCreateBy() {
		// TODO Auto-generated method stub
		return super.getDbCreateBy();
	}

	@Override
	@Column(name="DbCreateTime")
	public Date getDbCreateTime() {
		// TODO Auto-generated method stub
		return super.getDbCreateTime();
	}

	@Override
	@Column(name="DbLastUpdateBy")
	public int getDbLastUpdateBy() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name="DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateTime();
	}
}
