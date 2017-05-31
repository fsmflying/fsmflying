package com.fsmflying.sys.service;

public interface CacheService {
	public void put(String key,Object data);
	public Object get(String key);
	public void remove(String key);
	public void clear();
}
