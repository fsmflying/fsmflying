package com.fsmflying.mybatis.internal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="subjects")
@TableGenerator(name = "tableIdGenerator", 
	table = "Sys_IdGenerators", 
	pkColumnName = "KeyName", 
	valueColumnName = "NextValue", 
	pkColumnValue = "subject", 
	initialValue = 1)
public class Subject{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;


	private int id;
	private String name;
	private String memo;
	
	

	public Subject() {
		super();
	}

	public Subject(String name, String memo) {
		super();
		this.name = name;
		this.memo = memo;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE,generator="tableIdGenerator")
	@Column(name="id",nullable=false)
	public int getId(){
		 return this.id;
	}

	public void setId(int id){
		 this.id = id ;
	}

	@Column(name="name",nullable=false)
	public String getName(){
		 return this.name;
	}

	public void setName(String name){
		 this.name = name ;
	}

	@Column(name="memo")
	public String getMemo(){
		 return this.memo;
	}

	public void setMemo(String memo){
		 this.memo = memo ;
	}


}
