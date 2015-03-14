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
		
		if(ships[row][column].getClass().getSimpleName().equals("EmptySea")){ //change this to get ship type???
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
		
		System.out.println();
		System.out.print(" ");
		for(int i = 0; i < ships.length; i++){
			System.out.print(i);
		}
		
		System.out.println();
		
		for(int row = 0; row < ships.length; row++){
			
			System.out.print(row);
			
			for(int col = 0; col < ships[0].length; col++)
			{

				System.out.print(ships[row][col].toString());
				
			}//col
			
			System.out.println();
			
		}//row
		
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
				
				int cellsWanted[][] = new int[ship.getLength()][2];
				
				//go a random direction 
				if(ship.isHorizontal()){
					
					//go left or right
					boolean moveRight = random.nextBoolean();
					if(moveRight && startCol + ship.getLength() > ships[0].length){moveRight = false;}//go left if no room right
					if(!moveRight && startCol - ship.getLength() < 0){moveRight = true;}//go right if no room left
					
					//set the start and end cells
					int startCell = moveRight ? startCol : startCol - ship.getLength() + 1;
					
					//set cells wanted array
					for(int i = 0; i < ship.getLength(); i++){
						cellsWanted[i][0] = startRow;
						cellsWanted[i][1] = startCell + i;
					}
					
				} else {
					
					//ship is vertical so go up or down
					boolean moveUp = random.nextBoolean(); 
					if(moveUp && startRow - ship.getLength() < 0){moveUp = false;} //go down if no room to go up
					if(!moveUp && startRow + ship.getLength() > ships.length){moveUp = true;} //go up if no room down
					
					//set the start and end cells
					int startCell = moveUp ? startRow - ship.getLength() + 1 : startRow;

					//set the cells wanted array
					for(int i = 0; i < ship.getLength(); i++){
						cellsWanted[i][0] = startCell + i;
						cellsWanted[i][1] = startCol;
					}
					
				}//if ship is horizontal or vertical

				//try and place this ship in the cells specified
				shipPlaced = tryPlaceShip(ship, cellsWanted);
				
			}while(!shipPlaced);
						
		}//for each ship in actualShips (shipCount)
			
	}//placeAllShipsRandomly()
	

	/**
	 * Given a ship and a set of co-ordinates this method will check whether
	 * the cells are all available, if they are then the cells are
	 * allocated to this ship
	 * @param ship
	 * @param start
	 * @return
	 */
	private boolean tryPlaceShip(Ship ship, int[][]cellsWanted){

		for(int i = 0; i < cellsWanted.length; i++){
			
			if(!canPlace(cellsWanted[i][0], cellsWanted[i][1])){
				return false;
			}
			
		}//for each cell wanted
		
		//if here then all cells are available so place this ship
		
		for(int i = 0; i < cellsWanted.length; i++){
			
			ships[cellsWanted[i][0]][cellsWanted[i][1]] = ship;
			
		}//for each cell wanted
		
		return true;
		
	}//tryPlaceShip
	
	

	
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
		
		//better to keep each piece of logic discreetly in its own block rather than
		//having a multilined if statement that is more difficult to read and debug
		
		if (row > 0 && isOccupied(row - 1, col)){return true;} //check up
		if (row < ships.length - 1 && isOccupied(row + 1, col)){return true;} //check down
		if (col > 0 && isOccupied(row, col - 1)){return true;} //check left
		if (col < ships[0].length - 1 && isOccupied(row, col + 1)){return true;} //check right
			
		if(row > 0 && col < ships[0].length - 1 && isOccupied(row - 1, col + 1)){return true;} //check diagonal top right
		if(row > 0 && col > 0 && isOccupied(row - 1, col -1)){return true;}  //check diagonal top left		
		if(row < ships.length - 1 && col < ships[0].length - 1 && isOccupied(row + 1, col + 1)){return true;}  //check diagonal bottom right		
		if(row < ships.length - 1 && col > 0 && isOccupied(row + 1, col - 1)){return true;}  //check diagonal bottom left
		
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
