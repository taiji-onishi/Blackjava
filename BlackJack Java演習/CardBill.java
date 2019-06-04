package javaonly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardBill {

    private List<Card> gameCardBillDeck;

    CardBill() {
    }

    CardBill(List<Card> cardBill) {
        gameCardBillDeck = cardBill;
    }

    public List<Card> getGameCardBillDeck() {
        return gameCardBillDeck;
    }

    public void setGameCardBillDeck(List<Card> gameCardBillDeck) {
        this.gameCardBillDeck = gameCardBillDeck;
    }

    public void makeCardBill() {
        List<Card> cardBill = new ArrayList<Card>() {
        };
        for (Card.Marks mark : Card.Marks.values()) {
            for (int i = 1; i < 14; i++) {
                Card card = new Card(i, mark);
                cardBill.add(card);
            }
            this.gameCardBillDeck = cardBill;
        }
    }

    public int randomSelectDrawCards() {
        if (gameCardBillDeck == null) {
            return 9999;
        }
        return new Random().nextInt(gameCardBillDeck.size());
    }

}
