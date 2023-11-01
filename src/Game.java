import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The JavaFX GUI entrypoint of the Wordament game
 * 
 * @author Bill Xiang
 */
public class Game extends Application {
	/**
	 * <PRE>
	 * The model instance variable was declared so the GridModel of the Game Board could be created.
	 * </PRE>
	 */
	private GridModel<Integer> model;
	/**
	 * <PRE>
	 * A BorderPane rootNode was declared so a pane could be used for JavaFX
	 * </PRE>
	 */
	BorderPane rootNode;
	/**
	 * <PRE>
	 * A Board variable, view was made so the letters could be set using the model.
	 * </PRE>
	 */
	private Board view;
	//HashSet<KeyCode> hashSet;
	/**
	 * <PRE>
	 * A scene variable was declared so JavaFX could set scene.
	 * </PRE>
	 */
	Scene scene;
	/**
	 * <PRE>
	 * The index variable was initialized to zero to change the model variable.
	 * </PRE>
	 */
	private int index = 0;
	/**
	 * <PRE>
	 * The beginner MenuItem sets the level to an easy level that players can click onto to play
	 * </PRE>
	 */
	MenuItem beginner;
	/**
	 * <PRE>
	 * The intermediate MenuItem sets the level to an intermediate level that players can click onto to play.
	 * </PRE>
	 */
	MenuItem intermediate;
	/**
	 * <PRE>
	 * The hard MenuItem sets the level to a hard level that players can click onto to play.
	 * </PRE>
	 */
	MenuItem hard;
	/**
	 * <PRE>
	 * The nextLevel MenuItem sets the level to the next level that players can click onto to play.
	 * </PRE>
	 */
	MenuItem nextLevel;
	/**
	 * <PRE>
	 * The howto MenuItem provides an explanation for how to play the game with a given set of instructions
	 * to use commands and other basic UI. Though the game is simple, it is extrmely hard to design.
	 * </PRE>
	 */
	MenuItem howto;
	/**
	 * <PRE>
	 * The aboiut MenuItem lists the authors of the game, Bill Xiang, Colin Zhou, and Owen GUan
	 * </PRE>
	 */
	MenuItem about;
	/**
	 * <PRE>
	 * The exit MenuItem quits the JavaFX terminal window.
	 * </PRE>
	 */
	MenuItem exit;

	//private ArrayList<String> words = new ArrayList<String>();
	//private Score score;
	//private int time = 3600000;
	/**
	 * <PRE>
	 * The String s is an empty string that keeps track of the letters added to the string and determines
	 * the validity of the word and whether the user scores points.
	 * </PRE>
	 */
	private String s = "";
	/**
	 * <PRE>
	 * The totalSeconds variable provides the amount of time, 2 minutes that the user starts out with. It
	 * keeps ticking down until it reaches 0.
	 * </PRE>
	 */
	private int totalSeconds = 120;
	/**
	 * <PRE>
	 * The previousTime variable keeps track of the time before a second has passed and uses it to decrement
	 * the totalSeconds variable.
	 * </PRE>
	 */
	private long previousTime = 0;
	/**
	 * <PRE>
	 * The long, delay is the amount of nanoseconds in one second and is used to determine when the now
	 * parameter in a handle method has reached one second.
	 * </PRE>
	 */
	private long delay = (long)1e9;
	/**
	 * <PRE>
	 * The int time keeps track of the total number of nanoseconds need in order for 2 minutes to have passed
	 * </PRE>
	 */
	private int time = 2 * 6 * (int)10e10;
	/**
	 * <PRE>
	 * The int minutes is the number that is displayed onto the JavaFX when the timer starts and decrements
	 * when 60 seconds has passed.
	 * </PRE>
	 */
	private int minutes = 2;
	/**
	 * <PRE>
	 *The int seconds is the number that is displayed onto the JavaFX when the timer starts and decrements
	 * when 1 second has passed.
	 * </PRE>
	 */
	private int seconds = 0;
	/**
	 * <PRE>
	 * The totalSeconds variable provides the amount of time, 2 minutes that the user starts out with. It
	 * keeps ticking down until it reaches 0.
	 * </PRE>
	 */
	private String string = "";
	
//	private Timeline timeline;
	/**
	 * <PRE>
	 *The words ArrayList keeps track of all words a player has used within a round.
	 * </PRE>
	 */
	private static ArrayList<String> words = new ArrayList<String>();
	/**
	 * <PRE>
	 * The final int TILE_SIZE variable sets the width of each JavaFX tile as 100 pixels long.
	 * </PRE>
	 */
	final static int TILE_SIZE = 100;
	/**
	 * <PRE>
	 * The Score score variable keeps track of the score and is used to display the points earned in the game.
	 * </PRE>
	 */
	private static Score score = null;
	/**
	 * <PRE>
	 * The current text sets the text at the bottom to display whatever combination of letters were being
	 * formulated by the player.
	 * </PRE>
	 */
	Text current;
	/**
	 * <PRE>
	 * The AnimationTimer timer kept track of the time and was used to handle any changes that occurred
	 * while the game is being played.
	 * </PRE>
	 */
	private AnimationTimer timer;
	/**
	 * <PRE>
	 * The board of letters was each added to a 2D array of letters in the Letter class.
	 * </PRE>
	 */
	private Letter[][] actual;
	/**
	 * <PRE>
	 * A Level object was declared to keep track of the respective level a player was in and was used to
	 * determine the level of difficulty a Letter Board contained.
	 * </PRE>
	 */
	private Level level;
	/**
	 * <PRE>
	 * The File object was declared so a dictionary text file could be read.
	 * </PRE>
	 */
	File file;
	/**
	 * <PRE>
	 * A scanner object was declared so a file could be scanned for certain words.
	 * </PRE>
	 */
	Scanner scanner;
	/**
	 * <PRE>
	 * dictionaryFile was initialized so every word scanned in the file could be added to a list.
	 * </PRE>
	 */
	ArrayList<String> dictionaryFile = new ArrayList<String>();
	//FileReader fileReader;
//	File f = new File("words.txt");
	/**
	 * <PRE>
	 * The menu contains all the menuItems including, New Beginner Game, New Intermediate Game, New Hard
	 * Game, Next Level, How To Play, About, and Exit MenuItems.
	 * </PRE>
	 */
	Menu menu;
	/**
	 * <PRE>
	 * The MenuBar adds the menu to the MenuBar
	 * </PRE>
	 */
	MenuBar bar;
	/**
	 * <PRE>
	 * Button button is used for rotate board which moves the entire board of Letters 90 degrees around the
	 * board.
	 * </PRE>
	 */
	Button button;
	/**
	 * <PRE>
	 * HBox hbox organizes the bottom region of the game with rotateBoard, score and timer.
	 * </PRE>
	 */
	HBox hbox;
	/**
	 * <PRE>
	 * Label label is the JavaFX text for timer.
	 * </PRE>
	 */
	Label label;
	
	/**
	 * <PRE>
	 *  Hbox hbox2 organizes the Score Text into its own HBox.
	 * </PRE>
	 */
	HBox hbox2;
	/**
	 * <PRE>
	 * bottom groups the JavaFX elements together.
	 * </PRE>
	 */
	Group bottom;
	/**
	 * <PRE>
	 * rect is the brown border that makes the JavaFX GUI slightly more appealing than what it currently
	 * looks like.
	 * </PRE>
	 */
	Rectangle rect;
	/**
	 * <PRE>
	 * The main method launches arguments for JavaFX
	 * </PRE>
	 * @param args the argument provided by the start method.
	 */
	
	public static void main(String[] args) { 
		launch(args);
	}
	
	/**
	 * Returns the score of the game
	 * @return Score object
	 */
	public static Score getScore() {
		return score;
	}
	
	/**
	 * Returns an ArrayList of words from the game.
	 * @return words Arraylist<String>
	 */
	public static ArrayList<String> getWords() {
		return words;
	}
	
	/**
	 * Overrides the start method from JavaFX. Initializes all the components
	 * of the board including menu items, buttons and all display items.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		file = null;
		try {
			file = new File("text files//words.txt");
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				dictionaryFile.add(scanner.next());
			}
		//	System.out.println("Length: " + dictionaryFile.size());
		}catch(FileNotFoundException error){
			System.out.println(error);
		}
		//hashSet = new HashSet<KeyCode>();
		stage.setTitle("Wordament");
		stage.setResizable(true);   
		stage.sizeToScene();
		
		rootNode = new BorderPane();
		view = new Board(TILE_SIZE);
		Integer[][] arr = new Integer[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				arr[i][j] = new Integer(0);
			}
		}
		menu = new Menu("Game");
		beginner = new MenuItem("New Beginner Game");
		intermediate = new MenuItem("New Intermediate Game");
		hard = new MenuItem("New Hard Game");
		nextLevel = new MenuItem("Next Level");
		howto = new MenuItem("How to Play");
		about = new MenuItem("About");
		exit = new MenuItem("Exit");
		beginner.setOnAction(new MyButtonListener());
		intermediate.setOnAction(new MyButtonListener());
		hard.setOnAction(new MyButtonListener());
		nextLevel.setOnAction(new MyButtonListener());
		howto.setOnAction(new MyButtonListener());
		about.setOnAction(new MyButtonListener());
		exit.setOnAction(new MyButtonListener());
		
		menu.getItems().addAll(beginner, intermediate, hard, nextLevel, howto, about, exit);
		
		bar = new MenuBar();
		bar.getMenus().add(menu);
		
		level = new Level(1, 1);
		
		button = new Button("Rotate Board"); // fix alignment
		button.setShape(new Circle(25));
		button.setMinSize(100, 100);
		button.setMaxSize(100, 100);
		button.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				view.setLetters(level.rotateArray(view.getLetters()));			
			}	
		});
		
		
		hbox = new HBox();
		hbox.getChildren().addAll(bar);
				
		model = new GridModel<Integer>(arr);
		view.setModel(model);
		view.setOnMousePressed(new GridMouseHandler());
		view.setOnMouseDragged(new GridMouseHandler());
		view.setOnMouseReleased(new GridMouseHandler());
		
		//do {
			actual = level.constructArray(level.getLevel(), 4, 4);
		//}while (! level.isValidWordList(level.wordList(dictionaryFile, actual), actual));
		view.setLetters(actual);
		
		
		label = new Label("00");  //place to display the timer counting down.
		label.setFont(Font.font(68));
		label.setMinWidth(50);
		label.setMinHeight(50);
		timer = new AnimationTimer() {
			
			/**
			 * <PRE>
			 * The handle method decrements the timer after a certain number of nanoseconds to pass and 
			 * use math operators to create the correct String of text to be displayed the next second.
			 * </PRE>
			 * 
			 * @param currentTime is a long that is keeps track of the time of the time that has passed 
			 * at the present
			 */
			@Override
			public void handle(long currentTime) {//timeline 
				if (time > 0) {
					time--;
				}
				//System.out.println(currentTime);
				if (currentTime - previousTime >= delay) {
					previousTime = currentTime;
					if (minutes != 0 || seconds != 0) {
						totalSeconds--;
					}
					minutes = totalSeconds / 60;
					seconds = totalSeconds % 60;
					
					if (seconds < 10) {
						string = "0" + minutes + ":0" + seconds;
					}else {
						string = "0" + minutes + ":" + seconds;
					}
					label.setText(string);
				}
//				string = time + "";
//				label.setText(string);
			}
			
		};
		timer.start();
		//	AnimationTimer timer = new AnimationTimer(, label); //{
//		private int totalSeconds;
//		private int delay;
//		
//		public AnimationTimer(int totalTime, int d) {
//			totalSeconds = totalTime;
//			delay = d;
//		}
//		
//		@Override
//		public void handle(long currentTime) {
//timeline
////			delay *= 6 * (int)10e10;
//			System.out.println(seconds);
//			System.out.println(minutes);
//			if (time > 0) {
//				time--;
//			}
//			//System.out.println(time);
//			string = minutes + ":" + seconds;
//			minutes = time / (6 * (int) 10e10);
//			seconds = time / ((int)10e9) % 60;
////			minutes = time / 180000;
////			seconds = (time / 30000) % 60;
//			label.setText(string);
//		}
//		
//	
//
//		public int getDelay() {
//			return delay;
//		}
//	};
//	timer.start();
		
		
		hbox2 = new HBox();
		hbox2.getChildren().add(label);
		
		score = new Score();
		score.setScore(0);

		//HBox hbox3 = new HBox();
		hbox2.setSpacing(100);
		//hbox2.setStyle("-fx-background-color: #847566;");
		current = new Text("");
		//hbox2.getChildren().addAll(button, score, current);

		current.setFont(Font.font(STYLESHEET_MODENA, 35));
		HBox scoreContainer = new HBox();
		scoreContainer.getChildren().addAll(button, score, current);
		scoreContainer.setSpacing(50);
//		hbox2.getChildren().addAll();
		bottom = new Group();
		
		scene = new Scene(rootNode, 600, 600);
		hbox2.setLayoutX(scene.getWidth()-200);
		rootNode.setCenter(view);
//		rootNode.setBottom(score);
		stage.setScene(scene);
		model.addBoardListener(view);
		scoreContainer.setStyle("-fx-background-color: #847566;");
		
		rootNode.setTop(hbox);
		//rootNode.setRight(hbox2);
//		rootNode.setBottom(button);
		//hbox2.getChildren().add(button);
		//rootNode.setBottom(hbox2);
//		rootNode.setRight(hbox2);
//		rootNode.setBottom(button);
		rootNode.setBottom(bottom);
		
		rect = new Rectangle(scene.getWidth(), 100);
		rect.setFill(Color.rgb(135, 117, 100));
		bottom.getChildren().addAll(rect, hbox2, scoreContainer);
		//rootNode.setCenter(new Button("Test"));
		
//		System.out.println("Test");
		stage.show();
		
	}
	/**
	 * Deals with mouse presses and dragging on the board and alters the
	 * behavior of individual cells as intended.
	 * 
	 * @author Bill Xiang
	 *
	 */
	class GridMouseHandler implements EventHandler<MouseEvent> {

		TestSearch search = new TestSearch();
		
		@Override
		public void handle(MouseEvent event) {
			double x = event.getX();
			double y = event.getY();
			int row = view.rowForYPos(y);
			int col = view.colForXPos(x);
			int minY = row * Game.TILE_SIZE;
			int maxY = minY + TILE_SIZE;
			int minX = col * Game.TILE_SIZE;
			int maxX = minX + Game.TILE_SIZE;
			if (x > minX+Game.TILE_SIZE/6 && x < maxX-Game.TILE_SIZE/6 &&
					y > minY+Game.TILE_SIZE/6 && y < maxY-Game.TILE_SIZE/6) {
				
			
//			System.out.println("Row: " + row + "Col: " + col);
			if (row >= 0 && row < model.getNumRows() && col >= 0 && col < model.getNumCols() && (minutes != 0 || seconds != 0)) {
				if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {				
					if (event.getButton().equals(MouseButton.PRIMARY)) {
						if (model.getValueAt(row, col) == 0) {
							current.setText(current.getText()+(view.getLetters())[row][col].toString());
							s += (view.getLetters())[row][col].toString();
							index++;
						}
						model.setValueAt(row, col, index);		
						view.cellChanged(row, col, index);
					}
				} else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
					if (event.getButton().equals(MouseButton.PRIMARY)) {
						if (model.getValueAt(row, col) == 0) {
							index++;
						}
//						if (index > 1) {
//							
//						}
//						System.out.println("DRAGGED");
//						System.out.print("Index:" + index + "\n");
						if (model.getValueAt(row, col) == 0) {
							current.setText(current.getText()+(view.getLetters())[row][col].toString());
							s += (view.getLetters())[row][col].toString();
							model.setValueAt(row, col, index);
							view.cellChanged(row, col, index);
						}
					}
				}
			}
			}
				if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED) && (minutes != 0 || seconds != 0)) {
//					if (event.getButton().equals(MouseButton.PRIMARY)) {
//						System.out.println("Released");
						//s = "";
						index = 0;
						int index = 1;
						int score = 0;
						while (true) {
							for (int r = 0; r < model.getNumRows(); r++) {
								for (int c = 0; c < model.getNumCols(); c++) {
									int val = model.getValueAt(r, c);
//									System.out.print(val + " ");
									if (val == index) {
										//s += (view.getLetters())[r][c].toString();
//										System.out.println("Char: " + (view.getLetters())[r][c].toString());
										//if (s.length() >= 3) {
											score += (view.getLetters())[r][c].getLetterValue();
										//}
//										System.out.println("Letter value" + (view.getLetters())[r][c].getLetterValue());
										index++;
										r=0;
										c=0;
										continue;
									}
								}
							}
							break;
						}
//						for (int r = 0; r < model.getNumRows(); r++) {
//							for (int c = 0; c < model.getNumCols(); c++) {
//								int val = model.getValueAt(r, c);
//								System.out.print(val + " ");
//							}
//						}
						
						//check whether is a word
//						System.out.println("Word: " + s);
//						System.out.println("Length: " + s.length());
//						System.out.println("In text file: " + (search.binarySearch(s.toLowerCase()) > -1));
//						System.out.println("Not in dictionary list: " + !words.contains(s));
						if (s.length() >= 3 && search.binarySearch(s.toLowerCase()) > -1 && !words.contains(s)) { //and length is >= 3
							words.add(s);
							getScore().setScore(getScore().getScore()+score);
//							System.out.println(s);
//							System.out.println(getScore());
						}
						s = "";
						view.gridReplaced();
						current.setText("");
						
//					}
				}		
		}
	}
	
	/**
	 * Allows for mouse handling for clicking the instructions bar in menu, and
	 * adjusting the levels of the game, among other functions.
	 * 
	 * @author Bill Xiang
	 *
	 */
	class MyButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == beginner) {
				//do {
				actual = level.constructArray(level.getLevel(), 4, 4);
				//}while (! level.isValidWordList(level.wordList(dictionaryFile, actual), actual));
				view.setLetters(actual);
				totalSeconds = 120;
				getScore().setScore(0);
//				System.out.println("Word list Before: " + words);
				words.removeAll(words);
//				System.out.println("Word list After: " + words);
			} else if (event.getSource() == intermediate) {
				level = new Level(4, 4);
			//	do {
					actual = level.constructArray(level.getLevel(), 4, 4);
				//}while (! level.isValidWordList(level.wordList(dictionaryFile, actual), actual));
				view.setLetters(actual);
				totalSeconds = 120;
				getScore().setScore(0);
				words.removeAll(words);
			} else if (event.getSource() == hard) {
				level = new Level(10, 10);
				//do {
					actual = level.constructArray(level.getLevel(), 4, 4);
					//System.out.println("scanning new list: " + Arrays.deepToString(actual));
				//}while (! level.isValidWordList(level.wordList(dictionaryFile, actual), actual));
				view.setLetters(actual);
				totalSeconds = 120;
				getScore().setScore(0);
				words.removeAll(words);
			}else if (event.getSource() == nextLevel) {
				level.nextLevel();
				//do {
					actual = level.constructArray(level.getLevel(), 4, 4);
				//	System.out.println("scanning new list");
				//}while (! level.isValidWordList(level.wordList(dictionaryFile, actual), actual));
				view.setLetters(actual);
				totalSeconds = 120;
				getScore().setScore(0);
				words.removeAll(words);
			}else if (event.getSource() == howto) {
				WebView w = new WebView();
				WebEngine e = w.getEngine();
				
				File file = new File("gamehelp.html");
				String fileLoc = file.getAbsolutePath();
//				System.out.println(fileLoc);
				String url = "file://" + fileLoc;
				e.load(url);
				Stage sPopUp = new Stage();
				sPopUp.setTitle("How To Play");
				Scene scene = new Scene(w, 600, 400);
				sPopUp.setScene(scene);
				sPopUp.show();
			} else if (event.getSource() == about) {
				Alert about = new Alert(AlertType.NONE, "Wordament\nVersion: 6/2/2019\nMade by Bill Xiang", ButtonType.OK);
				about.showAndWait();
			} else if (event.getSource() == exit) {
				System.exit(0);
			}
		}		
	}
}
