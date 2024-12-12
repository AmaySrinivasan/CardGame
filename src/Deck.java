import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(String ranks[], String suits[], int values[]) {
        cards = new ArrayList<Card>();
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card(ranks[i], suit, values[i]));
            }
            // for (int j = 0; j < suits.length; j++) {
                // for (int k = 0; k < values.length; k++) {
                    // Card tempCard = new Card(ranks[i], suits[j], values[k]);
                    // cards.add(tempCard);
                // }
            // }
        }
        cardsLeft = cards.size();
        shuffle();
    }
    public boolean isEmpty() {
        if (cardsLeft == 0) {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }
    public Card deal(){
        if (isEmpty() == true) {
            return null;
        }
        return cards.get(--cardsLeft);
    }
    public void shuffle() {
        cardsLeft = cards.size();
        for (int i = cardsLeft - 1; i >= 0; i--) {
            int r = (int) (Math.random() * cardsLeft);
            Card tempCard = cards.get(r);
            cards.set(r, cards.get(i));
            cards.set(i, tempCard);
        }
    }
}
