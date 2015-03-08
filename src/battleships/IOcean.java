package battleships;

public interface IOcean {

	/**
	 * Places all ten ships randomly on the (initially empty) ocean
	 */
	public abstract void placeAllShipsRandomly();

	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row
	 * @param column
	 * @return
	 */
	public abstract boolean isOccupied(int row, int column);

	/**
	 * Returns true if the given location contains
	 *	a real ship, still afloat, (not an EmptySea), false if it does not. In addition, this
	 * method updates the number of shots that have been fired, and the number of hits
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public abstract boolean shootAt(int row, int column);

	/**
	 * Returns the number of shots fired (in this game)
	 * @return 
	 */
	public abstract int getShotsFired();

	/**
	 * Returns the number of hits recorded (in this game). All hits are
		counted, not just the first time a given square is hit.
	
	 * @return hitCount
	 */
	public abstract int getHitCount();

	/**
	 * Returns the number of ships sunk (in this game).
	 * @return shipsSunk
	 */
	public abstract int getShipsSunk();

	/**Returns true if all ships have been sunk, otherwise false
	 * @return
	 */
	public abstract boolean isGameOver();

	/**
	 * Returns the 10x10 array of ships, only used for test purposes
	 * @return
	 */
	public abstract Ship[][] getShipArray();

	/**
	 * Prints the ocean
	 */
	public abstract void print();

}