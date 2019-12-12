/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.animator;

import java.util.function.Consumer;

import dungeonshooter.CanvasMap;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.FpsCounter;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.Point;

public abstract class AbstractAnimator extends AnimationTimer {
	protected Point mouse;
	protected CanvasMap map;
	public FpsCounter fps;

	public AbstractAnimator() {
		// TODO Auto-generated constructor stub
		mouse = new Point();
		map = new CanvasMap();
		fps = new FpsCounter(10, 25);
		fps.getDrawable().setFill(Color.BLACK).setStroke(Color.WHITE).setWidth(1);

	}

	public void setCanvas(CanvasMap map) {
		this.map = map;
	}

	public void clearAndFill(GraphicsContext gc, Color background) {
		gc.setFill(background);
		gc.clearRect(0, 0, map.w(), map.h());
		gc.fillRect(0, 0, map.w(), map.h());
	}

	public void drawEntities(GraphicsContext gc) {
		Consumer<Entity> draw = new Consumer<Entity>() {

			@Override
			public void accept(Entity t) {
				// TODO Auto-generated method stub

				if (t.isDrawable()) {
					t.getDrawable().draw(gc);
					if (map.getDrawBounds()) {
						t.getHitBox().getDrawable().draw(gc);
					}

				}

			}
		};

		draw.accept(map.getMapShape());

		for (Entity e : map.staticShapes()) {
			draw.accept(e);
		}

		for (Entity e : map.projectiles()) {
			draw.accept(e);
		}

		for (Entity e : map.players()) {
			draw.accept(e);
		}

	}

	@Override
	public void handle(long now) {
		// TODO Auto-generated method stub
		GraphicsContext gc = map.gc();
		if (map.getDrawFPS())
			fps.calculateFPS(now);
		handle(gc, now);

		if (map.getDrawFPS())
			fps.getDrawable().draw(gc);

	}

	protected abstract void handle(GraphicsContext gc, long now);

}
