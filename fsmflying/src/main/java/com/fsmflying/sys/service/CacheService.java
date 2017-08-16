package com.fsmflying.sys.service;

public interface CacheService {
	public String getSubSystemName();
	public int getRunLevel();
	
	public void init();
	public void close();

	public void put(String subKey, String className, int runLevel,Object data);
	public void put(String subKey, String className,Object data);
	public void put(String subKey, Object data);

	public Object get(String subKey,String className,int runLevel);
	public Object get(String subKey,String className);

	public void clear();
	public void clearBySubSystem();
	public void clearByClassName(String className);
	public void clearByRunLevel(int runLevel);
	public void clear(String subKey,String className,int runLevel);
}
