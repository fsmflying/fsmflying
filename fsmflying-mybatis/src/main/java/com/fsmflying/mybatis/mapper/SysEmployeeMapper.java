package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysEmployee;

public interface SysEmployeeMapper {
	
	//Insert("insert into sys_employees(EmplId,DeptId,CompanyId,EmplNo,EmplName_CN,EmplName_EN,Sex,ShowOrder,AutoNo,BirthDate,CardId,NativePlace,ContactPhone,ContactMPhone,ContactAddress,ContactFax,ContactEmail,PositionId,PositionName,PositionTitle,Field001,Field002,Field003,Field004,Field005,DbDeleted,DbCreateBy,DbLastUpdateBy,DbCreateTime,DbLastUpdateTime,Flag) values(#{emplId},#{deptId},#{companyId},#{emplNo},#{emplName_CN},#{emplName_EN},#{sex},#{showOrder},#{autoNo},#{birthDate},#{cardId},#{nativePlace},#{contactPhone},#{contactMPhone},#{contactAddress},#{contactFax},#{contactEmail},#{positionId},#{positionName},#{positionTitle},#{field001},#{field002},#{field003},#{field004},#{field005},#{dbDeleted},#{dbCreateBy},#{dbLastUpdateBy},#{dbCreateTime},#{dbLastUpdateTime},#{flag})")
	boolean add(SysEmployee model);

	//@Update("update sys_employees set DeptId=#{deptId},CompanyId=#{companyId},EmplNo=#{emplNo},EmplName_CN=#{emplName_CN},EmplName_EN=#{emplName_EN},Sex=#{sex},ShowOrder=#{showOrder},AutoNo=#{autoNo},BirthDate=#{birthDate},CardId=#{cardId},NativePlace=#{nativePlace},ContactPhone=#{contactPhone},ContactMPhone=#{contactMPhone},ContactAddress=#{contactAddress},ContactFax=#{contactFax},ContactEmail=#{contactEmail},PositionId=#{positionId},PositionName=#{positionName},PositionTitle=#{positionTitle},Field001=#{field001},Field002=#{field002},Field003=#{field003},Field004=#{field004},Field005=#{field005},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbLastUpdateBy=#{dbLastUpdateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateTime=#{dbLastUpdateTime},Flag=#{flag} where 1=1 and EmplId=#{emplId}")
	boolean save(SysEmployee model);

	//@Delete("delete from Sys_Employees where 1=1 and emplId=#{emplId}")
	boolean delete(int emplId);

	//@Select("select * from Sys_Employees where 1=1 and emplId=#{emplId}")
	SysEmployee get(int emplId);

	//@Select("select * from Sys_Employees")
	List<SysEmployee> getAllList();
/*
	List<SysEmployee> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysEmployee> list);
	
	boolean addList(List<SysEmployee> list);
*/
}