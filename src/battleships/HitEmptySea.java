/**
 * 
 */
package battleships;

/**
 * @author Jbliss02
 * Describes a part of the ocean that doesn’t have a ship in it, but has been fired upon
 */

public class HitEmptySea extends Ship{


	/**
	 * This constructor sets the inherited length variable to 1
	 */
	public HitEmptySea(){
		super.length = 1;
	}	
	
	/* 
	 * This method overrides shootAt(int row, int column) that is inherited from Ship, and always returns false to indicate
		that nothing was hit.
	 */
	@Override 
	public boolean shootAt(int row, int column){
		return false;
	}	
	
	/* 
	 * this method overrides isSunk() that is inherited from Ship, and always returns false to indicate that you didn’t sink anything
	 */
	@Override 
	public boolean isSunk(){
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override 
	public String toString(){
		return "-";
	}
	
}
