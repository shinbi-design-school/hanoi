package com.design_shinbi.recursive;

public class Fact {
	// 再起処理を使った計算
	public static int calculate(int n) {
		int triangle = 1;
		
		// 演習
		// 実装してみよう。
		


		return triangle;
	}
	
	// For を使った計算
	public static int calculateUsingFor(int n) {
		int triangle = 1;
		
		for(int i = 1; i <= n; i++) {
			triangle = triangle * i;
		}
		
		return triangle;
	}
}
