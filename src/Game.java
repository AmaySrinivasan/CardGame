import java.util.Scanner;

public class Game {
    private Player player;
    private Deck deck;

    public Game() {
        String suits[] = {"Hearts", "Clubs", "Diamonds", "Spades"};
        String ranks[] = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        int values[] = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 11, 11};
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your name: ");
        String name = input.nextLine()
        deck = new Deck(ranks, suits, values);
        player = new Player(name);

    }
    public static void main(String[] args) {




    }
}
