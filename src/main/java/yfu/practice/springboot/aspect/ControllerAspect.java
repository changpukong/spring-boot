package yfu.practice.springboot.aspect;

import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class ControllerAspect {
	
	/*
	 * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
	 * modifiers-pattern: 修飾子
	 * ret-type-pattern: 回傳型別
	 * declaring-type-pattern: 類別路徑
	 * name-pattern: 方法名稱
	 * param-pattern: 傳入方法參數型別
	 * throws-pattern: 拋出例外類型
	 */
	private static final String POINT_CUT = "execution(* yfu.practice.springboot.controller.TestAspectController.*(..))";

//	private static final String POINT_CUT_2 = "execution(* yfu..controller.TestAspectController.*(..))";	// ..表示任意路徑
	
	@Before(value = POINT_CUT)
	public void doBefore(JoinPoint joinPoint) {
		printDefaultLog("Before", joinPoint);
	}
	
	@Around(value = POINT_CUT)
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		printDefaultLog("Around", joinPoint);

		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long spendTime = System.currentTimeMillis() - startTime;
		log.info("Around, return: {}, spendTime: {}", result, spendTime);
		
		return result;
	}
	
	@After(value = POINT_CUT)
	public void doAfter(JoinPoint joinPoint) {
		printDefaultLog("After", joinPoint);
	}
	
	@AfterReturning(value = POINT_CUT, returning = "result")
	public void doAfterReturning(JoinPoint joinPoint, Object result) {
		String target = joinPoint.getTarget().getClass().getSimpleName();
		String method = joinPoint.getSignature().getName();
		String args = Stream.of(joinPoint.getArgs())
				.collect(StringBuilder::new, (sb, e) -> sb.append(e).append(';'), StringBuilder::append)
				.toString();
		log.info("AfterReturning, target: {}, method: {}, args: {}, return: {}", target, method, args, result);
	}
	
	@AfterThrowing(value = POINT_CUT, throwing = "ex")
	public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
		String target = joinPoint.getTarget().getClass().getSimpleName();
		String method = joinPoint.getSignature().getName();
		String args = Stream.of(joinPoint.getArgs())
				.collect(StringBuilder::new, (sb, e) -> sb.append(e).append(';'), StringBuilder::append)
				.toString();
		log.info("AfterThrowing, target: {}, method: {}, args: {}, error occurs: {}", target, method, args, ex.getMessage());
	}
	
	private void printDefaultLog(String adviceName, JoinPoint joinPoint) {
		String target = joinPoint.getTarget().getClass().getSimpleName();
		String method = joinPoint.getSignature().getName();
		String args = Stream.of(joinPoint.getArgs())
				.collect(StringBuilder::new, (sb, e) -> sb.append(e).append(';'), StringBuilder::append)
				.toString();
		log.info("{}, target: {}, method: {}, args: {}", adviceName, target, method, args);
	}
}