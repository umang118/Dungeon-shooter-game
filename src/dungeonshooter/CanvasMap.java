/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter;

import java.util.ArrayList;
import java.util.List;

import dungeonshooter.animator.Animator;
import dungeonshooter.entity.Bullet;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.PolyShape;
import dungeonshooter.entity.property.HitBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class CanvasMap {
	private Canvas map;
	private BooleanProperty drawBounds;
	private BooleanProperty drawFPS;
	private List<Entity> players;
	private List<Entity> projectiles;
	private PolyShape border;
	private List<Entity> buffer;
	private Animator animator;
	private List<PolyShape> staticShapes;

	public CanvasMap() {
		// TODO Auto-generated constructor stub
		drawBounds = new SimpleBooleanProperty(true);
		drawFPS = new SimpleBooleanProperty(false);
		players = new ArrayList<Entity>(1);
		projectiles = new ArrayList<Entity>(500);
		buffer = new ArrayList<Entity>(500);
		staticShapes = new ArrayList<PolyShape>(50);
		border = new PolyShape();
		border.getDrawable().setFill(new ImagePattern(new Image("file:assets/floor/pavin.png"), 0, 0, 256, 256, false));

	}

	public BooleanProperty drawFPSProperty() {
		return drawFPS;

	}

	public boolean getDrawFPS() {
		return drawFPS.get();

	}

	public BooleanProperty drawBoundsProperty() {
		return drawBounds;

	}

	public boolean getDrawBounds() {
		return drawBounds.get();

	}

	public CanvasMap setDrawingCanvas(Canvas map) {

		if (map != null) {
			this.map = map;
			this.map.widthProperty().addListener((obs, oldVal, newVal) -> {
				border.setPoints(0, 0, w(), 0, w(), h(), 0, h());
			});
			this.map.heightProperty().addListener((obs, oldVal, newVal) -> {
				border.setPoints(0, 0, w(), 0, w(), h(), 0, h());
			});
			border.setPoints(0, 0, w(), 0, w(), h(), 0, h());
			return this;

		} else {
			throw new NullPointerException("Canvas not Exist");
		}

	}

	public CanvasMap setAnimator(Animator newAnimator) {
		if (animator != null) {
			stop();

		}
		animator = newAnimator;

		return this;

	}

	public void start() {
		animator.start();
	}

	public void stop() {
		animator.stop();
	}

	public Canvas getCanvas() {
		return map;
	}

	/**
	 * create a method called gc. get the {@link GraphicsContext} of {@link Canvas}
	 * that allows direct drawing.
	 * 
	 * @return {@link GraphicsContext} of {@link Canvas}
	 */
	public GraphicsContext gc() {
		return map.getGraphicsContext2D();
	}

	/**
	 * create a method called w. get the height of the map,
	 * {@link Canvas#getHeight()}
	 * 
	 * @return height of canvas
	 */

	public double h() {
		return map.getHeight();
	}

	/**
	 * create a method called w. get the width of the map, {@link Canvas#getWidth()}
	 * 
	 * @return width of canvas
	 */
	public double w() {
		return map.getWidth();
	}

	public List<PolyShape> staticShapes() {
		return staticShapes;

	}

	public List<Entity> players() {
		return players;
	}

	public List<Entity> projectiles() {

		return projectiles;
	}

	public CanvasMap addSampleShapes() {
		PolyShape s = new PolyShape().setPoints(100, 100, 200, 100, 200, 200, 100, 200);
		s.getDrawable().setStroke(Color.BLACK).setWidth(1.5);
		staticShapes.add(s);
		s = new PolyShape().setPoints(100, 100, 200, 100, 200, 200, 100, 200);
		s.getDrawable().setStroke(Color.BLACK).setWidth(1.5);
		staticShapes.add(s);
		s = new PolyShape().randomize(350, 150, 100, 3, 10);
		s.getDrawable().setStroke(Color.BLACK).setWidth(1.5);
		staticShapes.add(s);
		s = new PolyShape().setPoints(600, 100, 500, 100, 450, 200, 550, 200);
		s.getDrawable().setStroke(Color.BLACK).setWidth(1.5);
		staticShapes.add(s);
		s = new PolyShape().randomize(550, 350, 100, 3, 10);
		s.getDrawable().setStroke(Color.BLACK).setWidth(1.5);
		staticShapes.add(s);

		s = new PolyShape().randomize(350, 550, 100, 3, 10);
		s.getDrawable().setStroke(Color.BLACK).setWidth(1.5);
		staticShapes.add(s);
		s = new PolyShape().setPoints(100, 600, 200, 600, 250, 500, 150, 500);
		s.getDrawable().setStroke(Color.BLACK).setWidth(1.5);
		staticShapes.add(s);

		return this;
	}

	public void fireBullet(Bullet bullet) {
		// TODO Auto-generated method stub
		buffer.add(bullet);
		updateProjectileslist();

	}

	public void updateProjectileslist() {
		projectiles.addAll(buffer);
		buffer.clear();

	}

	public PolyShape getMapShape() {

		return border;
	}

	public boolean inMap(HitBox hitbox) {
		return border.getHitBox().containBounds(hitbox);
	}

}