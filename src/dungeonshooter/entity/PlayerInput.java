/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter.entity;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utility.InputAdapter;

public class PlayerInput {

	private double x, y;
	private boolean left = false, right = false, up = false, down = false;
	private boolean leftClick = false, rightClick = false, middleClick = false;
	private boolean space = false, shift = false;
	private InputAdapter adapter;

	public PlayerInput(InputAdapter adapter) {
		// TODO Auto-generated constructor stub

		this.adapter = adapter;
		adapter.forceFocusWhenMouseEnters();
		adapter.registerMouseMovment(this::moved, this::dragged);

		adapter.registerMouseClick(this::mousePressed, this::mouseReleased);

		adapter.registerKey(this::keyPressed, this::keyReleased);

	}

	public boolean hasMoved() {

		return left || right || up || down;

	}

	public int leftOrRight() {
		if (right) {
			return 1;
		}
		if (left) {
			return -1;
		}
		return 0;
	}

	public int upOrdown() {
		if (down) {
			return 1;
		}
		if (up) {
			return -1;
		}
		return 0;
	}

	public boolean leftClicked() {
		return leftClick;
	}

	public boolean rightClicked() {
		return rightClick;
	}

	public boolean middleClicked() {
		return middleClick;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}

	protected void mousePressed(MouseEvent e) {

		leftClick = e.isPrimaryButtonDown();
		rightClick = e.isSecondaryButtonDown();
		middleClick = e.isMiddleButtonDown();

	}

	protected void mouseReleased(MouseEvent e) {
		leftClick = false;
		rightClick = false;
		middleClick = false;

	}

	public void changeKeyStatus(KeyCode key, boolean isPressed) {

		switch (key) {
		case W:
			up = isPressed;
			break;

		case A:
			left = isPressed;
			break;

		case S:
			down = isPressed;
			break;

		case D:
			right = isPressed;
			break;

		case SHIFT:
			shift = isPressed;
			break;

		case SPACE:
			space = isPressed;
			break;

		default:

			break;

		}

	}

	protected void keyPressed(KeyEvent key) {
		changeKeyStatus(key.getCode(), true);

	}

	protected void keyReleased(KeyEvent key) {
		changeKeyStatus(key.getCode(), false);

	}

	public boolean isSpace() {
		return space;
	}

	public boolean isShift() {
		return shift;
	}

	protected void moved(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

	protected void dragged(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
	}

}
