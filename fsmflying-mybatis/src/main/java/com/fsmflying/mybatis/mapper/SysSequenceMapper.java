package com.fsmflying.mybatis.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Result;
//import org.apache.ibatis.annotations.Results;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import com.fsmflying.sys.dm.SysSequence;

@CacheNamespace
public interface SysSequenceMapper {


//	@Select(value = { "select * from sys_sequences where lower(keyName)=lower(#{keyName})" })
	SysSequence getSequence(String keyName);

//	@Insert(value = { "insert into sys_sequences(keyName,nextValue,generatedTime)"
//			+ " values(#{keyName},#{nextValue},#{generatedTime})" })
	void addSequence(SysSequence model);

//	@Insert(value = { "insert into sys_sequenceHistories(keyName,generatedValue,generatedTime)"
//			+ " values(#{keyName},#{nextValue},#{generatedTime})" })
	void addSequenceHistory(SysSequence model);

//	@Update(value = { "update sys_sequences" + " set nextValue=#{nextValue},generatedTime=#{generatedTime}"
//			+ " where keyName=#{keyName}" })
	void saveSequence(SysSequence model);

}
