package com.fsmflying.struts23.user.base;

import com.fsmflying.struts23.user.entity.PmsUser;



public interface UserLoginedAware {

	/**
	 * 取得登录的用户
	 * 
	 * @return
	 */
	public PmsUser getLoginedUser();
}
