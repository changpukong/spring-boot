package yfu.practice.springboot.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import yfu.practice.springboot.repository.YfuCardContentRepo;
import yfu.practice.springboot.repository.YfuCardRepo;

@Slf4j
@Aspect
@Component
public class TestJpaConnectionAspect {
	
	private static final String POINT_CUT = "execution(* yfu.practice.springboot.service.impl.TestJpaService.*(..))";
	
	@Autowired
	private YfuCardRepo yfuCardRepo;
	
	@Autowired
	private YfuCardContentRepo yfuCardContentRepo;
	
	@Before(value = POINT_CUT)
	@Transactional(propagation = Propagation.REQUIRED)
	public void doBefore() {
		log.info("Before =====> {}", yfuCardRepo.findAll());
	}
	
	@AfterReturning(value = POINT_CUT)
	public void doAfterReturning() {
		log.info("AfterReturning =====> {}", yfuCardContentRepo.findAll());
	}

}