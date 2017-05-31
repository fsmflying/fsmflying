package com.fsmflying.helpers;

import java.util.HashMap;
import java.util.Set;

/**
 * 
 * @author FangMing
 *
 */
public class SqlBuilder {
	private final String mAllFieldNames;
	private final String mTableName;
	private final String[] mFieldNames;
	private int mFieldCount;
	private String mSqlSelectFrom;
	private String mSqlUpdate;
	private String mSqlInsert;
	private String mSqlDelete;
	private HashMap<String,String> mCacheSqls = new HashMap<String,String>();
//	private HashTable<String,String> mCacheSqls = new HashTable<>();

//	private static SqlBuilder mBuilder = null;

	private static final Object obj = new Object();

	private static final HashMap<String, SqlBuilder> builderList = new HashMap<String,SqlBuilder>();

	public static SqlBuilder instance(String tableName, String allFieldNames) {
		if (tableName == null || tableName.isEmpty())
			throw new IllegalArgumentException();

		register(tableName, allFieldNames);
		return builderList.get(tableName.toUpperCase());
	}

	public static SqlBuilder instance(String tableName) {
		return instance(tableName, null);
	}

	public static void register(String tableName, String allFieldNames) {

//		System.out.printf("[\ntableName:%s,\nallFieldName:%s\n]\n", tableName,
//				allFieldNames);

		synchronized (obj) {
			if (!builderList.containsKey(tableName.toUpperCase())) {
				if (tableName == null || tableName.isEmpty()
						|| allFieldNames == null || allFieldNames.isEmpty())
					throw new IllegalArgumentException();
				builderList.put(tableName.toUpperCase(), new SqlBuilder(
						tableName, allFieldNames));
			}
		}

	}

	/**
	 * 
	 * 
	 * @param tableName 数据库表名库
	 * @param allFieldNames　所有字段的名称，以逗号隔开，比如"bookid,bookno,bookname,author"
	 *           
	 */
	private SqlBuilder(String tableName, String allFieldNames) {
		super();

		if (allFieldNames == null || tableName == null)
			throw new IllegalArgumentException();
		this.mAllFieldNames = allFieldNames;
		this.mTableName = tableName;
//		String[] fieldNames;

		this.mFieldNames = this.mAllFieldNames.split(",");
		this.mFieldCount = this.mFieldNames.length;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.mFieldCount; i++) {
			// if(i>0) builder.append(",");
			builder.append(",?");
		}
		builder.deleteCharAt(0);
		this.mSqlSelectFrom = String.format("Select %s From %s",
				this.mAllFieldNames, this.mTableName);
		this.mSqlInsert = String.format("Insert into %s(%s) values(%s)",
				this.mTableName, this.mAllFieldNames, builder.toString());
		builder.delete(0, builder.length());
		//StringBuffer buffer;
		
		for (int i = 0; i < this.mFieldCount; i++) {
			// if(i>0) builder.append(",");
			builder.append(String.format(",%s=?", this.mFieldNames[i]));
		}
		builder.deleteCharAt(0);
		this.mSqlUpdate = String.format("update %s set %s", this.mTableName,
				builder.toString());
		
		this.mSqlDelete = String.format("delete from %s", this.mTableName);
		
		
	}

	public int getFieldCount() {
		return mFieldCount;
	}

	public void setFieldCount(int mFieldCount) {
		this.mFieldCount = mFieldCount;
	}

	public String getSqlSelectFrom() {
		return mSqlSelectFrom;
	}

	public void setSqlSelectFrom(String mSqlSelectFrom) {
		this.mSqlSelectFrom = mSqlSelectFrom;
	}

	public String getSqlUpdate() {
		return mSqlUpdate;
	}

	public void setSqlUpdate(String mSqlUpdate) {
		this.mSqlUpdate = mSqlUpdate;
	}

	public String getSqlInsert() {
		return mSqlInsert;
	}

	public void setSqlInsert(String mSqlInsert) {
		this.mSqlInsert = mSqlInsert;
	}

	public String getAllFieldNames() {
		return mAllFieldNames;
	}

	public String getTableName() {
		return mTableName;
	}

	
	
	public String getSqlDelete() {
		return mSqlDelete;
	}

	public void setSqlDelete(String mSqlDelete) {
		this.mSqlDelete = mSqlDelete;
	}

	@Override
	public String toString() {
		return "SqlBuilder [\n" 
				+ " mFieldNames=" + mAllFieldNames
				+ ",\n mTableName=" + mTableName + ",\n mFieldCount="
				+ mFieldCount + ",\n mSqlSelectFrom=" + mSqlSelectFrom
				+ ",\n mSqlUpdate=" + mSqlUpdate + ",\n mSqlInsert="
				+ mSqlInsert + "\n]";
	}
	
	public String[] getFieldNames()
	{
		return this.mFieldNames;
	}
	
	public String getQuerySql(String sqlWhere)
	{
		if(sqlWhere != null && !sqlWhere.trim().isEmpty()) sqlWhere = String.format(" where %s ",sqlWhere);
		else sqlWhere = "";
		String key = String.format("query:%s", sqlWhere.trim());
		if(!this.mCacheSqls.containsKey(sqlWhere))
		{
			synchronized(this)
			{
				this.mCacheSqls.put(key, String.format("%s %s",this.mSqlSelectFrom,sqlWhere));
			}
		}
		return this.mCacheSqls.get(key);
	}
	
	public String getUpdateSql(String sqlWhere)
	{
		
		if(sqlWhere != null && !sqlWhere.trim().isEmpty()) sqlWhere = String.format(" where %s ",sqlWhere);
		else sqlWhere = "";
		String key = String.format("update:%s", sqlWhere.trim());
		if(!this.mCacheSqls.containsKey(sqlWhere))
		{
			synchronized(this)
			{
				this.mCacheSqls.put(key, String.format("%s %s",this.mSqlUpdate,sqlWhere));
			}
		}
		return this.mCacheSqls.get(key);
	}
	
	public String getDeleteSql(String sqlWhere)
	{
		
		if(sqlWhere != null && !sqlWhere.trim().isEmpty()) sqlWhere = String.format(" where %s ",sqlWhere);
		else sqlWhere = "";
		String key = String.format("delete:%s", sqlWhere.trim());
		if(!this.mCacheSqls.containsKey(sqlWhere))
		{
			synchronized(this)
			{
				this.mCacheSqls.put(key, String.format("%s %s",this.mSqlDelete,sqlWhere));
			}
		}
		return this.mCacheSqls.get(key);
	}
	
	public void printCacheSqls()
	{
		Set<String> keys = this.mCacheSqls.keySet();
		for(String key : keys)
			System.out.printf("%s:%s\n",key,this.mCacheSqls.get(key));
	}

}
