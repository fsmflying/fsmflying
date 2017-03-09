package com.fsmflying.sys.dm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="Sys_CustomPages")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.custompage",
		initialValue=1
		
	)
public class SysCustomPage extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private	int		mCustomPageId;
	private String	mCustomPageName;
	private String	mAppName;
	private String	mSourceUrl;
	private String	mDestinationUrl;
	private int		mHandleWay;
	private String	mMemo;
	
	public SysCustomPage() {
		super();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="CustomPageId",nullable=false)
	public int getCustomPageId() {
		return mCustomPageId;
	}

	public void setCustomPageId(int customPageId) {
		mCustomPageId = customPageId;
	}

	@Column(name="CustomPageName",nullable=false)
	public String getCustomPageName() {
		return mCustomPageName;
	}

	public void setCustomPageName(String customPageName) {
		mCustomPageName = customPageName;
	}

	@Column(name="SysName",nullable=false)
	public String getAppName() {
		return mAppName;
	}

	public void setAppName(String sysName) {
		mAppName = sysName;
	}


	@Column(name="SourceUrl",nullable=false)
	public String getSourceUrl() {
		return mSourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		mSourceUrl = sourceUrl;
	}

	@Column(name="DestinationUrl",nullable=false)
	public String getDestinationUrl() {
		return mDestinationUrl;
	}

	public void setDestinationUrl(String destinationUrl) {
		mDestinationUrl = destinationUrl;
	}
	
	/**
	 * 获取自定义页面的跳转方式，当系统检测到访问指定源地址(SourceUrl)的请求时，自动将其跳转到一个新的页面:<br/>
	 * [0]:表示智能跳转，如果有发向源地址(SourceUrl)的请求，则自动将请求其跳转到"custom/{appname}/{SourceUrl}";<br/>
	 * [1]:表示直接跳转，如果有发向源地址(SourceUrl)的请求，则直接将其跳转到此记录中配置的目录地址(DestinationUrl);<br/>
	 * @return 页面的跳转方式
	 */
	@Column(name="TranslationType",nullable=false)
	public int getTranslationType() {
		return mHandleWay;
	}
	
	/**
	 * 获取自定义页面的跳转方式，当系统检测到访问指定源地址(SourceUrl)的请求时，自动将其跳转到一个新的页面:<br/>
	 * [0]:表示智能跳转，如果有发向源地址(SourceUrl)的请求，则自动将请求其跳转到"custom/{appname}/{SourceUrl}"
	 * [1]:表示直接跳转，如果有发向源地址(SourceUrl)的请求，则直接将其跳转到此记录中配置的目录地址(DestinationUrl)
	 * @param translationType
	 */
	public void setTranslationType(int translationType) {
		mHandleWay = translationType;
	}

	@Column(name="Memo")
	public String getMemo() {
		return mMemo;
	}
	public void setMemo(String memo) {
		mMemo = memo;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.ecustompage";
	}
	
	@Override
	@Column(name="DbDeleted",nullable=false,columnDefinition="int default 0")
	public int getDbDeleted() {
		return super.getDbDeleted();
	}

	@Override
	@Column(name="DbCreateBy",nullable=false,columnDefinition="int default -1")
	public int getDbCreateBy() {
		return super.getDbCreateBy();
	}

	@Override
	@Column(name="DbCreateTime")
	public Date getDbCreateTime() {
		return super.getDbCreateTime();
	}

	@Override
	@Column(name="DbLastUpdateBy",nullable=false,columnDefinition="int default -1")
	public int getDbLastUpdateBy() {
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name="DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		return super.getDbLastUpdateTime();
	}

}