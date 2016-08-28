package KMRGames;

import KMRGames.UserInterface.BlackjackFrame;

public class Driver {
    public static void main(String[] args) {
	   
    	gameStarter();
    }  
    
    public static void gameStarter() {
		System.out.println("STARTING GAME");
		
		UserInterface uInterface = new UserInterface();
		uInterface.setDesign();       
		uInterface.initiallize();
		
		BlackjackFrame frame =	new BlackjackFrame("BLACKJACK");
		
		frame.add("Center",uInterface);
		frame.setSize(1000,700);
		frame.setVisible(true);
		frame.setResizable(false);
    }

}
