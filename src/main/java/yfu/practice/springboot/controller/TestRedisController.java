package yfu.practice.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import yfu.practice.springboot.entity.YfuCard;
import yfu.practice.springboot.service.impl.TestRedisCacheService;

@RestController
public class TestRedisController {

	@Autowired
	private TestRedisCacheService testRedisCacheService;
	
	@GetMapping(value = "/getAllCards", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<YfuCard> getAllCards() {
		return testRedisCacheService.getAllCards();
	}

	@GetMapping(value = "/getAllCards/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<YfuCard> getAllCards(@PathVariable("id") int id) {
		return testRedisCacheService.getAllCards(id);
	}
	
	@GetMapping(value = "/refreshCards", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<YfuCard> refreshCards() {
		return testRedisCacheService.refreshCards();
	}
	
	@GetMapping(value = "/clearCache", produces = MediaType.APPLICATION_JSON_VALUE)
	public void clearCache() {
		testRedisCacheService.clearCache();
	}
	
}
