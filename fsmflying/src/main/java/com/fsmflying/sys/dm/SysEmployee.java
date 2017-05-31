package com.fsmflying.sys.dm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "Sys_Employees")
@TableGenerator(name = "tableIdGenerator", table = "Sys_IdGenerators", pkColumnName = "KeyName", valueColumnName = "NextValue", pkColumnValue = "sys.eemployee", initialValue = 1)
public class SysEmployee extends AbstractBean {

	private static final long serialVersionUID = 1L;

	private int mEmplId;
	private int mDeptId;
	private int mCompanyId;
	private String mEmplNo;
	private String mEmplName_CN;
	private String mEmplName_EN;
	private int mSex;
	private int mShowOrder;
	private String mAutoNo;
	private Date mBirthDate;
	private String mNativePlace;
	private String mCardId;
	private String mContactPhone;
	private String mContactMPhone;
	private String mContactAddress;
	private String mContactFax;
	private String mContactEmail;
	private int mPositionId;
	private String mPositionName;
	private String mPositionTitle;
	private String mField001;
	private String mField002;
	private String mField003;
	private String mField004;
	private String mField005;
	private int mFlag;

	public SysEmployee() {
		super();
	}

	public SysEmployee(int emplId) {
		super();
		mEmplId = emplId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableIdGenerator")
	public int getEmplId() {
		return mEmplId;
	}

	public void setEmplId(int mEmployeeId) {
		this.mEmplId = mEmployeeId;
	}

	public int getDeptId() {
		return mDeptId;
	}

	public void setDeptId(int mDeptId) {
		this.mDeptId = mDeptId;
	}

	public int getCompanyId() {
		return mCompanyId;
	}

	public void setCompanyId(int mCompanyId) {
		this.mCompanyId = mCompanyId;
	}

	public String getEmplNo() {
		return mEmplNo;
	}

	public void setEmplNo(String mEmplNo) {
		this.mEmplNo = mEmplNo;
	}

	public String getEmplName_CN() {
		return mEmplName_CN;
	}

	public void setEmplName_CN(String mEmplName_CN) {
		this.mEmplName_CN = mEmplName_CN;
	}

	public String getEmplName_EN() {
		return mEmplName_EN;
	}

	public void setEmplName_EN(String mEmplName_EN) {
		this.mEmplName_EN = mEmplName_EN;
	}

	public int getSex() {
		return mSex;
	}

	public void setSex(int mSex) {
		this.mSex = mSex;
	}

	public int getShowOrder() {
		return mShowOrder;
	}

	public void setShowOrder(int showOrder) {
		mShowOrder = showOrder;
	}

	public String getAutoNo() {
		return mAutoNo;
	}

	public void setAutoNo(String autoNo) {
		mAutoNo = autoNo;
	}

	public Date getBirthDate() {
		return mBirthDate;
	}

	public void setBirthDate(Date mBirthdate) {
		this.mBirthDate = mBirthdate;
	}

	public String getNativePlace() {
		return mNativePlace;
	}

	public void setNativePlace(String mNativePlace) {
		this.mNativePlace = mNativePlace;
	}

	public String getCardId() {
		return mCardId;
	}

	public void setCardId(String mCardId) {
		this.mCardId = mCardId;
	}

	public String getContactPhone() {
		return mContactPhone;
	}

	public void setContactPhone(String contactPhone) {
		mContactPhone = contactPhone;
	}

	public String getContactMPhone() {
		return mContactMPhone;
	}

	public void setContactMPhone(String contactMPhone) {
		mContactMPhone = contactMPhone;
	}

	public String getContactAddress() {
		return mContactAddress;
	}

	public void setContactAddress(String contactAddress) {
		mContactAddress = contactAddress;
	}

	public String getContactFax() {
		return mContactFax;
	}

	public void setContactFax(String contactFax) {
		mContactFax = contactFax;
	}

	public String getContactEmail() {
		return mContactEmail;
	}

	public void setContactEmail(String contactEmail) {
		mContactEmail = contactEmail;
	}

	public int getPositionId() {
		return mPositionId;
	}

	public void setPositionId(int mPositionId) {
		this.mPositionId = mPositionId;
	}

	public String getPositionName() {
		return mPositionName;
	}

	public void setPositionName(String mPositionName) {
		this.mPositionName = mPositionName;
	}

	public String getPositionTitle() {
		return mPositionTitle;
	}

	public void setPositionTitle(String mTitleName) {
		this.mPositionTitle = mTitleName;
	}

	// public int getDeleted() {
	// return mDbDeleted;
	// }
	// public void setDeleted(int mDeleted) {
	// this.mDbDeleted = mDeleted;
	// }
	// public int getCreateBy() {
	// return mDbCreateBy;
	// }
	// public void setCreateBy(int mCreateBy) {
	// this.mDbCreateBy = mCreateBy;
	// }
	// public Date getCreateTime() {
	// return mDbCreateTime;
	// }
	// public void setCreateTime(Date mCreateTime) {
	// this.mDbCreateTime = mCreateTime;
	// }
	// public int getLastUpdateBy() {
	// return mDbLastUpdateBy;
	// }
	// public void setLastUpdateBy(int mLastUpdateBy) {
	// this.mDbLastUpdateBy = mLastUpdateBy;
	// }
	// public Date getLastUpdateTime() {
	// return mDbLastUpdateTime;
	// }
	// public void setLastUpdateTime(Date mLastUpdateTime) {
	// this.mDbLastUpdateTime = mLastUpdateTime;
	// }
	public String getField001() {
		return mField001;
	}

	public void setField001(String mField001) {
		this.mField001 = mField001;
	}

	public String getField002() {
		return mField002;
	}

	public void setField002(String mField002) {
		this.mField002 = mField002;
	}

	public String getField003() {
		return mField003;
	}

	public void setField003(String mField003) {
		this.mField003 = mField003;
	}

	public String getField004() {
		return mField004;
	}

	public void setField004(String mField004) {
		this.mField004 = mField004;
	}

	public String getField005() {
		return mField005;
	}

	public void setField005(String mField005) {
		this.mField005 = mField005;
	}

	public int getFlag() {
		return mFlag;
	}

	public void setFlag(int flag) {
		mFlag = flag;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.eemployee";
	}

	@Override
	public String toString() {
		return "SysEmployee [mEmplName_CN=" + mEmplName_CN + ", mEmplName_EN=" + mEmplName_EN + "]";
	}

}
