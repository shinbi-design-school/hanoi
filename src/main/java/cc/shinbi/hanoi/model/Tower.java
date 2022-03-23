package cc.shinbi.hanoi.model;

import java.util.ArrayList;
import java.util.List;

import cc.shinbi.hanoi.exception.HanoiException;
import cc.shinbi.hanoi.view.Logger;

/**
 * 塔クラス
 */
public class Tower {
	/** 円盤一覧 */
	private List< Disk > disks;

	/** 塔の名前 */
	private String name;

	/** ハノイの塔 */
	private Hanoi hanoi;

	/**
	 * コンストラクタ
	 */
	public Tower(Hanoi hanoi, String name) {
		this(hanoi, name, 0);
	}

	/**
	 * コンストラクタ
	 * @param height 初期の高さ
	 */
	public Tower(Hanoi hanoi, String name, int height) {
		this.hanoi = hanoi;
		this.name = name;
		this.disks = new ArrayList< Disk >();
		this.initialize(height);
	}

	/**
	 * 初期化
	 * @param height 初期の高さ
	 */
	public void initialize(int height) {
		this.disks.clear();
		for(int size = 1; size <= height; size++) {
			Disk disk = new Disk(size);
			this.disks.add(disk);
		}
	}


	/**
	 * 高さ取得
	 * @return 高さ
	 */
	public int getHeight() {
		return this.disks.size();
	}

	/**
	 * 塔の名前を取得する。
	 * @return 塔の名前
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 塔のディスクの枚数を取得する
	 * @return 塔のディスクの枚数
	 */
	public int count() {
		return this.disks.size();
	}

	/**
	 * 一番上の円盤を取り出す
	 * @return 一番上の円盤
	 * @throws HanoiException 塔に円盤が存在しないとき
	 */
	public Disk pop() throws HanoiException {
		if(this.disks.size() == 0) {
			String message = "塔に円盤が存在しません。" + this.toString();
			HanoiException exception = new HanoiException(message);
			throw exception;
		}

		Disk disk = this.hanoi.getHavingDisk();
		if(disk != null) {
			String message = "既に円盤を持っています。(" + disk.toString() + ") " + this.toString();
			HanoiException exception = new HanoiException(message);
			throw exception;
		}

		disk = this.disks.get(0);
		this.disks.remove(0);
		this.hanoi.setHavingDisk(disk);

		this.hanoi.addAction(this, Hanoi.ActionMethod.POP);

		return disk;
	}

	/**
	 * 一番上の円盤を取り出す (描画時処理用)
	 * @return 一番上の円盤
	 * @throws HanoiException 塔に円盤が存在しないとき
	 */
	Disk doPop() throws HanoiException  {
		Logger logger = Logger.getLogger();

		if(this.count() == 0) {
			String message = "塔に円盤が存在しません。" + this.toString();
			HanoiException exception = new HanoiException(message);
			throw exception;
		}

		Disk disk = this.disks.get(0);
		this.disks.remove(0);
		this.hanoi.setHavingDisk(disk);

		String log = "円盤取得: Tower(" + this.name + "), Disk(" + disk.getSize() + ")";
		logger.info(log);

		return disk;
	}

	/**
	 * 塔の一番上に円盤を置く
	 * @param disk 一番上に置く円盤
	 * @throws HanoiException 大きな円盤を小さな円盤の上に置いたとき
	 */
	public void push(Disk disk) throws HanoiException {
		if(this.disks.size() > 0) {
			Disk topDisk = this.disks.get(0);
			if(disk.getSize() >= topDisk.getSize()) {
				String message = "大きな円盤を小さな円盤の上に置くことはできません。(" + disk.toString() + ") "
							   + this.toString();
				HanoiException exception = new HanoiException(message);
				throw exception;
			}
		}

		if(this.hanoi.getHavingDisk() == null) {
			String message = "手持ちの円盤が存在しません。";
			HanoiException exception = new HanoiException(message);
			throw exception;
		}

		if(this.hanoi.getHavingDisk() != disk) {
			String message = "手持ちの円盤と違う円盤を置こうとしました。";
			HanoiException exception = new HanoiException(message);
			throw exception;
		}

		this.disks.add(0, disk);
		this.hanoi.addAction(this, Hanoi.ActionMethod.PUSH);
		this.hanoi.setHavingDisk(null);
	}

	/**
	 * 塔の一番上に円盤を置く
	 * @param disk 一番上に置く円盤 (描画時処理用)
	 * @throws HanoiException 大きな円盤を小さな円盤の上に置いたとき
	 */
	void doPush(Disk disk) throws HanoiException {
		Logger logger = Logger.getLogger();
		String log = "円盤設置: Tower(" + this.name + "), Disk(" + disk.getSize() + ")";
		logger.info(log);

		if(this.count() > 0) {
			Disk topDisk = this.disks.get(0);
			if(disk.getSize() >= topDisk.getSize()) {
				String message = "大きな円盤を小さな円盤の上に置くことはできません。(" + disk.toString() + ") "
							   + this.toString();
				HanoiException exception = new HanoiException(message);
				throw exception;
			}
		}

		this.disks.add(0, disk);
		this.hanoi.setHavingDisk(null);;
	}

	/**
	 * 指定の円盤を取得する。
	 * @param index 円盤のインデックス (0 ～)
	 * @return 円盤
	 * @exception HanoiException 無効なインデックスが指定されたとき
	 */
	public Disk getDisk(int index) throws HanoiException {
		if(index < 0 || index >= this.count()) {
			String message =  "無効なインデックスです。 (index=" + index + ") " + this.toString();
			HanoiException exception = new HanoiException(message);
			throw exception;
		}
		return this.disks.get(index);
	}

	/**
	 * 文字列取得
	 * @return 文字列
	 */
	public String toString() {
		List< String > disks = new ArrayList< String >();
		for(Disk disk : this.disks) {
			disks.add(disk.toString());
		}
		String name = this.name + " [" + String.join(",", disks) + "]";
		return name;
	}
}
