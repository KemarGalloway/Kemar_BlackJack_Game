package KMRGames;


 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.applet.Applet;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

//--------------------------------------------------------------------------------------------------------------------------------------------------
public class UserInterface extends JApplet //-----------------------| CLASS 
{

    public final static void setDesign()
    {
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception e){   
        }
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------

static class BlackjackFrame extends Frame //-----------------------| CLASS 
{ 

//--------------------------------------------------------------------------------------------------------------------------------------------------

	   class WindowData extends WindowAdapter //-----------------------| CLASS 
	   {
	      public void windoIconified(WindowEvent e) {
		  System.out.println("MINIMIZED");
	      }

	      public void windowDeiconified(WindowEvent e) {
		  System.out.println("RESTORED");
	      }
	      public void windowClosing(WindowEvent e) {
		  System.out.println("CLOSED");
		  setVisible(false);
		  dispose();
		  System.exit(0);
	      }	

	     
	   }
        
	   public BlackjackFrame (String name) {
	      setTitle(name);
	      addWindowListener(new WindowData());
        }
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------

    public void initiallize() {
       Dimension dimen = new Dimension(120, 50);
     
       BlackJackTable table = new BlackJackTable();
       getContentPane().add(table, BorderLayout.CENTER);
       
       JPanel buttonPanel = new JPanel();
       buttonPanel.setBackground(Color.black);
       getContentPane().add(buttonPanel, BorderLayout.SOUTH);
       
       JButton newGame = new JButton( "New Game" );
       newGame.setPreferredSize(dimen);
       newGame.setForeground(Color.yellow);
       newGame.setFont(new Font("Chiller", Font.BOLD, 25));
       newGame.addActionListener(table);
       buttonPanel.add(newGame);
       newGame.setBackground(Color.DARK_GRAY);

       JButton hit = new JButton( "Hit!" );
       hit.setPreferredSize(dimen);
       hit.setForeground(Color.yellow);
       hit.setFont(new Font("Chiller", Font.BOLD, 25));
       hit.addActionListener(table);
       buttonPanel.add(hit);
       hit.setBackground(Color.DARK_GRAY);
       
       JButton stand = new JButton( "Stand!" );
       stand.setPreferredSize(dimen);
       stand.setForeground(Color.yellow);
       stand.setFont(new Font("Chiller", Font.BOLD, 25));
       stand.addActionListener(table);
       buttonPanel.add(stand);
       stand.setBackground(Color.DARK_GRAY);
       
      
       JButton exitGame = new JButton( "Exit" );  // Exit added
       exitGame.setPreferredSize(dimen);
       exitGame.setForeground(Color.black);
       exitGame.setFont(new Font("Chiller", Font.BOLD, 25));
       exitGame.addActionListener(table);
       buttonPanel.add(exitGame);
       exitGame.setBackground(Color.red);
       
    }

//--------------------------------------------------------------------------------------------------------------------------------------------------
 
    class BlackJackTable extends JPanel implements ActionListener {//-----------------------| CLASS 
       Deck deck;		   
       String displayMessage; 
       boolean runningSate; 
       GameHand dealer;   
       GameHand player;        
       Font bigFont;     
       Font smallFont;   
       Font bigGameTitle;
       Font inventorName;

       BlackJackTable() {
       	
	      setBackground(Color.black);
          smallFont = new Font("Lucida Sans", Font.BOLD, 12);
          bigFont = new Font("Lucida Sans", Font.BOLD, 20);
          bigGameTitle = new Font("Segoe UI", Font.BOLD, 60);
          inventorName = new Font("Old English Text MT",Font.PLAIN, 30);
          iNewGame();
       }
//--------------------------------------------------------------------------------------------------------------------------------------------------

       public void actionPerformed(ActionEvent evt) {

          String userClick = evt.getActionCommand();
          if (userClick.equals("Hit!"))
             iHit();
          else if (userClick.equals("Stand!"))
             iStay();
          else if (userClick.equals("New Game"))
             iNewGame();
          else if (userClick.equals("Exit")) 
             iExit();
       }
//--------------------------------------------------------------------------------------------------------------------------------------------------

       void iHit() {
       
    	   if (runningSate == false) {
          
             displayMessage = "Hi there, to start a new game please go ahead and click \"New Game\" ";
             repaint();
             return;
          }
          player.addCard( deck.dealCard() );

          if ( player.getGameValue() > 21 ) {
             displayMessage = "BUSTED!!! Sorry, looks like you have lost. Better luck next time";
             runningSate = false;
          }
          else if (player.getCardCount() == 5) {
             displayMessage = "You win by taking 5 cards without going over 21.";
             runningSate = false;
          }
          else {
             displayMessage = "You have " + player.getGameValue()+ ". Hit or Stand?";
          }
          repaint();
       }
//--------------------------------------------------------------------------------------------------------------------------------------------------

       void iStay() {
    	   
        if (runningSate == false) {
           
             displayMessage = "Hi there, to start a new game please go ahead and click \"New Game\" ";
             repaint();
             return;
          }
          runningSate = false;

          while (dealer.getGameValue() <= 16 && dealer.getCardCount() < 5)
             	dealer.addCard( deck.dealCard() );
           
          if (dealer.getGameValue() > 21)
              displayMessage = "CONGRATS!!! You win! | Dealer has busted with " + dealer.getGameValue() + ".";
          else if (dealer.getCardCount() == 5)
              displayMessage = "Sorry, you lose.  Dealer took 5 cards without exceeding 21.";
          else if (dealer.getGameValue() > player.getGameValue())
              displayMessage = "Sorry Player, you lose, " + dealer.getGameValue()
                  + " to " + player.getGameValue() + ".";
          else if (dealer.getGameValue() == player.getGameValue())
              displayMessage = "Sorry Player, you lose.  Dealer wins on a tie.";
          else
              displayMessage = "You win, " + player.getGameValue() + " to " + dealer.getGameValue() + "!";
          repaint();
       }
//--------------------------------------------------------------------------------------------------------------------------------------------------

       void iNewGame() {
       
    	   if (runningSate) {
             displayMessage = "You have to finish current game before starting a new game!";
             repaint();
             return;
          }

          deck 						= new Deck(); 
          dealer 					= new GameHand(); 
          player 					= new GameHand(); 
          
          deck.randomizeCards();
          dealer.addCard( deck.dealCard() ); 
          dealer.addCard( deck.dealCard() ); 
          player.addCard( deck.dealCard() ); 
          player.addCard( deck.dealCard() ); 

          if (dealer.getGameValue() == 21) {
             displayMessage = "Sorry Player, you lose. Dealer has Blackjack.";
             runningSate = false;
          }
          else if (player.getGameValue() == 21) {
              displayMessage = "CONGRATS!!! | You win! | ****** You have Blackjack ******";
              runningSate = false;
          }
          else {
              displayMessage = "You have " + player.getGameValue() + ". Hit or stand?";
              runningSate = true;
          }
          repaint();
       }
 //--------------------------------------------------------------------------------------------------------------------------------------------------


       void iExit() {
             if (runningSate == false) {
             displayMessage = "Hi there, to start a new game please go ahead and click \"New Game\" ";
             setVisible(false);
             System.exit(1);
             return;
          }
          else {
             displayMessage = "You have to finish current game before starting a new game!";
          }
       repaint();
       }
//--------------------------------------------------------------------------------------------------------------------------------------------------


       public void paintComponent(Graphics g) {
          super.paintComponent(g); 
          g.setFont(inventorName);
          g.setColor(Color.red);
          g.drawString("Galloway's", 400, 20);
          g.setFont(bigGameTitle);
          g.setColor(Color.GREEN);
          g.drawString("BLACKJACK", 310, 75);
          g.setFont(bigFont);
          g.setColor(Color.cyan);
          g.drawString(displayMessage, 10, getSize().height - 20);

       
          g.drawString("Dealer's Cards\n", 10, 130);
          g.drawString("Player's Cards\n", 10, 290);

          g.setFont(smallFont);
          if (runningSate)
             drawCard(g, null, 10, 150);
          else
             drawCard(g, dealer.getCard(0), 10, 150);
          for (int i = 1; i < dealer.getCardCount(); i++)
             drawCard(g, dealer.getCard(i), 10 + i * 90, 150);

          for (int i = 0; i < player.getCardCount(); i++)
             drawCard(g, player.getCard(i), 10 + i * 90, 300);

       }
//--------------------------------------------------------------------------------------------------------------------------------------------------


       void drawCard(Graphics g, Card card, int x, int y) {
      
          if (card == null) {  
               // Draws a face-down card
             g.setColor(Color.darkGray);
             g.fillRect(x,y,80,100);
             g.setColor(Color.GREEN);
             g.draw3DRect(x, y, 70, 90, true);
             g.draw3DRect(x, y, 72, 92, true);
             g.draw3DRect(x, y, 74, 94, true);
             g.draw3DRect(x, y, 76, 96, true);
             g.fill3DRect(x, y, 78, 98, true);

             g.draw3DRect(x+9, y+9, 61, 81, true);
             g.setColor(Color.black);
             g.drawString("KMR", x + 15, y + 30);
             g.drawString("Cards", x + 12, y + 70);
            		 
          }
          else {
		  		  // Draws a card
             g.setColor(Color.cyan);
             g.fillRect(x,y,80,100);
             g.setColor(Color.magenta);
             g.drawRect(x,y,79,99);
             g.drawRect(x+1,y+1,77,97);
             if (card.getSuit() == Card.DIAMONDS || card.getSuit() == Card.HEARTS)
                g.setColor(Color.red);
             else
                g.setColor(Color.black);
             
             	g.drawString(card.getValueAsString(), x + 10, y + 30);
                g.drawString("of", x+ 10, y + 50);
                g.drawString(card.getSuitAsString(), x + 10, y + 70);
          }
       }
   
    }
}
//--------------------------------------------------------------------------------------------------------------------------------------------------


