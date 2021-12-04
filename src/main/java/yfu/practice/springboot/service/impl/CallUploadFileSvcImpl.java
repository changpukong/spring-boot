package yfu.practice.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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

import yfu.practice.springboot.service.CallUploadFileSvc;

/**
 * Call UploadFile API
 * @author yfu
 */
@Service
public class CallUploadFileSvcImpl implements CallUploadFileSvc {
    
    @Autowired
    private RestTemplate restTemplate;
	
//	private static final String URL_PATH = "http://localhost:8081/uploadFile";
	private static final String URL_PATH = "http://localhost:8081/uploadFile2";
	
	private static final String LOCAL_DIR = "localDir/";

	@Override
	public String uploadFile(String... fileNames) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		for (String fileName : fileNames) {
		    body.add("file", new ClassPathResource(LOCAL_DIR + fileName));
		}
		
		ResponseEntity<String> resp = restTemplate.postForEntity(URL_PATH, new HttpEntity<>(body, headers), String.class);
		if (resp.getStatusCode() == HttpStatus.OK) {
		    return resp.getBody();
		}
		return "連線異常";
	}
}