package KMRGames;

public class Deck {
	private Card[] deck;
	private int processedCards;
	
	public Deck(){
		deck = new Card[52];
		int cardCreated = 0;
		for(int suit = 0; suit <=3; suit++){
			for(int value = 1; value <= 13; value++){
				deck[cardCreated] = new Card(value, suit);
				cardCreated++;
			}
		}
		processedCards = 0;
	}
	
	public void randomizeCards(){
	  for ( int index = 51; index > 0; index-- ) {
            int rand = (int)(Math.random()*(index+1));
            Card temp = deck[index];
            deck[index] = deck[rand];
            deck[rand] = temp;
        }
        processedCards = 0;
	}

    public int cardsRemaining() {
        return 52 - processedCards;
    }
    
    public Card dealCard() {
        if (processedCards == 52)
           randomizeCards();
        processedCards++;
        return deck[processedCards - 1];
    }
}
