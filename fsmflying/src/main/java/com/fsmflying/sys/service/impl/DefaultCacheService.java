package com.fsmflying.sys.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fsmflying.sys.service.CacheService;

public class DefaultCacheService implements CacheService {
	Logger logger = LoggerFactory.getLogger(DefaultCacheService.class);
	static Properties props = null;//
	static Hashtable<String, String> propsHt = new Hashtable<String, String>();

	public DefaultCacheService() {
		if (props == null) {
			props = new Properties();
			try {
				props.load(DefaultCacheService.class.getClassLoader().getResourceAsStream("cache.properties"));
				for (Object key : props.keySet()) {
					propsHt.put((String) key, (String) props.get(key));
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				e.printStackTrace();
			}

		}

	}

	// 序列化
	public static byte[] serialize(Object obj) {
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(obj);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 反序列化
	public static Object unserizlize(byte[] byt) {
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		bis = new ByteArrayInputStream(byt);
		try {
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	public String getCacheKey(String subKey, String className, int runLevel) {
		return String.format("%s*%s*%s*%s", this.getSubSystemName(), runLevel, className, subKey);
	}

	public String getCacheKey(String subKey, String className) {
		return String.format("%s*%s*%s*%s", this.getSubSystemName(), this.getRunLevel(), className, subKey);
	}

	public String getValue(String propertyName) {
		if (propsHt != null && propsHt.size() > 0 && propsHt.containsKey(propertyName)) {
			return propsHt.get(propertyName);
		}
		return null;
	}

	@Override
	public String getSubSystemName() {
		return getValue("subSystemName");
	}

	@Override
	public int getRunLevel() {
		String value = getValue("defaultRunLevel");
		if (value != null && value.matches("0|([1-9][0-9]*)")) {
			return Integer.parseInt(value);
		}
		return 10000;
	}

	@Override
	public void put(String subKey, String className, int runLevel, Object data) {

	}

	@Override
	public void put(String subKey, String className, Object data) {

	}

	@Override
	public void put(String subKey, Object data) {

	}

	@Override
	public Object get(String subKey, String className, int runLevel) {
		return null;
	}

	@Override
	public Object get(String subKey, String className) {
		return null;
	}

	@Override
	public void clearBySubSystem() {
		
	}

	@Override
	public void clearByClassName(String className) {

	}

	@Override
	public void clearByRunLevel(int runLevel) {

	}

	@Override
	public void init() {

	}

	@Override
	public void close() {

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear(String subKey, String className, int runLevel) {
		// TODO Auto-generated method stub
		
	}

}
