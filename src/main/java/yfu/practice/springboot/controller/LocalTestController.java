package yfu.practice.springboot.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import yfu.practice.springboot.dto.TestDto;
import yfu.practice.springboot.dto.base.MwHeaderResponse;
import yfu.practice.springboot.dto.base.ResponseTemplate;
import yfu.practice.springboot.service.impl.TestExcelSvc;
import yfu.practice.springboot.service.impl.TestIsolationSvc;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

@RestController
public class LocalTestController {

	@Autowired
	private TestExcelSvc testExcelSvc;
	
	@Autowired
	private TestIsolationSvc testIsolationSvc;

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
	
	@PostMapping(value = "/test")
	public void test(HttpServletResponse resp) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		OutputStream os = resp.getOutputStream();
		// TODO 用到11的方法
//		os.write(Files.readAllBytes(Path.of("D:/save.jpg")));
		os.flush();
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_PNG);
//		return new ResponseEntity<>(Files.readAllBytes(Path.of("D:/save.jpg")), headers, HttpStatus.OK);
	}
	
	@PostMapping(value = "/testIsolation", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("測試隔離層級")
	public void testIsolation1() throws SQLException {
		testIsolationSvc.scenario();
	}
	
	@PostMapping(value = "/testIsolation2", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("測試隔離層級2")
	public void testIsolation2() throws SQLException {
		testIsolationSvc.scenario2();
	}
	
	@PostMapping(value = "/testIsolation3", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("測試隔離層級32")
	public void testIsolation3() throws SQLException {
		testIsolationSvc.scenario3();
	}
	
	@PostMapping(value = "/testIsolation4", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("測試隔離層級4")
	public void testIsolation4() throws SQLException {
		testIsolationSvc.scenario4();
	}
	
	@PostMapping(value = "/testIsolation5", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("測試隔離層級5")
	public void testIsolation5() throws SQLException {
		testIsolationSvc.scenario5();
	}

}