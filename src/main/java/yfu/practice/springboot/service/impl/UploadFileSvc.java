package yfu.practice.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import yfu.practice.springboot.dto.TestDto;

/**
 * 上傳檔案Service
 * @author yfu
 */
@Service
public class UploadFileSvc {
    
    @Autowired
    private RestTemplate restTemplate;

    /** 上傳URL */
//	private static final String URL_PATH = "http://localhost:8081/receiveFileSpring";
	private static final String URL_PATH = "http://localhost:8081/receiveFileServlet";
	
	/** 檔案路徑 */
	private static final String LOCAL_DIR = "localDir/";

	/**
	 * 上傳實體檔案
	 * @param fileNames
	 * @return
	 */
	public String uploadWithFile(String... fileNames) {
		// Header
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		// Body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		for (String fileName : fileNames) {
		    body.add("file", new ClassPathResource(LOCAL_DIR + fileName));
		}
		body.add("otherInfo", createOtherInfo());

		ResponseEntity<String> resp = restTemplate.postForEntity(URL_PATH, new HttpEntity<>(body, headers), String.class);
		if (resp.getStatusCode() == HttpStatus.OK) {
		    return resp.getBody();
		}
		return "連線異常";
	}
	
	/**
	 * 上傳ByteArray
	 * @return
	 */
	public String uploadWithByteArray() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new ByteArrayResource("123\n456\n".getBytes()) {
			
			@Override
			public String getFilename() {
				return "byteArrayFile.txt";
			}
			
		});
		body.add("otherInfo", createOtherInfo());
		
		ResponseEntity<String> resp = restTemplate.postForEntity(URL_PATH, new HttpEntity<>(body, headers), String.class);
		if (resp.getStatusCode() == HttpStatus.OK) {
		    return resp.getBody();
		}
		return "連線異常";
	}
	
	/**
	 * 建立隨檔案一併傳送的其他資訊
	 * @return
	 */
	private TestDto createOtherInfo() {
		TestDto testDto = new TestDto();
		testDto.setYfuBalance("100.0");
		testDto.setYfuDate("2022-08-21");
		testDto.setYfuName("陳X東");
		return testDto;
	}
	
}