package com.fsmflying.sys.dm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "Sys_Departments")
@TableGenerator(name = "tableIdGenerator", table = "Sys_IdGenerators", pkColumnName = "KeyName", valueColumnName = "NextValue", pkColumnValue = "sys.edepartment", initialValue = 1)
public class SysDepartment extends AbstractBean {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int mDeptId;
	private int mParentDeptId;
	private int mCompanyId;
	private String mDeptName;
	private String mDeptNo;
	private int mLevelDepth;
	private String mAutoNo;
	private int mShowOrder;
	private int mIsLeaf;
	private int mFlag;

	private SysCompany mCompany;

	public SysDepartment() {
		super();
	}

	public SysDepartment(int deptId) {
		super();
		mDeptId = deptId;
	}

	@ManyToOne(targetEntity = SysCompany.class,fetch=FetchType.EAGER)
	@JoinColumn(name = "CompanyId")
	public SysCompany getCompany() {
		return mCompany;
	}

	public void setCompany(SysCompany company) {
		mCompany = company;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableIdGenerator")
	@Column(name = "DeptId", nullable = false)
	public int getDeptId() {
		return mDeptId;
	}

	public void setDeptId(int deptId) {
		mDeptId = deptId;
	}

	@Column(name = "ParentDeptId", nullable = false, columnDefinition = "int default -1")
	public int getParentDeptId() {
		return mParentDeptId;
	}

	public void setParentDeptId(int parentDeptId) {
		mParentDeptId = parentDeptId;
	}

	// public int getCompanyId() {
	// return mCompanyId;
	// }
	// public void setCompanyId(int companyId) {
	// mCompanyId = companyId;
	// }
	@Column(name = "DeptName", nullable = false)
	public String getDeptName() {
		return mDeptName;
	}

	public void setDeptName(String deptName) {
		mDeptName = deptName;
	}

	@Column(name = "DeptNo")
	public String getDeptNo() {
		return mDeptNo;
	}

	public void setDeptNo(String deptNo) {
		mDeptNo = deptNo;
	}

	@Column(name = "AutoNo")
	public String getAutoNo() {
		return mAutoNo;
	}

	public void setAutoNo(String autoNo) {
		mAutoNo = autoNo;
	}

	@Column(name = "ShowOrder", nullable = false, columnDefinition = "int default 10000")
	public int getShowOrder() {
		return mShowOrder;
	}

	public void setShowOrder(int showOrder) {
		mShowOrder = showOrder;
	}

	@Column(name = "IsLeaf", nullable = false, columnDefinition = "int default 0")
	public int getIsLeaf() {
		return mIsLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		mIsLeaf = isLeaf;
	}

	@Column(name = "LevelDepth", nullable = false, columnDefinition = "int default -1")
	public int getLevelDepth() {
		return mLevelDepth;
	}

	public void setLevelDepth(int depth) {
		mLevelDepth = depth;
	}

	@Column(name = "Flag", nullable = false, columnDefinition = "int default 0")
	public int getFlag() {
		return mFlag;
	}

	public void setFlag(int flag) {
		mFlag = flag;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		return "sys.edepartment";
	}

	@Override
	public String toString() {
		return "SysDepartment [mDeptName=" + mDeptName + "]";
	}

	@Override
	@Column(name = "DbDeleted", nullable = false, columnDefinition = "int default 0")
	public int getDbDeleted() {
		return super.getDbDeleted();
	}

	@Override
	@Column(name = "DbCreateBy", nullable = false, columnDefinition = "int default -1")
	public int getDbCreateBy() {
		return super.getDbCreateBy();
	}

	@Override
	@Column(name = "DbCreateTime")
	public Date getDbCreateTime() {
		return super.getDbCreateTime();
	}

	@Override
	@Column(name = "DbLastUpdateBy", nullable = false, columnDefinition = "int default -1")
	public int getDbLastUpdateBy() {
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name = "DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		return super.getDbLastUpdateTime();
	}

	//@Column(name = "CompanyId", nullable = false, columnDefinition = "int default -1")
	@Transient
	public int getCompanyId() {
		return mCompanyId;
	}

	public void setCompanyId(int companyId) {
		mCompanyId = companyId;
	}

}
