/**
 * 
 */
package battleships;

/**
 * @author JBliss02	
 * Describes a ship of length 1
 */
public class Submarine extends Ship{

	public Submarine(){
		super.length = 1;
	}
	@Override 
	public String getShipType() {
		return "Submarine";
	}
	
	@Override
	public String toString(){
		if(super.isSunk()){
			return "X";
		}
		else{
			return "S";
		}	
	}
	

	
}
