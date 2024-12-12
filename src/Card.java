public class Card {
    // Instance variables for the Card Class
    private String rank;
    private String suit;
    private int value;

    //Constructor for the Card Class
    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
    }

    // Getter for Card Rank
    public String getRank() {
        return rank;
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
