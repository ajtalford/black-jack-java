package blackJackSprint;
import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack");

        //creates the main deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();

        //creates hands for the player and dealer
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        //sets the starting value for the player's total gambling money
        double playerMoney = 200.00;

        Scanner userInput = new Scanner(System.in);
        while(playerMoney > 0){
            // initial bet
            double playerBet = 5.00;
            System.out.println("You have $" + playerMoney + " with $5 initial bet. Would you like to increase your bet by $5? Enter 1 for yes and 2 for no");
            if(userInput.nextInt() == 1){
                if (playerMoney >= 10){
                    playerBet = playerBet + 5;
                }
                else {
                    System.out.println("You don't have enough balance to increment your bet.");
                }
            }

            //Gives player starting hand
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Gives dealer starting hand
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            System.out.println("Your hand:");
            System.out.println(playerDeck.toString());
            System.out.println("Your hand is valued at " + playerDeck.cardsValue() + " points.");
            System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [hidden]");
            
            System.out.println("Would you like to (1)Hit or (2)Stand? or split(3)");
            int response = userInput.nextInt();
            
            if(response == 3){
                //code for split
                if(playerDeck.getCard(0).getValue() == playerDeck.getCard(1).getValue()){
                    Deck playerDeck2 = new Deck();
                    playerDeck2.addCard(playerDeck.getCard(1));
                    playerDeck.removeCard(1);
                    
                    playerDeck.draw(playingDeck);
                    playerDeck2.draw(playingDeck);
                    
                    System.out.println("------hand1------");
                    System.out.println(playerDeck.toString());
                    System.out.println("Your hand is valued at " + playerDeck.cardsValue() + " points.");
                    System.out.println("Would you like to (1)Hit or (2)Stand?");
                    response = userInput.nextInt();
                    Player playGame = new Player(playingDeck, dealerDeck, playerDeck, playerMoney, playerBet);
                    playerMoney = playGame.playTime(response);
                
                    System.out.println("-----hand2------");
                    System.out.println(playerDeck2.toString());
                    System.out.println("Your hand is valued at " + playerDeck2.cardsValue() + " points.");
                    System.out.println("Would you like to (1)Hit or (2)Stand?");
                    response = userInput.nextInt();
                    Player playGame2 = new Player(playingDeck, dealerDeck, playerDeck2, playerMoney, playerBet);
                    playerMoney = playGame2.playTime(response);
                    playerDeck2.moveAllToDeck(playingDeck);
                }
                else {
                    System.out.println("Split is not valid.");
                    System.out.println("Would you like to (1)Hit or (2)Stand?");
                    response = userInput.nextInt();
                }   
            }
            if (response == 1 || response == 2){
                Player playGame = new Player(playingDeck, dealerDeck, playerDeck, playerMoney, playerBet);
                playerMoney = playGame.playTime(response);
            }

            playerDeck.moveAllToDeck(playingDeck);
            dealerDeck.moveAllToDeck(playingDeck);

            System.out.println("End of hand.");
        }
        System.out.println("You're bankrupt!");
        userInput.close(); 
    }
}