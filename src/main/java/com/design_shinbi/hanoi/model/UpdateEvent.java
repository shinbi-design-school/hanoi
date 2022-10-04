package com.design_shinbi.hanoi.model;

/**
 * 更新イベントインターフェース
 */
public interface UpdateEvent {
	/**
	 * 更新時の処理
	 * @param tower 更新対象の塔
	 * @param disk 更新対象の円盤
	 */
	public void onUpdate(Tower tower, Disk disk);
}
