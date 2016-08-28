package KMRGames;

public class GameHand extends Hand{
	
	public int getGameValue(){

	int computedVal		= 0;     
    boolean ace			= false; 
    int numbCards		= getCardCount();    

    for ( int index = 0;  index < numbCards;  index++ ) {
          // Add the i-th cards value in the hand.
         Card card;    // The i-th card. 
         int cardVal;  // The BJ value of the i-th card.
         card = getCard(index);
         cardVal = card.getValue();  // The normal value (1...13).
         if (cardVal > 10) {
             cardVal = 10;   // Value of Jack, Queen, or King.
         }
         if (cardVal == 1) {
             ace = true;     // At least one ace is there.
         }
         computedVal = computedVal + cardVal;
      }
      if ( ace == true  &&  computedVal + 10 <= 21 )
    	  computedVal += 10;

      return computedVal; 
	}
}
