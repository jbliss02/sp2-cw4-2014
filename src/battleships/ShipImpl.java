/**
 * 
 */
package battleships;

/**
 * @author Jbliss02
 * 
 * Superclass which defines the common attributes and methods for a ship
 *
 */
public class ShipImpl implements Ship {

	/* (non-Javadoc)
	 * @see battleships.Ship#getBowRow()
	 */
	@Override
	public int getBowRow() {
		return bowRow;
	}
	/* (non-Javadoc)
	 * @see battleships.Ship#setBowRow(int)
	 */
	@Override
	public void setBowRow(int bowRow) {
		this.bowRow = bowRow;
	}
	/* (non-Javadoc)
	 * @see battleships.Ship#getBowColumn()
	 */
	@Override
	public int getBowColumn() {
		return bowColumn;
	}
	/* (non-Javadoc)
	 * @see battleships.Ship#setBowColumn(int)
	 */
	@Override
	public void setBowColumn(int bowColumn) {
		this.bowColumn = bowColumn;
	}
	/* (non-Javadoc)
	 * @see battleships.Ship#isHorizontal()
	 */
	@Override
	public boolean isHorizontal() {
		return horizontal;
	}
	/* (non-Javadoc)
	 * @see battleships.Ship#setHorizontal(boolean)
	 */
	@Override
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	/* (non-Javadoc)
	 * @see battleships.Ship#getLength()
	 */
	@Override
	public int getLength() {
		return length;
	}
	public int bowRow; //the row (0 to 9) which contains the bow (front) of the ship
	public int bowColumn; //the column (0 to 9) which contains the bow (front) of the ship
	public int length; //the number of squares occupied by the ship
	public boolean horizontal; //true if the ship occupies a single row, false otherwise
	public boolean [] hit = new boolean[4]; // an array of booleans telling whether that part of the ship has been hit
	
	/* (non-Javadoc)
	 * @see battleships.Ship#getShipType()
	 */
	@Override
	public String getShipType(){
		return null;
	}
	
	/* (non-Javadoc)
	 * @see battleships.Ship#okToPlaceShipAt(int, int, boolean, battleships.Ocean)
	 */
	@Override
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see battleships.Ship#placeShipAt(int, int, boolean, battleships.Ocean)
	 */
	@Override
	public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
	}
	
	/* (non-Javadoc)
	 * @see battleships.Ship#shootAt(int, int)
	 */
	@Override
	public boolean shootAt(int row, int column) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see battleships.Ship#isSunk()
	 */
	@Override
	public boolean isSunk(){
		return true;
	}
	
}

