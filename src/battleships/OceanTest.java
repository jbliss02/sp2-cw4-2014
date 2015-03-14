package battleships;

import static org.junit.Assert.*;

import org.junit.Test;


public class OceanTest {

	
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOccupied_allempty(){
		
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				assertEquals("Empty Test", false, new Ocean().isOccupied(row, col));
			}
		}

	}//testIsOccupied_allempty

	@Test
	public void placeShipsByRandom(){
		
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
	}//placeShipsByRandom
	
	//@Test
	public void printOcean()
	{
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();
	}//printOcean

	@Test
	public void shootAtTest(){
		
		Ocean ocean = new Ocean();
		Battleship battleship = new Battleship();
		battleship.setHorizontal(true);
		ocean.getShipArray()[0][0] = battleship;
		ocean.getShipArray()[0][1] = battleship;
		ocean.getShipArray()[0][2] = battleship;
		ocean.getShipArray()[0][3] = battleship;
		
		//row 1 cols 4 to 9 should fail
		for(int i = 4; i < 10; i++){
			assertTrue(!ocean.shootAt(0, 4));
		}
		
		//row 1 + should fail
		for(int row = 1; row < 10; row++){
			for(int col = 0; col < 10; col++){				
				assertTrue(!ocean.shootAt(row, col));
				
			}
		}

		//try the hits
		assertEquals("1", 0, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(!battleship.isSunk());
		assertEquals("1", 0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 1));
		assertTrue(!battleship.isSunk());
		assertEquals("1", 0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 2));
		assertTrue(!battleship.isSunk());
		assertEquals("1", 0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 3));
		assertTrue(battleship.isSunk());
		assertEquals("1", 1, ocean.getShipsSunk());
		
		
	}//shootAtTest
	
	@Test
	public void testShotsMissed(){
		
		//visual tests
		
		Ocean ocean = new Ocean();
		ocean.print();
		ocean.shootAt(0,0);
		ocean.print();
		ocean.shootAt(5,6);
		ocean.print();
				
	}
	
	@Test
	public void testShotsHitandSunk(){
		
		//visual tests	
		Ocean ocean = new Ocean();
		Battleship battleship = new Battleship();
		battleship.setHorizontal(true);
		ocean.getShipArray()[0][0] = battleship;
		ocean.getShipArray()[0][1] = battleship;
		ocean.getShipArray()[0][2] = battleship;
		ocean.getShipArray()[0][3] = battleship;
		
		ocean.print();
		
		//try the hits
		assertEquals("1", 0, ocean.getShipsSunk());
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(!battleship.isSunk());
		ocean.print();
		assertEquals("1", 0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 1));
		assertTrue(!battleship.isSunk());
		ocean.print();
		assertEquals("1", 0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 2));
		assertTrue(!battleship.isSunk());
		ocean.print();
		assertEquals("1", 0, ocean.getShipsSunk());
		
		assertTrue(ocean.shootAt(0, 3));
		assertTrue(battleship.isSunk());
		ocean.print();
		assertEquals("1", 1, ocean.getShipsSunk());
		
	}
	
	@Test
	public void testgameover(){
		
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		ocean.print();
		assertTrue(!ocean.isGameOver());
		
		//brute force
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
					
				ocean.shootAt(row, col);
			}
		}
		ocean.print();
		assertTrue(ocean.isGameOver());
		
	}
	
	
	
}
