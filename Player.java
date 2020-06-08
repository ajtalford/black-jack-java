package blackJackSprint;
import java.util.Scanner;
public class Player {
    private Deck playingDeck,  dealerDeck,  playerDeck;
    private double playerMoney, playerBet;
    private boolean endRound;
    //constructor
    public Player(Deck playingDeck, Deck dealerDeck, Deck playerDeck, double playerMoney,double  playerBet){
        this.playingDeck = playingDeck;
        this.dealerDeck = dealerDeck;
        this.playerDeck = playerDeck;
        this.playerMoney = playerMoney;
        this.playerBet = playerBet;
    }
    //Black jack rules
    public double playTime (int response){
        if (response == 1){
            Scanner userInput = new Scanner(System.in);
            int doubleDown =0;
            while(true){
                // check if double down is an option
                if((playerDeck.cardsValue() >= 9 && playerDeck.cardsValue() <= 11 ) && (playerBet * 2 <= playerMoney)){
                    System.out.println("Would you like to double down your bet? (Enter 1 for 'yes' or 2 for 'no')");
                    doubleDown = userInput.nextInt();
                    if(doubleDown == 1){
                        this.playerBet = this.playerBet * 2 ;
                    }
                } 
                
                // hit
                playerDeck.draw(playingDeck);
                System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                if(playerDeck.cardsValue() < 21 && doubleDown == 0){
                    System.out.println("Your hand is valued at " + playerDeck.cardsValue() + " points.");
                    System.out.println("Would you like to (1)Hit or (2)Stand?");
                    if (userInput.nextInt() != 1){
                        break;
                    }
                }
                else{
                    break;
                }
            }
            //userInput.close(); 
        }
      

        //Bust if over 21s
        if(playerDeck.cardsValue() > 21  && endRound == false){
            System.out.println("BUST! Your hand is worth: " + playerDeck.cardsValue());
            playerMoney -= playerBet;
            this.endRound = true;
        }

        //Shows dealer's cards
        System.out.println("Dealer Cards: " + dealerDeck.toString());
        
        //dealer only draws if their hand value is less than 16. Also draws the dealer a card and displays their hand value.
        while((dealerDeck.cardsValue() < 17) && endRound == false) {
            dealerDeck.draw(playingDeck);
            System.out.println("Dealer draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
        }
        System.out.println("Dealer's hand is valued at: " + dealerDeck.cardsValue());
        
        //If the dealer's value goes over 21 they bust.
        if((dealerDeck.cardsValue() > 21) && endRound == false){
            System.out.println("Dealer BUSTS! You win!");
            playerMoney += playerBet;
            this.endRound = true;
        }
        
        //Dealer wins if the value of their hand is greater than the player's
        if((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
            System.out.println("Dealer wins");
            playerMoney -= playerBet;
            this.endRound = true;
        }
        
        //If player's hand value > dealer's they win the hand. Otherwise, they lose.
        if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound == false){
            System.out.println("You win the hand!");
            playerMoney += playerBet;
            this.endRound = true;
        } 
        
        //Tie condition
        if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
            System.out.println("PUSH. You tied with the dealer.");
            this.endRound = true;
        }
        return playerMoney;
    }
}