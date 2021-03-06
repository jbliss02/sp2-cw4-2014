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
	
	@Test
	public void testHorizontal(){

		IShip battleship = new Battleship();
		battleship.setHorizontal(true);
		assertEquals("Battleship", true, battleship.isHorizontal());
		battleship.setHorizontal(false);
		assertEquals("Battleship", false, battleship.isHorizontal());
		
		IShip cruiser = new Cruiser();
		cruiser.setHorizontal(true);
		assertEquals("Cruiser", true, cruiser.isHorizontal());
		cruiser.setHorizontal(false);
		assertEquals("Cruiser", false, cruiser.isHorizontal());
		
		IShip destroyer = new Destroyer();
		destroyer.setHorizontal(true);
		assertEquals("Destroyer", true, destroyer.isHorizontal());
		destroyer.setHorizontal(false);
		assertEquals("Destroyer", false, destroyer.isHorizontal());
		
		IShip submarine = new Submarine();
		submarine.setHorizontal(true);
		assertEquals("Submarine", true, submarine.isHorizontal());
		submarine.setHorizontal(false);
		assertEquals("Submarine", false, submarine.isHorizontal());
		
	}
	
	@Test
	public void gettersSetters(){
		
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 10; col++){
				
				IShip battleship = new Battleship();
				battleship.setBowColumn(col);
				battleship.setBowRow(row);
				assertEquals("Battleship", col, battleship.getBowColumn());
				assertEquals("Battleship", row, battleship.getBowRow());
				
				IShip cruiser = new Cruiser();
				cruiser.setBowColumn(col);
				cruiser.setBowRow(row);
				assertEquals("Cruiser", col, cruiser.getBowColumn());
				assertEquals("Cruiser", row, cruiser.getBowRow());
				
				IShip destroyer = new Destroyer();
				destroyer.setBowColumn(col);
				destroyer.setBowRow(row);
				assertEquals("Destroyer", col, destroyer.getBowColumn());
				assertEquals("Destroyer", row, destroyer.getBowRow());
				
				IShip submarine = new Submarine();
				submarine.setBowColumn(col);
				submarine.setBowRow(row);
				assertEquals("Submarine", col, submarine.getBowColumn());
				assertEquals("Submarine", row, submarine.getBowRow());
				
			}//col
		}//row
		
	}
	
	@Test
	public void okToPlaceShipAt(){
	
		Ocean ocean = new Ocean();
		
		for(int row = 0; row < ocean.getShipArray().length; row++){			
			for(int col = 0; col < ocean.getShipArray()[0].length - 4; col++){
				assertEquals("Empty", true, new Battleship().okToPlaceShipAt(row, col, true, ocean));
			}			
		}
		
		ocean = new Ocean();
		ocean.getShipArray()[0][0] = new Submarine();
		assertEquals("Taken", false, new Battleship().okToPlaceShipAt(0, 0, false, ocean));
		
		//check above
		ocean = new Ocean();
		Submarine submarine = new Submarine();
		submarine.setHorizontal(false);
		ocean.getShipArray()[1][6] = submarine;
		
		//ocean.print();
		
		assertTrue(!new Battleship().okToPlaceShipAt(0, 5, true, ocean));
		assertTrue(!new Battleship().okToPlaceShipAt(0, 6, true, ocean));
		assertTrue(!new Battleship().okToPlaceShipAt(0, 7, true, ocean));
		
		assertTrue(!new Battleship().okToPlaceShipAt(2, 5, true, ocean));
		assertTrue(!new Battleship().okToPlaceShipAt(2, 6, true, ocean));
		assertTrue(!new Battleship().okToPlaceShipAt(2, 7, true, ocean));
		
		assertTrue(!new Battleship().okToPlaceShipAt(1, 2, true, ocean));

		
		
	}//okToPlaceShipAt
	
	@Test 
	public void placeShipAt(){
		
		Ocean ocean; 
		Battleship battleship;
		
		ocean = new Ocean();
		battleship = new Battleship();
		battleship.placeShipAt(0,0,false,ocean);
		
		assertEquals("Placed", true, ocean.isOccupied(0, 0));
		
		for(int row = 1; row < 10; row++){
			for(int col = 1; col < 10; col++){
				
				battleship = new Battleship();
				battleship.placeShipAt(0,0,false,ocean);
				
				assertEquals("Placed", false, ocean.isOccupied(row, col));
			}
		}
				
	}//placeShipAt
	
	@Test
	public void shootandsinktest() {
		
		Ocean ocean = new Ocean();
		Battleship battleship = new Battleship();
		battleship.setHorizontal(true);
		battleship.placeShipAt(0, 0, true, ocean);
		
		//ship should not be sunk
		assertTrue(!battleship.isSunk());
		
		//row 1 lower should all be unsuccessful
		for(int row = 1; row < 10; row++){
			for(int col = 0; col < 10; col++){				
				assertTrue(!battleship.shootAt(row, col));			
			}	
		}
		
		//ship should not be sunk
		assertTrue(!battleship.isSunk());
		
		//row 0 first 4 should be successful, last 6 should not
		for(int i = 0; i < 4; i++){			
			assertTrue(battleship.shootAt(0, i));		
			if(i < 3){assertTrue(!battleship.isSunk());}
		}
		
		//ship should be sunk
		assertTrue(battleship.isSunk());

	}//isSunkTest
	

}
