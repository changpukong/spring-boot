package yfu.practice.springboot.service;

import javax.servlet.http.HttpServletRequest;

/**
 * 上傳檔案 (Servlet)
 * @author yfu
 */
public interface UploadFileSvc2 {

	String uploadFile(HttpServletRequest req);
}