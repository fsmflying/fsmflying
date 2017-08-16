package com.fsmflying.sys.dm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Sys_Users", uniqueConstraints = @UniqueConstraint(columnNames = { "Username" }))
@TableGenerator(name = "tableIdGenerator", table = "Sys_IdGenerators", pkColumnName = "KeyName", valueColumnName = "NextValue", pkColumnValue = "sys.euser", initialValue = 1

)
public class SysUser extends AbstractBean/**/ {

	private static final long serialVersionUID = 1L;

	private int userId;
	private int mainUserId;
	private String username;
	private String userPwd;
	private String nickname;
	private String email;
	private int ipPolicy;
	private String ipAddress;
	private Date registerTime;
	private Date lastLoginTime;
	private Date pwdErrorTime;
	private int pwdErrorCount;
	private int requireChangePwd;
	private String memo;
	private String mobileNo;
	private int userType;

	private int status;
	private Date disabledTime;
	private int disabledMinutes;
	private String pwdPromptQuestion;
	private String pwdProtectQuestion;
	private String pwdProtectAnswer;

	private Set<SysRole> roles = new HashSet<SysRole>();

	public SysUser() {
		super();

	}

	public SysUser(int userId) {
		super();
		this.userId = userId;
	}

	public SysUser(String username) {
		this();
		this.username = username;
	}

	@ManyToMany(targetEntity = SysRole.class, fetch = FetchType.EAGER)
	@JoinTable(name = "Sys_RUserRole", joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "UserId"), inverseJoinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "RoleId"))
	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableIdGenerator")
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "MainUserId", nullable = false, columnDefinition = "int default -1")
	public int getMainUserId() {
		return mainUserId;
	}

	public void setMainUserId(int mainUserId) {
		this.mainUserId = mainUserId;
	}

	@Column(name = "Username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "UserPwd")
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Column(name = "Nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "IpPolicy")
	public int getIpPolicy() {
		return ipPolicy;
	}

	public void setIpPolicy(int mIPPolicy) {
		this.ipPolicy = mIPPolicy;
	}

	@Column(name = "IPAddress")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String mIPAddress) {
		this.ipAddress = mIPAddress;
	}

	@Column(name = "RegisterTime")
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name = "LastLoginTime")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "PwdErrorTime")
	public Date getPwdErrorTime() {
		return pwdErrorTime;
	}

	public void setPwdErrorTime(Date pwdErrorTime) {
		this.pwdErrorTime = pwdErrorTime;
	}

	@Column(name = "PwdErrorCount", columnDefinition = "int default 0")
	public int getPwdErrorCount() {
		return pwdErrorCount;
	}

	public void setPwdErrorCount(int pwdErrorCount) {
		this.pwdErrorCount = pwdErrorCount;
	}

	/**
	 * 获取用户账号状态<br/>
	 * [0]:用户账号正常，可以登录，退出;<br/>
	 * [1]:用户被永久禁用;<br/>
	 * [2]:用户被禁用一段时间，禁用时间以分钟为单位，使用disabledMinutes属性来设置;<br/>
	 * [3]:每天都禁用一段时间,"xxxx-xx-xx
	 * 23:15:00.000",DisabledMinutes=6*60=360,则分在每一天23:15开始禁用，禁用6个小时，到第二天的5:15结束
	 * ;<br/>
	 * ;
	 * 
	 * @return 用户账号状态
	 */
	@Column(name = "Status")
	public int getStatus() {
		return status;
	}

	/**
	 * 设置用户的账号状态(set current user status)<br/>
	 * 获取用户账号状态<br/>
	 * [0]:用户账号正常，可以登录，退出;<br/>
	 * [1]:用户被永久禁用;<br/>
	 * [2]:用户被禁用一段时间，禁用时间以分钟为单位，使用disabledMinutes属性来设置;<br/>
	 * [3]:每天都禁用一段时间,"xxxx-xx-xx
	 * 23:15:00.000",DisabledMinutes=6*60=360,则分在每一天23:15开始禁用，禁用6个小时，到第二天的5:15结束
	 * ;<br/>
	 * ;
	 * 
	 * @param status
	 *            用户账号状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "DisabledMinutes")
	public int getDisabledMinutes() {
		return disabledMinutes;
	}

	public void setDisabledMinutes(int mDisabledMinutes) {
		this.disabledMinutes = mDisabledMinutes;
	}

	@Column(name = "DisabledTime")
	public Date getDisabledTime() {
		return disabledTime;
	}

	public void setDisabledTime(Date mDisabledTime) {
		this.disabledTime = mDisabledTime;
	}

	@Column(name = "PwdPromptQuestion")
	public String getPwdPromptQuestion() {
		return pwdPromptQuestion;
	}

	public void setPwdPromptQuestion(String pwdPromptQuestion) {
		this.pwdPromptQuestion = pwdPromptQuestion;
	}

	@Column(name = "PwdProtectQuestion")
	public String getPwdProtectQuestion() {
		return pwdProtectQuestion;
	}

	public void setPwdProtectQuestion(String pwdProtectQuestion) {
		this.pwdProtectQuestion = pwdProtectQuestion;
	}

	@Column(name = "PwdProtectAnswer")
	public String getPwdProtectAnswer() {
		return pwdProtectAnswer;
	}

	public void setPwdProtectAnswer(String pwdProtectAnswer) {
		this.pwdProtectAnswer = pwdProtectAnswer;
	}

	@Column(name = "Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "RequireChangePwd", columnDefinition = "int default 0")
	public int getRequireChangePwd() {
		return requireChangePwd;
	}

	public void setRequireChangePwd(int requireChangePwd) {
		this.requireChangePwd = requireChangePwd;
	}

	@Override
	@Column(name = "DbDeleted", nullable = false, columnDefinition = "int default 0")
	public int getDbDeleted() {
		return super.getDbDeleted();
	}

	@Override
	@Column(name = "DbCreateBy", nullable = false, columnDefinition = "int default -1")
	public int getDbCreateBy() {
		return super.getDbCreateBy();
	}

	@Override
	@Column(name = "DbCreateTime")
	public Date getDbCreateTime() {
		return super.getDbCreateTime();
	}

	@Override
	@Column(name = "DbLastUpdateBy", nullable = false, columnDefinition = "int default -1")
	public int getDbLastUpdateBy() {
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name = "DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		return super.getDbLastUpdateTime();
	}

	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.euser";
	}

	private SysEmployee employee;

	@Transient
	// @OneToOne(targetEntity=SysEmployee.class,fetch=FetchType.EAGER)
	// @JoinTable(name="Sys_RUserEmpl",
	// joinColumns=@JoinColumn(name="UserId", referencedColumnName="UserId"),
	// inverseJoinColumns= @JoinColumn(name="EmplId",
	// referencedColumnName="EmplId"))
	public SysEmployee getEmployee() {
		return this.employee;
	}

	public void setEmployee(SysEmployee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", username=" + username + ", nickname=" + nickname + ", email=" + email
				+ ", ipPolicy=" + ipPolicy + ", ipAddress=" + ipAddress + ", registerTime=" + registerTime
				+ ", lastLoginTime=" + lastLoginTime + ", status=" + status + ", disabledTime=" + disabledTime
				+ ", disabledMinutes=" + disabledMinutes + ", pwdPromptQuestion=" + pwdPromptQuestion
				+ ", pwdProtectQuestion=" + pwdProtectQuestion + ", pwdProtectAnswer=" + pwdProtectAnswer + ", roles="
				+ roles + ", employee=" + employee + "]";
	}

	@Column(name = "Memo")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "MobileNo")
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(name = "UserType", nullable = false, columnDefinition = "int default 0")
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	// @Override
	// public String toString() {
	// return "SysUser [mUserId=" + userId + ", mUsername=" + username
	// + ", mPassword=" + userPwd + ", mNickname=" + nickname
	// + ", mIPPolicy=" + ipPolicy + ", mIPAddress=" + ipAddress
	// + ", mRegisterTime=" + registerTime + ", mLastLoginTime="
	// + lastLoginTime + ", mDisabledPolicy=" + status
	// + ", mDisabledTime=" + disabledTime + ", mDisabledMinutes="
	// + disabledMinutes + ", mPwdPromptQuestion="
	// + pwdPromptQuestion + ", mPwdProtectQuestion="
	// + pwdProtectQuestion + ", mPwdProtectAnswer="
	// + pwdProtectAnswer + ", mPwdResetEmail=" + email
	// + ", getDeleted()=" + getDbDeleted() + ", getCreateBy()="
	// + getDbCreateBy() + ", getCreateTime()=" + getDbCreateTime()
	// + ", getLastUpdateBy()=" + getDbLastUpdateBy()
	// + ", getLastUpdateTime()=" + getDbLastUpdateTime()
	// + "]";
	// }

}
