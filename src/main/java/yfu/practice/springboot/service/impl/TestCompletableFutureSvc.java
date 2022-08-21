package yfu.practice.springboot.service.impl;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class TestCompletableFutureSvc {

	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	public void doService() {
		for (int i = 0; i < 100; i++) {
			int j = i;
			CompletableFuture.runAsync(() -> work(j), threadPoolTaskExecutor);
			System.err.println("Caller," + System.currentTimeMillis() + "," + i);
		}
	}

	private void work(int j) {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("Method," + System.currentTimeMillis() + "," + j);
	}


}