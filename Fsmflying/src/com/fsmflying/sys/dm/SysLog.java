package com.fsmflying.sys.dm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="Sys_Logs")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.elog",
		initialValue=1
		
	)
public class SysLog extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int 	mLogId;
	private String 	mLogThread;
	private String 	mLogLevel;
	private String 	Logger;
	private int		mOperatorId;
	private	String 	mOperatorName;
	private Date	mOperationTime;
	private String	mOperationType;
	private String 	mOperationAddress;
	private String 	mMachineIP;
	private String	mMachineName;
	private String	mBrowser;
	private String	mApplicationName;
	private String 	mOSName;
	private String 	mMessage;
	
	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.elog";
	}

	@Id
	@Column(name="LogId")
//	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@GeneratedValue
	public int getLogId() {
		return mLogId;
	}


	public void setLogId(int logId) {
		mLogId = logId;
	}

//	@Column(name="Log_Thread")
	public String getLogThread() {
		return mLogThread;
	}

	public void setLogThread(String logThread) {
		mLogThread = logThread;
	}

//	@Column(name="Log_Level")
	public String getLogLevel() {
		return mLogLevel;
	}

	public void setLogLevel(String logLevel) {
		mLogLevel = logLevel;
	}

	
	public String getLogger() {
		return Logger;
	}

	public void setLogger(String logger) {
		Logger = logger;
	}

	public int getOperatorId() {
		return mOperatorId;
	}

	public void setOperatorId(int operatorId) {
		mOperatorId = operatorId;
	}

	public String getOperatorName() {
		return mOperatorName;
	}

	public void setOperatorName(String operatorName) {
		mOperatorName = operatorName;
	}

	public Date getOperationTime() {
		return mOperationTime;
	}

	public String getOperationType() {
		return mOperationType;
	}

	public void setOperationType(String operationType) {
		mOperationType = operationType;
	}

	public void setOperationTime(Date operationTime) {
		mOperationTime = operationTime;
	}

	public String getOperationAddress() {
		return mOperationAddress;
	}

	public void setOperationAddress(String operationAddress) {
		mOperationAddress = operationAddress;
	}
	@Column(name="Machine_IP")
	public String getMachineIP() {
		return mMachineIP;
	}

	public void setMachineIP(String machineIP) {
		mMachineIP = machineIP;
	}
	public String getMachineName() {
		return mMachineName;
	}

	public void setMachineName(String machineName) {
		mMachineName = machineName;
	}
	public String getBrowser() {
		return mBrowser;
	}

	public void setBrowser(String browser) {
		mBrowser = browser;
	}
	public String getApplicationName() {
		return mApplicationName;
	}

	public void setApplicationName(String applicationName) {
		mApplicationName = applicationName;
	}
	public String getOSName() {
		return mOSName;
	}

	public void setOSName(String oSName) {
		mOSName = oSName;
	}
	public String getMessage() {
		return mMessage;
	}

	public void setMessage(String message) {
		mMessage = message;
	}

}
