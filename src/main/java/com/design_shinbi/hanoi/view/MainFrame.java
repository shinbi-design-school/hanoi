package com.design_shinbi.hanoi.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.design_shinbi.hanoi.Const;
import com.design_shinbi.hanoi.Solution;
import com.design_shinbi.hanoi.model.Hanoi;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * メインフレーム クラス
 */
public class MainFrame implements Initializable {
	/** 描画キャンバス */
	private HanoiCanvas canvas;

	/** ログ表示用 テキストエリア */
	@FXML
	private TextArea logText;

	/** メインフレーム レイアウト */
	@FXML
	private BorderPane pane;

	/** キャンバス 領域 */
	@FXML
	private BorderPane canvasPane;

	/** 高さ選択 コンボボックス */
	@FXML
	private  ComboBox< Integer > heightCombo;

	/** スタート ボタン */
	@FXML
	private Button startButton;

	/**
	 * 高さを変えた時の処理
	 * @param event イベント情報
	 */
	@FXML
	private void onChangeHeight(ActionEvent event) {
		this.canvas.initialize(this.heightCombo.getValue());
		this.canvas.update();
		this.startButton.setText("Start");
	}

	/**
	 * スタートボタンをクリックしたときの処理
	 * @param event イベント情報
	 */
	@FXML
	private void onStart(ActionEvent event) {
		Logger logger = Logger.getLogger();

		String name = this.startButton.getText();
		if(name.equalsIgnoreCase("Reset")) {
			this.canvas.initialize(this.heightCombo.getValue());
			this.startButton.setText("Start");
		}
		else {
			this.logText.setText("");
			String errorMessage = null;

			Solution solution = new Solution();
			Hanoi hanoi = this.canvas.getHanoi();
			try {
				solution.solve(hanoi.getLeft(), hanoi.getMiddle(), hanoi.getRight(), logger);
			}
			catch(Exception e) {
				errorMessage = e.getMessage();
			}
			this.canvas.getHanoi().prepareDraw();
			this.canvas.startDraw(errorMessage);

			this.startButton.setText("Reset");
		}
	}

	/**
	 * 閉じるボタンをクリックしたときの処理
	 * @param event イベント情報
	 */
	@FXML
	private void onClose(ActionEvent event) {
		Node node = (Node)event.getSource();
		Scene scene = node.getScene();
		Stage stage = (Stage)scene.getWindow();
		stage.close();
	}

	/**
	 * 初期化
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int height = 1; height <= 10; height++) {
			this.heightCombo.getItems().add(height);
		}
		this.heightCombo.setValue(Const.DEFAULT_HEIGHT);

		this.canvas = new HanoiCanvas();
		this.canvasPane.setCenter(this.canvas);
		this.canvas.initialize(Const.DEFAULT_HEIGHT);

		MainFrame me = this;

		ChangeListener< Number > listener = (observable, oldValue, newValue) -> {
			me.canvas.update();
		};
		this.canvas.widthProperty().addListener(listener);
		this.canvas.heightProperty().addListener(listener);
		this.canvas.widthProperty().bind(this.canvasPane.widthProperty());
		this.canvas.heightProperty().bind(this.canvasPane.heightProperty());

		new Logger(this.logText);
		this.logText.setText("高さを選んで [Start] ボタンをクリックしてください。");
	}

}
