package yfu.practice.springboot.test;

public class Test02 {
	
	public static void main(String[] args) {
		Test02 test02 = new Test02();
		long start = System.currentTimeMillis();
		System.out.println(test02.execute(new int[] {0, -3, 1, 4}));
		System.out.println(test02.execute(new int[] {0, -2, 5, 1, 2, 4, 3}));
		System.out.println(test02.execute(new int[] {11, 10, -10, -1, 19, 3, 17, 14, 1}));
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}
	
	public int execute(int[] numArr) {
		int length = numArr.length;
		int[] cnt = new int[length];	// 出現數列最大長度次數
		int[] len = new int[length];	// 數列最大長度
		int maxLen = 0;
		int accCnt = 0;
		for (int i = 0; i < length; i++) {
			cnt[i] = 1;
			for (int j = 0; j < i; j++) {
				if (numArr[j] > numArr[i]) {
					continue;
				}
				if (len[i] < len[j]) {	// 若有找到更長的數列，重設值
					len[i] = len[j];
					cnt[i] = cnt[j];
				} else if (len[i] == len[j]) {
					cnt[i] += cnt[j];
				}
			}
			len[i]++;	// 加上自己，數列最大長度 + 1

			if (len[i] > maxLen) {
				maxLen = len[i];
				accCnt = cnt[i];
			} else if (len[i] == maxLen) {
				accCnt += cnt[i];
			}
		}
		
		return accCnt;
	}

}
