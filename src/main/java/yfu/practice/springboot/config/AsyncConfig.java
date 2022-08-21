package yfu.practice.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(10);
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
//		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

}