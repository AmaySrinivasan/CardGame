import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Deck {
    // Instance variables for the deck class
    public ArrayList<Card> cards;
    private int cardsLeft;
    private Image cardImage;
    // Constructor for deck class
    public Deck(String ranks[], String suits[], int values[],BlackJackViewer viewer) {
        cards = new ArrayList<Card>();
        // Creates a new card that holds a unique suit and rank, and blackjack assigned values, as well as a cardImage for each card
        for (int i = 0; i < ranks.length; i++) {
            for (int j = 0; j < suits.length; j++) {
                cardImage = new ImageIcon("Resources/" + (i*4 + j +1) + ".png").getImage();
                cards.add(new Card(ranks[i], suits[j], values[i], cardImage,viewer));
            }
        }
        cardsLeft = cards.size();
        shuffle();
    }
    // Returns the arraylist of cards
    public  ArrayList<Card> getCards() {
        return cards;
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
