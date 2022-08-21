package yfu.practice.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import yfu.practice.springboot.dto.TestDto;
import yfu.practice.springboot.service.DownloadFileSvc;
import yfu.practice.springboot.service.impl.ReceiveFileSvc;
import yfu.practice.springboot.service.impl.UploadFileSvc;

@RestController
public class TestFileController {

    @Autowired
    private ReceiveFileSvc receiveFileSvc;
    
    @Autowired
    private UploadFileSvc uploadFileSvc;
    
    @Autowired
    private DownloadFileSvc downloadFileSvc;
    
    @PostMapping(value = "/receiveFileSpring")
    @ApiOperation("接收檔案(Spring)")
    public String receiveFileSpring(@Valid @RequestPart("otherInfo") TestDto testDto, @RequestPart("file") MultipartFile[] multipartFile) {
    	return receiveFileSvc.receiveFileSpring(multipartFile);
    }

    @PostMapping(value = "/receiveFileServlet")
    @ApiOperation("接收檔案(Servlet)")
    public String receiveFileServlet(HttpServletRequest req) {
    	return receiveFileSvc.receiveFileServlet(req);
    }

    @PostMapping(value = "/uploadWithFile")
    @ApiOperation("上傳實體檔案")
    public String uploadWithFile() {
        return uploadFileSvc.uploadWithFile("未命名.png");
    }
    
    @PostMapping(value = "/uploadWithByteArray")
    @ApiOperation("上傳ByteArray")
    public String uploadWithByteArray() {
        return uploadFileSvc.uploadWithByteArray();
    }

    @GetMapping(value = "/downloadFile")
    @ApiOperation("下載檔案")
    public ResponseEntity<byte[]> downloadFile() throws Exception {
        return downloadFileSvc.downloadFile("未命名.png");
    }
    
}