import javax.swing.*;
import java.awt.*;
public class Card {
    // Instance variables for the Card Class
    private String rank;
    private String suit;
    private int value;
    public Image cardImage;
    private BlackJackViewer viewer;
    //Constructor for the Card Class
    public Card(String rank, String suit, int value, Image cardImage, BlackJackViewer viewer) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.cardImage = cardImage;
        this.viewer = viewer;
    }

    // Getter for Card Rank
    public String getRank() {
        return rank;
    }
    // Lets Each Card Draw Itself
    public void drawCardImage(Graphics g, int locationX, int locationY) {
        g.drawImage(this.cardImage,        // image to draw
                locationX, locationY ,
                100, 140,// (x, y) of upper left corner in output window
                viewer);
    }

    // Setter for Card Rank
    public void setRank(String rank) {
        this.rank = rank;
    }
    // Getter for Card Suit
    public String getSuit() {
        return suit;
    }
    // Setter for Card Suit
    public void setSuit(String suit) {
        this.suit = suit;
    }
    // Getter for Card Value
    public int getValue() {
        return value;
    }
    // Setter for Card Value
    public void setValue(int value) {
        this.value = value;
    }
    // To String for each card
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
