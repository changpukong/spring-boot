package yfu.practice.springboot.service;

import org.springframework.http.ResponseEntity;

/**
 * 下載檔案
 * @author yfu
 */
public interface DownloadFileSvc {

	public ResponseEntity<byte[]> downloadFile(String fileName) throws Exception;
}