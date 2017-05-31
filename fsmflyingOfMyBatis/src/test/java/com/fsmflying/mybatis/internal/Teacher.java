package com.fsmflying.mybatis.internal;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name="teachers")
@TableGenerator(name = "tableIdGenerator", 
	table = "Sys_IdGenerators", 
	pkColumnName = "KeyName", 
	valueColumnName = "NextValue", 
	pkColumnValue = "teacher", 
	initialValue = 1)
public class Teacher{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;


	private int id;
	private String name;
	private int sex;
	private Date birthDate;

	public Teacher() {
		super();
	}

	public Teacher(String name, int sex, Date birthDate) {
		super();
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
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

	@Column(name="sex",nullable=false,columnDefinition="int default 0")
	public int getSex(){
		 return this.sex;
	}

	public void setSex(int sex){
		 this.sex = sex ;
	}

	@Column(name="birthDate")
	public Date getBirthDate(){
		 return this.birthDate;
	}

	public void setBirthDate(Date birthDate){
		 this.birthDate = birthDate ;
	}


}
