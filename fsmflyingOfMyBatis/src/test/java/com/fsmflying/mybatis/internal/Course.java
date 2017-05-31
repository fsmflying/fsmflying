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
@Table(name="courses")
@TableGenerator(name = "tableIdGenerator", 
	table = "Sys_IdGenerators", 
	pkColumnName = "KeyName", 
	valueColumnName = "NextValue", 
	pkColumnValue = "course", 
	initialValue = 1)
public class Course{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;


	private int id;
	private String name;
	private int teacherId;
	private int subjectId;
	private Date startTime;
	private String memo;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String name, int teacherId, int subjectId, Date startTime, String memo) {
		super();
		this.name = name;
		this.teacherId = teacherId;
		this.subjectId = subjectId;
		this.startTime = startTime;
		this.memo = memo;
	}

	public Course(int id, String name, int teacherId, int subjectId, Date startTime, String memo) {
		super();
		this.id = id;
		this.name = name;
		this.teacherId = teacherId;
		this.subjectId = subjectId;
		this.startTime = startTime;
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

	@Column(name="teacherId",nullable=false)
	public int getTeacherId(){
		 return this.teacherId;
	}

	public void setTeacherId(int teacherId){
		 this.teacherId = teacherId ;
	}

	@Column(name="subjectId",nullable=false)
	public int getSubjectId(){
		 return this.subjectId;
	}

	public void setSubjectId(int subjectId){
		 this.subjectId = subjectId ;
	}

	@Column(name="startTime")
	public Date getStartTime(){
		 return this.startTime;
	}

	public void setStartTime(Date startTime){
		 this.startTime = startTime ;
	}

	@Column(name="memo")
	public String getMemo(){
		 return this.memo;
	}

	public void setMemo(String memo){
		 this.memo = memo ;
	}


}
