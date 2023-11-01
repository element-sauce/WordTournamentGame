import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Constructs the letters and letter combinations that will be displayed on the board
 * for the users to connect to form words! This representation of the letter also 
 * encapsulates the property of their value.
 * @author Bill Xiang
 */
public class Letter extends Text {
//	final int A = 1;
//	final int B = 3;
//	final int C = 2;
//	int A;
//	int B;
//	int C;
//	int D;
//	int E;
//	int F;
//	int G;
//	int H;
//	int I;
//	int J;
//	int K;
//	int L;
//	int M;
//	int N;
//	int O;
//	int P;
//	int Q;
//	int R;
//	int S;
//	int T;
//	int U;
//	int V;
//	int W;
//	int X;
//	int Y;
//	int Z;	
	//private int wordLength;
	private int letterValue;
//	private double positionX;
//	private double positionY;
	private String str;
	private Letter letter1;
	private Letter letter2;
	private Letter letter3;
	private boolean isSpecial;
	private Font f = new Font("Papyrus", 50.0);
	private Font font;
	
	/**
	 * Constructs two letter combinations like "to" or "at" that can be used
	 * to form words.
	 * @param l1 first letter
	 * @param l2 second letter
	 * @param fontType the object represents the properties of the font the text is to be rendered in
	 */
	public Letter(Letter l1, Letter l2, Font fontType) {
		letter1 = l1;
		letter2 = l2;
		isSpecial = true;
		letterValue = l1.getLetterValue() + l2.getLetterValue();
		str = l1.toString() + l2.toString();
		font = fontType;
		setFont(f);
		setText(str);
	}
	
	/**
	 * Constructs three letter combinations like "ion" or "ing" that can be used 
	 * to form words. 
	 * @param l1 first letter
	 * @param l2 second letter
	 * @param l3 third letter
	 */
	public Letter(Letter l1, Letter l2, Letter l3) {
		letter1 = l1;
		letter2 = l2;
		letter3 = l3;
		isSpecial = true;
		letterValue = l1.getLetterValue() + l2.getLetterValue() + l3.getLetterValue();
		str = l1.toString() + l2.toString() + l3.toString();
		setFont(f);
		setText(str);
	}
	
	/**
	 * Initializes the letter combination
	 * @param val value of letter
	 * @param letter letter combo.
	 * @param fontType the object represents the properties of the font the text is to be rendered in 
	 */	
	public Letter(int val, String letter, Font fontType) {
		letterValue = val;
//		positionX = xPos;
//		positionY = yPos;
		str = letter;
		isSpecial = false;
		font = fontType;
		setFont(f);
		setText(str);
	}

	/**
	 * Assigns values for each letter. 
	 * @param letter the string representation of the character
	 * @param fontType the object represents the properties of the font the text is to be rendered in
	 */
	public Letter(String letter, Font fontType) {
		str = letter;
		//System.out.println(str);
		font = fontType;
		setFont(f);
		setText(str);
		isSpecial = false;
		if (letter.charAt(0) == 'A' || letter.charAt(0) == 'E' || letter.charAt(0) == 'I' || 
			letter.charAt(0) == 'O' || letter.charAt(0) == 'U' || letter.charAt(0) == 'N' ||  
			letter.charAt(0) == 'R' || letter.charAt(0) == 'T' || letter.charAt(0) == 'L' ||
			letter.charAt(0) == 'S') {
			letterValue = 1;
		}else if (letter.charAt(0) == 'D' || letter.charAt(0) == 'G') {
			letterValue = 2;
		}else if (letter.charAt(0) == 'C' || letter.charAt(0) == 'M' || letter.charAt(0) == 'B' || 
				letter.charAt(0) == 'P') {
			letterValue = 3;
		}else if (letter.charAt(0) == 'F' || letter.charAt(0) == 'H' || letter.charAt(0) == 'V' || 
				letter.charAt(0) == 'Y' || letter.charAt(0) == 'W') {
			letterValue = 4;
		}else if (letter.charAt(0) == 'K') {
			letterValue = 5;
		}else if (letter.charAt(0) == 'J' || letter.charAt(0) == 'X') {
			letterValue = 8;
		}else if (letter.charAt(0) == 'Q' || letter.charAt(0) == 'Z') {
			letterValue = 10;
		}else {
			letterValue = 0;
		}
	}
		/**
		 * Constructs a Letter object given the String representation of the character in question,
		 * and assigns the appropriate value given the letter.
		 * @param letter the character as a String
		 */
		public Letter(String letter) {
			str = letter;
			//System.out.println(str);
			font = f;
			setFont(f);
			setText(str);
			isSpecial = false;
			if (letter.charAt(0) == 'A' || letter.charAt(0) == 'E' || letter.charAt(0) == 'I' || 
				letter.charAt(0) == 'O' || letter.charAt(0) == 'U' || letter.charAt(0) == 'N' ||  
				letter.charAt(0) == 'R' || letter.charAt(0) == 'T' || letter.charAt(0) == 'L' ||
				letter.charAt(0) == 'S') {
				letterValue = 1;
			}else if (letter.charAt(0) == 'D' || letter.charAt(0) == 'G') {
				letterValue = 2;
			}else if (letter.charAt(0) == 'C' || letter.charAt(0) == 'M' || letter.charAt(0) == 'B' || 
					letter.charAt(0) == 'P') {
				letterValue = 3;
			}else if (letter.charAt(0) == 'F' || letter.charAt(0) == 'H' || letter.charAt(0) == 'V' || 
					letter.charAt(0) == 'Y' || letter.charAt(0) == 'W') {
				letterValue = 4;
			}else if (letter.charAt(0) == 'K') {
				letterValue = 5;
			}else if (letter.charAt(0) == 'J' || letter.charAt(0) == 'X') {
				letterValue = 8;
			}else if (letter.charAt(0) == 'Q' || letter.charAt(0) == 'Z') {
				letterValue = 10;
			}else {
				letterValue = 0;
			}
		
	}

//	public int getwordLength() {
//		return wordLength;
//	}
	/**
	 * Determines whether letter combination is special. 
	 * @return true if yes, false if no.
	 */	
	public boolean isLetterSpecial() {
		return isSpecial;
	}
	
	/**
	 * Printing method to display specified String
	 */
	public String toString() {
		return str;
	}
	
	/**
	 * Gets the letter value.
	 * @return letter value.
	 */
	public int getLetterValue() {
		return letterValue;
	}
	
	/**
	 * Sets the letter value of the specified letter.
	 * @param letterValue the numerical value to be set to this letter
	 */
	public void setLetterValue(int letterValue) {
		this.letterValue = letterValue;
	}

//	public double getPositionX() {
//		return positionX;
//	}
//
//	public void setPositionX(double positionX) {
//		this.positionX = positionX;
//	}
//
//	public double getPositionY() {
//		return positionY;
//	}
//
//	public void setPositionY(double positionY) {
//		this.positionY = positionY;
//	}
	
	/**
	 * Updates the display of the board with the string of letters created.
	 */
	public void updateDisplay() {
		this.setText(toString());
		setText(toString());
	}
}
