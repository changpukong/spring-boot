package yfu.practice.springboot.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test09 {
	
	private Test09() {
		
	}
	
	public static void main(String[] args) {
		Test09 test09 = new Test09();
		long start = System.currentTimeMillis();
		System.out.println(test09.execute("a"));
		System.out.println(test09.execute("an"));
		System.out.println(test09.execute("win"));
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	public String execute(String qi) {
		List<String> words = Arrays.asList("acm", "icpc", "regional", "asia", "jakarta", "two", "thousand", "and",
				"nine", "arranged", "by", "universitas", "bina", "nusantara", "especially", "for", "you");
		
		Comparator<String> compareLength = (e1, e2) -> {
			if (e1.length() > e2.length()) {
				return 1;
			} else if (e1.length() < e2.length()) {
				return -1;
			}
			return 0;
		};
		
		List<Integer> top10 = words.stream()
				.sorted(compareLength
						.thenComparing((e1, e2) -> e1.compareTo(e2))
						.thenComparing((e1, e2) -> words.indexOf(e1) - words.indexOf(e2)))
				.filter(word -> word.contains(qi))
				.map(words::indexOf)
				.limit(10)
				.collect(Collectors.toList());
		
		if (top10.isEmpty()) {
			return "-1";
		}
		
		return top10.stream()
				.map(e -> String.valueOf(e + 1))
				.collect(Collectors.joining(" "));
	}

}
