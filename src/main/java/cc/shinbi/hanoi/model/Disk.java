package cc.shinbi.hanoi.model;

/**
 * 円盤クラス
 */
public class Disk {
	/**
	 * コンストラクタ
	 * @param size 円盤サイズ
	 */
	Disk(int size) {
		this.size = size;
	}

	/** 円盤サイズ */
	private int size;

	/**
	 * 円盤サイズ取得
	 * @return 円盤サイズ
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 文字列変換
	 */
	public String toString() {
		String name = "Disk (size=" + this.size + ")";
		return name;
	}

}
