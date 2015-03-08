/**
 * 
 */
package battleships;

import java.util.Random;

/**
 * @author Jbliss02
 * This contains a 10x10 array of Ships, representing the “ocean”, and some
 * methods to manipulate it.
 */
public class Ocean implements IOcean {

	private Ship[][] ships = new Ship[10][10];
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
		createEmptyOcean();
	}
	
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#isOccupied(int, int)
	 */
	@Override
	public boolean isOccupied(int row, int column) {
		
		if(ships[row][column].getClass().getSimpleName().equals("EmptySea")){
			return false;
		}
		else{
			return true;
		}

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
		
		Random random = new Random();
		
		//start with one ship
		IShip battleship = new Battleship();
		
		//keep randomly placing the ship until it fits somewhere
		boolean shipPlaced = false;
		do
		{
			//randomly place horizontally or vertically
			battleship.setHorizontal(random.nextBoolean());
			
			battleship.setHorizontal(true);
			
			//get a random start row and column
			int startRow = random.nextInt(ships.length);
			int startCol = random.nextInt(ships[0].length);
					
			//set a direction to go in from the initial row		
			if(battleship.isHorizontal()){
				
				//go left or right
				boolean right = random.nextBoolean();
				if(right && startCol + battleship.getLength() > ships[0].length){right = false;}//go left if no room right
				if(!right && startCol - battleship.getLength() < 0){right = true;}//go right if no room left
				
				//check if there is room
				if(roomHorizontally(startRow, startCol, battleship.getLength(), right)){
					System.out.println("yes");
					shipPlaced = true;
				}
				else{
					System.out.println("no");
				}
					
			}
			else {
				boolean up = random.nextBoolean(); 
				if(up && startRow - battleship.getLength() < 0){up = false;} //go down if no room to go up
				if(!up && startRow + battleship.getLength() > ships.length){up = true;} //go up if no room down
			}
		
		
		}while(!shipPlaced);
			
		System.out.print("ship placed");
		
	}//placeAllShipsRandomly()
	
	/**
	 * Checks whether a ship can be placed horizontally given a start row, start column, ship length and direction of travel
	 * @param startRow
	 * @param startCol
	 * @param length
	 * @param moveRight
	 * @return
	 */
	private boolean roomHorizontally(int startRow, int startCol, int length, boolean moveRight){
		
		if(moveRight){
			
			for(int i = startCol; i < startCol + length + 1; i++){
				
				if(isOccupied(startRow, i)){
					return false;
				}
				
			}
			
			return true;
		}
		else{ //moving left
			
			for(int i = startCol; i > startCol - length - 1; i--){
				
				if(isOccupied(startRow, i)){
					return false;
				}
			}
			
			return true;
			
		}//whether we are moving left or right
		
	}//roomHorizontally()
	
	

	/**
	 * Populates the array with EmptyOcean objects,
	 * used at the start of the game to set the ocean to "empty"
	 */
	private void createEmptyOcean(){
		
		for(int i = 0; i < ships.length; i++){
			
			for(int n = 0; n < ships[i].length; n++){
				
				ships[i][n] = new EmptySea();
				
			}//n
			
		}//i
		
	}//createEmptyOcean()
	
	
}//Ocean class ends
