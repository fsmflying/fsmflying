
package com.fsmflying.mybatis.internal;

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
@Table(name="students")
@TableGenerator(name = "tableIdGenerator", 
	table = "Sys_IdGenerators", 
	pkColumnName = "KeyName", 
	valueColumnName = "NextValue", 
	pkColumnValue = "student", 
	initialValue = 1)
public class Student{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;


	private int id = Integer.MIN_VALUE;
	private String name;
	private int sex;
	private Date birthDate;

	public Student() {
		super();
	}

	public Student(String name, int sex, Date birthDate) {
		super();
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
	}

	public Student(int id, String name, int sex, Date birthDate) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", birthDate=" + birthDate + "]";
	}

	
}
