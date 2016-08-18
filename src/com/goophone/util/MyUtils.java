package com.goophone.util;

public class MyUtils {

	/**
	 * 将传入的字符串转化为数组
	 * @param str  字符串
	 * @param tag 标记
	 * @return  数组
	 */
	public static String[] stringToStrArray(String str, char tag) {
		int count = 0;
		String[] result = null;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == tag) {
				count++;
			}
			if (count >= 1) {
				result = new String[count + 1];
				result = str.split(tag + "");
			} else {
				result = new String[] { str };
			}

		}

		return result;
	}

}
