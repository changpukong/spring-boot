package yfu.practice.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yfu.practice.springboot.dto.TwdDeposit;
import yfu.practice.springboot.jpa.entity.Card;
import yfu.practice.springboot.service.impl.TestRedisCacheSvc;

@RestController
public class TestRedisController {

	@Autowired
	private TestRedisCacheSvc testRedisCacheSvc;
	
	@PostMapping(value = "/getAllCards", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Card> getAllCards() {
		return testRedisCacheSvc.getAllCards();
	}

	@PostMapping(value = "/getAllCards/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Card> getAllCards(@PathVariable("param") int param) {
		return testRedisCacheSvc.getAllCards(param);
	}
	
	@PostMapping(value = "/getAllCards/{param}/{param2}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Card> getAllCards(@PathVariable("param") int param, @PathVariable("param2") int param2) {
		return testRedisCacheSvc.getAllCards(param, param2);
	}
	
	@PostMapping(value = "/refreshCards", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Card> refreshCards() {
		return testRedisCacheSvc.refreshCards();
	}
	
	@PostMapping(value = "/clearAllCards")
	public void clearAllCards() {
		testRedisCacheSvc.clearAllCards();
	}
	
	@PostMapping(value = "/clearCache")
	public void clearCache() {
		testRedisCacheSvc.clearCache();
	}
	
	@PostMapping(value = "/getRate/{ccyCode}/{period}")
	public String getRate(@PathVariable("ccyCode") String ccyCode, @PathVariable("period") String period) {
		return testRedisCacheSvc.getRate(ccyCode, period);
	}
	
	@PostMapping(value = "/getTestData")
	public TwdDeposit getTestData(@RequestParam String idNo) {
		return testRedisCacheSvc.getTestData(idNo);
	}
	
	@PostMapping(value = "/getTestDataWithRedisTemplate")
	public TwdDeposit getTestDataWithRedisTemplate(@RequestParam String idNo) {
		return testRedisCacheSvc.getTestDataWithRedisTemplate(idNo);
	}
	
}