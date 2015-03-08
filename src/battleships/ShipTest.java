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
	

}
