package javaonly;

public class Dealer extends Person {
    private MainMethod mainMethod;

    Dealer() {
        super();
    }

    Dealer(MainMethod mainMethod) {
        super();
        setPersonName("ディーラー");
        this.mainMethod = mainMethod;
    }

    @Override
    void draw(CardBill cardBill) {
        if (getHandCards().size() < 1) {
            drawCardTwice(cardBill);
            return;
        }

        if (decideToDraw()) {
            drawCard(cardBill);
        } else {
            decideNotToDrawAnymore();
            mainMethod.turnChange();
        }

    }

    boolean decideToDraw() {
        if (getHandCardsNumberSum() < 18) {
            return true;
        }
        return false;
    }

    void drawCard(CardBill cardBill) {
        drawCardProcess(cardBill);
        if (judgeBursted()) {
            mainMethod.thereIsBursted();
        } else {
            mainMethod.turnChange();
        }
    }

    public void drawCardTwice(CardBill cardBill) {

        Card drewCard = drawCardProcess(cardBill);
        displayDrawCard(drewCard, this, true);

        Card drewCardSecond = drawCardProcess(cardBill);
        displayDrawCard(drewCardSecond, this, false);

    }
}
