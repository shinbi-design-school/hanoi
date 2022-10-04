package com.design_shinbi.recursive;

public class TriangleNumber {
	// 再起処理を使った計算
	public static int calculate(int n) {
		int triangle = 0;
		
		if(n == 1) {
			triangle = 1;
		}
		else {
			triangle = n + calculate(n - 1);
		}

		return triangle;
	}
	
	// For を使った計算
	public static int calculateUsingFor(int n) {
		int triangle = 0;
		
		for(int i = 1; i <= n; i++) {
			triangle = triangle + i;
		}
		
		return triangle;
	}
}
