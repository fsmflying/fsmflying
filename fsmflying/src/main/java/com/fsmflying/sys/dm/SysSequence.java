package com.fsmflying.sys.dm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Sys_Sequences")
public class SysSequence extends AbstractBean {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
//	private int mSequenceId;
	private String mKeyName;
	private int mNextValue;
	private Date mGeneratedTime;

	public SysSequence() {
		super();
	}

//	public SysSequence(int sequenceId) {
//		super();
//		mSequenceId = sequenceId;
//	}

	public SysSequence(String keyName) {
		super();
		mKeyName = keyName;
	}
	
	
	
//	@Id
//	@GeneratedValue
//	public int getSequenceId() {
//		return mSequenceId;
//	}
//
//	public void setSequenceId(int sequenceId) {
//		mSequenceId = sequenceId;
//	}

	public SysSequence(String keyName, int nextValue, Date generatedTime) {
		super();
		mKeyName = keyName;
		mNextValue = nextValue;
		mGeneratedTime = generatedTime;
	}

	@Id
	@Column(name = "KeyName", unique = true, nullable = false)
	public String getKeyName() {
		return mKeyName;
	}

	public void setKeyName(String keyName) {
		mKeyName = keyName;
	}

	@Column(name = "NextValue", nullable = false)
	public int getNextValue() {
		return mNextValue;
	}

	public void setNextValue(int nextValue) {
		mNextValue = nextValue;
	}

	@Column(name = "GeneratedTime", nullable = false)
	public Date getGeneratedTime() {
		return mGeneratedTime;
	}

	public void setGeneratedTime(Date generatedTime) {
		mGeneratedTime = generatedTime;
	}

	@Transient
	@Override
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "esequence";
	}

	@Override
	public String toString() {
		return "SysSequence [mKeyName=" + mKeyName + ", mNextValue=" + mNextValue + ", mGeneratedTime=" + mGeneratedTime
				+ "]";
	}

}
