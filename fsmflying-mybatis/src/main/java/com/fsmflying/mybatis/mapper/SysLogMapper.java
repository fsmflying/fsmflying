package com.fsmflying.mybatis.mapper;

import java.util.List;

//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysLog;

public interface SysLogMapper {
	
	//@Insert("insert into sys_logs(LogId,LogThread,LogLevel,Logger,OperatorId,OperatorName,OperationTime,OperationType,OperationAddr,MachineIp,MachineName,Browser,AppName,ClientSystemName,Message) values(#{logId},#{logThread},#{logLevel},#{logger},#{operatorId},#{operatorName},#{operationTime},#{operationType},#{operationAddr},#{machineIp},#{machineName},#{browser},#{appName},#{clientSystemName},#{message})")
	boolean add(SysLog model);

	//@Update("update sys_logs set LogThread=#{logThread},LogLevel=#{logLevel},Logger=#{logger},OperatorId=#{operatorId},OperatorName=#{operatorName},OperationTime=#{operationTime},OperationType=#{operationType},OperationAddr=#{operationAddr},MachineIp=#{machineIp},MachineName=#{machineName},Browser=#{browser},AppName=#{appName},ClientSystemName=#{clientSystemName},Message=#{message} where 1=1 and LogId=#{logId}")
	boolean save(SysLog model);

	//@Delete("delete from Sys_Logs where 1=1 and logId=#{logId}")
	boolean delete(Integer id);

	//@Select("select * from Sys_Logs where 1=1 and logId=#{logId}")
	SysLog get(Integer id);

	//@Select("select * from Sys_Logs")
	List<SysLog> getAllList();

/*
	List<SysLog> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysLog> list);
	
	boolean addList(List<SysLog> list);
*/

}
