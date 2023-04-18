package yfu.practice.springboot.service.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import yfu.practice.springboot.dto.TwdDeposit;
import yfu.practice.springboot.jpa.entity.Card;

@Slf4j
@Service
public class TestRedisCacheSvc {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Cacheable(value = "getAllCards")
	public List<Card> getAllCards() {
		log.debug("Get cards from method...");
	
		Card card = new Card();
		card.setCardId("001");
		card.setType("L");
		
		Card card2 = new Card();
		card2.setCardId("002");
		card2.setType("M");
		
		return Arrays.asList(card, card2);
	}

	@Cacheable(value = "getAllCards", key = "#p0")	// 根據傳入參數使用不同的快取空間
	public List<Card> getAllCards(int param) {
		log.debug("Get cards from method...");
	
		Card card = new Card();
		card.setCardId("001");
		card.setType("L");
		
		Card card2 = new Card();
		card2.setCardId("002");
		card2.setType("M");
		
		return Arrays.asList(card, card2);
	}
	
	@Cacheable(value = "getAllCards")	// key預設使用所有參數
	public List<Card> getAllCards(int param, int param2) {
		log.debug("Get cards from method...");
	
		Card card = new Card();
		card.setCardId("001");
		card.setType("L");
		
		Card card2 = new Card();
		card2.setCardId("002");
		card2.setType("M");
		
		return Arrays.asList(card, card2);
	}
	
	@CachePut(value = "getAllCards")	// 每次都會更新快取
	public List<Card> refreshCards() {
		log.debug("Refresh cards...");
	
		Card card = new Card();
		card.setCardId("003");
		card.setType("R");
		
		Card card2 = new Card();
		card2.setCardId("004");
		card2.setType("R");
		
		return Arrays.asList(card, card2);
	}
	
	@CacheEvict(value = "getAllCards")	// 清空特定快取空間
	public void clearAllCards() {
		log.info("Clear cache...");
	}
	
	@CacheEvict(value = "getAllCards", allEntries = true)	// 清空所有快取
	public void clearCache() {
		log.info("Clear all cache...");
	}
	
	@Cacheable(value = "getRate")
	public String getRate(String ccyCode, String period) {
		return "005000";
	}
	
//	public void putData() {
//		redisTemplate.opsForValue().set("intRate:USD", "0.05");
//		BoundValueOperations<String, String> key = redisTemplate.boundValueOps("intRate");
//		key.set("USD");
//	}
	
	@Cacheable(value = "tdm-back-chub-testData", key = "#p0")
	public TwdDeposit getTestData(String idNo) {
		log.info("get data from method...");
		return new TwdDeposit(idNo, "活存", "123456789", "999");
	}
	
	public TwdDeposit getTestDataWithRedisTemplate(String idNo) {
		String key = "tdm-back-chub-testData::" + idNo;
		if (redisTemplate.hasKey(key).booleanValue()) {
			return (TwdDeposit) redisTemplate.opsForValue().get(key);
		}
		
		log.info("get data from method...");
		TwdDeposit twdDeposit = new TwdDeposit(idNo, "活存", "123456789", "999");
		redisTemplate.opsForValue().set(key, twdDeposit, Duration.ofMinutes(1));
		return twdDeposit;
	}
}