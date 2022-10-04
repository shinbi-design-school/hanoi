package com.design_shinbi.recursive;

public class Loop {
	public static void say(String s, int times) {
		if(times > 0) {
			System.out.println(s);
			say(s, times - 1);
		}
	}
	
	public static void sayUsingFor(String s, int times) {
		for(int i = 0; i < times; i++) {
			System.out.println(s);
		}
	}
}
