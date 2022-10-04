package com.design_shinbi.hanoi;

import com.design_shinbi.hanoi.view.MainFrame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * メインクラス
 */
public class HanoiApplication extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(MainFrame.class.getResource("MainFrame.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("ハノイの塔");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
