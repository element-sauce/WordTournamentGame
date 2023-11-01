/*
 * <PRE>
 * The TestRecursion class contains the recursive algorithm that determines as many word combinations
 * as possible in a given board and keeps track of them in a private ArrayList; this main console
 * mechanic in the game that allows the scoring to only be given to actual words.
 * </PRE>
 *
 * @author Bill Xiang
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class TestRecursion {
	/**
	 * The ArrayList contains every String that can be made in the board and is also found the in the 
	 * dictionary text file. No word is ever repeated within the ArrayList.
	 */
	static ArrayList<String> possibleWordList = new ArrayList<String>();
	/**
	 * The list array contains every word in the dictionary text file.
	 */
	static String[] list;
	/**
	 * 
	 */
	//static ArrayList<String> arrList;
	static TestSearch search1 = new TestSearch();
	/**
	 * The words ArrayList adds in the hasWords recursion method.
	 */
	static ArrayList<String> words = new ArrayList<>();
	
//	public TestRecursion() {
//		
//	}
	/**
	 * 
	 * The main method is used to print the board and check whether the method works for the actual game
	 * itself.
	 */
	public static void main(String[] args) {
//		Letter[][] letters = new Letter[4][4];
//		char ascii = 'A';
//		for (int row = 0; row < 4; row++) {
//			for (int col = 0; col < 4; col++) {
//				Letter label = new Letter(ascii+"");
//				letters[row][col] = label;
//				ascii++;
//			}
//		}
		/**
		 * A level object was constructed to determinetest a board of medium difficulty.
		 */
		Level l = new Level(6, 8);
		/**
		 * The letters 2D arrays constructs a random board with the given specifications
		 */
		Letter[][] letters = l.constructArray(l.getLevel(), 4, 4);
//		char ascii = 'A';
//		for (int row = 0; row < 4; row++) {
//			for (int col = 0; col < 4; col++) {
//				//System.out.println(ascii + "");
//				String s = ascii + "";
//				Letter label = new Letter(ascii+"");
//				letters[row][col] = label;
//				ascii+=2;
//			}
//		}
		//printLetters(letters);
		/**
		 * A new TestRecursion object called search was created in the main method to test the
		 * recursive method recursion through the scanList method. 
		 */
		TestRecursion search = new TestRecursion();
		search.print2DArray(letters);
		System.out.println(TestRecursion.possibleWordList);
		
		list = new String[10000];//99154 //466551
	//	arrList = new ArrayList<>();
		/**
		 * A scanner was declared to detect every line in a dictionary text file and add it to the list array.
		 */
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("text files//words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while (scanner.hasNext()) {
			list[i] = scanner.nextLine().toUpperCase();

			i++;
		}
		System.out.println("Before scan");
		search.scanList(list, letters);
		System.out.println("After scan");
		System.out.println(TestRecursion.possibleWordList);
		l.print2DArray(letters);
		//hasWords(letters, "", 0, 0);
		//System.out.println(words);
	}
	
	/**
	 * 
	 * @param list an ArrayList of Strings that are part of the possibleWordList
	 * @param key the String that is possibly being added to list
	 * @return whether or not the key is a repeat word within the list
	 * 
	 *<PRE>
	 *returns true if the key parameter is found within the list parameter and false otherwise.
	 *</PRE>
	 */
	public boolean isRepeat(ArrayList<String> list, String key) {
//		int index = list.indexOf(key);
//		boolean repeated = binsearch(key, 0, list.size()-1, list);
//		list.add(index, key);
//		return repeated;
		return (list.get(list.size() - 1).equals(key));
	}
	
	/**
	 * <PRE>
	 * Getter method for possibleWordList ArrayList
	 * </PRE>
	 * 
	 * @return possibleWordList
	 */
	public ArrayList<String> getArrayList() {
		return possibleWordList;
	}
//	/**
//	 * <PRE>
//	 * 
//	 * </PRE>
//	 * @param letters
//	 */
//	public static void printLetters(Letter[][] letters) {
////		for (int i = 0; i < letters.length; i++) {
////			
////		}
//	}
	/**
	 * <PRE>
	 * The class passes through the dictionary text file arr and checks for letters within the board class.
	 * If a board has the first letter of board in the board 2D array; if the letter is found, the index
	 * variable of the current string will be incremented and the recursion method that checks for all combinations
	 * of the possible words
	 * </PRE>
	 * @param arr the array of dictionary words from a text file
	 * @param board board of Letters
	 */
	public void scanList(String[] arr, Letter[][] board) {
		int currentLetterIndex = 0;
		int currentWordIndex = 0;
		System.out.println(arr.length);
		while (currentWordIndex < arr.length) {
		//	System.out.println(currentWordIndex);
			if (arr[currentWordIndex].length() >= 3) {
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
	//					if (currentLetterIndex == arr[currentWordIndex].length()){
	// 						possibleWordList.add(arr[currentWordIndex]);
	// 					}else
						//System.out.println("txt file word: "+ arr[currentWordIndex].substring(currentLetterIndex, currentLetterIndex + 1));
						//System.out.println("board letter: " + board[i][j].toString());
						if (arr[currentWordIndex].substring(currentLetterIndex, currentLetterIndex + 1).equals(board[i][j].toString())) {
							currentLetterIndex++;
							//System.out.println("entering recursion: " + currentWordIndex);
							recursion(arr[currentWordIndex], currentLetterIndex, i, j, board, -1, -1, -1, -1);
							//System.out.println("exit recursion");
							//currentLetterIndex = 0;
						}//else {
//							//currentLetterIndex = 0;
//						}
						currentLetterIndex = 0;
						//System.out.println(currentWordIndex);		
					}
				}
			}
			//System.out.println(possibleWordList);
			currentWordIndex++;
			//recursion(arr, currentWordIndex, board);
		}
	}
	
	/**
	 * <PRE>
	 * The recursion algorithm compares the current string to the word and adds words that are not repeats
	 * to possibleWordList; the recursion alogrithm keeps track of each word and determines whether the
	 * currentString can add another letter to match the given word; it also keeps track of previous indexes
	 * so it makes words that do not use letters from already used columns and rows.
	 * </PRE>
	 * @param word the word being matched with in the algorithm
	 * @param currentLetterIndex the index of word
	 * @param currentRow the current row of the 2D array
	 * @param currentCol the current col of an array
	 * @param board the 2D board of letters
	 * @param previousRow the row of the previous recursion method; is -1 if none existed
	 * @param previousCol the col of the previous recursion method; is -1 if none existed
	 * @param prevPrevRow the row of the row of the previous recursion method; is -1 if none existed
	 * @param prevPrevCol the col of the col of the previous recursion method; is -1 if none existed
	 */
	public void recursion(String word, int currentLetterIndex, int currentRow, int currentCol, Letter[][] board, int previousRow, int previousCol, int prevPrevRow, int prevPrevCol) {
//		while (currentLetterIndex < word.length()) {
//			currentLetterIndex++;
		if (currentLetterIndex != word.length()) {
			//System.out.println(currentLetterIndex + " of word # " + word);
			for (int i = currentRow - 1; i <= currentRow + 1; i++) {
				for (int j = currentCol - 1; j <= currentCol + 1; j++) {
					if ((i != currentRow || j != currentCol) && (i != previousRow || j != previousCol) && (i != prevPrevRow || j != prevPrevCol) && i >= 0 && i < board.length && j >= 0 && j <board[0].length) {
						if (word.substring(currentLetterIndex, currentLetterIndex + 1).equals(board[i][j].toString())) {
							System.out.println(word + " incrementing after this index: " + currentLetterIndex);
							currentLetterIndex++;
							recursion(word, currentLetterIndex, i, j, board, currentRow, currentCol, previousRow, previousCol);
							currentLetterIndex--;
						}
					}
					//currentLetterIndex = 1;
				}
			}
		}else {
			if (possibleWordList.size() == 0 || ! isRepeat(possibleWordList, word)) {
				possibleWordList.add(word);
			}
			System.out.println(possibleWordList);
		}
//		for (int i = 0; i < letters.length; i++) {
//			for (int j = 0; j < letters[0].length; j++) {
//				System.out.print(letters[i][j].toString() + " ");
//			}
//			System.out.println();
//		}
	}
	
	/**
	 * <PRE>
	 *  The other recursive algorithm that adds words from the dictionary file if on the board
	 * </PRE>
	 * @param letters the 2D array of letters used
	 * @param s the string of the word being checked for
	 * @param r the current row of the board
	 * @param c the current col of the board
	 */
	public static void hasWords(Letter[][] letters, String s, int r, int c) {
		if (0 <= r && r < letters.length && 0 <= c && c < letters[0].length){
			s += letters[r][c].toString();
			if (isWord(s)) {
				words.add(s);
			}
			hasWords(letters, s, r - 1, c);
	        hasWords(letters, s, r, c + 1);
	        hasWords(letters, s, r + 1, c);
	        hasWords(letters, s, r, c - 1);
		}
		
	}
	
	/*
	 * <PRE>
	 * Determines whether or not the word is inside the dictionary textFile
	 * </PRE>
	 * 
	 * @param word the String being searched
	 * 
	 * @return the index of the parameter word
	 */
	public static boolean isWord(String word) {
		int index = search1.binarySearch(word);
		return index > -1;
	}
	
	/**
	 * <PRE>
	 * The 2D array board is printed for the game developers to see if the game works
	 * </PRE>
	 * 
	 * @param arr the 2D array of Letters
	 */
	public void print2DArray(Letter[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");	
			}
			System.out.println();
		}
	}
}
