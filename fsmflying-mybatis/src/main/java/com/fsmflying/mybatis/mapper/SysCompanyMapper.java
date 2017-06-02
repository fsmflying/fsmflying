package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysCompany;

public interface SysCompanyMapper {
	
	//Insert("insert into sys_companys(CompanyId,ParentCompanyId,FullName,ShortName,CompanyNo,AutoNo,ShowOrder,Flag,ContactPerson,ContactPhone,ContactEmail,ContactAddress,ContactFax,ContactPostalCode,RegisterationDate,ScopeOfBusiness,Memo,DbDeleted,DbCreateBy,DbCreateTime,DbLastUpdateBy,DbLastUpdateTime) values(#{companyId},#{parentCompanyId},#{fullName},#{shortName},#{companyNo},#{autoNo},#{showOrder},#{flag},#{contactPerson},#{contactPhone},#{contactEmail},#{contactAddress},#{contactFax},#{contactPostalCode},#{registerationDate},#{scopeOfBusiness},#{memo},#{dbDeleted},#{dbCreateBy},#{dbCreateTime},#{dbLastUpdateBy},#{dbLastUpdateTime})")
	boolean add(SysCompany model);

	//@Update("update sys_companys set ParentCompanyId=#{parentCompanyId},FullName=#{fullName},ShortName=#{shortName},CompanyNo=#{companyNo},AutoNo=#{autoNo},ShowOrder=#{showOrder},Flag=#{flag},ContactPerson=#{contactPerson},ContactPhone=#{contactPhone},ContactEmail=#{contactEmail},ContactAddress=#{contactAddress},ContactFax=#{contactFax},ContactPostalCode=#{contactPostalCode},RegisterationDate=#{registerationDate},ScopeOfBusiness=#{scopeOfBusiness},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbCreateTime=#{dbCreateTime},DbLastUpdateBy=#{dbLastUpdateBy},DbLastUpdateTime=#{dbLastUpdateTime} where 1=1 and CompanyId=#{companyId}")
	boolean save(SysCompany model);

	//@Delete("delete from Sys_Companys where 1=1 and companyId=#{companyId}")
	boolean delete(int companyId);

	//@Select("select * from Sys_Companys where 1=1 and companyId=#{companyId}")
	SysCompany get(int companyId);

	//@Select("select * from Sys_Companys")
	List<SysCompany> getAllList();
/*
	List<SysCompany> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysCompany> list);
	
	boolean addList(List<SysCompany> list);
*/
}