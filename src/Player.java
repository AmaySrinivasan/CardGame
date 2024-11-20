import java.util.ArrayList;

public class Player {
    private String name;
    private int points;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.points = 0;
        this.name = name;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.points = 0;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public void addPoints(int pointsadded) {
        this.points += pointsadded;
    }
    public void addCard(Card card) {
        hand.add(card);
    }

    @Override
    public String toString() {
        return name + "has" + points + "points" + "\n" +
                name + "'s cards: " + hand;
    }
}
