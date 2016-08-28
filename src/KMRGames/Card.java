package KMRGames;

public class Card {
	private final int suit;
	private final int value;
	
	public final static int CLUBS 		= 3;
	public final static int DIAMONDS 	= 2;
	public final static int HEARTS 		= 1;
	public final static int SPADES 		= 0;
	
	public final static int ACE 		= 1;
	public final static int JACK 		= 11;
	public final static int KING 		= 13;	
	public final static int QUEEN 		= 12;
	
	public Card(){
		suit 	= 0;
		value 	= 0; 
	}
	
	public Card(int val, int st){
		suit 	= st;
		value 	= val; 
	}
	
	public int getValue(){
		return value;
	}
	
	public int getSuit(){
		return suit;
	}
	
	public String getSuitAsString() {
	    switch ( suit ) {
	       case CLUBS:   	return "Clubs";
	       case DIAMONDS: 	return "Diamonds";
	       case HEARTS:   	return "Hearts";	       
	       case SPADES:    	return "Spades";
	       default:       	return "??";
	    }
	}
	
	public String getValueAsString() {
	    switch ( value ) {
	       case 1:   return "Ace";
	       case 2:   return "2";
	       case 3:   return "3";
	       case 4:   return "4";
	       case 5:   return "5";
	       case 6:   return "6";
	       case 7:   return "7";
	       case 8:   return "8";
	       case 9:   return "9";
	       case 10:  return "10";
	       case 11:  return "Jack";
	       case 12:  return "Queen";
	       case 13:  return "King";
	       default:  return "??";
	    }
	}
	
	public String toString() {
	       return getValueAsString() + " of " + getSuitAsString();
	}
		
	
	
}
