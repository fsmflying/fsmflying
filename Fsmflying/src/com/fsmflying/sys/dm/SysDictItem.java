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
@Table(name = "Sys_DictItems")
@TableGenerator(name = "tableIdGenerator", table = "Sys_IdGenerators", pkColumnName = "KeyName", valueColumnName = "NextValue", pkColumnValue = "sys.edictitem", initialValue = 1)
public class SysDictItem extends AbstractBean {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int mItemId;
	private String mItemName;
	private String mItemValue;
	private String mDisplayName;
	private int mDirId;
	private int mShowOrder;
	private int mIsDefault;
	private String mMemo;

	private SysDictDir mDir;

	public SysDictItem() {
		super();
	}

	public SysDictItem(int itemId) {
		super();
		mItemId = itemId;
	}

	public SysDictItem(String itemName, String itemCode, String displayName, int showOrder, int isDefault,
			String memo) {
		super();
		mItemName = itemName;
		mItemValue = itemCode;
		mDisplayName = displayName;
		mShowOrder = showOrder;
		mIsDefault = isDefault;
		mMemo = memo;
	}

	@ManyToOne(targetEntity = SysDictDir.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "dirId")
	public SysDictDir getDir() {
		return mDir;
	}

	public void setDir(SysDictDir dir) {
		mDir = dir;
	}

	@Id
	@Column(name = "ItemId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableIdGenerator")
	public int getItemId() {
		return mItemId;
	}

	public void setItemId(int itemId) {
		mItemId = itemId;
	}

	@Column(name = "ItemName")
	public String getItemName() {
		return mItemName;
	}

	public void setItemName(String itemName) {
		mItemName = itemName;
	}

	@Column(name = "ItemValue")
	public String getItemValue() {
		return mItemValue;
	}

	public void setItemValue(String itemValue) {
		mItemValue = itemValue;
	}

	@Column(name = "DisplayName")
	public String getDisplayName() {
		return mDisplayName;
	}

	public void setDisplayName(String displayName) {
		mDisplayName = displayName;
	}

	// @Column(name="DirId")
	// public int getDirId() {
	// return mDirId;
	// }
	// public void setDirId(int dirId) {
	// mDirId = dirId;
	// }
	@Column(name = "ShowOrder")
	public int getShowOrder() {
		return mShowOrder;
	}

	public void setShowOrder(int showOrder) {
		mShowOrder = showOrder;
	}

	@Column(name = "IsDefault")
	public int getIsDefault() {
		return mIsDefault;
	}

	public void setIsDefault(int isDefault) {
		mIsDefault = isDefault;
	}

	@Column(name = "Memo")
	public String getMemo() {
		return mMemo;
	}

	public void setMemo(String memo) {
		mMemo = memo;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.edictitem";
	}

	@Override
	@Column(name = "DbDeleted")
	public int getDbDeleted() {
		// TODO Auto-generated method stub
		return super.getDbDeleted();
	}

	@Override
	@Column(name = "DbCreateBy")
	public int getDbCreateBy() {
		// TODO Auto-generated method stub
		return super.getDbCreateBy();
	}

	@Override
	@Column(name = "DbCreateTime")
	public Date getDbCreateTime() {
		// TODO Auto-generated method stub
		return super.getDbCreateTime();
	}

	@Override
	@Column(name = "DbLastUpdateBy")
	public int getDbLastUpdateBy() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateBy();
	}

	@Override
	@Column(name = "DbLastUpdateTime")
	public Date getDbLastUpdateTime() {
		// TODO Auto-generated method stub
		return super.getDbLastUpdateTime();
	}

	@Transient
	public int getDirId() {
		return mDirId;
	}

	public void setDirId(int dirId) {
		mDirId = dirId;
	}
}
