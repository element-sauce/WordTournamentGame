import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class was used to test whether our searching methods work in the program by running 
 * the main method of the class, but later was actually used in the program, because of the 
 * usefulness of its binary search methods.
 * 
 * @author Bill Xiang
 *
 */
public class TestSearch {
	static String[] list;
	static ArrayList<String> arrList;
	
	/**
	 * Constructs a TestSearch object capable of storing 10000 words in its list,
	 * drawing these words from a text file named words.txt
	 */
	public TestSearch() {
		list = new String[10000];//99154 //466551
		arrList = new ArrayList<>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("text files//words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while (scanner.hasNext()) {
			list[i] = scanner.nextLine();

			i++;
		}
	}
	
	public static void main(String[] args) {
		//list = new String[10000];//99154 //466551
		arrList = new ArrayList<>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("text files//words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int i = 0;
		while (scanner.hasNext()) {
			//System.out.println(i);
			//scanner.nextLine();
//			list[i] = scanner.nextLine();
//			String str = scanner.nextLine();
//			arrList.add(str.toLowerCase());
//			System.out.println(str);
			i++;
		}
		
//		printList(list);
		
//		System.out.println(binarySearch(list, "apple"));
//		System.out.println(binarySearch(list, "parentss"));
//		System.out.println(binarySearch(list, "parentless"));
//		System.out.println(binarySearch(list, "tea"));
//		System.out.println(binarySearch(list, "hospital"));
//		System.out.println(binarySearch(list, "aaaaaaaaa"));
//		System.out.println(binarySearch(list, "banana"));//printed index is one less in index in text file list
//		System.out.println(Collections.binarySearch(arrList, "zoomed"));
		
	}
	
	/**
	 * This method prints all the words in the given String array.
	 * 
	 * @param s An array of strings
	 */
	public static void printList(String[] s) {
		for (int i = 0; i < s.length; i++) 
			System.out.println(s[i]);
	}
	
//	static int binarySearch(String arr[], String x) 
//    { 
//        int l = 0, r = arr.length - 1; 
//        while (l <= r) { 
//            int m = l + (r - l) / 2; 
//  
//            System.out.println(arr[m]);
//            if (arr[m].equals(x)) 
//                return m; 
//  
//            if (arr[m].compareTo(x)<0) 
//                l = m + 1; 
//
//            else
//                r = m - 1; 
//        } 
//
//        return -1; 
//    }
	
	/**
	 * This method returns the index of the key value, or -1, if it is not
	 * found in the list.
	 * 
	 * @param key The String key being searched for
	 * @return the index of the key
	 */
	public int binarySearch(String key) {
		return binsearch(key, 0, list.length-1, list);
	}
	
	/**
	 * The recursive binary search method that is initially called or "set into motion" by
	 * the binarySearch method. It returns the index of the key value, or -1, if it is not
	 * found in the list.
	 * 
	 * @param value the String key that is being searched for
	 * @param low the low index of the sublist being searched
	 * @param high the high index of the sublist being searched
	 * @param list The list the String key is being searched for in
	 * @return the index of the key
	 */
	int binsearch(String value, int low, int high, String[] list) {
		int mid = (low + high) / 2;
//		System.out.println("here");
//		System.out.println(list[0]);
		if (list[mid].equals(value)) {
			
			return mid;
			

		} else if (list[high].equals(value)) {
			return high;

		}else if (mid == low || mid == high) {
			return -1;

		}else if (list[mid].compareTo(value) < 0) {
			return(binsearch(value, mid + 1, high, list));

		}else if (list[mid].compareTo(value) > 0) {
			return(binsearch(value, low, mid - 1, list));

		}else {
			return -1;
		}
	}

}
