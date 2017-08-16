package com.fsmflying.struts23.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.fsmflying.struts23.common.core.dao.BaseDaoImpl;
import com.fsmflying.struts23.user.dao.SysUserDao;
import com.fsmflying.struts23.user.entity.PmsUser;
import com.fsmflying.sys.dm.SysUser;


/**
 * 
 * @描述: 用户表数据访问层接口实现类.
 * @作者: WuShuicheng .
 * @创建时间: 2013-7-22,下午5:51:47 .
 * @版本: 1.0 .
 */
//@Repository("pmsUserDao")
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements SysUserDao {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param username
	 *            .
	 * @return user .
	 */

	public SysUser findByUserNo(String username) {
		return super.getSqlSession().selectOne(getStatement("findByUserNo"), username);
	}

}
