package yfu.practice.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import yfu.practice.springboot.repository.YfuMemberRepo;

@Slf4j
@Service
public class TestJpaConnectionService {

	@Autowired
	private YfuMemberRepo yfuMemberRepo;
	
	public void query() {
		log.info("Service =====> {}", yfuMemberRepo.findAll());
	}
}