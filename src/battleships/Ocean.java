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
	
	private IShip[] actualShips = new IShip[10]; //An array of the actual ships in the game
	private int shotsFired; //The total number of shots fired by the user
	private int hitCount; //The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit is counted
	private int shipsSunk; //The number of ships sunk
	
	final int NBATTLESHIPS = 1;
	final int NCRUSIER = 2;
	final int NDESTROYER = 3;
	final int NSUBMARINE = 4;
	
	/**
	 * Creates an empty ocean (fills the ships array with EmptySeas)
	 * initialises any game variables
	 */
	public Ocean(){
		shotsFired = 0;
		hitCount = 0;
		shipsSunk = 0;
		createEmptyOcean();
		setActualShips();
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
									
			//get a random start row and column
			int startRow = random.nextInt(ships.length);
			int startCol = random.nextInt(ships[0].length);
					
			//set a direction to go in from the initial row		
			if(battleship.isHorizontal()){
				
				//go left or right
				boolean right = random.nextBoolean();
				if(right && startCol + battleship.getLength() > ships[0].length){right = false;}//go left if no room right
				if(!right && startCol - battleship.getLength() < 0){right = true;}//go right if no room left
				
				//if there is room then place the ship
				if(roomHorizontally(startRow, startCol, battleship.getLength(), right)){
					
					//update the ship object
					battleship.setBowRow(startRow);
					battleship.setBowColumn(startCol);
					
					//update the ocean
					
					System.out.println("yes horizontal");
					shipPlaced = true;
				}
				else{
					System.out.println("no");
				}				
			}
			else { //vertically
				boolean up = random.nextBoolean(); 
				if(up && startRow - battleship.getLength() < 0){up = false;} //go down if no room to go up
				if(!up && startRow + battleship.getLength() > ships.length){up = true;} //go up if no room down
				
				//check if there is room
				if(roomVertically(startRow, startCol, battleship.getLength(), up)){
					System.out.println("yes vertical");
					shipPlaced = true;
				}
				else{
					System.out.println("no");
				}	
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
		
		//set the start and end cells
		int startCell = moveRight ? startCol : startCol - length + 1;
		int endCell = moveRight ? startCol + length - 1 : startCol;
		
		//iterate through the cells checking if any cannot accommodate a ship
		for(int i = startCell; i < endCell + 1; i++){
			if(!canPlace(startRow, i)){
				return false;
			}	
		}
		
		return true; //if we are here all cells validated so we have room
		
	}//roomHorizontally()
	
	/**
	 * Checks whether a ship can be placed vertically given a start row, start column, ship length and direction of travel
	 * @param startRow
	 * @param startCol
	 * @param length
	 * @param moveUp
	 * @return
	 */
	private boolean roomVertically(int startRow, int startCol, int length, boolean moveUp){
		
		//set the start and end cells
		int startCell = moveUp ? startRow - length + 1 : startRow;
		int endCell = moveUp ? startRow : startRow + length - 1;
		
		//iterate through the cells checking if any cannot accommodate a ship
		for(int i = startCell; i < endCell + 1; i++){
			if(!canPlace(i, startCol)){
				return false;
			}	
		}
	
		return true; //if we are here all cells validated so we have room
					
	}//roomVertically
	

	/**
	 * Checks whether the cell reference passed in is legal to place part of a ship to
	 * Checks both whether that particular cell is occupied
	 * And whether any adjacent cell is occupied
	 * @param row
	 * @param cell
	 * @return
	 */
	private boolean canPlace(int row, int cell){		
		return isOccupied(row, cell) || isAdjacentOccupied(row, cell) ? false : true;
	}
	
	/**
	 * Checks whether cells adjacent to the cells past in are occupied
	 * Ensures ships are not placed with cells adjacent to each other
	 * If cell has no adjacent cells then these are not checked
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isAdjacentOccupied(int row, int col){
		
		if (row > 0 && isOccupied(row - 1, col)){return true;} //check up
		if (row < ships.length - 1 && isOccupied(row + 1, col)){return true;} //check down
		if (col > 0 && isOccupied(row, col - 1)){return true; } //check left
		if (col < ships[0].length - 1 && isOccupied(row, col + 1)){return true; } //check right
		
		return false; //if here no adjacent cells have been occupied
		
	}
	
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
	
	/**
	 * Fills the actualShips Array with the actual
	 * ships that will be visible in the game
	 * uses the class final variables to assign
	 * the correct number of ships, this allows the
	 * number to be easily changed
	 */
	private void setActualShips(){
				 
		actualShips[0] = new Battleship();
		actualShips[1] = new Cruiser();
		actualShips[2] = new Cruiser();
		actualShips[3] = new Destroyer();
		actualShips[4] = new Destroyer();
		actualShips[5] = new Destroyer();
		actualShips[6] = new Submarine();
		actualShips[7] = new Submarine();
		actualShips[8] = new Submarine();
		actualShips[9] = new Submarine();
		
	
	}//setActualShips
	
	
}//Ocean class ends
