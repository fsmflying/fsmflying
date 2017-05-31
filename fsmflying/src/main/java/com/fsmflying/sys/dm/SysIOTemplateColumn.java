package com.fsmflying.sys.dm;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;


@Entity
@Table(name="Sys_IOTemplate_Columns")
@TableGenerator(
		name="tableIdGenerator",
		table="Sys_IdGenerators",
		pkColumnName="KeyName",
		valueColumnName="NextValue",
		pkColumnValue="sys.iotemplatecolumn",
		initialValue=1
		
	)
public class SysIOTemplateColumn extends AbstractBean{
	private static final long serialVersionUID = 1L;

	private int mColumnId;
	
	
	public SysIOTemplateColumn(int columnId) {
		super();
		mColumnId = columnId;
	}

	@Override
	@Transient
	public String getTypeUnique() {
		// TODO Auto-generated method stub
		return "sys.eiotemplatecolumn";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	public int getColumnId() {
		return mColumnId;
	}


	public void setColumnId(int mColumnId) {
		this.mColumnId = mColumnId;
	}

}
