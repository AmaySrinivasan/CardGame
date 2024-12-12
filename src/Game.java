import java.util.Scanner;

public class Game {
    private Player player;
    private Deck deck;
    private Player dealer;

    public Game(String[] ranks, String[] suits, int[] values) {
        deck = new Deck(ranks, suits, values);
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = input.nextLine();
        System.out.println("Please enter your starting money: ");
        int startingMoney = input.nextInt();
        player = new Player(name,startingMoney);
        dealer = new Player("Dealer", 0);
    }
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
        Scanner input = new Scanner(System.in);
        System.out.println("Do you understand the rules, and are you ready to play? (yes)");
        String response = input.nextLine();
        if (!response.equals("yes")) {
            printInstructions();
        }
    }
    public void playGame() {
        printInstructions();
        Scanner input = new Scanner(System.in);
        while (player.getMoney() > 0) {
            System.out.println("You currently have $" + player.getMoney() + ".");

            if(player.getMoney() <= 0) {
                System.out.println("Sorry you don't have enough money to continue playing!");
                break;
            }

            System.out.println("How much do you want to bet? ");
            int bet = input.nextInt();
            while (bet <= 0 || bet > player.getMoney()) {
                if (bet <= 0) {
                    System.out.println("Bets must be greater than 0. How much would you like to bet?");
                } else {
                    System.out.println("You don't have enough for that bet! You have $" + player.getMoney() + ". How much would you like to bet?");
                }
                bet = input.nextInt();
            }
            player.setBet(bet);

            deck.shuffle();

            player.reset();
            dealer.reset();

            player.addCard(deck.deal());
            player.addCard(deck.deal());
            dealer.addCard(deck.deal());
            dealer.addCard(deck.deal());

            System.out.println(player);
            System.out.println("Dealers cards: " + dealer.getHand().get(0) + " and [You don't get to see this card yet]");

            makePlayerMove();

            if (player.getPoints() > 21) {
                System.out.println("You busted! You Lost!");
                player.loseBet();
            }
            else {
                makeDealerMove();
                if (dealer.getPoints() > 21) {
                    System.out.println("Dealer busted! You won!");
                    player.winBet();
                } else if (player.getPoints() > dealer.getPoints()) {
                    System.out.println("Your " + player.getPoints() + " points is more than the Dealer's " + dealer.getPoints() + "!");
                    System.out.println("You just won!");
                    player.winBet();
                } else if (player.getPoints() < dealer.getPoints()) {
                    System.out.println("Sorry! The dealer had more with " + dealer.getPoints() + " compared to your " + player.getPoints());
                    System.out.println("You Lost!");
                    player.loseBet();
                } else {
                    System.out.println("Your point values are the same at " + player.getPoints());
                    System.out.println("It's a tie!");
                    player.pushBet();
                }
            }
            if (player.getMoney() <= 0) {
                System.out.println("Sorry! You don't have enough money to keep playing!");
                break;
            } else {
                System.out.println("Do you want to play another round? (yes)");
                input.nextLine();
                if (!input.nextLine().equals("yes")) {
                    break;
                }
            }
        }
        System.out.println("Game over! You finished with $" + player.getMoney());
    }
    private void makePlayerMove() {
        while (player.getPoints() <= 21) {
            System.out.println("Your current points: " + player.getPoints() + ". Would you like to hit or stand? ");
            Scanner input = new Scanner(System.in);
            String response = input.nextLine();
            if (response.equals("hit")) {
                Card drawnCard = deck.deal();
                player.addCard(drawnCard);
                System.out.println("You drawn card is: " + drawnCard);
                System.out.println(player);
                if (player.getPoints() > 21) {
                    break;
                }
            } else if (response.equals("stand")) {
                System.out.println(player.getName() + " has stood.");
                break;
            } else {
                System.out.println("That's not a valid option");
            }
        }
    }
    private void makeDealerMove() {
        while (dealer.getPoints() < 17) {
            Card drawnCard = deck.deal();
            dealer.addCard(drawnCard);
        }
        System.out.println("Final Dealer's hand: " + dealer);
    }

    public static void main(String[] args) {
        String ranks[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String suits[] = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int values[] = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
        Game game = new Game(ranks,suits, values);
        game.playGame();
    }
}
