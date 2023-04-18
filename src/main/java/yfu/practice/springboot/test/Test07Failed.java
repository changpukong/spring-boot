package yfu.practice.springboot.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test07Failed {
	
	public static void main(String[] args) {
		Test07Failed test07 = new Test07Failed();
		long start = System.currentTimeMillis();
//		test07.execute(924);
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	public void execute(String boxSeries) {
		Set<Character> stacks = new HashSet<>();
		
		for (int i = boxSeries.length(); i >= 0; i--) {
			char box = boxSeries.charAt(i);
			if (stacks.contains(box)) {
				continue;
			}
			
			for (int j = i - 1; j >= 0; j--) {
				char nextBox = boxSeries.charAt(j);
				if (stacks.contains(nextBox)) {
					continue;
				}
				if (box > nextBox) {
					stacks.add(box);
					break;
				}
			}
		}
	}
	
}
