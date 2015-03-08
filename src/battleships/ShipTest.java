package battleships;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShipTest {


	public void test() {
		fail("Not yet implemented");
	}
	
	@Test 
	public void testType(){	
		assertEquals("Battleship", "Battleship", new Battleship().getShipType());
		assertEquals("Cruiser", "Cruiser", new Cruiser().getShipType());
		assertEquals("Destroyer", "Destroyer", new Destroyer().getShipType());
		assertEquals("Submarine", "Submarine", new Submarine().getShipType());
	}
	
	@Test
	public void testLength(){
		assertEquals("Battleship", 4, new Battleship().getLength());
		assertEquals("Cruiser", 3, new Cruiser().getLength());
		assertEquals("Destroyer", 2, new Destroyer().getLength());
		assertEquals("Submarine", 1, new Submarine().getLength());		
	}
	

	public void gettersSetters(){
		
		
		
	}
	

}
