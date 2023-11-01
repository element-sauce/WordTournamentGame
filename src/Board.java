import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Deals with all the printing and assignment of random letters to the board
 * that the user will see in order to play the game. 
 * @author Bill Xiang
 */
public class Board extends Group implements GridListener<Integer>{
	/**
	 * <PRE>
	 * The tileSize double determines the size of each JavaFX tile for the grid.
	 * </PRE>
	 */
	private double tileSize;
	/**
	 * <PRE>
	 * The cells 2D array of Rectangles contains rectangles which can store values from the model
	 * </PRE>
	 */
	private Rectangle[][] cells;
	/**
	 * <PRE>
	 * The letters 2D array stores Letters for a board
	 * </PRE>
	 */
	private Letter[][] letters;
	/**
	 * <PRE>
	 * The GridModel model stores the location of each grid and allows letters to exist within them.
	 * </PRE>
	 */
	private GridModel<Integer> model;
	
	/**
	 * initializes tile size of each individual tile of the board.
	 * @param letterSize (size of letter)
	 */
	public Board(double letterSize) {
		tileSize = letterSize;
	}
	
	/**
	 * Sets the letters to whatever cell in the board. Also sets the color of the 
	 * cells. Allows for the mouse to control behavior of the letter in the board.
	 * @param letters 2-D array of letters.
	 */
	public void setLetters(Letter[][] letters) {
		getChildren().remove(0, getChildren().size());
		cells = new Rectangle[model.getNumRows()][model.getNumCols()];
		this.letters = letters;
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				Rectangle cell = new Rectangle(tileSize, tileSize);
				Letter label = new Letter(letters[row][col].toString());
				label.addEventHandler(MouseEvent.ANY, e -> cell.fireEvent(e));
				
				cell.setFill(model.getValueAt(row, col) == 0 ? Color.LIGHTSKYBLUE : Color.KHAKI);
				cell.setX(tileSize * col); //+5
				cell.setY(tileSize * row); //+5
				cell.setStroke(Color.BLACK);
				cell.setStrokeWidth(1);
				
				cell.setX(tileSize * col); //+5
				cell.setY(tileSize * row); //+5
				label.setX(tileSize * col + tileSize/4);
				label.setY(tileSize * row + 5 * tileSize / 8);
				cells[row][col] = cell;
				letters[row][col] = label;
				getChildren().addAll(cell, label);
			}
		}
	}
	
	/**
	 * Resets the board to a new game, filled with new letters. Allows for
	 * mouse handling events and also sets the aesthetic and colors of each cell.
	 */
	public void resetCells() {
		getChildren().remove(0, getChildren().size());
		cells = new Rectangle[model.getNumRows()][model.getNumCols()];
		letters = new Letter[model.getNumRows()][model.getNumCols()];
		char ascii = 'A';
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				Rectangle cell = new Rectangle(tileSize, tileSize);
				Letter label = new Letter(ascii+"");
				label.addEventHandler(MouseEvent.ANY, e -> cell.fireEvent(e));
				
				cell.setFill(model.getValueAt(row, col) == 0 ? Color.LIGHTSKYBLUE : Color.KHAKI);
				cell.setX(tileSize * col); //+5
				cell.setY(tileSize * row); //+5
				cell.setStroke(Color.BLACK);
				cell.setStrokeWidth(1);
				
				cell.setX(tileSize * col); //+5
				cell.setY(tileSize * row); //+5
				label.setX(tileSize * col+tileSize/2);
				label.setY(tileSize * row+ tileSize/2);
				cells[row][col] = cell;
				letters[row][col] = label;
				getChildren().addAll(cell, label);
//				getChildren().add(stack);
				ascii++;
			}
		}
		
	}
	
	/**
	 * Returns the X position of the cell in that column
	 * @param column number
	 * @return X position given column number
	 */
	public double xPosForCol(int col) {
		return col * tileSize;
	}
	/**
	 * Returns the Y position of the cell in that column
	 * @param row number
	 * @return Y position given column number
	 */
	public double yPosForRow(int row) {
		return row * tileSize;
	}
	
	/**
	 * Returns the column number given an x-position
	 * @param x the x position
	 * @return the column given the x position
	 */
	public int colForXPos(double x) {
		return (int)(x / tileSize);
	}
	
	/**
	 * Returns the row number given a y-position
	 * @param y
	 * @return the row given the y position
	 */
	public int rowForYPos(double y) {
		return (int)(y / tileSize);
	}
	
	/**
	 * The method called when the cell changed responding to its drag gesture status,
	 * (if its been dragged on in the current gesture.) It sets the cell to the appropriate color
	 */
	@Override
	public void cellChanged(int row, int col, Integer newVal) {
		(cells[row][col]).setFill(newVal == 0 ? Color.LIGHTSKYBLUE : Color.KHAKI);
		//place to change the individual tiles when hints class is called
	}

	/**
	 * The method called in response to the ending of the drag gesture. Resets the color of each
	 * cell back to the default color.
	 */
	@Override
	public void gridReplaced() {
		for (int row = 0; row < model.getNumRows(); row++) {
			for (int col = 0; col < model.getNumCols(); col++) {
				model.setValueAt(row, col, 0);
				cells[row][col].setFill(Color.LIGHTSKYBLUE);
			}
		}
	}
	
	/**
	 * Sets grid mode of letters and resets the cells.
	 * @param model representing the order that cells have been dragged in a drag gesture
	 */
	public void setModel(GridModel<Integer> model) {
		this.model = model;
		resetCells();
	}
	
	/**
	 * Returns the array of letters that is going to be used in the board.
	 * @return 2-D array of letters.
	 */
	public Letter[][] getLetters() {
		return letters;
	}

}
