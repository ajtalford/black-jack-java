package blackJackSprint;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    private ArrayList<Card> cards;

    public Deck(){
        this.cards = new ArrayList<Card>();
    }
    //creates arraylist of cards that we can manipulate later

    public void createFullDeck(){
        for(Suit cardSuit : Suit.values()){
            for(Value cardValue : Value.values()) {
                this.cards.add(new Card(cardSuit,cardValue));
            }
        }
    }
    //combines the suit and value enums using for loops to creates 13 value cards for each suit

    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        Random random = new Random();
        int randomCardIndex = 0;
        int originalSize = this.cards.size();

        for(int i=0; i < originalSize; i++){
            randomCardIndex = random.nextInt((this.cards.size()-1 -0) +1) +0;
            tempDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }

        this.cards = tempDeck;
    }
    //randomly takes each card out of created arraylist and places them in a temporary deck, the new order of cards is then added to the main deck

    public String toString() {
        String cardListOutput = "";
        for(Card aCard : this.cards){
            cardListOutput += "\n " + "-" + aCard.toString();
        }
        return cardListOutput;
    }
    //allows each card to be displayed and identified by its suit and value

    public void removeCard(int i) {
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    public void draw(Deck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }
    //takes the "top card", array position 0, out of the deck array

    public int deckSize(){
        return this.cards.size();
    }
    //displays the number of cards in deck/hand

    public void moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.cards.size();

        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(1));
        }

        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }
    //moves all cards back to the playing deck after the game is over

    public int cardsValue() {
        int totalValue = 0;
        int aces = 0;

        for(Card aCard : this.cards){
            switch(aCard.getValue()){
                case TWO: totalValue += 2; break;
                case THREE: totalValue += 3; break;
                case FOUR: totalValue += 4; break;
                case FIVE: totalValue += 5; break;
                case SIX: totalValue += 6; break;
                case SEVEN: totalValue += 7; break;
                case EIGHT: totalValue += 8; break;
                case NINE: totalValue += 9; break;
                case TEN: totalValue += 10; break;
                case JACK: totalValue += 10; break;
                case KING: totalValue += 10; break;
                case QUEEN: totalValue += 10; break;
                case ACE: aces += 1; break;
                //sets the int value for each potential card value
            }
        }

        for(int i = 0; i < aces; i++){
            if(totalValue > 10){
                totalValue += 1;
            } else {
                totalValue += 11;
            }
        }
        //sets the value for aces. If a player has a total hand value less than 10, an ace will be worth 11. Otherwise, an ace will be worth 1

        return totalValue;
    }
}