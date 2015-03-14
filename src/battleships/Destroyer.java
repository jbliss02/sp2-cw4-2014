/**
 * 
 */
package battleships;

/**
 * @author Jbliss02
 * Describes a ship of length 2
 */
public class Destroyer extends Ship {

	public Destroyer(){
		super.length = 2;
	}
	
	@Override 
	public String getShipType() {
		return "Destroyer";
	}
	
	@Override public String toString() {
		if(super.isSunk()){
			return "X";
		}
		else{
			return "S";
		}	
	}
	
}
