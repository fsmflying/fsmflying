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
	private String	mPassword;
	private String	mNickname;
	private int		mIPPolicy;
	private String	mIPAddress;
	private Date	mRegisterTime;
	private Date	mLastLoginTime;

	private int 	mDisabledPolicy;
	private Date	mDisabledTime;
	private int 	mDisabledMinutes;
	private String	mPwdPromptQuestion;
	private	String	mPwdProtectQuestion;
	private	String	mPwdProtectAnswer;
	private String	mPwdResetEmail;
	
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
	
	public void setUserId(int mUserId) {
		this.mUserId = mUserId;
	}

	@Column(name="Username")
	public String getUsername() {
		return mUsername;
	}
	
	public void setUsername(String mUsername) {
		this.mUsername = mUsername;
	}

	@Column(name="UserPwd")
	public String getPassword() {
		return mPassword;
	}
	
	public void setPassword(String mPassword) {
		this.mPassword = mPassword;
	}

	@Column(name="Nickname")
	public String getNickname() {
		return mNickname;
	}

	public void setNickname(String mNickname) {
		this.mNickname = mNickname;
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

	public void setRegisterTime(Date mRegisterTime) {
		this.mRegisterTime = mRegisterTime;
	}

	@Column(name="LastLoginTime")
	public Date getLastLoginTime() {
		return mLastLoginTime;
	}

	public void setLastLoginTime(Date mLastLoginTime) {
		this.mLastLoginTime = mLastLoginTime;
	}

	@Column(name="DisabledPolicy")
	public int getDisabledPolicy() {
		return mDisabledPolicy;
	}

	public void setDisabledPolicy(int mDisabledPolicy) {
		this.mDisabledPolicy = mDisabledPolicy;
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

	@Column(name="PwdResetEmail")
	public String getPwdResetEmail() {
		return mPwdResetEmail;
	}

	public void setPwdResetEmail(String pwdResetEmail) {
		mPwdResetEmail = pwdResetEmail;
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
				+ ", mPassword=" + mPassword + ", mNickname=" + mNickname
				+ ", mIPPolicy=" + mIPPolicy + ", mIPAddress=" + mIPAddress
				+ ", mRegisterTime=" + mRegisterTime + ", mLastLoginTime="
				+ mLastLoginTime + ", mDisabledPolicy=" + mDisabledPolicy
				+ ", mDisabledTime=" + mDisabledTime + ", mDisabledMinutes="
				+ mDisabledMinutes + ", mPwdPromptQuestion="
				+ mPwdPromptQuestion + ", mPwdProtectQuestion="
				+ mPwdProtectQuestion + ", mPwdProtectAnswer="
				+ mPwdProtectAnswer + ", mPwdResetEmail=" + mPwdResetEmail
				+ ", getDeleted()=" + getDbDeleted() + ", getCreateBy()="
				+ getDbCreateBy() + ", getCreateTime()=" + getDbCreateTime()
				+ ", getLastUpdateBy()=" + getDbLastUpdateBy()
				+ ", getLastUpdateTime()=" + getDbLastUpdateTime() 
				+ "]";
	}
	
	
}
