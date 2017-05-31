package com.fsmflying.sys.dm.helper;

import java.util.Date;

import com.fsmflying.sys.dm.SysUser;

public class User {
	private int userId;
	private String username;
	private String nickname;
	private Date loginTime;

	public User() {
	}

	public User(String username, String nickname, Date loginTime) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.loginTime = loginTime;
	}

	public User(int userId, String username, String nickname, Date loginTime) {
		super();
		this.userId = userId;
		this.username = username;
		this.nickname = nickname;
		this.loginTime = loginTime;
	}

	public User(SysUser model) {
		super();
		this.userId = model.getUserId();
		this.username = model.getUsername();
		this.nickname = model.getNickname();
		this.loginTime = model.getLastLoginTime();
	}

	public User(String username) {
		super();
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	@Override
	public String toString() {
		return "{userId=" + userId + ", username=" + username + ", nickname=" + nickname + ", loginTime="
				+ loginTime + "}";
	}

	
	
	
}
