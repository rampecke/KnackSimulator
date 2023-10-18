public class Card {
    private CardValue cardValue;
    private CardColor cardColor;

    public Card(CardValue cardValue, CardColor cardColor) {
        this.cardValue = cardValue;
        this.cardColor = cardColor;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public CardColor getCardColor() {
        return cardColor;
    }

    public void setCardColor(CardColor cardColor) {
        this.cardColor = cardColor;
    }

    public int getCardValue() {
        return switch (this.cardValue) {
            case SEVEN -> 7;
            case EIGHT -> 8;
            case NINE -> 9;
            case TEN, QUEEN, JACK, KING -> 10;
            case ACE -> 11;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "[" + cardValue + ", " + cardColor + ']';
    }
}

enum CardValue {
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
}

enum CardColor {
    DIMONDS,
    HEARTS,
    CLUBS,
    SPADES
}