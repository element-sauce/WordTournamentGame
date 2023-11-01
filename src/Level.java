import java.util.ArrayList;
import java.util.Arrays;

/*
 *@author Bill Xiang
 */
public class Level {
	
	public static void main(String[] args) {
		Level l = new Level(1, 8);
		Letter[][]arr = l.constructArray(l.getLevel(),  4, 4);
		l.print2DArray(arr);
		System.out.println();
		l.print2DArray(l.rotateArray(arr));
	}
	
	private int level;
	private int numVowels;
	private Letter[][]array;
	private ArrayList<String> possibleWordList;
	private Score s;
	private ArrayList<String> wordList;
	
	String[]array2 = {"K", "Z", "Q", "V"};
	String[]array3 = {"A", "C", "E", "L", "R", "S", "B", "O", "I", "H", "U"};
	String[]array4 = {"D", "F", "G", "J", "M", "N", "P", "T", "W", "X", "Y"};
	//k, z, q, v are the least probable except in hard ( < 10 %)
	//a, c, e, l, r, s, b, o, i, h, u are high probability. (70 - 90 %)
	//d, f, g, j, m, n, p, t, w, x, y are medium probability (30 - 60 %)
	
	/**
	 * The level constructor asks for the level and number of vowels in a board and initializes an
	 * ArrayList of words and possible words and initializes score.
	 * @param lvl the level that the user would like to start with
	 * @param numVow the total number of vowels a user uses.
	 */
	public Level(int lvl, int numVow) {
		level = lvl;
		numVowels = numVow;
		wordList = new ArrayList<String>();
		possibleWordList = new ArrayList<String>();
		s = new Score();
	}
	
	/**
	 * The wordList method returns a possibleWordList after looking through all possible combination of words
	 * @param arr the ArrayList dictionary text file
	 * @param board a 2D array of Letters
	 * @return the possiblewordList
	 */
	public ArrayList<String> wordList(ArrayList<String> arr, Letter[][] board) {
//		System.out.println(arr);
//		System.out.println(Arrays.deepToString(board));
		int currentLetterIndex = 0;
		int currentWordIndex = 0;
		//System.out.println(arr.size());
		while (currentWordIndex < arr.size()) {
		//	System.out.println(currentWordIndex);
			if (arr.get(currentWordIndex).length() >= 3) {
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
	//					if (currentLetterIndex == arr[currentWordIndex].length()){
	// 						possibleWordList.add(arr[currentWordIndex]);
	// 					}else
						//System.out.println("txt file word: "+ arr[currentWordIndex].substring(currentLetterIndex, currentLetterIndex + 1));
						//System.out.println("board letter: " + board[i][j].toString());
						if (arr.get(currentWordIndex).substring(currentLetterIndex, currentLetterIndex + 1).equals(board[i][j].toString())) {
							currentLetterIndex++;
							//System.out.println("entering recursion: " + currentWordIndex);
							recursion(arr.get(currentWordIndex), currentLetterIndex, i, j, board, arr, -1, -1, -1, -1);
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
			currentWordIndex++;
			//recursion(arr, currentWordIndex, board);
		}
		//System.out.println(possibleWordList);
		return possibleWordList;
	}
	
	/**
	 * The wordList method returns true if the board is valid and false otherwise.
	 * @param arr the ArrayList dictionary text file
	 * @param board a 2D array of Letters
	 * @return whether the board is valid, if it meets the specifications of a playable board
	 */
	public boolean isValidWordList(ArrayList<String> arr, Letter[][] board) {
		int currentLetterIndex = 0;
		int currentWordIndex = 0;
	//	System.out.println(arr.size());
		while (currentWordIndex < arr.size()) {
		//	System.out.println(currentWordIndex);
			if (arr.get(currentWordIndex).length() >= 3) {
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board[0].length; j++) {
	//					if (currentLetterIndex == arr[currentWordIndex].length()){
	// 						possibleWordList.add(arr[currentWordIndex]);
	// 					}else
						//System.out.println("txt file word: "+ arr[currentWordIndex].substring(currentLetterIndex, currentLetterIndex + 1));
						//System.out.println("board letter: " + board[i][j].toString());
						if (arr.get(currentWordIndex).substring(currentLetterIndex, currentLetterIndex + 1).equals(board[i][j].toString())) {
							currentLetterIndex++;
							//System.out.println("entering recursion: " + currentWordIndex);
							recursion(arr.get(currentWordIndex), currentLetterIndex, i, j, board, arr, -1, -1, -1, -1);
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
		return (possibleWordList.size() >= 10);
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
	public void recursion(String word, int currentLetterIndex, int currentRow, int currentCol, Letter[][] board, ArrayList<String> list, int previousRow, int previousCol, int prevPrevRow, int prevPrevCol) {
//		while (currentLetterIndex < word.length()) {
//			currentLetterIndex++;
		System.out.println("currentLetterIndex: " + currentLetterIndex);
		if (currentLetterIndex != word.length()) {
			//System.out.println(currentLetterIndex + " of word # " + word);
			for (int i = currentRow - 1; i <= currentRow + 1; i++) {
				for (int j = currentCol - 1; j <= currentCol + 1; j++) {
					if ((i != currentRow || j != currentCol) && (i != previousRow || j != previousCol) && (i != prevPrevRow || j != prevPrevCol) && i >= 0 && i < board.length && j >= 0 && j <board[0].length) {
						if (word.substring(currentLetterIndex, currentLetterIndex + 1).equals(board[i][j].toString())) {
							System.out.println(word + " incrementing after this index: " + currentLetterIndex);
							currentLetterIndex++;
							recursion(word, currentLetterIndex, i, j, board, list, currentRow, currentCol, previousRow, previousCol);
							currentLetterIndex--;
						}
					}
					//currentLetterIndex = 1;
				}
			}
		}else {
			//System.out.println(list.size());
			if (possibleWordList.size() == 0 || ! isRepeat(possibleWordList, word)) {
				System.out.println("adds word");
				possibleWordList.add(word);
			}
			//System.out.println(list);
			//System.out.println(possibleWordList);
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
	 *  A getter method for levels
	 *  </PRE>
	 * @return int level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * <PRE>
	 * The method returns whether or not a word has already been added to a list or not.
	 * </PRE>
	 * @param list a list of words that the user has already added variables
	 * @param key the word being search in the parameter list
	 * @return whether the key parameter is a repeat
	 */
	public boolean isRepeat(ArrayList<String> list, String key) {
//		int index = list.indexOf(key);
//		boolean repeated = binsearch(key, 0, list.size()-1, list);
//		list.add(index, key);
//		return repeated;
		return (list.get(list.size() - 1).equals(key));
	}

//	public boolean isValidWordList(ArrayList<String> list) {
//		scanList(list)
//		return true;
//		//return (possibleWordList.size() >= 5);
//	}
	
	/**
	 * <PRE>
	 * a random 3 letter word will be blinking red and white
	 * </PRE>
	 * @param allWords the possible word list
	 * @return a string with a possible combination of letters
	 */
	public String getHint(ArrayList<String> allWords) {
		int random = (int)(Math.random() * allWords.size());
		while (allWords.get(random).length() != 3 && ! isRepeat(wordList, allWords.get(random))) {
			random = (int)(Math.random() * allWords.size());
		}
		return allWords.get(random);
	}
	
	/**
	 * <PRE>
	 * binary search searches through the list ArrayList for the index that matches the parameter of value
	 * within certain boundaries of low and high and returns the index of value and returns -1 if none are
	 * found.
	 * </PRE>
	 * @param value the String being searched
	 * @param low the smallest index value could be
	 * @param high the largest index value could be
	 * @param list the list being searched
	 * @return the index of value; returns -1 if value is not found
	 */
	static boolean binsearch(String value, int low, int high, ArrayList<String> list) {
		int mid = (low + high) / 2;

		if (list.get(mid).toLowerCase().equals(value.toLowerCase())) 
			return true;

		else if (list.get(high).toLowerCase().equals(value.toLowerCase()))
			return true;

		else if (mid == low || mid == high)
			return false;

		else if (list.get(mid).compareTo(value) < 0) 
			return(binsearch(value, mid + 1, high, list));

		else if (list.get(mid).compareTo(value) > 0)
			return(binsearch(value, low, mid - 1, list));

		else
			return false;
	}
	
	/**
	 * a word is added to possibleWordList and the score is updated
	 * @param word the word being added
	 */
	public void addWord(String word) {
		wordList.add(word);
		for (int i = 0; i < word.length(); i++) {
			Letter l = new Letter(word.substring(i, i + 1));
			s.setScore(s.getScore() + l.getLetterValue());
		}
	}
	
	/**
	 * The method increments the level, resets the score, and clears the wordList.
	 */
	public void nextLevel() {
		level++;
		wordList.clear();
		s.setScore(0);
	}
	
	/**
	 * <PRE>
	 * The constructArray method accounts for the difficulty of level and number of rows and columns before
	 * generating a 2D Letter board using 3 arraylists; each arraylist has a probability of being added to
	 * the board and determines which letters categorized from easy, medium, and difficult to create a 
	 * pseudorandom board for users to enhoy.
	 * </PRE>
	 * @param lvl the current level being played
	 * @param arrayRows number of rows
	 * @param arrayCols number of columns
	 * @return the 2d array Letter board
	 */
	public Letter[][] constructArray(int lvl, int arrayRows, int arrayCols) {
		array = new Letter[arrayRows][arrayCols];
		int arr3Count = 0;
		int arr4Count = 0;
		int arr2Count = 0;
		//Medium Difficulty
		if (lvl > 3 && lvl <= 6) { // 9 from array3, 7 from array4
			//array = new String[4][4];
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					int probability = (int)(Math.random() * 16);
					if (probability < 9) {
						probability = (int)(Math.random() * 11);
						if (arr3Count < 9) {
							arr3Count++;
//							probability = (int)(Math.random() * 11);
							array[i][j] = new Letter(array3[probability]);
						}else {
							arr4Count++;
//							probability = (int)(Math.random() * 11);
							array[i][j] = new Letter(array4[probability]);
						}
					}else {
						probability = (int)(Math.random() * 11);
							if (arr4Count < 7) {
								arr4Count++;
								//probability = (int)(Math.random() * 7);
								array[i][j] = new Letter(array4[probability]);
							}else {
								arr3Count++;
								//probability = (int)(Math.random() * 9);
								array[i][j] = new Letter(array3[probability]);
							}
					}
				}
//					for (int k = 0; k < 8; k++) {
//						int probability = (int)(Math.random() * 11); 
//						array[i][j] = array3[probability];		
//					}
//					
//					for (int l = 0; l < 6; l++) {
//						int probability2 = (int)(Math.random() * 11);
//						array[i][j] = array4[probability2];
//					}				
			}
		//Hard Difficulty
		} else if (lvl > 6 ) { // 5 from array3, 8 from array4, 4 array2
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
//					System.out.println("Arr2Count: " + arr2Count);
//					System.out.println("Arr3Count: " + arr3Count);
//					System.out.println("Arr4Count: " + arr4Count);
					int probability = (int)(Math.random() * 16);
					//System.out.println("Probability: " + probability);
					if (probability < 5) {
						if (arr3Count < 5) {
							arr3Count++;
							probability = (int)(Math.random() * 11);
							array[i][j] = new Letter(array3[probability]);
						}else if (arr4Count < 8 && arr2Count < 4){
							probability = (int)(Math.random() * 12) + 5;
							if (probability >= 5 && probability < 13){
									if (arr4Count < 8) {
										arr4Count++;
										probability = (int)(Math.random() * 11);
										array[i][j] = new Letter(array4[probability]);
									}else if (arr2Count < 4){
										arr2Count++;
										probability = (int)(Math.random() * 4);
										array[i][j] = new Letter(array2[probability]);
									}
							}else {
								if (arr2Count < 4){
									arr2Count++;
									probability = (int)(Math.random() * 4);
									array[i][j] = new Letter(array2[probability]);
								}else {
									arr4Count++;
									probability = (int)(Math.random() * 11);
									array[i][j] = new Letter(array4[probability]);
								}
							}
						}else if (arr4Count < 8) {
							arr4Count++;
							probability = (int)(Math.random() * 11);
							array[i][j] = new Letter(array4[probability]);
						}else if (arr2Count < 4){
							arr2Count++;
							probability = (int)(Math.random() * 4);
							array[i][j] = new Letter(array2[probability]);
						}
					}else if (probability >= 5 && probability < 13){
							if (arr4Count <= 8) {
								arr4Count++;
								probability = (int)(Math.random() * 11);
								array[i][j] = new Letter(array4[probability]);
							}else if (arr3Count < 5 && arr2Count < 4){
								probability = (int)(Math.random() * 9);
								if (probability < 5){
									probability = (int)(Math.random() * 11);
									arr3Count++;
									array[i][j] = new Letter(array4[probability]);										
								}else if (probability >= 5){
									probability = (int)(Math.random() * 4);
									arr2Count++;
									array[i][j] = new Letter(array2[probability]);
								}
							}else if (arr3Count < 5) {
								arr3Count++;
								probability = (int)(Math.random() * 11);
								array[i][j] = new Letter(array3[probability]);
							}else if (arr2Count < 4){
								arr2Count++;
								probability = (int)(Math.random() * 4);
								array[i][j] = new Letter(array2[probability]);
							}
					}else if (probability >= 13){
						if (arr2Count < 4) {
							arr2Count++;
							probability = (int)(Math.random() * 4);
							array[i][j] = new Letter(array2[probability]);
						}else if (arr3Count < 5 && arr4Count < 8){
							probability = (int)(Math.random() * 12);
							if (probability < 5){
								probability = (int)(Math.random() * 11);
								arr3Count++;
								array[i][j] = new Letter(array3[probability]);									
							}else if (probability >= 5){
								probability = (int)(Math.random() * 11);
								arr4Count++;
								array[i][j] = new Letter(array4[probability]);
							}
						}else if (arr3Count < 5) {
							arr3Count++;
							probability = (int)(Math.random() * 11);
							array[i][j] = new Letter(array3[probability]);
						}else if (arr4Count < 8){
							arr4Count++;
							probability = (int)(Math.random() * 4);
							array[i][j] = new Letter(array4[probability]);
						}
						
					}
				}
			}
	//		for (int i = 0; i < 4; i++) {
	//			int probability = (int)(Math.random() * 11); 
	//			array = new ArrayList<String>();
	//			array.add(array3[probability]);			
	//		}
	//		
	//		for (int i = 0; i < 7; i++) {
	//			int probability2 = (int)(Math.random() * 11);
	//			array.add(array4[probability2]);
	//		}
			//Easy Difficulty
		} else if (lvl <= 3) { // 
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					int probability = (int)(Math.random() * 11);
					//System.out.println(probability);
					//System.out.println(array3[probability]);
					array[i][j] = new Letter(array3[probability]);
				}
			}
		}
		return array;
	}
	
	/**
	 * the letter board is printed for the game developers to test code
	 * @param arr the letter board
	 */
	public void print2DArray(Letter[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");	
			}//
			System.out.println();
		}//
	}
	/**
	 * <PRE>
	 * The entire 2d board remains the same except it is rotated in a different angle with the same 
	 * amount of combinations to allow users to visualize the board without changing possible combinations.
	 * </PRE>
	 * @param arr the 2d letter board being used
	 * @return a new letter board with the same combinations but different rotation
	 */
	public Letter[][] rotateArray(Letter[][] arr) { //for rotating
		Letter[][] array = new Letter[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				array[i][j] = arr[arr.length - 1 - j][i];
			}
		}
		return array;
	}
}
