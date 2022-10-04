package com.design_shinbi.recursive.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.design_shinbi.recursive.Fact;

class FactTest {

	@Test
	void test() {
			for(int i = 1; i <= 10; i++) {
				System.out.println(i + " の階乗");
				int recursiveAnswer = Fact.calculate(i);
				int forAnswer = Fact.calculateUsingFor(i);
				System.out.println(
					String.format(
						"    再起処理を使った場合: %d,   forを使った場合: %d",
						recursiveAnswer,
						forAnswer
					)
				);
				
				if(recursiveAnswer != forAnswer) {
					fail("結果が違います。");
				}
			}
	}

}
