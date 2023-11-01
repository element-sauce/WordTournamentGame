/**
 * Detects money stored in Game class
 * 
 * @author Bill Xiang
 */
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Money extends Text {
	/**
	 * The integer value for money and the font for the Money class
	 */
	private int money;
	Font f = new Font("Comic Sans", 50.0);
	
	/**
	 * Default constructor for Money class initialized money as 0, sets the font to Comic Sans with a font
	 * size of 50. The JavaFX GUI displays the font and the value of money in the terminal window.
	 */
	public Money() {
		money = 0;
		setFont(f);
		updateDisplay();
	}
	
	/**
	 * <PRE>
	 * Getter method that returns the value for money.
	 * </PRE>
	 * @return int value of money
	 */
	public int getMoney() {
		if (money == 0) {
			System.out.println("You have no more money!");
//			return 0;
		}
		return money;
	}
	
	/**
	 * 
	 * @param m the value of money that the money class sets a money object to
	 * 
	 * <PRE>
	 * a setter method that sets the private int money variable to parameter m and updates the display of
	 * the new money value in the terminal window.
	 * </PRE>
	 */
	public void setMoney(int m) {
		money = m;
		updateDisplay();
	}
	
	/**
	 * <PRE>
	 * The JavaFX terminal window changes the current String of money to the new String of the money value.
	 * The method is also a protected method.
	 * </PRE>
	 */
	protected void updateDisplay() {
		setText("" + money);
	}
	
}