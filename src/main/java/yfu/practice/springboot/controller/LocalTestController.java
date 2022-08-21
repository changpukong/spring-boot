package yfu.practice.springboot.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import yfu.practice.springboot.dto.TestDto;
import yfu.practice.springboot.dto.base.MwHeaderResponse;
import yfu.practice.springboot.dto.base.ResponseTemplate;
import yfu.practice.springboot.service.impl.TestExcelSvc;

@RestController
public class LocalTestController {

	@Autowired
	private TestExcelSvc testExcelSvc;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/xlsx/download")
	public ResponseEntity<byte[]> download() throws IOException {
		return testExcelSvc.genXlxsFile();
	}

	@PostMapping(value = "/testRestTemplate/server", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseTemplate<TestDto> testRestTemplateServer() {
		ResponseTemplate<TestDto> resp = new ResponseTemplate<>();
		resp.setMwHeader(new MwHeaderResponse());
		resp.setTranrs(new TestDto());
		return resp;
	}
	
	@PostMapping(value = "/testRestTemplate/server2")
	public String testRestTemplateServer2() {
		return "OK";
	}

	@PostMapping(value = "/testRestTemplate/client", produces = MediaType.APPLICATION_JSON_VALUE)
	public void testRestTemplateClient() throws RestClientException, URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

//		ResponseEntity<ResponseTemplate<TestDto>> resp = restTemplate.exchange(new URI("http://localhost:8081/testRestTemplate/server3"),
//				HttpMethod.POST, new HttpEntity<>(headers),
//				new ParameterizedTypeReference<ResponseTemplate<TestDto>>() {});
		ResponseEntity<ResponseTemplate<TestDto>> resp = restTemplate.exchange(new URI("http://localhost:8081/testRestTemplate/server2"),
				HttpMethod.POST, new HttpEntity<>(headers),
				new ParameterizedTypeReference<ResponseTemplate<TestDto>>() {});

		System.err.println(resp.getBody());
	}

}