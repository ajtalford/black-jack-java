package blackJackSprint;
import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {
        
        System.out.println("Welcome to Blackjack");

        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        //creates the main deck

        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();
        //creates hands for the player and dealer

        //System.out.println(playingDeck);

        double playerMoney = 200.00;
        //sets the starting value for the player's total gambling money

        Scanner userInput = new Scanner(System.in);

        while(playerMoney > 0){
            System.out.println("You have $" + playerMoney + ", how much will you bet?");
            double playerBet = userInput.nextDouble();
            if(playerBet > playerMoney){
                System.out.println("You can't bet more than you have.");
                break;
            }
            //Asks the player how much of their money they would like to bet. Breaks loop if they attempt to bet more than they have.

            boolean endRound = false;
            //keeps the game going unless another method causes it to end

            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);
            //Gives player starting hand

            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);
            //Gives dealer starting hand

            while(true){
                System.out.println("Your hand:");
                System.out.println(playerDeck.toString());
                System.out.println("Your hand is valued at " + playerDeck.cardsValue() + " points.");

                System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [hidden]");

                System.out.println("Would you like to (1)Hit or (2)Stand?");
                int response = userInput.nextInt();

                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("BUST! Your hand is worth: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                    //Bust if over 21
                }

                if(response == 2){
                    break;
                }
            }
            //Primary game loop. Allows player to choose whether they want to hit or stand. Displays the hand and value to the player.

            System.out.println("Dealer Cards: " + dealerDeck.toString());
            if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
                System.out.println("Dealer wins");
                playerMoney -= playerBet;
                endRound = true;
            }
            //Dealer wins if the value of their hand is greater than the player's

            while((dealerDeck.cardsValue() < 17) && endRound == false) {
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }
            System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());
            //dealer only draws if their hand value is less than 16. Also draws the dealer a card and displays their hand value.

            if((dealerDeck.cardsValue() > 21) && endRound == false){
                System.out.println("Dealer BUSTS! You win!");
                playerMoney += playerBet;
                endRound = true;
            }
            //If the dealer's value goes over 21 they bust.


            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
                System.out.println("PUSH. You tied with the dealer.");
                endRound = true;
            }
            //Tie condition

            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
                System.out.println("You win the hand!");
                playerMoney += playerBet;
                endRound = true;
            } else if(endRound == false){
                System.out.println("You lose the hand.");
                playerMoney -= playerBet;
                endRound = true;
            }
            //If player's hand value > dealer's they win the hand. Otherwise, they lose.

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);
            System.out.println("End of hand.");

        }

        System.out.println("You're bankrupt!");
    }
    
}