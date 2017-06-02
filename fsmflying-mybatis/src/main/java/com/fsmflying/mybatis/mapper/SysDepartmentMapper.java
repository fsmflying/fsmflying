package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysDepartment;

public interface SysDepartmentMapper {
	
	//Insert("insert into sys_departments(DeptId,ParentDeptId,CompanyId,DeptNo,DeptName,AutoNo,ShowOrder,LevelDepth,IsLeaf,Memo,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime,Flag) values(#{deptId},#{parentDeptId},#{companyId},#{deptNo},#{deptName},#{autoNo},#{showOrder},#{levelDepth},#{isLeaf},#{memo},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime},#{flag})")
	boolean add(SysDepartment model);

	//@Update("update sys_departments set ParentDeptId=#{parentDeptId},CompanyId=#{companyId},DeptNo=#{deptNo},DeptName=#{deptName},AutoNo=#{autoNo},ShowOrder=#{showOrder},LevelDepth=#{levelDepth},IsLeaf=#{isLeaf},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime},Flag=#{flag} where 1=1 and DeptId=#{deptId}")
	boolean save(SysDepartment model);

	//@Delete("delete from Sys_Departments where 1=1 and deptId=#{deptId}")
	boolean delete(int deptId);

	//@Select("select * from Sys_Departments where 1=1 and deptId=#{deptId}")
	SysDepartment get(int deptId);

	//@Select("select * from Sys_Departments")
	List<SysDepartment> getAllList();
/*
	List<SysDepartment> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysDepartment> list);
	
	boolean addList(List<SysDepartment> list);
*/
}