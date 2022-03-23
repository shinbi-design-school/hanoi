package cc.shinbi.hanoi.view;

import java.util.Arrays;
import java.util.List;

import cc.shinbi.hanoi.Const;
import cc.shinbi.hanoi.model.Disk;
import cc.shinbi.hanoi.model.Hanoi;
import cc.shinbi.hanoi.model.Tower;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * ハノイの塔描画用キャンバス
 */
public class HanoiCanvas extends Canvas {
	private Hanoi hanoi;
	private List< Color > colors;
	private Timeline timeline;

	/**
	 * コンストラクタ
	 */
	public HanoiCanvas() {
		this.hanoi = new Hanoi();

		this.colors = Arrays.asList(
				null,
				Const.DISK_COLOR_1,
				Const.DISK_COLOR_2,
				Const.DISK_COLOR_3,
				Const.DISK_COLOR_4,
				Const.DISK_COLOR_5,
				Const.DISK_COLOR_6,
				Const.DISK_COLOR_7,
				Const.DISK_COLOR_8,
				Const.DISK_COLOR_9,
				Const.DISK_COLOR_10
		);

		this.timeline = null;
	}

	/**
	 * ハノイの塔 オブジェクト取得
	 * @return ハノイの塔オブジェクト
	 */
	public Hanoi getHanoi() {
		return this.hanoi;
	}


	/**
	 * ハノイの塔初期化
	 * @param height ハノイの塔の高さ
	 */
	public void initialize(int height) {
		this.hanoi.prepare(height);
		this.draw();
	}

	/**
	 * 描画開始
	 */
	public void startDraw(String errorMessage) {
		HanoiCanvas me = this;
		Hanoi hanoi = this.getHanoi();

		this.stopDraw(null);

		this.timeline = new Timeline(
			new KeyFrame(
				Duration.millis((double)Const.SLEEP_TIME / 2.0),
				(event) -> {
					me.draw();
					if(hanoi.isEmptyAction()) {
						me.stopDraw(errorMessage);
					}
					else {
						try {
							hanoi.step();
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				}
			)
		);
		this.timeline.setCycleCount(Timeline.INDEFINITE);
		this.timeline.play();
	}

	/**
	 * 描画停止
	 */
	public void stopDraw(String errorMessage) {
		if(this.timeline != null) {
			this.timeline.stop();
			this.timeline = null;
			if(errorMessage != null) {
				Logger logger = Logger.getLogger();
				logger.error(errorMessage);
			}
		}
	}

	/**
	 * 描画更新
	 */
	public void update() {
		this.draw();
	}

	/**
	 * ハノイの塔 描画
	 */
	private void draw() {
		int width = (int)Math.round(this.getWidth());
		int height = (int)Math.round(this.getHeight());
		int towerWidth = (width - 2 * Const.MARGIN - 2 * Const.GAP) / 3;

		GraphicsContext g = this.getGraphicsContext2D();
		g.clearRect(0.0, 0.0, (double)width * 2.0, (double)height * 2.0);

		int position = Const.MARGIN;
		Tower[] towers = { this.hanoi.getLeft(), this.hanoi.getMiddle(), this.hanoi.getRight() };

		for(Tower tower : towers) {
			Disk havingDisk = null;
			if(tower == this.hanoi.getMiddle()) {
				havingDisk = this.hanoi.getHavingDisk();
			}

			this.drawTower(
				g,
				tower,
				position,
				Const.MARGIN,
				position + towerWidth,
				height - Const.MARGIN,
				havingDisk
			);

			position += towerWidth + Const.GAP;
		}

		this.drawBaseLine(g,  Const.MARGIN, width  - Const.MARGIN, height - Const.MARGIN);
	}

	/**
	 * 地面描画
	 * @param g 描画オブジェクト
	 * @param left 左側座標
	 * @param right 右側座標
	 * @param bottom 下側座標
	 */
	private void drawBaseLine(GraphicsContext g, int left, int right, int bottom) {
		g.setStroke(Color.BLACK);
		g.strokeLine((double)left,  (double)bottom, (double)right,  (double)bottom);
	}

	/**
	 * 塔の描画
	 * @param tower 塔オブジェクト
	 * @param left 左側座標
	 * @param top 上側座標
	 * @param right 右側座標
	 * @param bottom 下側座標
	 */
	private void drawTower(
			GraphicsContext g,
			Tower tower,
			int left,
			int top,
			int right,
			int bottom,
			Disk havingDisk
	) {
		int diskHeight = (bottom - top) / 15;
		int diskWidthUnit = (right - left) / 15;
		int diskWidthBase = 5 * diskWidthUnit;
		int middle = (left + right) / 2;
		int yPosition = bottom - diskHeight;

		int poleWidth = diskWidthBase / 4;
		int poleLeft = middle - poleWidth / 2;
		int poleHeight = diskHeight * 11;
		int poleTop = bottom - poleHeight;

		g.setFill(Color.BLACK);
		g.fillRect((double)poleLeft,  (double)poleTop,  (double)poleWidth,  (double)poleHeight);

		try {
			for(int i = tower.count() - 1; i >= 0; i--) {
				Disk disk = tower.getDisk(i);
				this.drawDisk(g, middle, yPosition, disk, diskWidthBase, diskWidthUnit, diskHeight);
				yPosition -= diskHeight;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		if(havingDisk != null) {
			this.drawDisk(g,  middle,  top, havingDisk,  diskWidthBase,  diskWidthUnit,  diskHeight);
		}
	}

	/**
	 * 円盤描画
	 * @param g 描画オブジェクト
	 * @param xPosition
	 * @param yPosition
	 * @param disk
	 * @param diskWidthBase
	 * @param diskWidthUnit
	 * @param diskHeight
	 */
	private void drawDisk(
			GraphicsContext g,
			int xPosition,
			int yPosition,
			Disk disk,
			int diskWidthBase,
			int diskWidthUnit,
			int diskHeight) {
		int size = disk.getSize();
		Color color = this.colors.get(size);
		int width = diskWidthBase + diskWidthUnit * size;
		int left = xPosition - width / 2;

		g.setStroke(Color.BLACK);
		g.setFill(color);
		g.strokeRect((double)left,  (double)yPosition, (double)width,  (double)diskHeight);
		g.fillRect((double)left,  (double)yPosition, (double)width,  (double)diskHeight);
	}
}
