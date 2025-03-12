import javax.swing.*;
import java.awt.*;
public class BlackJackViewer extends JFrame {
    // Instance variables for BlackJackViewer Class
    private Game game;
    public String gameState;
    // Size of the window (fits dimensions of blackjack table)
    private static final int WINDOW_WIDTH = 1440;
    private static final int WINDOW_HEIGHT = 720;
    public BlackJackViewer(Game game) {
        // Initializes variables and connects ends
        this.game = game;
        // Starts off with Instructions
        this.gameState = "Instructions";
        // Sets up the graphical side
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Black Jack");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void paint(Graphics g) {
        // Always Start with an image of the table
        g.drawImage(game.getTable(),        // image to draw
                0, 0,
                1440, 720,// (x, y) of upper left corner in output window
                this);
        // State for printing out the instructions
        if (this.gameState.equals("Instructions")) {
            drawInstructions(g);
        }
        // State for actually playing the game
        else if(this.gameState.equals("Play Game")) {
            drawPlayGame(g);
        }
        // State for end of game or between rounds
        else if(this.gameState.equals("Game Over")){
            drawGameOver(g);
        }

    }
    // Prints the instruction with the GUI
    private void drawInstructions(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Welcome to Blackjack!", 500, 100);
        g.drawString("The goal is to get as close to 21 without going over.", 100, 150);
        g.drawString("Aces are worth 11 points, face cards are worth 10 points.", 100, 200);
        g.drawString("Type 'hit' to draw a card, or 'stand' to end your turn.", 100, 250);
        g.drawString("You will play against the dealer.", 100, 300);
        g.drawString("Good luck!", 100, 350);
    }
    // Plays the actual game with the GUI
    private void drawPlayGame(Graphics g) {
        // Creates images of the player's hand
        if (game.getPlayer() != null && game.getPlayer().getHand() != null && !game.getPlayer().getHand().isEmpty()) {
            for (int i = 0; i < game.getPlayer().getHand().size(); i++) {
                game.getPlayer().getHand().get(i).drawCardImage(g,600+100*i,450);
            }
        }
        // Creates images of the dealer's hand
        if (game.getDealer() != null && game.getDealer().getHand() != null && !game.getDealer().getHand().isEmpty()) {
            // At the start of each round(when the dealer has 2 cards), only one card is visible
            if(game.getDealer().getHand().size() == 2) {
                game.getDealer().getHand().get(0).drawCardImage(g, 600, 150);
                if (game.isDealerTurnOver()) {
                    game.getDealer().getHand().get(1).drawCardImage(g, 700, 150);
                } else {
                    // The not visible card is face down
                    Image faceDownCard = new ImageIcon("Resources/back.png").getImage();
                    g.drawImage(faceDownCard, 700, 150, 100, 140, this);
                }
            }
            // Otherwise, display all of the dealer's cards
            else {
                for (int i = 0; i < game.getDealer().getHand().size(); i++) {
                    game.getDealer().getHand().get(i).drawCardImage(g,600+100*i,150);
                }
            }
        }
    }
    // Prints the game over case with the GUI
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Round/Game over. You have $" + game.getPlayer().getMoney() + " left", 500, 300);
    }
    // Updates the game state when necessary
    public void setGameState(String gameState) {
        this.gameState = gameState;
    }
}