package battleships;

import static org.junit.Assert.*;

import org.junit.Test;


public class OceanTest {

	
	public void test() {
		fail("Not yet implemented");
	}

	//@Test
	public void testIsOccupied_allempty(){
		
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				assertEquals("Empty Test", false, new Ocean().isOccupied(row, col));
			}
		}

	}//testIsOccupied_allempty

	//@Test
	public void placeShipsByRandom(){
		
		new Ocean().placeAllShipsRandomly();
		
	}
	
	@Test
	public void printOcean()
	{
		
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();
	}
	
}
