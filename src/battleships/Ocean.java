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
	private Ship[] actualShips = new Ship[10]; //An array of the actual ships in the game
	
	private boolean[][] shotsReceived = new boolean[10][10];
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
	}//isOccupied
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#shootAt(int, int)
	 */
	@Override
	public boolean shootAt(int row, int column){
		
		//update the flags that are set with each shot
		shotsFired++; 
		shotsReceived[row][column] = true;
		
		//set a flag so we can determine whether his shot has sunk the ship
		boolean hasSunk = false;
		if(ships[row][column].isSunk()){hasSunk = true;}
		
		//see if this shot hit a ship
		if(ships[row][column].shootAt(row, column)){
			hitCount++;
			if(!hasSunk && ships[row][column].isSunk()){shipsSunk++;}
			return true;
		}
		else {
			
			ships[row][column] = new HitEmptySea(); //mark this bit of the ocean as being hit
			return false;
		}
		
	}//shootAt
	
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
		
		boolean allSunk = true; //try to disprove
		
		for(int i = 0; i < actualShips.length; i++){
			
			if(!actualShips[i].isSunk()){
				allSunk = false;
				break; //no point continuing
			}
			
		}//for each ship
		
		return allSunk;
		
	}//isGameOver
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#getShipArray()
	 */
	@Override
	public Ship[][] getShipArray(){
		return ships;
	}

	/* (non-Javadoc)
	 * @see battleships.IOcean#print()
	 */
	@Override
	public void print(){
		
		System.out.println();
		System.out.print(" ");
		for(int i = 0; i < ships.length; i++){
			System.out.print(i);
		}
		
		System.out.println();
		
		for(int row = 0; row < ships.length; row++){
			
			System.out.print(row); //print the header
			
			for(int col = 0; col < ships[0].length; col++)
			{			
				//print the cell
				if(shotsReceived[row][col]){
					System.out.print(ships[row][col].toString());
				}
				else{
					System.out.print(".");
				}

			}//col
			
			System.out.println(""); 
			
		}//row
		
		System.out.println(""); //give some space
		
	}//print()
	
	
	/* (non-Javadoc)
	 * @see battleships.IOcean#placeAllShipsRandomly()
	 */
	@Override
	public void placeAllShipsRandomly() {
		
		Random random = new Random();
		
		for(int shipCount = 0; shipCount < actualShips.length; shipCount++){
			
			Ship ship = actualShips[shipCount]; //assign a ship for this iteration
			
			//keep randomly placing the ship until it fits somewhere
			boolean shipPlaced = false;
			do
			{
				//randomly place horizontally or vertically
				ship.setHorizontal(random.nextBoolean());
										
				//get a random start row and column
				int startRow = random.nextInt(ships.length);
				int startCol = random.nextInt(ships[0].length);
								
				//go a random direction 
				if(ship.isHorizontal()){
					
					//go left or right
					boolean moveRight = random.nextBoolean();
					if(moveRight && startCol + ship.getLength() > ships[0].length){moveRight = false;}//go left if no room right
					if(!moveRight && startCol - ship.getLength() < 0){moveRight = true;}//go right if no room left
					
					//re-set the starting column so the bow is always the left most column
					startCol = moveRight ? startCol : startCol - ship.getLength() + 1;
					
				} 
				else {
					
					//ship is vertical so go up or down
					boolean moveUp = random.nextBoolean(); 
					if(moveUp && startRow - ship.getLength() < 0){moveUp = false;} //go down if no room to go up
					if(!moveUp && startRow + ship.getLength() > ships.length){moveUp = true;} //go up if no room down
					
					//re-set the starting row so the bow is always the lowest value row
					startRow = moveUp ? startRow - ship.getLength() + 1 : startRow;
				
				}//if ship is horizontal or vertical

				if(ship.okToPlaceShipAt(startRow, startCol, ship.isHorizontal(), this)){
					ship.placeShipAt(startRow, startCol, ship.isHorizontal(), this);
					shipPlaced = true;
				}
									
			}while(!shipPlaced);
						
		}//for each ship in actualShips (shipCount)
			
	}//placeAllShipsRandomly()
	

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
