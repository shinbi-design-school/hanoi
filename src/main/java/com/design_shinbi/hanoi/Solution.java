package com.design_shinbi.hanoi;

import com.design_shinbi.hanoi.exception.HanoiException;
import com.design_shinbi.hanoi.model.Disk;
import com.design_shinbi.hanoi.model.Tower;
import com.design_shinbi.hanoi.view.Logger;

/**
 * ハノイの塔を実行する。
 */
public class Solution {
	/**
	 * ハノイの塔を移動する。
	 * @param left 左側の塔。(最初はここに全ての円盤が積んである。)
	 * @param middle 真ん中の塔 (初期状態では空)
	 * @param right 右側の塔 (初期状態では空)
	 * @param logger ログ表示用オブジェクト
	 */
	public void solve(Tower left, Tower middle, Tower right, Logger logger) throws HanoiException {
		// 以下の処理を全て削除してから、solve メソッドを実装してみてください。
		logger.info("このプログラムは演習用教材です。");
		logger.info("jp.trans_it.hanoi.Solution クラスの solve メソッドを実装して、ハノイの塔を動かしてみて下さい。");
		logger.info("一度に動かせる円盤は一枚だけです。");
		logger.info("大きな円盤を小さな円盤の上に乗せることはできません。");
		logger.info("以上のルールを守って、左の塔を他の場所へ移動させてください。");

		// Tower オブジェクトの getHeight() メソッドで塔の高さを取得することができます。
		int height = left.getHeight();

		// Tower オブジェクトの getDisk() メソッドで円盤情報を取得することができます。
		for (int i = 0; i < height; i++) {
			Disk disk = left.getDisk(i);
		}

		// Tower オブジェクトの getName() メソッドで塔の名前を取得することができます。
		String name = left.getName();

		// Tower オブジェクトの pop() メソッドで一番上の円盤を取得します。
		Disk disk = left.pop();

		// Tower オブジェクトの push() メソッドで持っている円盤を一番上に置きます。
		right.push(disk);

		// Disk オブジェクトの getSize() メソッドで円盤のサイズを取得することができます。
		int size = disk.getSize();		
		logger.info("ヒントは moveTower を実装して solve メソッドの中で "
						+ "moveTower(left, middle, right, left.getHeight()) を呼び出します。");
		logger.info("それでは、Let's Challenge!!!");
		
		// this.moveTower(left, middle, right, left.getHeight());
	}
	
	/**
	 * ハノイの塔を移動する
	 * @param from 移動元の塔
	 * @param to 移動先の塔
	 * @param another 移動元、移動先以外の塔
	 * @param height 移動したい塔の段数
	 * @throws HanoiException 例外
	 */
	private void moveTower(Tower from, Tower to, Tower another, int height) throws HanoiException {
		
		// これを実装して
		// solve で this.moveTower(left, middle, right, left.getHeight()) を呼び出します。
		
	}

}
