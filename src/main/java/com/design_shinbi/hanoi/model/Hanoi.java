package com.design_shinbi.hanoi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.design_shinbi.hanoi.exception.HanoiException;
import com.design_shinbi.hanoi.view.Logger;

/**
 * ハノイの塔 クラス
 */
public class Hanoi implements Iterable<Tower>{
	/** 左側の塔 */
	private Tower left;

	/** 真ん中の塔 */
	private Tower middle;

	/** 右側の塔 */
	private Tower right;

	/** 手持ちの円盤 */
	private Disk havingDisk;

	/** 高さ */
	private int height;

	/** ステップ数 */
	private int stepCount;

	/**
	 * 操作種類
	 */
	public enum ActionMethod {
		PUSH,
		POP
	}

	/**
	 * 操作情報
	 */
	public class Action {
		public ActionMethod method;
		Tower tower;
	}

	/**
	 * 操作リスト
	 */
	private List< Action > actionList;

	/**
	 * コンストラクタ
	 */
	public Hanoi() {
		this.left = null;
		this.middle = null;
		this.right = null;
		this.havingDisk = null;
		this.actionList = null;
		this.stepCount = 0;
	}

	/**
	 * 左側の塔を取得する。
	 * @return 左側の塔
	 */
	public Tower getLeft() {
		return left;
	}

	/**
	 * 真ん中の塔を取得する。
	 * @return 真ん中の塔
	 */
	public Tower getMiddle() {
		return middle;
	}

	/**
	 * 右側の塔を取得する。
	 * @return 右側の塔
	 */
	public Tower getRight() {
		return right;
	}


	/**
	 * 手持ちの円盤を取得
	 * @return 手持ちの円盤
	 */
	public Disk getHavingDisk() {
		return this.havingDisk;
	}


	/**
	 * 手持ちの円盤をセット
	 * @param havingDisk 手持ち円盤
	 */
	void setHavingDisk(Disk havingDisk) {
		this.havingDisk = havingDisk;
	}


	/**
	 * 現在のステップ数を取得
	 * @return 現在のステップ数
	 */
	public int getStepCount() {
		return stepCount;
	}

	/**
	 * ハノイの塔準備
	 * @param height
	 */
	public void prepare(int height) {
		this.left = new Tower(this, "Left", height);
		this.middle = new Tower(this, "Middle");
		this.right = new Tower(this, "Right");
		this.havingDisk = null;
		this.actionList = new ArrayList< Action >();
		this.height = height;
		this.stepCount = 0;
	}

	/**
	 * 描画準備
	 */
	public void prepareDraw() {
		this.left.initialize(this.height);
		this.middle.initialize(0);
		this.right.initialize(0);
		this.havingDisk = null;
		this.stepCount = 0;
	}

	/**
	 * 操作初期化
	 */
	public void clearActions() {
		this.havingDisk = null;
		this.actionList = new ArrayList< Action >();
	}

	/**
	 * 操作追加
	 * @param tower 塔
	 * @param method 操作種類
	 */
	public void addAction(Tower tower, ActionMethod method) {
		Action action = new Action();
		action.tower = tower;
		action.method = method;
		this.actionList.add(action);
	}

	/**
	 * 操作が空か判定
	 * @return 操作が空であれば true
	 */
	public boolean isEmptyAction() {
		return (this.actionList == null || this.actionList.isEmpty());
	}

	/**
	 * 操作を一つすすめる。
	 */
	public void step() throws HanoiException {
		Logger logger = Logger.getLogger();
		if(this.isEmptyAction()) {
			return;
		}

		Action action = this.actionList.get(0);
		this.actionList.remove(0);

		Tower tower = action.tower;
		if(action.method == ActionMethod.PUSH) {
			tower.doPush(this.havingDisk);
			this.havingDisk = null;
			this.stepCount += 1;
			logger.info("ステップ数: " + this.stepCount);
		}
		else {
			this.havingDisk = tower.doPop();
		}
	}

	public Iterator<Tower> iterator() {
		List<Tower> towers = Arrays.asList(this.left, this.middle, this.right);
		return towers.iterator();
	}
}
