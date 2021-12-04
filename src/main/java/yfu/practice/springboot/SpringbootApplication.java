package yfu.practice.springboot;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) throws MalformedURLException, URISyntaxException {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
