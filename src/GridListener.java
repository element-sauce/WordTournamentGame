
/**
 * This interface defines the method that a class that listens for the grid 
 * must implement
 * 
 * @author Bill Xiang
 *
 * @param <T> the type of value associated with the cell
 */
public interface GridListener<T> {
	/**
	 * Implementation will define what happens when a cell is changed
	 * 
	 * @param row the row number
	 * @param col the column number
	 * @param newVal the new value being changed to
	 */
	public void cellChanged(int row, int col, T newVal);
	
	/**
	 * Implementation will define what happens the grid is to be replaced
	 */
	public void gridReplaced();
}
