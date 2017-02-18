package com.fsmflying.sys.dm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;



@Entity
@Table(name="Sys_Companys")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.ecompany",
		initialValue=1
		
	)
public class SysCompany extends AbstractBean {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private int		mCompanyId;
	private int 	mPCompanyId;
	private String 	mCompanyNo;
	private String 	mAutoNo;
	private String 	mShortName;
	private String 	mFullName;
	private Date 	mEstablishedDate;
	private int 	mShowOrder;
	private String 	mContacts;
	private String 	mContactAddress;
	private String 	mContactPhone;
	private String 	mContactMPhone;
	private String 	mContactFax;
	private String 	mContactEmail;
	private String 	mContactPostalCode;
	private String 	mBusinessScope;
	private int		mFlag;
	
	private Set<SysDepartment> mDepartments = new HashSet<SysDepartment>();
	
	public SysCompany() {
		super();
	}
	@OneToMany(targetEntity=SysDepartment.class)
	@JoinColumn(name="CompanyId")
	public Set<SysDepartment> getDepartments() {
		return mDepartments;
	}

	public void setDepartments(Set<SysDepartment> departments) {
		mDepartments = departments;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.ecompany";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="CompanyId")
	public int getCompanyId() {
		return mCompanyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		mCompanyId = companyId;
	}

	@Column(name="PCompanyId")
	public int getPCompanyId() {
		return mPCompanyId;
	}
	public void setPCompanyId(int pCompanyId) {
		mPCompanyId = pCompanyId;
	}

	@Column(name="CompanyNo")
	public String getCompanyNo() {
		return mCompanyNo;
	}

	public void setCompanyNo(String companyNo) {
		mCompanyNo = companyNo;
	}

	@Column(name="AutoNo")
	public String getAutoNo() {
		return mAutoNo;
	}
	public void setAutoNo(String autoNo) {
		mAutoNo = autoNo;
	}

	@Column(name="ShortName")
	public String getShortName() {
		return mShortName;
	}
	public void setShortName(String shortName) {
		mShortName = shortName;
	}

	@Column(name="FullName")
	public String getFullName() {
		return mFullName;
	}
	public void setFullName(String fullName) {
		mFullName = fullName;
	}

	@Column(name="EstablishedDate")
	public Date getEstablishedDate() {
		return mEstablishedDate;
	}
	public void setEstablishedDate(Date establishedDate) {
		mEstablishedDate = establishedDate;
	}

	@Column(name="ShowOrder")
	public int getShowOrder() {
		return mShowOrder;
	}
	public void setShowOrder(int showOrder) {
		mShowOrder = showOrder;
	}

	@Column(name="Contacts")
	public String getContacts() {
		return mContacts;
	}
	public void setContacts(String contacts) {
		mContacts = contacts;
	}

	@Column(name="ContactAddress")
	public String getContactAddress() {
		return mContactAddress;
	}
	public void setContactAddress(String contactAddress) {
		mContactAddress = contactAddress;
	}

	@Column(name="ContactPhone")
	public String getContactPhone() {
		return mContactPhone;
	}
	public void setContactPhone(String contactPhone) {
		mContactPhone = contactPhone;
	}

	@Column(name="ContactMPhone")
	public String getContactMPhone() {
		return mContactMPhone;
	}
	public void setContactMPhone(String contactMPhone) {
		mContactMPhone = contactMPhone;
	}

	@Column(name="ContactFax")
	public String getContactFax() {
		return mContactFax;
	}
	public void setContactFax(String contactFax) {
		mContactFax = contactFax;
	}

	@Column(name="ContactEmail")
	public String getContactEmail() {
		return mContactEmail;
	}
	public void setContactEmail(String contactEmail) {
		mContactEmail = contactEmail;
	}

	@Column(name="ContactPostalCode")
	public String getContactPostalCode() {
		return mContactPostalCode;
	}
	public void setContactPostalCode(String contactPostalCode) {
		mContactPostalCode = contactPostalCode;
	}

	@Column(name="BusinessScope")
	public String getBusinessScope() {
		return mBusinessScope;
	}
	public void setBusinessScope(String businessScope) {
		mBusinessScope = businessScope;
	}

	@Column(name="Flag")
	public int getFlag() {
		return mFlag;
	}
	public void setFlag(int flag) {
		mFlag = flag;
	}
	
	@Override
	@Column(name="DbDeleted",nullable=false,columnDefinition="int default 0")
	public int getDbDeleted() {
		// TODO Auto-generated method stub
		return super.getDbDeleted();
	}

	@Override
	@Column(name="DbCreateBy",nullable=false,columnDefinition="int default -1")
	public int getDbCreateBy() {
		// TODO Auto-generated method stub
		return super.getDbCreateBy();
	}

	@Override
	@Column(name="DbCreateTime")
	public Date getDbCreateTime() {
		// TODO Auto-generated method stub
		return super.getDbCreateTime();
	}

	@Override
	@Column(name="DbLastUpdateBy",nullable=false,columnDefinition="int default -1")
	public int getDbLastUpdateBy() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name="DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateTime();
	}
	

	@Override
	public String toString() {
		return "SysCompany [mFullName=" + mFullName + "]";
	}
	
	
	
	
}
