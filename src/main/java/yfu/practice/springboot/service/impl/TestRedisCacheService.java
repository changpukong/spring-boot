package yfu.practice.springboot.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import yfu.practice.springboot.entity.YfuCard;

@Slf4j
@Service
public class TestRedisCacheService {
	
	@Cacheable(value = "getAllCards")
	public List<YfuCard> getAllCards() {
		log.debug("Get cards from method...");
	
		YfuCard card = new YfuCard();
		card.setCardId("001");
		card.setType("L");
		
		YfuCard card2 = new YfuCard();
		card2.setCardId("002");
		card2.setType("M");
		
		return Arrays.asList(card, card2);
	}

	@Cacheable(value = "getAllCards", key = "#p0")	// 根據傳入參數使用不同的快取空間
	public List<YfuCard> getAllCards(int key) {
		log.debug("Get cards from method...");
	
		YfuCard card = new YfuCard();
		card.setCardId("001");
		card.setType("L");
		
		YfuCard card2 = new YfuCard();
		card2.setCardId("002");
		card2.setType("M");
		
		return Arrays.asList(card, card2);
	}
	
	@CachePut(value = "getAllCards")	// 每次都會更新快取
	public List<YfuCard> refreshCards() {
		log.debug("Refresh cards...");
	
		YfuCard card = new YfuCard();
		card.setCardId("003");
		card.setType("R");
		
		YfuCard card2 = new YfuCard();
		card2.setCardId("004");
		card2.setType("R");
		
		return Arrays.asList(card, card2);
	}
	
	@CacheEvict(value = "getAllCards", allEntries = true)	// 方法執行後清空所有快取
	public void clearCache() {
		log.info("Clear cache...");
	}
}