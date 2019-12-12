/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.animator;

import java.util.Iterator;

import dungeonshooter.entity.Bullet;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.Player;
import dungeonshooter.entity.PolyShape;
import dungeonshooter.entity.property.HitBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animator extends AbstractAnimator {

	private Color background = Color.ANTIQUEWHITE;

	@Override
	protected void handle(GraphicsContext gc, long now) {
		// TODO Auto-generated method stub
		updateEntities();
		clearAndFill(gc, background);
		drawEntities(gc);
	}

	public void updateEntities() {
		map.updateProjectileslist();
//		map.players();
//		map.projectiles();
//		map.staticShapes();

		for (Entity t : map.projectiles()) {
			t.update();

		}
		for (Entity t : map.players()) {
			t.update();

		}
		for (PolyShape t : map.staticShapes()) {
			t.update();

		}

		if (map.getDrawBounds()) {
			for (Entity e : map.projectiles()) {
				e.getHitBox().getDrawable().setStroke(Color.RED);

			}

			for (Entity e : map.players()) {
				e.getHitBox().getDrawable().setStroke(Color.RED);

			}
		}

		for (PolyShape e : map.staticShapes()) {
			processEntity(map.projectiles().iterator(), e.getHitBox());
			processEntity(map.players().iterator(), e.getHitBox());

		}

	}

	public void processEntity(Iterator<Entity> iterator, HitBox shapeHitBox) {

		while (iterator.hasNext()) {
			Entity en = iterator.next();
			HitBox bounds = en.getHitBox();

			if (!map.inMap(bounds)) {
				if (en instanceof Player) {
					((Player) en).stepBack();

				} else if (en instanceof Bullet) {
					iterator.remove();
				}

			} else if (shapeHitBox.intersectBound(bounds)) {
				if (map.getDrawBounds()) {
					bounds.getDrawable().setStroke(Color.BLUEVIOLET);

				}

				if (shapeHitBox.intersectFull(bounds)) {
					if (en instanceof Player) {
						((Player) en).stepBack();

					} else if (en instanceof Bullet) {
						iterator.remove();
					}
				}
			}

		}

	}

}
