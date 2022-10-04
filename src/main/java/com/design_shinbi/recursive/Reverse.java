package com.design_shinbi.recursive;

public class Reverse {
	public static String reverse(String s) {
		String reversed = "";
		
		if(s.length() <= 1) {
			reversed = s;
		}
		else {
			String head = s.substring(0, 1);    // 1文字目
			String body = s.substring(1);       // 2文字目移行
			reversed = reverse(body) + head;
		}
	
		return reversed;
	}
}
