package yfu.practice.springboot.service.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.stereotype.Service;

import yfu.practice.springboot.service.UploadFileSvc2;

/**
 * 上傳檔案 (Servlet)
 * @author yfu
 */
@Service
public class UploadFileSvcImpl2 implements UploadFileSvc2 {
    
    private static final String SAVE_DIR = "D:/temp/";

    @Override
    public String uploadFile(HttpServletRequest req) {
        try {
            for (Part part : req.getParts()) {
                String fileName = getFileName(part);    // part.getSubmittedFileName()不支援IE
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
    
    private String getFileName(Part part) {
    	// form-data; name="file"; filename="未命名.png"
        String header = part.getHeader("content-disposition");
        if (header.contains("filename")) {
            int start = header.lastIndexOf("filename") + 10;
            int end = header.indexOf('"', start);
            return new File(header.substring(start, end)).getName();	// 避免原有檔案名稱含有路徑資訊
        }
        return "";
    }
}