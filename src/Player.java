import java.util.ArrayList;

public class Player {
    private String name;
    private int points;
    private ArrayList<Card> hand;
    private int money;
    private int bet;

    public Player(String name) {
        this.points = 0;
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.money = 1000;
        this.bet = 0;
    }

    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.points = 0;
        this.hand = hand;
        this.money = 1000;
        this.bet = 0;
    }
    public Player(String name, int startingMoney) {
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        this.money = startingMoney;
        this.bet = 0;
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

    public void reset() {
        this.hand.clear();
        this.points = 0;
    }

    public void addPoints(int pointsadded) {
        this.points += pointsadded;
    }
    public void addCard(Card card) {
        hand.add(card);
        this.points += card.getValue();
    }
    public void setBet(int betAmount) {
        this.bet += betAmount;
        this.money -= betAmount;
    }
    public void winBet () {
        this.money += this.bet * 2;
        this.bet = 0;
    }
    public void loseBet () {
        this.bet = 0;
    }
    public void pushBet () {
        this.money += this.bet;
        this.bet = 0;
    }
    public int getMoney () {
        return this.money;
    }
    public int getBet () {
        return this.bet;
    }
    @Override
    public String toString() {
        if (!this.name.equals("Dealer")) {
            return name + " has $" + money + "remaining and has placed a bet of $" + bet + "\n" +
                    name + " has " + points + " points " + "\n" +
                    name + "'s cards: " + hand;
        }
        else {
            return name + " has " + points + " points\n" +
                    name + "'s cards: " + hand;
        }
    }
}
