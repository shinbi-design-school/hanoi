package com.design_shinbi.recursive.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.design_shinbi.recursive.Reverse;

class ReverseTest {

	@Test
	void test() {
		String[] sources = {"abcde", "akasaka", "a", "シンビデザインスクール"};
		String[] answers = {"edcba", "akasaka", "a", "ルークスンイザデビンシ"};
		
		for(int i = 0; i < sources.length; i++) {
			String s = sources[i];
			String reversed = Reverse.reverse(s);
			
			System.out.println("元の文字列: " + s);
			System.out.println("反転文字列: " + reversed);
	
			if(!reversed.equals(answers[i])) {
				fail("結果が違います。");
			}
		}
	}
}
