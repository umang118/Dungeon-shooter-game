/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;

public interface Entity {

	public void update();

	public boolean hasHitBox();

	public HitBox getHitBox();

	public boolean isDrawable();

	public Drawable<?> getDrawable();

}
