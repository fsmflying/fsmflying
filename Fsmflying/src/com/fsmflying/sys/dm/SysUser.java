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
	
	private int 	mUserId;
	private String	mUsername;
	private String	mUserPwd;
	private String	mNickname;
	private String	mEmail;
	private int		mIPPolicy;
	private String	mIPAddress;
	private Date	mRegisterTime;
	private Date	mLastLoginTime;

	private int 	mStatus;
	private Date	mDisabledTime;
	private int 	mDisabledMinutes;
	private String	mPwdPromptQuestion;
	private	String	mPwdProtectQuestion;
	private	String	mPwdProtectAnswer;
	
	private Set<SysRole>	roles = new HashSet<SysRole>();
	

	public SysUser()
	{
		super();
		
	}
	
	public SysUser(String username)
	{
		this();
		this.mUsername = username;
	}
	
	@ManyToMany(targetEntity=SysRole.class)
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
		return mUserId;
	}
	
	public void setUserId(int userId) {
		this.mUserId = userId;
	}

	@Column(name="Username")
	public String getUsername() {
		return mUsername;
	}
	
	public void setUsername(String username) {
		this.mUsername = username;
	}

	@Column(name="UserPwd")
	public String getUserPwd() {
		return mUserPwd;
	}
	
	public void setUserPwd(String userPwd) {
		this.mUserPwd = userPwd;
	}

	@Column(name="Nickname")
	public String getNickname() {
		return mNickname;
	}

	public void setNickname(String nickname) {
		this.mNickname = nickname;
	}

	@Column(name="IPPolicy")
	public int getIPPolicy() {
		return mIPPolicy;
	}

	public void setIPPolicy(int mIPPolicy) {
		this.mIPPolicy = mIPPolicy;
	}

	@Column(name="IPAddress")
	public String getIPAddress() {
		return mIPAddress;
	}

	public void setIPAddress(String mIPAddress) {
		this.mIPAddress = mIPAddress;
	}

	@Column(name="RegisterTime")
	public Date getRegisterTime() {
		return mRegisterTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.mRegisterTime = registerTime;
	}

	@Column(name="LastLoginTime")
	public Date getLastLoginTime() {
		return mLastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.mLastLoginTime = lastLoginTime;
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
		return mStatus;
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
		this.mStatus = status;
	}

	@Column(name="DisabledMinutes")
	public int getDisabledMinutes() {
		return mDisabledMinutes;
	}

	public void setDisabledMinutes(int mDisabledMinutes) {
		this.mDisabledMinutes = mDisabledMinutes;
	}

	@Column(name="DisabledTime")
	public Date getDisabledTime() {
		return mDisabledTime;
	}

	public void setDisabledTime(Date mDisabledTime) {
		this.mDisabledTime = mDisabledTime;
	}

	@Column(name="PwdPromptQuestion")	
	public String getPwdPromptQuestion() {
		return mPwdPromptQuestion;
	}

	public void setPwdPromptQuestion(String pwdPromptQuestion) {
		mPwdPromptQuestion = pwdPromptQuestion;
	}

	@Column(name="PwdProtectQuestion")
	public String getPwdProtectQuestion() {
		return mPwdProtectQuestion;
	}

	public void setPwdProtectQuestion(String pwdProtectQuestion) {
		mPwdProtectQuestion = pwdProtectQuestion;
	}

	@Column(name="PwdProtectAnswer")
	public String getPwdProtectAnswer() {
		return mPwdProtectAnswer;
	}

	public void setPwdProtectAnswer(String pwdProtectAnswer) {
		mPwdProtectAnswer = pwdProtectAnswer;
	}

	@Column(name="Email")
	public String getEmail() {
		return mEmail;
	}

	public void setEmail(String email) {
		mEmail = email;
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

	@Override
	public String toString() {
		return "SysUser [mUserId=" + mUserId + ", mUsername=" + mUsername
				+ ", mPassword=" + mUserPwd + ", mNickname=" + mNickname
				+ ", mIPPolicy=" + mIPPolicy + ", mIPAddress=" + mIPAddress
				+ ", mRegisterTime=" + mRegisterTime + ", mLastLoginTime="
				+ mLastLoginTime + ", mDisabledPolicy=" + mStatus
				+ ", mDisabledTime=" + mDisabledTime + ", mDisabledMinutes="
				+ mDisabledMinutes + ", mPwdPromptQuestion="
				+ mPwdPromptQuestion + ", mPwdProtectQuestion="
				+ mPwdProtectQuestion + ", mPwdProtectAnswer="
				+ mPwdProtectAnswer + ", mPwdResetEmail=" + mEmail
				+ ", getDeleted()=" + getDbDeleted() + ", getCreateBy()="
				+ getDbCreateBy() + ", getCreateTime()=" + getDbCreateTime()
				+ ", getLastUpdateBy()=" + getDbLastUpdateBy()
				+ ", getLastUpdateTime()=" + getDbLastUpdateTime() 
				+ "]";
	}
	
	
}
