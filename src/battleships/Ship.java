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
public class Ship implements IShip {

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
	private int bowRow; //the row (0 to 9) which contains the bow (front) of the ship
	private int bowColumn; //the column (0 to 9) which contains the bow (front) of the ship
	protected int length; //the number of squares occupied by the ship
	private boolean horizontal; //true if the ship occupies a single row, false otherwise
	private boolean [] hit = new boolean[4]; // an array of booleans telling whether that part of the ship has been hit

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
	public boolean okToPlaceShipAt(int row, int column, boolean horizontal, IOcean ocean) {
		
		ocean.print();
		
		if(horizontal){
			
			for(int i = column; i < column + length; i++){
				if(ocean.isOccupied(row, i) || isAdjacentOccupied(row, i, ocean)){ return false;}
			}
			
		}
		else { //isVertical
					
			for(int i = row; i < row + length; i++){
				if(ocean.isOccupied(i, column) || isAdjacentOccupied(i, column, ocean)){return false;}
			}
			
		}//whether ship is horizontal
			
		return true; //if we are here nothing has failed so the ship is ok to place here
				
	}//okToPlaceShipAt
	
	/* (non-Javadoc)
	 * @see battleships.Ship#placeShipAt(int, int, boolean, battleships.Ocean)
	 */
	@Override
	public void placeShipAt(int row, int column, boolean horizontal, IOcean ocean) {
		
		if(horizontal){

			for(int i = column; i < column + length; i++){
				ocean.getShipArray()[row][i] = this;
			}
			
		}
		else {
			
			for(int i = row; i < row + length; i++){
				ocean.getShipArray()[i][column] = this;
			}
			
		}//horizontal or not
		
	}//placeShipAt

	/**
	 * Checks whether cells adjacent to the cells past in are occupied
	 * Ensures ships are not placed with cells adjacent to each other
	 * If cell has no adjacent cells then these are not checked
	 * @param row
	 * @param col
	 * @return
	 */
	private boolean isAdjacentOccupied(int row, int col, IOcean ocean){
		
		//better to keep each piece of logic discreetly in its own block rather than
		//having a multilined if statement that is more difficult to read and debug
		
		if (row > 0 && ocean.isOccupied(row - 1, col)){return true;} //check up
		if (row < ocean.getShipArray().length - 1 && ocean.isOccupied(row + 1, col)){return true;} //check down
		if (col > 0 && ocean.isOccupied(row, col - 1)){return true;} //check left
		if (col < ocean.getShipArray()[0].length - 1 && ocean.isOccupied(row, col + 1)){return true;} //check right
			
		if(row > 0 && col < ocean.getShipArray()[0].length - 1   && ocean.isOccupied(row - 1, col + 1)){return true;} //check diagonal top right
		if(row > 0 && col > 0 && ocean.isOccupied(row - 1, col -1)){return true;}  //check diagonal top left		
		if(row <  ocean.getShipArray().length - 1  && col < ocean.getShipArray()[0].length - 1   && ocean.isOccupied(row + 1, col + 1)){return true;}  //check diagonal bottom right		
		if(row < ocean.getShipArray().length - 1   && col > 0 && ocean.isOccupied(row + 1, col - 1)){return true;}  //check diagonal bottom left
		
		return false; //if here no adjacent cells have been occupied
		
	}
	
	/* (non-Javadoc)
	 * @see battleships.Ship#shootAt(int, int)
	 */
	@Override
	public boolean shootAt(int row, int column) {
			
		if(isSunk()) {return false;} //ship has been sunk so cannot have a successful pop at it
		
		if(horizontal){
			
			//check each cell the ship is in
			for(int i = bowColumn; i < bowColumn + length; i++){
				
				if(i == column && row == bowRow){
					hit[i] = true; //mark the cell as hit
					return true; //successful hit
				}
				
			}//for each cell			
		}
		else { //is vertical
			
			//check each cell the ship is in
			for(int i = bowRow; i < bowRow + length; i++){
				
				if(i == row && column == bowColumn){
					hit[i] = true;
					return true;
				}
				
			}//for each cell
			
		}//which way the ship is facing
		
		return false; //if here no hits have been successful
		
	}//shootAt
	
	/* (non-Javadoc)
	 * @see battleships.Ship#isSunk()
	 */
	@Override
	public boolean isSunk(){
		
		for(int i = 0; i < length; i++){
			if(!hit[i]){return false;} //part of ship has not been hit
		}
		
		return true; //if we are here all parts of the ship have been hit
		
	}
	
}

