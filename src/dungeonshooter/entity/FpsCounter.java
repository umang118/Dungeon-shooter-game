/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utility.Point;

public class FpsCounter implements Entity {

	public static final double ONE_SECOND = 1000000000L;
	public static final double HALF_SECOND = ONE_SECOND / 2F;
	private Font fpsFont;
	private String fpsDisplay;
	private int frameCount;
	private double lastTime;
	private Point pos;
	private HitBox hitbox;

	private Sprite sprite;

	public FpsCounter(double x, double y) {
		sprite = new Sprite() {

			@Override
			public void draw(GraphicsContext gc) {
				Font temp = gc.getFont();
				gc.setFont(fpsFont);
				gc.setFill(getFill());
				gc.fillText(fpsDisplay, pos.x(), pos.y());
				gc.setStroke(getStroke());
				gc.setLineWidth(getWidth());
				gc.strokeText(fpsDisplay, pos.x(), pos.y());
				gc.setFont(temp);
			}
		};
		pos = new Point();
		setPos(x, y).setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24));
	}

	public void calculateFPS(long now) {
		if (now - lastTime >= HALF_SECOND) {
			fpsDisplay = Integer.toString(frameCount * 2);
			frameCount = 0;
			lastTime = now;
		}
		frameCount++;
	}

	public FpsCounter setFont(Font font) {
		fpsFont = font;
		return this;
	}

	public FpsCounter setPos(double x, double y) {
		pos.set(x, y);
		return this;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public Drawable<?> getDrawable() {
		return sprite;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hasHitBox() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HitBox getHitBox() {
		// TODO Auto-generated method stub
		return hitbox;
	}
}
