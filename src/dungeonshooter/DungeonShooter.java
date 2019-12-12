/*
 * @author: Umang Patel
 * @StudentNumber: 040918355
 * @Assignment 2 
 * 
 * */
package dungeonshooter;

import dungeonshooter.animator.Animator;
import dungeonshooter.entity.Player;
import dungeonshooter.entity.PlayerInput;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utility.InputAdapter;

public class DungeonShooter extends Application {

	private double width = 700;
	private double height = 700;
	private Canvas canvas;
	private BorderPane root;
	private CanvasMap board;
	private PlayerInput input;

	@Override
	public void init() throws Exception {

		canvas = new Canvas();

		InputAdapter adapter;
		input = new PlayerInput(adapter = new InputAdapter(canvas));
		board = new CanvasMap();

		Player player = new Player(width / 2, height / 2, 70, 46);
		player.seInput(input);
		player.setMap(board);

		Animator animator = new Animator();
		animator.setCanvas(board);
		board.setDrawingCanvas(canvas);
		board.setAnimator(animator);
		board.addSampleShapes();
		board.players().add(player);

		ToolBar statusBar = createStatusBar();
		ToolBar optionsBar = createOptionsBar();

		root = new BorderPane();
		root.setTop(optionsBar);
		root.setCenter(board.getCanvas());
		root.setBottom(statusBar);

		board.getCanvas().widthProperty().bind(root.widthProperty());
		board.getCanvas().heightProperty()
				.bind(root.heightProperty().subtract(statusBar.heightProperty()).subtract(optionsBar.heightProperty()));

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// scene holds all JavaFX components that need to be displayed in Stage
		Scene scene = new Scene(root, width, height, Color.LIGHTPINK);
		primaryStage.setScene(scene);
		primaryStage.setTitle("DUNGEON SHOOTER");
		// when escape key is pressed close the application
		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			if (KeyCode.ESCAPE == event.getCode()) {
				primaryStage.hide();
			}
		});
		// display the JavaFX application
		primaryStage.show();
		// select first index of animatorsBox as start,
		// this will also sets the new animator as the lambda we setup will be triggered
		board.start();
	}

	@Override
	public void stop() {
		board.stop();
	}

	public ToolBar createOptionsBar() {
		Button startButton = createButton("Start", a -> board.start());

		Button stopButton = createButton("Stop", a -> board.stop());

		Pane menuBarFiller1 = new Pane();
		Pane menuBarFiller2 = new Pane();
		HBox.setHgrow(menuBarFiller1, Priority.ALWAYS);
		HBox.setHgrow(menuBarFiller2, Priority.ALWAYS);

		MenuButton options = new MenuButton("Options", null,

				createCheckBox("FPS", true, board.drawFPSProperty()),

				createCheckBox("Bounds", false, board.drawBoundsProperty()));

		return new ToolBar(startButton, stopButton, menuBarFiller1, options, menuBarFiller2);
	}

	public ToolBar createStatusBar() {
		Label mouseCoordLabel = new Label("(0,0)");
		Label dragCoordLabel = new Label("(0,0)");

		return new ToolBar(new Label("Mouse: "), mouseCoordLabel, new Label("Drag: "), dragCoordLabel);
	}

	public CheckMenuItem createCheckBox(String text, boolean isSelected, BooleanProperty binding) {
		CheckMenuItem check = new CheckMenuItem(text);
		binding.bind(check.selectedProperty());
		check.setSelected(isSelected);
		return check;
	}

	public Button createButton(String text, EventHandler<ActionEvent> action) {
		Button button = new Button(text);
		button.setOnAction(action);
		return button;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
