package com.design_shinbi.hanoi.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.TextArea;

/**
 * ログ記述クラス
 */
public class Logger {
	private TextArea textArea;
	private static Logger instance;

	/**
	 * コンストラクター
	 * @param textArea ログ用テキストエリア
	 */
	public Logger(TextArea textArea) {
		this.textArea = textArea;
		Logger.instance = this;
	}

	/**
	 * エラー表示
	 * @param message エラーメッセージ
	 */
	public void error(String message) {
		this.addText("ERROR", message);
	}

	/**
	 * 警告表示
	 * @param message 警告メッセージ
	 */
	public void warning(String message) {
		this.addText("WARN",  message);
	}

	/**
	 * 情報表示
	 * @param message 情報メッセージ
	 */
	public void info(String message) {
		this.addText("INFO", message);
	}


	/**
	 * デバッグ情報表示
	 * @param message デバッグメッセージ
	 */
	public void debug(String message) {
		this.addText("DEBUG", message);
	}

	/**
	 * トレース情報表示
	 * @param message トレースメッセージ
	 */
	public void trace(String message) {
		this.addText("TRACE", message);
	}

	/**
	 * ログ追加
	 * @param level ログレベル
	 * @param message ログメッセージ
	 */
	private void addText(String level, String message) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = new Date(System.currentTimeMillis());
		String line = String.format("[%s] %s - %s\n", level, format.format(now), message);

		int length = this.textArea.getLength();
		if(length == 0) {
			textArea.setText(line);
		}
		else {
			textArea.selectEnd();
			textArea.insertText(length, line);
		}
	}

	/**
	 * ロガー取得
	 * @return ロガー
	 */
	public static Logger getLogger() {
		return Logger.instance;
	}
}
