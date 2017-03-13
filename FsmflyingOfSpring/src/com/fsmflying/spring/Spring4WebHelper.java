package com.fsmflying.spring;

//import org.springframework.context.ApplicationContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fsmflying.sys.dao.SystemManageDao;
import com.fsmflying.sys.dm.SysFile;
import com.fsmflying.sys.service.SequenceService;
import com.fsmflying.sys.service.SystemManageService;

public class Spring4WebHelper {
	
//	@Resource
//	private static SystemManageService systemManageService;

	public static WebApplicationContext getApplicationContext(
			HttpServletRequest request) {
		if (request != null) {
			ServletContext servletContext = request.getServletContext();
			return (WebApplicationContext) servletContext
					.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		}
		return null;
	}

	public static Object getBean(HttpServletRequest request, String name) {
		WebApplicationContext wac = getApplicationContext(request);
		if (wac != null)
			return wac.getBean(name);
		return null;
	}

	/**
	 * 使用FileOutputStream保存封装CommonsMultipartFile.getInputStream()对象的输入流
	 * 
	 * @param request
	 *            请求
	 * @param file
	 *            　spring封装的文件
	 * @return
	 */
	public static SysFile saveFile(HttpServletRequest request,
			CommonsMultipartFile file) {

		SysFile record = new SysFile();
		record.setDbCreateTime(Calendar.getInstance().getTime());
		record.setFileExtName(file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf('.')));
		record.setFileAddress("upload/"
				+ new SimpleDateFormat("yyyyMM").format(record
						.getDbCreateTime()) + "/"
				+ UUID.randomUUID().toString() + record.getFileExtName());
		record.setFileName(file.getOriginalFilename());
		record.setFileLength(file.getSize());
		record.setDbCreateBy(-1);
		record.setDbDeleted(0);

		SequenceService sequenceService = (SequenceService) getBean(request,
				"sequenceService");
		if (sequenceService != null) {
			record.setFileGroupId(sequenceService.getNextId("sysfilegroup"));
			record.setFileId(sequenceService.getNextId("sysfile"));
		}

		String fileRealAddress = request.getServletContext().getRealPath(
				record.getFileAddress());
		try {
			File directory = new File(fileRealAddress.substring(0,
					fileRealAddress.lastIndexOf('\\')));
			if (!directory.exists())
				directory.mkdirs();
			// 获取输出流
			OutputStream os = new FileOutputStream(fileRealAddress);
			// 获取输入流 CommonsMultipartFile 中可以直接得到文件的流
			InputStream is = file.getInputStream();
			int temp;
			// 一个一个字节的读取并写入
			while ((temp = is.read()) != (-1)) {
				os.write(temp);
			}
			os.flush();
			os.close();
			is.close();
			record.setMemo("保存成功!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SystemManageService systemManageService = (SystemManageService) getBean(request,
				"systemManageService");
		systemManageService.add(record);
		return record;
	}

	/**
	 * 使用CommonsMultipartFile的transferTo方法保存文件
	 * 
	 * @param request
	 *            请求
	 * @param file
	 *            　spring封装的文件
	 * @return
	 */
	public static SysFile saveFile2(HttpServletRequest request,
			CommonsMultipartFile file) {

		SysFile record = new SysFile();
		record.setDbCreateTime(Calendar.getInstance().getTime());
		record.setFileExtName(file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf('.')));
		record.setFileAddress("upload/"
				+ new SimpleDateFormat("yyyyMM").format(record
						.getDbCreateTime()) + "/"
				+ UUID.randomUUID().toString() + record.getFileExtName());
		record.setFileName(file.getOriginalFilename());
		record.setFileLength(file.getSize());
		record.setDbCreateBy(-1);
		record.setDbDeleted(0);

		SequenceService sequenceService = (SequenceService) getBean(request,
				"sequenceService");
	
		if (sequenceService != null) {
			record.setFileGroupId(sequenceService.getNextId("sysfilegroup"));
			record.setFileId(sequenceService.getNextId("sysfile"));
		}

		String fileRealAddress = request.getServletContext().getRealPath(
				record.getFileAddress());
		try {

			File directory = new File(fileRealAddress.substring(0,
					fileRealAddress.lastIndexOf('\\')));

			if (!directory.exists())
				directory.mkdirs();
			file.transferTo(new File(fileRealAddress));
			record.setMemo("保存成功!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(record);
		SystemManageService systemManageService = (SystemManageService) getBean(request,
					"systemManageService");
		systemManageService.add(record);
		return record;
	}

	/**
	 * 使用MultipartHttpServletRequest保存多个文件
	 * 
	 * @param request
	 *            请求
	 * @return
	 */
	public static List<SysFile> saveFiles(HttpServletRequest request) {

		List<SysFile> list = new ArrayList<SysFile>();
		try {
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					// 一次遍历所有文件
					MultipartFile file = multiRequest.getFile(iter.next()
							.toString());

					if (file != null) {
						SysFile record = new SysFile();
						record.setDbCreateTime(Calendar.getInstance().getTime());
						record.setFileExtName(file.getOriginalFilename()
								.substring(
										file.getOriginalFilename().lastIndexOf(
												'.')));
						record.setFileAddress("upload/"
								+ new SimpleDateFormat("yyyyMM").format(record
										.getDbCreateTime()) + "/"
								+ UUID.randomUUID().toString()
								+ record.getFileExtName());
						record.setFileName(file.getOriginalFilename());
						record.setFileLength(file.getSize());
						record.setDbCreateBy(-1);
						record.setDbDeleted(0);
						String fileRealAddress = request.getServletContext()
								.getRealPath(record.getFileAddress());

						File directory = new File(fileRealAddress.substring(0,
								fileRealAddress.lastIndexOf('\\')));
						if (!directory.exists())
							directory.mkdirs();
						file.transferTo(new File(fileRealAddress));
						record.setMemo("保存成功!");
						list.add(record);
					}

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SequenceService sequenceService = (SequenceService) getBean(request,
				"sequenceService");
		if (sequenceService != null) {
			int fileGroupId = sequenceService.getNextId("sysfilegroup");
			for (SysFile file : list) {
				file.setFileGroupId(fileGroupId);
				file.setFileId(sequenceService.getNextId("sysfile"));
			}

		}
		// System.out.println(list);
		SystemManageService systemManageService = (SystemManageService) getBean(request,
				"systemManageService");
		for (SysFile file : list)
			systemManageService.add(file);

		return list;
	}

	public static void downloadFile(int fileId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		SysFile record = null;

		record = new SysFile();
		record.setFileName("日出东方_001.TXT");
		record.setFileExtName(".TXT");
		record.setFileAddress("upload/201703/839701d8-4647-43b3-8974-d5d1b74c3cbd.TXT");

		// SystemManageService systemManageService = (SystemManageService)
		// getBean(
		// request, "systemManageService");
		// if (systemManageService != null) {
		// record = systemManageService.getModelOfSysFile(fileId);
		// }

		if (record != null) {
			String fileRealAddress = request.getServletContext().getRealPath(
					record.getFileAddress());
			if (fileRealAddress != null) {
				File file = new File(fileRealAddress);
				if (file.exists()) {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/force-download");// 设置强制下载不打开
					response.addHeader(
							"Content-Disposition",
							"attachment;fileName="
									+ URLEncoder.encode(record.getFileName(),
											"UTF-8"));// 设置文件名
					// 获取响应报文输出流对象
					ServletOutputStream out = response.getOutputStream();
					FileInputStream fis = new FileInputStream(file);
					byte[] bytes = new byte[fis.available()];
					fis.read(bytes);
					// 输出
					out.write(bytes);
					out.flush();
					out.close();
				}

			}
		}

	}

}
