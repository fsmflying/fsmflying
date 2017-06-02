package com.fsmflying.spring.service.impl;

import org.springframework.cache.annotation.Cacheable;

import com.fsmflying.sys.dm.SysUser;

public class SystemManageServiceImpl extends com.fsmflying.sys.service.impl.SystemManageServiceImpl {
	
	@Override
	public SysUser getModelOfSysUser(int id) {
		return super.getModelOfSysUser(id);
	}
	
	@Cacheable
	@Override
	public SysUser getModelOfSysUser(String username,boolean refreshCache) {
		return super.getModelOfSysUser(username,true);
	}
}
