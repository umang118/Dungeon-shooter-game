/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.entity;

import dungeonshooter.entity.geometry.RectangleBounds;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet implements Entity {

	private static final Image BULLET = new Image("file:assets\\bullet\\b_3.png");
	private double angel;
	private Sprite sprite;
	private HitBox hitbox;

	public Bullet(double angel, double x, double y) {

		this(angel, x, y, 6, 6);
	}

	public Bullet(double angel, double x, double y, double w, double h) {

		this.angel = angel;
		hitbox = new HitBox();
		hitbox.setBounds(x, y, w, h);

		sprite = new Sprite() {
			private RectangleBounds bounds = hitbox.getBound();

			@Override
			public void draw(GraphicsContext gc) {
				// TODO Auto-generated method stub
				gc.drawImage(BULLET, bounds.x(), bounds.y(), bounds.w(), bounds.h());

			}
		};

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		double x = Math.cos(Math.toRadians(angel)) * 7;
		double y = Math.sin(Math.toRadians(angel)) * 7;
		hitbox.translate(x, y);
	}

	@Override
	public boolean hasHitBox() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public HitBox getHitBox() {
		// TODO Auto-generated method stub
		return hitbox;
	}

	@Override
	public boolean isDrawable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Sprite getDrawable() {
		// TODO Auto-generated method stub
		return sprite;
	}

	@Override
	public String toString() {
		return "BULLET";
	}

}
