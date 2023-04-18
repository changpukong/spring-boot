package yfu.practice.springboot.test;

import java.util.ArrayList;
import java.util.List;

public class Test03 {
	
	public static void main(String[] args) {
		Test03 test03 = new Test03();
		long start = System.currentTimeMillis();
		test03.execute(123456789);
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	public void execute(int num) {
		List<Integer> primeFactor = new ArrayList<>();
		int prime = 2;
		while (num != 1) {
			if (num % prime == 0) {
				num /= prime;
				primeFactor.add(prime);
			} else {
				prime++;
			}
		}
		printExpr(primeFactor);
	}
	
	private void printExpr(List<Integer> primeFactor) {
		StringBuilder sb = new StringBuilder();
		int prevNum = 0;
		int count = 0;
		for (Integer i : primeFactor) {
			if (count == 0 || i == prevNum) {
				prevNum = i;
				count++;
				continue;
			}
			
			if (sb.length() > 0) {
				sb.append(" * ");
			}
			sb.append(prevNum);
			if (count > 1) {
				sb.append('^').append(count);
			}
			
			prevNum = i;
			count = 1;
		}
		
		if (sb.length() > 0) {
			sb.append(" * ");
		}
		sb.append(prevNum);
		if (count > 1) {
			sb.append('^').append(count);
		}
		
		System.out.println(sb.toString());
	}

//	private void printExpr(List<Integer> primeFactor) {
//		Map<Integer, Long> countMap = primeFactor.stream()
//				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//		
//		String expr = countMap.entrySet().stream()
//				.sorted(Entry.comparingByKey())
//				.map(entry -> {
//					StringBuilder sb = new StringBuilder();
//					sb.append(entry.getKey());
//					if (entry.getValue() > 1) {
//						sb.append('^').append(entry.getValue());
//					}
//					return sb.toString();
//				}).collect(Collectors.joining(" * "));
//		
//		System.out.println(expr);
//	}
}
