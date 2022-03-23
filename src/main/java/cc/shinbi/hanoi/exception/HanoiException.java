package cc.shinbi.hanoi.exception;

/**
 * ハノイの塔 例外 (大きい円盤を小さな円盤の上に置いた時など)
 */
public class HanoiException extends Exception {
	/**
	 * コンストラクタ
	 * @param message 例外メッセージ
	 */
	public HanoiException(String message) {
		super(message);
	}

}
