/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.entity.property;

import dungeonshooter.entity.Entity;
import dungeonshooter.entity.geometry.RectangleBounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utility.IntersectUtil;
import utility.Point;

public class HitBox implements Entity {

	private Point prev;
	private RectangleBounds bounds;
	private Sprite sprite;
	private double[][] points;
	private double[] result;

	public HitBox() {
		// TODO Auto-generated constructor stub
		bounds = new RectangleBounds();
		points = new double[2][4];
		result = new double[4];
		prev = new Point();
		sprite = new Sprite() {

			@Override
			public void draw(GraphicsContext gc) {
				// TODO Auto-generated method stub
				gc.setStroke(getStroke());
				gc.setLineWidth(getWidth());
				gc.strokeRect(bounds.x(), bounds.y(), bounds.w(), bounds.h());

			}
		};
		sprite.setStroke(Color.RED).setWidth(3);
	}

	public HitBox setBounds(RectangleBounds bounds) {
		this.bounds = bounds;
		return this;

	}

	public HitBox setBounds(double x, double y, double w, double h) {
		bounds = new RectangleBounds(x, y, w, h);
		setBounds(bounds);
		return this;

	}

	public HitBox translate(double dx, double dy) {
		prev.move(bounds.startPos());
		bounds.translate(dx, dy);
		return this;

	}

	public HitBox UndoTranslate() {
		bounds.move(prev);
		return this;

	}

	public boolean containBounds(HitBox box) {

		return bounds.contains(box.getBound());

	}

	public boolean intersectBound(HitBox box) {
		return bounds.intersects(box.getBound());
	}

	public boolean intersectFull(HitBox box) {
		return intersectFull(box.getPoints());

	}

	protected boolean intersectFull(double[][] otherPoints) {
		points = getPoints();
		for (int i = 0, j = points[0].length - 1; i < points[0].length; i++, j = i - 1) {
			for (int m = 0, n = otherPoints[0].length - 1; m < otherPoints[0].length; m++, n = m - 1) {
				boolean intersect = IntersectUtil.getIntersection(result, points[0][i], points[1][i], points[0][j],
						points[1][j], points[0][m], points[1][m], points[0][n], points[1][n]);

				if (intersect && result[2] <= 1) {
					return true;
				}

			}

		}
		return false;

	}

	protected boolean hasIntersectFull() {
		return false;
	}

	protected double[][] getPoints() {

		return bounds.toArray(points);

	}

	@Override
	public void update() {

	}

	@Override
	public boolean hasHitBox() {
		return true;

	}

	@Override
	public HitBox getHitBox() {
		return this;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public Sprite getDrawable() {
		return sprite;
	}

	@Override
	public String toString() {

		return "HitBox";

	}

	public RectangleBounds getBound() {

		return bounds;

	}

	public Point getPrev() {
		return prev;
	}

}
