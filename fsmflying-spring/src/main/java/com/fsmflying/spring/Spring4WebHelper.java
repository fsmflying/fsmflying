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
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.fsmflying.sys.dm.SysFile;
import com.fsmflying.sys.service.ISequenceService;
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
	 * @param request
	 * @param file
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

		ISequenceService sequenceService = (ISequenceService) getBean(request,
				"sequenceService");
		if (sequenceService != null) {
			record.setFileGroupId(sequenceService.generateNextId("sysfilegroup"));
			record.setFileId(sequenceService.generateNextId("sysfile"));
		}

		String fileRealAddress = request.getServletContext().getRealPath(
				record.getFileAddress());
		try {
			File directory = new File(fileRealAddress.substring(0,
					fileRealAddress.lastIndexOf('\\')));
			if (!directory.exists())
				directory.mkdirs();
			OutputStream os = new FileOutputStream(fileRealAddress);
			InputStream is = file.getInputStream();
			int temp;
			while ((temp = is.read()) != (-1)) {
				os.write(temp);
			}
			os.flush();
			os.close();
			is.close();
			record.setMemo("upload successfully !");

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
	 * 
	 * @param request The client request object which was wrapped by servlet
	 * @param file The file object which was wrapped by springmvc
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

		ISequenceService sequenceService = (ISequenceService) getBean(request,
				"sequenceService");
	
		if (sequenceService != null) {
			record.setFileGroupId(sequenceService.generateNextId("sysfilegroup"));
			record.setFileId(sequenceService.generateNextId("sysfile"));
		}

		String fileRealAddress = request.getServletContext().getRealPath(
				record.getFileAddress());
		try {

			File directory = new File(fileRealAddress.substring(0,
					fileRealAddress.lastIndexOf('\\')));

			if (!directory.exists())
				directory.mkdirs();
			file.transferTo(new File(fileRealAddress));
			record.setMemo("The file has been uploaded successfully!");

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
	 * 
	 * @param request The client request object which was wrapped by servlet
	 * @return
	 */
	public static List<SysFile> saveFiles(HttpServletRequest request) {

		List<SysFile> list = new ArrayList<SysFile>();
		try {
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());

			// 
			if (multipartResolver.isMultipart(request)) {
				// 
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				//
				Iterator iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					// 
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
						record.setMemo("These files has been uploaded successfully!");
						list.add(record);
					}

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ISequenceService sequenceService = (ISequenceService) getBean(request,
				"sequenceService");
		if (sequenceService != null) {
			int fileGroupId = sequenceService.generateNextId("sysfilegroup");
			for (SysFile file : list) {
				file.setFileGroupId(fileGroupId);
				file.setFileId(sequenceService.generateNextId("sysfile"));
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
		record.setFileName("�ճ�����_001.TXT");
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
					response.setCharacterEncoding("UTF-8");//set the encoding
					response.setContentType("application/force-download");//set the content for download
					response.addHeader(
							"Content-Disposition",
							"attachment;fileName="
									+ URLEncoder.encode(record.getFileName(),
											"UTF-8"));// set the file name for client will be downloading.
					// 
					ServletOutputStream out = response.getOutputStream();
					FileInputStream fis = new FileInputStream(file);
					byte[] bytes = new byte[fis.available()];
					fis.read(bytes);
					// output the file content
					out.write(bytes);
					out.flush();
					out.close();
				}

			}
		}

	}

}
