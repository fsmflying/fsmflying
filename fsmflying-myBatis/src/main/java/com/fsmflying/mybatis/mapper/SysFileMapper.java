package com.fsmflying.mybatis.mapper;

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
import java.util.List;
//import com.fsmflying.helpers.SqlParameter;
import com.fsmflying.sys.dm.SysFile;

public interface SysFileMapper {
	
	//Insert("insert into sys_files(FileId,FileName,FileExtName,FileGroupId,FileAddress,FileLength,FileType,Memo,DbDeleted,DbCreateBy,DbCreateTime) values(#{fileId},#{fileName},#{fileExtName},#{fileGroupId},#{fileAddress},#{fileLength},#{fileType},#{memo},#{dbDeleted},#{dbCreateBy},#{dbCreateTime})")
	boolean add(SysFile model);

	//@Update("update sys_files set FileName=#{fileName},FileExtName=#{fileExtName},FileGroupId=#{fileGroupId},FileAddress=#{fileAddress},FileLength=#{fileLength},FileType=#{fileType},Memo=#{memo},DbDeleted=#{dbDeleted},DbCreateBy=#{dbCreateBy},DbCreateTime=#{dbCreateTime} where 1=1 and FileId=#{fileId}")
	boolean save(SysFile model);

	//@Delete("delete from Sys_Files where 1=1 and fileId=#{fileId}")
	boolean delete(int fileId);

	//@Select("select * from Sys_Files where 1=1 and fileId=#{fileId}")
	SysFile get(int fileId);

	//@Select("select * from Sys_Files")
	List<SysFile> getAllList();
/*
	List<SysFile> getListByProperties(List<SqlParameter<?>> parameters);

	boolean deleteByProperties(List<SqlParameter<?>> parameters);

	boolean saveList(List<SysFile> list);
	
	boolean addList(List<SysFile> list);
*/
}