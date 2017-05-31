package com.fsmflying.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.fsmflying.util.TwoTuple;

public class DBUtils {

	private static final Object db_lock = new Object();

	private static Properties dbProperties = null;

	private static String getDBProperty(String key) {
		getDBProperties();
		return dbProperties.getProperty(key);
	}

	private static void getDBProperties() {
		// String value = null;

		synchronized (db_lock) {
			if (dbProperties == null) {
				dbProperties = new Properties();
				try {
					URL url = null;
					InputStream is = null;
					
//					url = DBUtils.class.getClassLoader().getResource(
//							"");
//					if (url != null)
//						System.out.println("1:"+url.getPath());
//					url = DBUtils.class.getClassLoader().getResource(
//							"exampledb.properties");
//					if (url != null)
//						System.out.println("2:"+url.getPath());
//					
//					url = DBUtils.class.getResource("/");
//					if (url != null)
//						System.out.println("3:"+url.getPath());
					
					url = DBUtils.class.getResource("/exampledb.properties");
//					if (url != null)
//						System.out.println("4:"+url.getPath());
					
					if (url != null && new File(url.getPath()).exists()/*&& new File(url.getPath()).isFile()*/)
					{
						is = new FileInputStream(url.getPath());
						System.out.println("com.fsmflying.helpers.DBUtils loading database configuratoin file ["+url.getPath()+"]!");
					}
					else
						is = DBUtils.class
								.getResourceAsStream("/resource/exampledb.properties");
					dbProperties.load(is);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


//	public static void printPackageInfo() {
//		String filePath = DBUtils.class.getResource("/exampledb.properties")
//				.getPath();
//		System.out.println("example database file path:" + filePath);
//	}


//	public static String getDefaultDbConfigFilePath() {
//		return DBUtils.class.getResource("/exampledb.properties").getPath();
//	}

	public static ResultSet getResultSet(String sql) throws SQLException,
			ClassNotFoundException {
		Connection conn = getConnection();
		Statement smt = null;
		ResultSet rs = null;
		smt = conn.createStatement();
		rs = smt.executeQuery(sql);
		return rs;

	}

	public static ResultSet getResultSet(String tableName, String sql,
			List<SqlParameter<Object>> params) throws SQLException,
			ClassNotFoundException {
		Connection conn = getConnection();
		PreparedStatement smt = null;
		ResultSet rs = null;
		smt = conn.prepareStatement(sql);
		AddParameters(smt, params);
		rs = smt.executeQuery(sql);
		smt.close();
		conn.close();

		return rs;

	}

	private static void AddParameters(PreparedStatement smt,
			List<SqlParameter<Object>> params) {

		SqlParameter<?> param = null;
		try {
			for (int i = 1; i <= params.size(); i++) {
				param = params.get(i);
				smt.setObject(i, param.getValue());
				// if ("java.lang.Integer".equals(param.getValue().getClass()
				// .getCanonicalName()))
				// smt.setInt(i, (int) param.getValue());
				// else if("java.lang.String".equals(param.getValue().getClass()
				// .getCanonicalName()))
				// {
				// smt.setString(i, param.getValue());
				// }
				// else if("java.util.Date".equals(param.getValue().getClass()
				// .getCanonicalName()))
				// {
				// smt.setDate(i, (Date) param.getValue());
				// }else
				// if("java.lang.Integer".equals(param.getValue().getClass()
				// .getCanonicalName()))
				// {
				//
				// }
				// else{
				// smt.setob
				// }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static HashMap<String, HashMap<String, TwoTuple<Method, Method>>> dm_get_set_accessors = new HashMap<String, HashMap<String, TwoTuple<Method, Method>>>();

	private static final Object dm_get_set_lock = new Object();

	private static HashMap<String, TwoTuple<Method, Method>> buildDMAccessMap(
			String tableName, Class<?> cls) {
		if (tableName == null || tableName.trim().isEmpty() || cls == null)
			throw new IllegalArgumentException();
		String[] fieldNames = getFieldNames(tableName);
		Method[] methods = cls.getMethods();
		Method m = null;
		HashMap<String, TwoTuple<Method, Method>> getsetpairs = new HashMap<String, TwoTuple<Method, Method>>();
		for (int i = 0; i < fieldNames.length; i++) {
			getsetpairs.put(fieldNames[i], new TwoTuple<Method, Method>());
		}
		TwoTuple<Method, Method> tuple = null;
		for (int i = 0; i < methods.length; i++) {
			m = methods[i];

			if (m.isAnnotationPresent(DbFieldSet.class)) {
				DbFieldSet s = m.getAnnotation(DbFieldSet.class);
				tuple = getsetpairs.get(s.value());
				if (tuple == null) {
					System.out.println(""
							+ cls.getClass().getCanonicalName() + ""
							+ s.value() + "");
				}
				if (tuple != null && s != null)
					tuple.setSecond(m);

			} else if (m.isAnnotationPresent(DbFieldGet.class)) {

				DbFieldGet g = m.getAnnotation(DbFieldGet.class);
				tuple = getsetpairs.get(g.value());
				if (tuple == null) {
					System.out.println(""
							+ cls.getClass().getCanonicalName() + ""
							+ g.value() + "");
				}
				if (tuple != null && g != null)
					tuple.setFirst(m);
			}
		}
		return getsetpairs;
	}

	private static HashMap<String, TwoTuple<Method, Method>> getDMAccessMap(
			String tableName, Class<?> cls) {
		if (tableName == null || tableName.trim().isEmpty() || cls == null)
			throw new IllegalArgumentException();
		if (dm_get_set_accessors == null
				|| !dm_get_set_accessors.containsKey(tableName.toUpperCase())) {
			synchronized (dm_get_set_lock) {
				if (dm_get_set_accessors == null)
					dm_get_set_accessors = new HashMap<String, HashMap<String, TwoTuple<Method, Method>>>();
				if (!dm_get_set_accessors.containsKey(tableName.toUpperCase()))
					dm_get_set_accessors.put(tableName.toUpperCase(),
							buildDMAccessMap(tableName, cls));
			}
		}
		return dm_get_set_accessors.get(tableName.toUpperCase());

	}

	private static List<SqlParameter<Object>> parseParameters(String tableName,
			Object obj) {

		if (tableName == null || tableName.trim().isEmpty() || obj == null)
			throw new IllegalArgumentException();

		List<SqlParameter<Object>> list = new ArrayList<SqlParameter<Object>>();
		SqlParameter<Object> param = null;

		HashMap<String, TwoTuple<Method, Method>> getsetpairs = getDMAccessMap(
				tableName, obj.getClass());

		Set<String> keys = getsetpairs.keySet();
		Method getM = null;
		// Method setM = null;
		Object value = null;
		for (String key : keys) {
			TwoTuple<Method, Method> t = getsetpairs.get(key);
			getM = t.getFirst();
			// setM = t.getSecond();
			if (getM == null)
				System.out.println(key + "");
			try {
				value = getM.invoke(obj);
			} catch (IllegalAccessException e) {

			} catch (IllegalArgumentException e) {
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			param = new SqlParameter<Object>(key, value);
			list.add((SqlParameter<Object>) param);
		}
		return list;
	}

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {

		Connection conn = null;
		// String dbtype = getDBProperty("dbtype");
		String driver = getDBProperty("driver");
		String username = getDBProperty("username");
		String password = getDBProperty("password");
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection(driver, username, password);
		return conn;
	}

	public static void RegisterTable(String tableName, String allFieldNames) {
		SqlBuilder.register(tableName, allFieldNames);
	}

	public static String getQuerySql(String tableName, String sqlWhere,
			String allFieldNames) {

		return SqlBuilder.instance(tableName, allFieldNames).getQuerySql(
				sqlWhere);
	}

	public static String getUpdateSql(String tableName, String sqlWhere,
			String allFieldNames) {

		return SqlBuilder.instance(tableName, allFieldNames).getUpdateSql(
				sqlWhere);
	}

	public static String getDeleteSql(String tableName, String sqlWhere,
			String allFieldNames) {

		return SqlBuilder.instance(tableName, allFieldNames).getDeleteSql(
				sqlWhere);
	}

	public static String[] getFieldNames(String tableName) {
		return SqlBuilder.instance(tableName, null).getFieldNames();
	}

	public static String getInsertSql(String tableName) {
		return SqlBuilder.instance(tableName, null).getSqlInsert();
	}

	public static <T> boolean Add(String tableName, T t) throws SQLException,
			ClassNotFoundException {
		if (tableName == null || tableName.trim().isEmpty() || t == null)
			throw new IllegalArgumentException();
		String sql = getInsertSql(tableName);
		Connection conn = getConnection();
		PreparedStatement smt = conn.prepareStatement(sql);
		List<SqlParameter<Object>> params = parseParameters(tableName, t);
		AddParameters(smt, params);
		int flag = smt.executeUpdate();
		return flag == 0 ? true : false;
	}

	public static <T> boolean Update(String tableName, T t)
			throws SQLException, ClassNotFoundException {
		if (tableName == null || tableName.trim().isEmpty() || t == null)
			throw new IllegalArgumentException();
		String sql = getUpdateSql(tableName, null, null);
		Connection conn = getConnection();
		PreparedStatement smt = conn.prepareStatement(sql);
		List<SqlParameter<Object>> params = parseParameters(tableName, t);
		AddParameters(smt, params);
		int flag = smt.executeUpdate();
		return flag == 0 ? true : false;
	}

}
