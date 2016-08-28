package KMRGames;

import java.util.*;
public class Hand {
	private Vector hand;
	
	public Hand(){
		hand = new Vector();
	}
	
	public void clear(){
		hand.removeAllElements();
	}
	
	public void addCard(Card crd){
		if(crd != null)
			hand.addElement(crd);
	}
	
	public void removeCard(Card crd){
		hand.remove(crd);
	}
	
	public int getCardCount(){
		return hand.size();
	}
	
	public Card getCard(int pos){
		if(pos >= 0 && pos < hand.size()) {
			return (Card)hand.elementAt(pos);
		}else{
			return null;
		}
	}
	
	public void suitSort() {
      Vector newHand = new Vector();
	  while (hand.size() > 0) {
	     int pos = 0;  // Position of smallest card.
	     Card crd = (Card)hand.elementAt(0);  // Smallest card.
	     for (int i = 1; i < hand.size(); i++) {
	        Card c1 = (Card)hand.elementAt(i);
	        if ( c1.getSuit() < crd.getSuit() || (c1.getSuit() == crd.getSuit() && c1.getValue() < crd.getValue()) ) {
	            pos = i;
	            crd = c1;
	        }
	     }
         hand.removeElementAt(pos);
         newHand.addElement(crd);
      }
      hand = newHand;
	   }
	
   public void valueSort() {
      Vector newHand = new Vector();
      while (hand.size() > 0) {
         int pos = 0;  // Position of smallest card.
         Card crd = (Card)hand.elementAt(0);  // Smallest card.
         for (int i = 1; i < hand.size(); i++) {
            Card c1 = (Card)hand.elementAt(i);
            if ( c1.getValue() < crd.getValue() || (c1.getValue() == crd.getValue() && c1.getSuit() < crd.getSuit()) ) {
                pos = i;
                crd = c1;
            }
         }
         hand.removeElementAt(pos);
         newHand.addElement(crd);
      }
      hand = newHand;
   }

}
