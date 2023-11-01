import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class is meant to a grid model of the order in which the cells are visited
 * in a given completed or in-progress drag gesture.
 * 
 * @author Bill Xiang
 *
 * @param <Integer> 
 */
public class GridModel<Integer> {

	private Integer[][] grid;
	private ArrayList<GridListener<Integer>> listeners;
	
	/**
	 * Constructs a Grid Model with the given 2-D integer grid 
	 * 
	 * @param grid an 2-d Integer array that the GridModel is to be constructed based on
	 */
	public GridModel(Integer[][] grid) {
		this.grid = grid;
		this.listeners = new ArrayList<>();
	}
	
	/**
	 * Returns the number of rows in the model
	 * 
	 * @return number of rows in the model
	 */
	public int getNumRows() {
		return this.grid.length;
	}
	
	/**
	 * Returns the nubmer of columns in the model
	 * 
	 * @return number of columns in the model
	 */
	public int getNumCols() {
		return this.grid.length > 0 ? this.grid[0].length : 0;
	}
	
	/**
	 * Returns the Integer value associated with the cell at the given location
	 * 
	 * @param row the row number of the cell
	 * @param col the column of the cell
	 * @return the cell's Integer value
	 */
	public Integer getValueAt(int row, int col) {
		return this.grid[row][col];
	}
	
	/**
	 * Sets the Integer value of the cell at the given location 
	 * 
	 * @param row the row number of the cell
	 * @param col the column number of the cell
	 * @param val the cell's future Integer value
	 */
	public void setValueAt(int row, int col, Integer val) {
		Integer oldVal = this.grid[row][col];
		this.grid[row][col] = val;
		if (!oldVal.equals(val)) {
			for (GridListener<Integer> l : listeners) {
				l.cellChanged(row, col, val);
			}
		}
	}

	/**
	 * Sets the 2-D integer grid associated with the GridModel
	 * 
	 * @param grid the 2-D integer grid
	 */
	public void setGrid(Integer[][] grid) {
		if (grid == null) throw new IllegalArgumentException("grid cannot be null.");
		this.grid = grid;
		for (GridListener<Integer> l : listeners) {
			l.gridReplaced();
		}
	}

	@Override
	public String toString() {
		return "GridModel [grid=" + Arrays.toString(grid) + ", listeners=" + listeners + "]";
	}
	
	/**
	 * Adds a board listener that responds to changes in the board
	 * 
	 * @param l the GridListener
	 */
	void addBoardListener(GridListener<Integer> l) {
		if (!listeners.contains(l)) {
			listeners.add(l);
		}
	}
	
//	public int[] getNumberPosition(int index) {
//		int[] pos = new int[2];
//		
//
//		for (int r = 0; r < getNumRows(); r++) {
//			for (int c = 0; c < getNumCols(); c++) {
//				Integer val = getValueAt(r, c);
////					System.out.print(val + " ");
//				if (val == index) {
//					pos[0] = r;
//					pos[1] = c;
//					break;
////						System.out.println("Char: " + (view.getLetters())[r][c].getText());
//				}
//			}
//		}
//			
//		
//		
//		return pos;
//	}
	
}
