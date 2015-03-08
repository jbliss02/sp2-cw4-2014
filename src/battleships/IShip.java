package battleships;


public interface IShip {

	public abstract int getBowRow();

	public abstract void setBowRow(int bowRow);

	public abstract int getBowColumn();

	public abstract void setBowColumn(int bowColumn);

	public abstract boolean isHorizontal();

	public abstract void setHorizontal(boolean horizontal);

	public abstract int getLength();

	/**
	 * Returns the type of this ship. This method exists only to be overridden
	 * 
	 * @return 
	 */
	public abstract String getShipType();

	/**
	 * Returns true if it is okay to put a ship of this length with its bow in this location,
	 * with the given orientation, and returns false otherwise. The ship must not overlap
	 * another ship, or touch another ship (vertically, horizontally, or diagonally), and it
	 * must not stick out beyond the array. Does not actually change either the ship or
	 * the Ocean, just says whether it is legal to do so
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 * @return
	 */
	public abstract boolean okToPlaceShipAt(int row, int column,
			boolean horizontal, IOcean ocean);

	/**
	 * Puts the ship in the ocean. This involves giving values to the bowRow, bowColumn, and
	 * horizontal instance variables in the ship, and it also involves putting a reference
	 * to the ship in each of 1 or more locations (up to 4) in the ships array in the Ocean object.
	 * @param row
	 * @param column
	 * @param horizontal
	 * @param ocean
	 */
	public abstract void placeShipAt(int row, int column, boolean horizontal,
			IOcean ocean);

	/**
	 *  If a part of the ship occupies the given row
	 *  and column, and the ship hasn’t been sunk, mark that part of the ship as hit (in
	 * the hit array, 0 indicates the bow) and return true, otherwise return false.
	 * @param row
	 * @param column
	 * @return
	 */
	public abstract boolean shootAt(int row, int column);

	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 * @return
	 */
	public abstract boolean isSunk();

}