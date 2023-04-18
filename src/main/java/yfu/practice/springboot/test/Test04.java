package yfu.practice.springboot.test;

import java.util.LinkedList;
import java.util.List;

public class Test04 {
	
	public static void main(String[] args) {
		Test04 test04 = new Test04();
		long start = System.currentTimeMillis();
		System.out.println(test04.execute("[]{[()]}"));
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	public boolean execute(String str) {
		List<Character> residual = new LinkedList<>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (residual.isEmpty() || !isPair(residual.get(residual.size() - 1), c)) {
				residual.add(c);
			} else {
				residual.remove(residual.size() - 1);
			}
		}
		
		return residual.isEmpty();
	}
	
	private boolean isPair(char c1, char c2) {
		return c1 == '(' && c2 == ')'
				|| c1 == '[' && c2 == ']'
				|| c1 == '{' && c2 == '}';
	}

}
