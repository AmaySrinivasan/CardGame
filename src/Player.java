import java.util.ArrayList;

public class Player {
    // Instance variables for player class
    private String name;
    private int points;
    private ArrayList<Card> hand;
    private int money;
    private int bet;
    // Constructor for player class that takes in just name
    public Player(String name) {
        this.points = 0;
        this.name = name;
        this.hand = new ArrayList<Card>();
        this.money = 1000;
        this.bet = 0;
    }
    // Constructor for player class that takes in name and hand
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.points = 0;
        this.hand = hand;
        this.money = 1000;
        this.bet = 0;
    }
    // Used Constructor for Player class that takes in name and starting money. Points start at zero.
    public Player(String name, int startingMoney) {
        this.name = name;
        this.points = 0;
        this.hand = new ArrayList<Card>();
        this.money = startingMoney;
        this.bet = 0;
    }
    // Getter for name
    public String getName() {
        return name;
    }
    // Getter for points
    public int getPoints() {
        return points;
    }
    // Getter for hand
    public ArrayList<Card> getHand() {
        return hand;
    }
    // Resets hands after each round
    public void resetHand() {
        this.hand.clear();
        this.points = 0;
    }
    // Manually increases points
    public void addPoints(int pointsadded) {
        this.points += pointsadded;
    }
    // Adds a card and its value to hand
    public void addCard(Card card) {
        hand.add(card);
        this.points += card.getValue();
    }
    // Creates a player bet by taking bet from their total already
    public void setBet(int betAmount) {
        this.bet += betAmount;
        this.money -= betAmount;
    }
    // When player wins bet, double their bet into their money count
    public void winBet () {
        this.money += this.bet * 2;
        this.bet = 0;
    }
    // When player loses bet, make sure it remains lost from money total
    public void loseBet () {
        this.bet = 0;
    }
    // Returns bet if it is a tie
    public void pushBet () {
        this.money += this.bet;
        this.bet = 0;
    }
    // Getter for the amount of Money a user has
    public int getMoney () {
        return this.money;
    }
    // Getter for the current bet of a user
    public int getBet () {
        return this.bet;
    }
    @Override
    // ToStrings for the user and dealer (changed from assignment to make sense for game)
    public String toString() {
        // To string of player so it says money and bets
        if (!this.name.equals("Dealer")) {
            return name + " has $" + money + " remaining and has placed a bet of $" + bet + "\n" +
                    name + " has " + points + " points " + "\n" +
                    name + "'s cards: " + hand;
        }
        else {
            // Tostring of dealer because dealers don't bet
            return name + " has " + points + " points\n" +
                    name + "'s cards: " + hand;
        }
    }
}
