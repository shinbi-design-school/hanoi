package com.design_shinbi.recursive.test;

import org.junit.jupiter.api.Test;

import com.design_shinbi.recursive.Loop;

class LoopTest {

	@Test
	void test() {
		System.out.println("=== 再起処理を使った場合 ===");		
		Loop.say("M.O.M.O. Momoiro Clover!!!", 10);
		System.out.println("");
		System.out.println("=== for を使った場合 ===");
		Loop.sayUsingFor("M.O.M.O. Momoiro Clover!!!", 10);
	}
}
