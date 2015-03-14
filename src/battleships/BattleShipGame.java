/**
 * 
 */
package battleships;
import java.util.Scanner;
/**
 * @author Jbliss02
 *The BattleshipGame class is the main class — that is, it contains a main method. In
	this class you will set up the game; accept shots from the user; display the results; print
	final scores; and ask the user if s/he wants to play again.
	
	All input/output is done here (although some of it is done by calling a print()
	method in the Ocean class.) All computation will be done in the Ocean class and the
	various Ship classes.

 */
/**
 * @author J
 *
 */
/**
 * @author J
 *
 */
public class BattleShipGame {

	/**
	 * @param args
	 */
	
	private Ocean ocean; //the specific instance of ocean for this game
	private Scanner sc; //takes the user input;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.print("Welcome to Battleships; the game of highly coupled classes, but the spec has to be followed...\n\n");
		
		 new BattleShipGame().startGame();
		

		
	}

	/**
	 * Sets up the game and then starts the game
	 * once the first game has finished continues playing
	 * the game if the user selects so
	 */
	private void startGame(){
		
		setupGame();
		playGame();
		
		while(playAgain()){
			playGame();
		}
				
	}//startGame
	
	
	/**
	 * Runs the game and continues until the game is over
	 */
	private void playGame(){

		do{
			int row = getRow();
			int col = getColumn();
			
			//redraw the screen and show any information required
			
			if(ocean.shootAt(row, col)){				
				System.out.println("Hit");
			}
			else{
				System.out.println("Miss");
			}
							
			ocean.print();
			
		}while(!ocean.isGameOver());
	
		System.out.println("Game over in " + ocean.getShotsFired() + " shots");
		
	}
	
	/**
	 * Prompts the user to enter a row, 
	 * @return row user has selected
	 */
	private int getRow(){
		
		System.out.print("Enter a row to shoot at:    ");
		return getNumberInput();
	}
	
	/**
	 * Prompts the user to enter a column, 
	 * @return column user has selected
	 */
	private int getColumn(){
		System.out.print("Enter a column to shoot at: ");
		return getNumberInput();
	}
	
	
	/**
	 * Asks the user whether they want to play again
	 * @return - user choice
	 */
	private boolean playAgain(){
	
		System.out.println("Press Y to play again or any other key to exit");
	
		if(sc.next().toLowerCase().equals("y")){
			return true;
		}
		else {
			return false;
		}
	}//playAgain
	
	
	/**
	 * Gets and returns a numeric input from the user
	 * Ensures that the number inputed is valid for the current
	 * game. Used to obtain value for both columns and rows
	 * so assumes the game always has equal number of sides
	 * @return
	 */
	private int getNumberInput(){
		
		int input; //what the user enters and what will be returned
		
		//test for non integers
		while(!sc.hasNextInt()) //non ints
		{
			System.out.print("Numbers only, try again: ");
			sc.next();
		}
		
		input = sc.nextInt();

		if(input < 0 || input > ocean.getShipArray().length - 1){
			
			while(input < 0 || input > ocean.getShipArray().length - 1){
				System.out.print("Has to be between 0 and " + (ocean.getShipArray().length - 1) +  ", try again: ");
				input = sc.nextInt();
			}
			
		}//whether number is in range
		
		return input;		

	}//getNumberInput
	
	
	/**
	 * Initialises ocean and prints it (so prints a ocean with no shots received)
	 * And initialises scanner
	 */
	private void setupGame(){
		
			sc = new Scanner(System.in); 
		 	ocean = new Ocean();
			ocean.placeAllShipsRandomly();
			ocean.print();
	}
	
	
}
