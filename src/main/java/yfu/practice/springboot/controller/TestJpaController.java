package yfu.practice.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import yfu.practice.springboot.service.impl.TestJpaConnectionService;

@RestController
public class TestJpaController {
	
	@Autowired
	private TestJpaConnectionService testJpaService;
	
	/*
	 * DatasourceConnectionProviderImpl.getConnection() 取得連線
	 * 整個request結束才會歸還連線
	 */
	@PostMapping("/testJpaConnection")
	public void testJpaConnection() {
		testJpaService.query();
	}
	
}