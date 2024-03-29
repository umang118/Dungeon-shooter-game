/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.entity.property;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

/**
 * <p>
 * Sprite represents what to be drawn during the canvas drawing stage. this
 * class holds information such as {@link Sprite#fill}, {@link Sprite#stroke}
 * and {@link Sprite#strokeWidth}. this class is abstract, meaning it must be
 * inherited and draw method overridden. fill and stroke values in this class
 * are of type {@link Paint}. this allows the user to set any color to the
 * sprite or using the {@link ImagePattern} class choose an image asset and set
 * it as filling.
 * </p>
 * 
 * ex: sprite for polyshape<br>
 * <code><pre>
    sprite = new Sprite(){
        {
            setFill( new ImagePattern( new Image( "file:assets/concrete/dsc_1621.png")));
        }

        public void draw( GraphicsContext gc){
            gc.setLineWidth( getWidth());
            if( getStroke() != null){
                gc.setStroke( getStroke());
                gc.strokePolygon( points[0], points[1], pointCount);
            }
            if( getFill() != null){
                gc.setFill( getFill());
                gc.fillPolygon( points[0], points[1], pointCount);
            }
        }
    };
 * </Pre></code>
 * 
 * @author Shahriar (Shawn) Emami
 * @version Mar 11, 2019
 */
public abstract class Sprite implements Drawable<Sprite> {

	private Paint fill, stroke;
	private double strokeWidth;

	@Override
	public Sprite setFill(Paint paint) {
		// TODO Auto-generated method stub
		fill = paint;
		return this;
	}

	@Override
	public Sprite setStroke(Paint paint) {
		stroke = paint;
		return this;
	}

	@Override
	public Sprite setWidth(double width) {
		// TODO Auto-generated method stub
		this.strokeWidth = width;
		return this;
	}

	@Override
	public Paint getFill() {
		// TODO Auto-generated method stub
		return fill;
	}

	@Override
	public Paint getStroke() {
		// TODO Auto-generated method stub
		return stroke;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return strokeWidth;
	}

	@Override
	public abstract void draw(GraphicsContext gc);
	// TODO Auto-generated method s
	// implement all methods from inherited interface

}
