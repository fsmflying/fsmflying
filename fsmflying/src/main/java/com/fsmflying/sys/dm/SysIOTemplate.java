package com.fsmflying.sys.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="Sys_IOTemplates")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.iotemplate",
		initialValue=1
		
	)
public class SysIOTemplate extends AbstractBean{
	private static final long serialVersionUID = 1L;

	private int mTemplateId;
	
	
	
	public SysIOTemplate(int templateId) {
		super();
		mTemplateId = templateId;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.eiotemplate";
	}

	@Id
	@Column(name="TemplateId")
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	public int getTemplateId() {
		return mTemplateId;
	}

	public void setTemplateId(int templateId) {
		mTemplateId = templateId;
	}

}
