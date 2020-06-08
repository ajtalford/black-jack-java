package blackJackSprint;

public class Card {

    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value){

        this.value = value;
        this.suit = suit;

    }

    public String toString(){
        return this.suit.toString() + "-" + this.value.toString();
    }

    public Value getValue(){
        return this.value;
    }
    public int cardValue() {
        int value = 0;
        switch(this.getValue()){
            case TWO: value = 2; break;
            case THREE: value = 3; break;
            case FOUR: value = 4; break;
            case FIVE: value = 5; break;
            case SIX: value = 6; break;
            case SEVEN: value = 7; break;
            case EIGHT: value = 8; break;
            case NINE: value = 9; break;
            case TEN: value = 10; break;
            case JACK: value = 10; break;
            case KING: value = 10; break;
            case QUEEN: value = 10; break;
            case ACE: value = 1; break;
            //sets the int value for each potential card value
        }
        return value;
    }

}