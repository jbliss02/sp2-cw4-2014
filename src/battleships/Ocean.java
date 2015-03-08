/**
 * 
 */
package battleships;

/**
 * @author Jbliss02
 * This contains a 10x10 array of Ships, representing the “ocean”, and some
 * methods to manipulate it.
 */
public class Ocean implements IOcean {

	public Ship[][] ships = new Ship[10][10];
	private int shotsFired; //The total number of shots fired by the user
	private int hitCount; //The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit is counted
	private int shipsSunk; //The number of ships sunk
	
	/**
	 * Creates an empty ocean (fills the ships array with EmptySeas)
	 * initialises any game variables
	 */
	public Ocean(){
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
	}
	
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#isOccupied(int, int)
	 */
	@Override
	public boolean isOccupied(int row, int column) {
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#shootAt(int, int)
	 */
	@Override
	public boolean shootAt(int row, int column){
		return false;
	}
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#getShotsFired()
	 */
	@Override
	public int getShotsFired() {
		return shotsFired;
	}
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#getHitCount()
	 */
	@Override
	public int getHitCount(){
		return hitCount;
	}
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#getShipsSunk()
	 */
	@Override
	public int getShipsSunk(){
		return shipsSunk;
	}
	
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#isGameOver()
	 */
	@Override
	public boolean isGameOver(){
		return false;
	}
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#getShipArray()
	 */
	@Override
	public Ship[][] getShipArray(){
		return null;
	}

	
	/* (non-Javadoc)
	 * @see battleships.IOcean#print()
	 */
	@Override
	public void print(){
		
	}
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#placeAllShipsRandomly()
	 */
	@Override
	public void placeAllShipsRandomly() {
		
	}
	
	
}//Ocean class ends
