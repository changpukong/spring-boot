package yfu.practice.springboot.service.impl;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 接收檔案Service 
 * @author yfu
 */
@Service
public class ReceiveFileSvc {

	/** 儲存路徑 */
	private static final String SAVE_DIR = "D:/temp/";

	/**
	 * 接收檔案(Spring)
	 * @param multipartFiles
	 * @return
	 */
	public String receiveFileSpring(MultipartFile[] multipartFiles) {
		try {
			for (MultipartFile multipartFile : multipartFiles) {
				if (multipartFile.isEmpty()) {
					continue;
				}

				File file = new File(SAVE_DIR, multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
			}
			return "上傳成功";
		} catch (IOException e) {
			e.printStackTrace();
			return "上傳失敗";
		}
	}

	/**
	 * 接收檔案(Servlet)
	 * @param req
	 * @return
	 */
	public String receiveFileServlet(HttpServletRequest req) {
		try {
			for (Part part : req.getParts()) {
				String fileName = getFileName(part); // part.getSubmittedFileName()不支援IE
				if (!fileName.isEmpty()) {
					part.write(SAVE_DIR + fileName);
				}
			}
			return "上傳成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "上傳失敗";
		}
	}

	/**
	 * 取得檔名
	 * @param part
	 * @return
	 */
	private String getFileName(Part part) {
		// form-data; name="file"; filename="未命名.png"
		String header = part.getHeader("content-disposition");
		if (header.contains("filename")) {
			int start = header.lastIndexOf("filename") + 10;
			int end = header.indexOf('"', start);
			return new File(header.substring(start, end)).getName(); // 避免原有檔案名稱含有路徑資訊
		}
		return "";
	}
}