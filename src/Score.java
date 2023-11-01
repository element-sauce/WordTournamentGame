/**
 * Keeps track of the score in the game. 
 * @author Bill Xiang
 *
 */
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Keeps track of the score in the game, and represents the GUI element that also displays it
 *
 */
public class Score extends Text {
	/**
	 * The integer value of score and the Font object with  for score.
	 */
	private int score;
	private Font f = new Font("Comic Sans", 50.0);
	
	/**
	 * Initializes the score of the game to 0, sets the font of score, and updates the display.
	 */
	public Score() {
		score = 0;
		setFont(f);
		updateDisplay();
	}
	
	/**
	 * Returns the score.
	 * @return score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * <PRE>
	 * Sets the score and updates the text on the JavaFX terminal window.
	 * </PRE>
	 * 
	 * @param: int value to score to parameter int s
	 * 
	 */
	public void setScore(int s) {
		score = s;
		updateDisplay();
	}
	
	/**
	 * Updates the display of the board with specified score. 
	 */
	protected void updateDisplay() {
		setText("" + score);
	}

}
