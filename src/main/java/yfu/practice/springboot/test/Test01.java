package yfu.practice.springboot.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test01 {
	
	public static void main(String[] args) {
		Test01 test01 = new Test01();
		long start = System.currentTimeMillis();
		test01.execute(5);
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	public void execute(int n) {
		List<String> cords = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n + i - 1; j++) {
				if (i == n || j == n + i - 1 || j == n - i + 1) {
					cords.add("(" + i + "," + j + ")");
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
				if (j == n + i - 1) {
					System.out.println();
				}
			}
		}
		
		System.out.println(cords.stream().collect(Collectors.joining(",")));
	}

}
