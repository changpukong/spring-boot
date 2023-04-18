package yfu.practice.springboot.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeetCode {
	
	public static void main(String[] args) {
		System.err.println(new LeetCode().restoreIpAddresses("101023"));
	}
	
	public List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<>();
		spliter(s, 0, new String[4], 0, result);
		return result;
    }
	
	public void spliter(String s, int start, String[] ip, int curPart, List<String> result) {
		if (s.length() - start > 3 * (4 - curPart)) {	// 位數剩餘太多
			return;
		} else if (curPart >= 4) {
			result.add(Arrays.stream(ip).collect(Collectors.joining(".")));
		}
		
		for (int end = start; end < s.length(); end++) {
			String subStr = s.substring(start, end + 1);
			if (s.charAt(start) == '0' && subStr.length() > 1 || Integer.parseInt(subStr) > 255) {
				break;
			}
			ip[curPart] = subStr;
			spliter(s, end + 1, ip, curPart + 1, result);
		}
	}
    
}