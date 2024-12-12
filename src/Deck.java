import java.util.ArrayList;

public class Deck {
    // Instance variables for the deck class
    private ArrayList<Card> cards;
    private int cardsLeft;
    // Constructor for deck class
    public Deck(String ranks[], String suits[], int values[]) {
        cards = new ArrayList<Card>();
        // Creates a new card that holds a unique suit and rank, and blackjack assigned values
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit, values[i]));
            }
        }
        cardsLeft = cards.size();
        shuffle();
    }
    // Checks if the deck has any cards left
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }
    // Returns number of cards left in deck
    public int getCardsLeft() {
        return cardsLeft;
    }
    // If there is a card in the deck, deal/remove from deck
    public Card deal(){
        if (isEmpty()) {
            return null;
        }
        return cards.get(--cardsLeft);
    }
    // Shuffles order of cards, resets to original deck count
    public void shuffle() {
        cardsLeft = cards.size();
        // Randomly changes order of deck
        for (int i = cardsLeft - 1; i >= 0; i--) {
            int r = (int) (Math.random() * cardsLeft);
            Card tempCard = cards.get(r);
            cards.set(r, cards.get(i));
            cards.set(i, tempCard);
        }
    }
}
