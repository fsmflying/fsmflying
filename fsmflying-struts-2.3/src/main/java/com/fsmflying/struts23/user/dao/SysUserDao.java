package com.fsmflying.struts23.user.dao;

import com.fsmflying.struts23.common.core.dao.BaseDao;
import com.fsmflying.struts23.user.entity.PmsUser;
import com.fsmflying.sys.dm.SysUser;


/**
 * 
 * @描述: 用户表数据访问层接口.
 * @作者: WuShuicheng .
 * @创建时间: 2013-7-22,下午5:51:47 .
 * @版本: 1.0 .
 */
public interface SysUserDao extends BaseDao<SysUser> {

	/**
	 * 根据用户登录名获取用户信息.
	 * 
	 * @param loginName
	 *            .
	 * @return user .
	 */
	PmsUser findByUserNo(String userNo);

}
