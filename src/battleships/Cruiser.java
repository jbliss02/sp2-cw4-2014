/**
 * 
 */
package battleships;

/**
 * @author Jbliss02
 * Describes a ship of length 3
 */
public class Cruiser extends Ship {

	public Cruiser(){
		super.length = 3;
	}
	@Override 
	public String getShipType() {
		return "Cruiser";
	}
	
	@Override public String toString() {
		return "ndkl";
	}
}

