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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Sys_Users",
uniqueConstraints=@UniqueConstraint(columnNames = { "Username" })
)
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.euser",
		initialValue=1
		
	)
public class SysUser extends AbstractBean/**/{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int 	userId;
	private String	username;
	private String	userPwd;
	private String	nickname;
	private String	email;
	private int		ipPolicy;
	private String	ipAddress;
	private Date	registerTime;
	private Date	lastLoginTime;

	private int 	status;
	private Date	disabledTime;
	private int 	disabledMinutes;
	private String	pwdPromptQuestion;
	private	String	pwdProtectQuestion;
	private	String	pwdProtectAnswer;
	
	private Set<SysRole>	roles = new HashSet<SysRole>();
	
	public SysUser()
	{
		super();
		
	}
	
	public SysUser(int userId) {
		super();
		this.userId = userId;
	}

	public SysUser(String username)
	{
		this();
		this.username = username;
	}
	
	@ManyToMany(targetEntity=SysRole.class,fetch=FetchType.EAGER)
	@JoinTable(name="Sys_RUserRole",
		joinColumns=@JoinColumn(name="UserId", referencedColumnName="UserId"),
        inverseJoinColumns= @JoinColumn(name="RoleId", referencedColumnName="RoleId"))
	public Set<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SysRole> roles) {
		this.roles = roles;
	}

	@Id
	@Column(name="UserId")
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name="Username")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name="UserPwd")
	public String getUserPwd() {
		return userPwd;
	}
	
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Column(name="Nickname")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name="IpPolicy")
	public int getIpPolicy() {
		return ipPolicy;
	}

	public void setIpPolicy(int mIPPolicy) {
		this.ipPolicy = mIPPolicy;
	}

	@Column(name="IPAddress")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String mIPAddress) {
		this.ipAddress = mIPAddress;
	}

	@Column(name="RegisterTime")
	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Column(name="LastLoginTime")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 获取当前用户的状态，其含义如下：<br/>
	 * [0]:正常状态，用户可正常登录;<br/>
	 * [1]:完全禁用状态，用户不可以登录;<br/>
	 * [2]:规定的时间段内禁用，从DisabledTime开始，禁用DisabledMinutes分钟;<br/>
	 * [3]:按一天内的时间段禁用，如果DisabledTime为"xxxx-xx-xx 23:15:00.000",DisabledMinutes=6*60=360,
	 * 则在每天的23:15开始，禁用6个小时，即直到第二天05:15;<br/>
	 * 注意：如果stauts=3,则DisabledMinutes最好不要大于1440,因为一天也只有1440分钟，如果大于或等于1440，则等同于完全禁用;
	 * @return
	 */
	@Column(name="Status")
	public int getStatus() {
		return status;
	}
	
	/**
	 *　设置当前用户的状态(set current user status)<br/>
	 * [0]:正常状态，用户可正常登录;<br/>
	 * [1]:完全禁用状态，用户不可以登录;<br/>
	 * [2]:规定的时间段内禁用，从DisabledTime开始，禁用DisabledMinutes分钟;<br/>
	 * [3]:按一天内的时间段禁用，如果DisabledTime为"xxxx-xx-xx 23:15:00.000",DisabledMinutes=6*60=360,
	 * 则在每天的23:15开始，禁用6个小时，即直到第二天05:15;<br/>
	 * 注意：如果stauts=3,则DisabledMinutes最好不要大于1440,因为一天也只有1440分钟，如果大于或等于1440，则等同于完全禁用;
	 * @param status 用户状态
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="DisabledMinutes")
	public int getDisabledMinutes() {
		return disabledMinutes;
	}

	public void setDisabledMinutes(int mDisabledMinutes) {
		this.disabledMinutes = mDisabledMinutes;
	}

	@Column(name="DisabledTime")
	public Date getDisabledTime() {
		return disabledTime;
	}

	public void setDisabledTime(Date mDisabledTime) {
		this.disabledTime = mDisabledTime;
	}

	@Column(name="PwdPromptQuestion")	
	public String getPwdPromptQuestion() {
		return pwdPromptQuestion;
	}

	public void setPwdPromptQuestion(String pwdPromptQuestion) {
		this.pwdPromptQuestion = pwdPromptQuestion;
	}

	@Column(name="PwdProtectQuestion")
	public String getPwdProtectQuestion() {
		return pwdProtectQuestion;
	}

	public void setPwdProtectQuestion(String pwdProtectQuestion) {
		this.pwdProtectQuestion = pwdProtectQuestion;
	}

	@Column(name="PwdProtectAnswer")
	public String getPwdProtectAnswer() {
		return pwdProtectAnswer;
	}

	public void setPwdProtectAnswer(String pwdProtectAnswer) {
		this.pwdProtectAnswer = pwdProtectAnswer;
	}

	@Column(name="Email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	@Column(name="DbDeleted",nullable=false,columnDefinition="int default 0")
	public int getDbDeleted() {
		return super.getDbDeleted();
	}

	@Override
	@Column(name="DbCreateBy",nullable=false,columnDefinition="int default -1")
	public int getDbCreateBy() {
		return super.getDbCreateBy();
	}

	@Override
	@Column(name="DbCreateTime")
	public Date getDbCreateTime() {
		return super.getDbCreateTime();
	}

	@Override
	@Column(name="DbLastUpdateBy",nullable=false,columnDefinition="int default -1")
	public int getDbLastUpdateBy() {
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name="DbLastUpdateTime")
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
//	@OneToOne(targetEntity=SysEmployee.class,fetch=FetchType.EAGER)
//	@JoinTable(name="Sys_RUserEmpl",
//		joinColumns=@JoinColumn(name="UserId", referencedColumnName="UserId"),
//        inverseJoinColumns= @JoinColumn(name="EmplId", referencedColumnName="EmplId"))
	public SysEmployee getEmployee(){
		return this.employee;
	}
	
	public void setEmployee(SysEmployee employee){
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
	

//	@Override
//	public String toString() {
//		return "SysUser [mUserId=" + userId + ", mUsername=" + username
//				+ ", mPassword=" + userPwd + ", mNickname=" + nickname
//				+ ", mIPPolicy=" + ipPolicy + ", mIPAddress=" + ipAddress
//				+ ", mRegisterTime=" + registerTime + ", mLastLoginTime="
//				+ lastLoginTime + ", mDisabledPolicy=" + status
//				+ ", mDisabledTime=" + disabledTime + ", mDisabledMinutes="
//				+ disabledMinutes + ", mPwdPromptQuestion="
//				+ pwdPromptQuestion + ", mPwdProtectQuestion="
//				+ pwdProtectQuestion + ", mPwdProtectAnswer="
//				+ pwdProtectAnswer + ", mPwdResetEmail=" + email
//				+ ", getDeleted()=" + getDbDeleted() + ", getCreateBy()="
//				+ getDbCreateBy() + ", getCreateTime()=" + getDbCreateTime()
//				+ ", getLastUpdateBy()=" + getDbLastUpdateBy()
//				+ ", getLastUpdateTime()=" + getDbLastUpdateTime() 
//				+ "]";
//	}
	
	
}
