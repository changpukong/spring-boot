package yfu.practice.springboot.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import yfu.practice.springboot.service.UploadFileSvc;

/**
 * 上傳檔案 (Spring)
 * @author yfu
 */
@Service
public class UploadFileSvcImpl implements UploadFileSvc {
	
    private static final String SAVE_DIR = "D:/temp";
    
	@Override
	public String uploadFile(MultipartFile[] multipartFiles) {
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
}