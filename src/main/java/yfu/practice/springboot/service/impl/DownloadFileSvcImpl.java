package yfu.practice.springboot.service.impl;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import yfu.practice.springboot.service.DownloadFileSvc;

/**
 * 下載檔案
 * @author yfu
 */
@Service
public class DownloadFileSvcImpl implements DownloadFileSvc {

    private static final String FILE_DIR = "localDir/";
    
    @Override
    public ResponseEntity<byte[]> downloadFile(String fileName) throws Exception {
        try (InputStream inputStream = new ClassPathResource(FILE_DIR + fileName).getInputStream()) {
            int size = inputStream.available(); 
            byte[] body = new byte[size];
            if (size != inputStream.read(body)) {
                throw new Exception("讀檔失敗");
            }
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);    // body的內容類型
            /*
             * 內容為附件形式，可設定下載後的檔名
             * 非附件形式時，圖片將直接顯示於瀏覽器而非下載檔案至電腦
             * 中文檔名必須設定編碼，否則會出現亂碼
             */
            headers.setContentDisposition(ContentDisposition.attachment().filename(fileName, StandardCharsets.UTF_8).build());
            
            return new ResponseEntity<>(body, headers, HttpStatus.OK);
        }
    }
}