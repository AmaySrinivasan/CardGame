// Blackjack Card Game by Amay Srinivasan
import java.util.Scanner;

public class Game {
    // Instance Variables for Game Class
    private Player player;
    private Deck deck;
    private Player dealer;
    // Constructor for Game Class
    public Game(String[] ranks, String[] suits, int[] values) {
        deck = new Deck(ranks, suits, values);
        Scanner input = new Scanner(System.in);
        // Takes in user's name
        System.out.println("Please enter your name: ");
        String name = input.nextLine();
        // Users set their starting money total
        System.out.println("Please enter your starting money: ");
        int startingMoney = input.nextInt();
        player = new Player(name,startingMoney);
        dealer = new Player("Dealer", 0);
    }
    public void playGame() {
        // Prints game instructions
        printInstructions();
        Scanner input = new Scanner(System.in);
        // Users can play as long as their money total is greater than 0.
        while (player.getMoney() > 0) {
            // Informs users of current total money
            System.out.println("You currently have $" + player.getMoney() + ".");
            // Runs game setup
            setupGame();
            // Player makes their decision
            makePlayerMove();
            // Runs through winning logic
            determineWinner();
            // If they run out of total money, the game ends
            if (player.getMoney() <= 0) {
                System.out.println("Sorry! You don't have enough money to keep playing!");
                break;
            }
            // Otherwise, they are invited to play another round
            else {
                System.out.println("Do you want to play another round? (yes)");
                input.nextLine();
                // Game also ends if they don't say yes to playing again
                if (!input.nextLine().equals("yes")) {
                    break;
                }
            }
        }
        // Informs players of their final total
        System.out.println("Game over! You finished with $" + player.getMoney());
    }
    //Prints instructions for BlackJack
    public void printInstructions(){
        System.out.println("Welcome to the game of Blackjack!");
        System.out.println("The goal is to get as close as possible with the sum of your cards to 21 points without exceeding 21.");
        System.out.println("Aces are worth 11 and Jack, Queen Kings, are worth 10.");
        System.out.println("You are playing against the dealer");
        System.out.println("You lose if your cards exceed 21(bust) or if the dealer has a higher sum than you");
        System.out.println("You win if you have a higher set of cards than the dealer, or if the dealer exceeds 21(busts)");
        System.out.println("Before each round starts, you will be asked to place a bet");
        System.out.println("If you win, you win you'll win double your bet, but if you lose you'll lose your bet. (Ties you have your bet returned)");
        System.out.println();
        // Checks to see if they are ready to play
        Scanner input = new Scanner(System.in);
        System.out.println("Do you understand the rules, and are you ready to play? (yes)");
        String response = input.nextLine();
        if (!response.equals("yes")) {
            printInstructions();
        }
    }
    // Takes in a user's bet
    public int takeBet() {
        // Asks them for a bet
        Scanner input = new Scanner(System.in);
        System.out.println("How much do you want to bet? ");
        int bet = input.nextInt();
        // Checks to see if bet is valid(greater than 0 and less than their total)
        while (bet <= 0 || bet > player.getMoney()) {
            // If not valid, prompt them until they enter a valid bet
            if (bet <= 0) {
                System.out.println("Bets must be greater than 0. How much would you like to bet?");
            } else {
                System.out.println("You don't have enough for that bet! You have $" + player.getMoney() + ". How much would you like to bet?");
            }
            bet = input.nextInt();
        }
        // Takes in valid bet
        return bet;
    }
    public void setupGame () {
        // Uses the user's valid bet and takes it out of total
        player.setBet(takeBet());
        // Resets the hands and shuffles deck
        deck.shuffle();
        player.resetHand();
        dealer.resetHand();
        // Deals player and dealer cards
        player.addCard(deck.deal());
        player.addCard(deck.deal());
        dealer.addCard(deck.deal());
        dealer.addCard(deck.deal());
        // Informs player of cards, but only one of dealer's is face-up
        System.out.println(player);
        System.out.println("Dealers cards: " + dealer.getHand().get(0) + " and [You don't get to see this card yet]");
    }
    private void makePlayerMove() {
        // Lets user play as long as they haven't busted
        while (player.getPoints() <= 21) {
            // Asks them for their decision
            System.out.println("Your current points: " + player.getPoints() + ". Would you like to hit or stand? ");
            Scanner input = new Scanner(System.in);
            String response = input.nextLine();
            // If they hit deal them another card
            if (response.equals("hit")) {
                Card drawnCard = deck.deal();
                player.addCard(drawnCard);
                System.out.println("You drawn card is: " + drawnCard);
                System.out.println(player);
                // Check again if they bust
                if (player.getPoints() > 21) {
                    break;
                }
            }
            // If they stand, then end the turn
            else if (response.equals("stand")) {
                System.out.println(player.getName() + " has stood.");
                break;
            }
            // Ensure they enter a valid option
            else {
                System.out.println("That's not a valid option");
            }
        }
    }
    public void determineWinner() {
        // If user busts, they automatically lose(and lose bet)
        if (player.getPoints() > 21) {
            System.out.println("You busted! You Lost!");
            player.loseBet();
        }
        else {
            // If they don't bust then it is the dealers turn
            makeDealerMove();
            // If the dealer busts, they win(and win bet)
            if (dealer.getPoints() > 21) {
                System.out.println("Dealer busted! You won!");
                player.winBet();
            }
            // If the player outscores the dealer they win (and win bet)
            else if (player.getPoints() > dealer.getPoints()) {
                System.out.println("Your " + player.getPoints() + " points is more than the Dealer's " + dealer.getPoints() + "!");
                System.out.println("You just won!");
                player.winBet();
            }
            // If the dealer outscores the player, they lose (and lose bet)
            else if (player.getPoints() < dealer.getPoints()) {
                System.out.println("Sorry! The dealer had more with " + dealer.getPoints() + " compared to your " + player.getPoints());
                System.out.println("You Lost!");
                player.loseBet();
            }
            // If they tie in total values, bets are returned(pushed)
            else {
                System.out.println("Your point values are the same at " + player.getPoints());
                System.out.println("It's a tie!");
                player.pushBet();
            }
        }
    }
    // Dealer's turn
    private void makeDealerMove() {
        // Pre-determined BlackJack dealer logic, hits on anything under 17
        while (dealer.getPoints() < 17) {
            Card drawnCard = deck.deal();
            dealer.addCard(drawnCard);
        }
        // Lets user know what the dealer had
        System.out.println("Final Dealer's hand: " + dealer);
    }

    public static void main(String[] args) {
        // Ranks for every card
        String ranks[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        // Suits for every card
        String suits[] = {"Hearts", "Clubs", "Diamonds", "Spades"};
        // Blackjack values for every card
        int values[] = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        // Runs the game
        Game game = new Game(ranks,suits, values);
        game.playGame();
    }
}
