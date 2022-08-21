package yfu.practice.springboot.service.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import yfu.practice.springboot.dto.TestDto3;

@Slf4j
@Service
public class TestCompletableFutureSvc2 {
	
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	private static final Random REANDOM = new Random();
	
	public void doService() {	
		for (int i = 0; i < 100_000_000; i++) {
			TestDto3 testDto = new TestDto3();
			testDto.setYfuBalance(new BigDecimal(1000));
			testDto.setYfuDate(Date.valueOf(LocalDate.now()));
			testDto.setYfuTimestamp(Timestamp.valueOf(LocalDateTime.now()));
			testDto.setYfuName("陳X東");
			
			int j = i;
			CompletableFuture.runAsync(() -> work(j), threadPoolTaskExecutor);
		}
	}

	private void work(int j) {
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Map<String, String> workAndReturnMap(String workName) {
		log.info("{} 開始", workName);
		
		try {
			Thread.sleep(REANDOM.nextInt(2000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("{} 結束", workName);
		return new HashMap<>();
	}
}