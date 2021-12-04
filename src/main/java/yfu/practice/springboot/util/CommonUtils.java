package yfu.practice.springboot.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
	
	private CommonUtils() {
	}
	
	/**
	 * 駝峰轉大寫
	 * @param str
	 * @return
	 */
	public static String camel2UpperCase(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		Pattern pattern = Pattern.compile("[A-Z]*[a-z0-9]*");
		Matcher matcher = pattern.matcher(str);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			String substr = matcher.group();
			if (!substr.isEmpty()) {
				sb.append(substr.toUpperCase());
				sb.append(matcher.end() == str.length() ? "" : "_");
			}
		}

		return sb.toString();
	}

	/**
	 * 字串駝峰化
	 * @param str
	 * @param smallCaseHead 是否為小寫開頭
	 * @return
	 */
	public static String camelize(String str, boolean smallCaseHead) {
		if (str == null || str.isEmpty()) {
			return str;
		}

		Pattern pattern = Pattern.compile("[^_]+");
		Matcher matcher = pattern.matcher(str);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			String substr = matcher.group();
			sb.append(substr.substring(0, 1).toUpperCase());
			sb.append(substr.substring(1).toLowerCase());
		}

		if (smallCaseHead) {
			sb.replace(0, 1, sb.substring(0, 1).toLowerCase());
		}
		return sb.toString();
	}
}