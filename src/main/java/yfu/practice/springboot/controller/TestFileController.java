package yfu.practice.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import yfu.practice.springboot.service.CallUploadFileSvc;
import yfu.practice.springboot.service.DownloadFileSvc;
import yfu.practice.springboot.service.UploadFileSvc;
import yfu.practice.springboot.service.UploadFileSvc2;

@RestController
public class TestFileController {

    @Autowired
    private UploadFileSvc uploadFileSvc;
    
    @Autowired
    private UploadFileSvc2 uploadFileSvc2;
    
    @Autowired
    private CallUploadFileSvc callUploadFileSvc;
    
    @Autowired
    private DownloadFileSvc downloadFileSvc;
    
    /**
     * 上傳檔案 (Spring)
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile[] multipartFile) {
        return uploadFileSvc.uploadFile(multipartFile);
    }
    
    /**
     * 上傳檔案 (Servlet)
     * @param req
     * @return
     */
    @PostMapping(value = "/uploadFile2")
    public String callUploadFile2(HttpServletRequest req) {
    	return uploadFileSvc2.uploadFile(req);
    }

    /**
     * Call UploadFile API
     * @return
     */
    @PostMapping(value = "/callUploadFile")
    @ApiOperation("打上傳檔案API")
    public String callUploadFile() {
        return callUploadFileSvc.uploadFile("未命名.png");
    }
    
    /**
     * 下載檔案
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/downloadFile")
    @ApiOperation("下載檔案")
    public ResponseEntity<byte[]> downloadFile() throws Exception {
        return downloadFileSvc.downloadFile("未命名.png");
    }
}