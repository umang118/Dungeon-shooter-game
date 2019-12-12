/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.entity;

import java.util.ArrayList;
import java.util.List;

import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import utility.Point;
import utility.RandUtil;

public class PolyShape implements Entity {

	private int pointCount;
	private double[][] points;

	private double minX, minY, maxX, maxY;
	private HitBox hitbox;
	private Sprite sprite;

	public PolyShape() {
		// TODO Auto-generated constructor stub
		hitbox = new HitBox() {
			@Override
			protected boolean hasIntersectFull() {
				return true;
			}

			@Override
			protected double[][] getPoints() {
				return points;
			}
		};
		sprite = new Sprite() {
			{
				setFill(new ImagePattern(new Image("file:assets/concrete/dsc_1621.png")));
			}

			@Override
			public void draw(GraphicsContext gc) {
				gc.setLineWidth(getWidth());
				if (getStroke() != null) {
					gc.setStroke(getStroke());
					gc.strokePolygon(points[0], points[1], pointCount);
				}
				if (getFill() != null) {
					gc.setFill(getFill());
					gc.fillPolygon(points[0], points[1], pointCount);
				}
			}
		};
	}

	/**
	 * 
	 * Copied from your given lab 3 solution
	 * 
	 * 
	 * create a random shape at a given (x,y) with a range of sides and max size.
	 * 
	 * @param centerX   - initial center in x direction which random point are
	 *                  generated around
	 * @param centerY   - initial center in y direction which random point are
	 *                  generated around
	 * @param size      - max size (radius) which points can generate from center
	 * @param minPoints - inclusive, min number of sides in this shape
	 * @param maxPoints - exclusive, max number of points in this shape
	 * @return current instance of this object
	 */
	public PolyShape randomize(double centerX, double centerY, double size, int minPoints, int maxPoints) {
		pointCount = RandUtil.getInt(minPoints, maxPoints);

		points = new double[2][pointCount];

		final Point center = new Point(centerX, centerY);

		List<Point> unsortedPoints = new ArrayList<>(pointCount);

		centerX = 0;

		centerY = 0;
		// create random points using the Point class

		for (int i = 0; i < pointCount; i++) {

			Point randP = center.randomPoint(size);

			unsortedPoints.add(randP);

			// keep the total of all x and y values to calculate the new center

			centerX += randP.x();

			centerY += randP.y();
		}
		// set the new center
		center.set(centerX / pointCount, centerY / pointCount);
		// sort all the points based on their angular relationship to center
		// this prevents the lines from crossing each other.
		unsortedPoints.sort((p1, p2) -> center.angle(p1) > center.angle(p2) ? 1 : -1);
		int i = 0;
		// set the min and max of x to first index of unsortedPoints
		// set the min and max of y to first index of unsortedPoints
		minX = maxX = unsortedPoints.get(0).x();
		minY = maxY = unsortedPoints.get(0).y();
		// store all points in the points array
		for (Point p : unsortedPoints) {
			// check each point for potential min and max
			updateMinMax(p.x(), p.y());
			points[0][i] = p.x();
			points[1][i] = p.y();
			i++;
		}
		hitbox.setBounds(minX, minY, maxX - minX, maxY - minY);
		return this;
	}

	/**
	 * <b>Copied from your given lab 3 solution</b>
	 * 
	 * set the current points with the given points
	 * 
	 * @param nums - points in format of x1,y1,x2,y2,x3,y3,...
	 * @return current instance of this object
	 */

	public PolyShape setPoints(double... nums) {
		minX = maxX = nums[0];
		minY = maxY = nums[1];
		pointCount = nums.length / 2;
		points = new double[2][pointCount];

		for (int i = 0, j = 0; i < nums.length; i += 2, j++) {
			updateMinMax(nums[i], nums[i + 1]);
			points[0][j] = nums[i];
			points[1][j] = nums[i + 1];

		}
		hitbox.setBounds(minX, minY, maxX - minX, maxY - minY);

		return this;

	}

	private void updateMinMax(double x, double y) {
		if (x < minX)
			minX = x;
		else if (x > maxX)
			maxX = x;
		if (y < minY)
			minY = y;
		else if (y > maxY)
			maxY = y;
	}

	public int pointCount() {
		return pointCount;
	}

	public double[][] points() {
		return points;
	}

	public double pX(int index) {
		return points[0][index];
	}

	public double pY(int index) {
		return points[1][index];
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
		return hitbox;
	}

	@Override
	public boolean isDrawable() {
		return true;
	}

	@Override
	public Sprite getDrawable() {
		return sprite;
	}

}
