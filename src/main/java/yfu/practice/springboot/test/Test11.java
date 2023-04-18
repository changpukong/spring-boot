package yfu.practice.springboot.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test11 {
	
	private Test11() {
		
	}
	
	public static void main(String[] args) {
		Test11 test11 = new Test11();
		long start = System.currentTimeMillis();
		System.out.println(test11.execute(1_000_000_000));
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	public int execute(int n) {
		int[] rem = new int[n];
		rem[1] = 2;
		
		int curNum = 2;
		int curLoc = 1;
		while (curLoc < n) {
			for (int j = 0; j < rem[curNum - 1]; j++) {
				if (curLoc >= n) {
					break;
				}
				rem[curLoc++] = curNum;
			}
			curNum++;
		}
		return rem[n - 1];
	}

}
