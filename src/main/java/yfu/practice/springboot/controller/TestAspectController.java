package yfu.practice.springboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAspectController {

	@PostMapping("/testAspect/{isFailed}")
	public String testAspect(@PathVariable("isFailed") boolean isFailed) throws Exception {
		Thread.sleep(1000);
		if (isFailed) {
			throw new Exception("喔哦~ 失敗了");
		}
		return "success";
	}
	
}