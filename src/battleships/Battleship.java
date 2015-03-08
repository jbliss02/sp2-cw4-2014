/**
 * 
 */
package battleships;

/**
 * @author Jbliss02
 * Describes a ship of length 4
 */

public class Battleship extends Ship{

	
	public Battleship(){
		super.length = 4;
	}
	
	@Override 
	public String getShipType() {
		return "Battleship";
	}
	
	@Override public String toString() {
		return "ndkl";
	}
}
