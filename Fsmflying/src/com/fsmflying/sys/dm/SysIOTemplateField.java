package com.fsmflying.sys.dm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="Sys_IOTemplate_Fields")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.iotemplatefield",
		initialValue=1
		
	)
public class SysIOTemplateField extends AbstractBean{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private int mFieldId;
	
	public SysIOTemplateField() {
		super();
	}

	public SysIOTemplateField(int fieldId) {
		super();
		mFieldId = fieldId;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.eiotemplatefield";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	public int getFieldId() {
		return mFieldId;
	}


	public void setFieldId(int fieldId) {
		mFieldId = fieldId;
	}

}
