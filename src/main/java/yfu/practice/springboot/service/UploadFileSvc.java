package yfu.practice.springboot.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上傳檔案 (Spring)
 * @author yfu
 */
public interface UploadFileSvc {

	String uploadFile(MultipartFile[] multipartFiles);
}