package javaonly;

import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private List<Card> handCards;
    private int handCardsNumberSum;
    private boolean isBursted;
    private boolean isFinishedDraw;
    private String personName;

    private MainMethod mainMethod;


    Person() {
        handCards = new ArrayList<Card>() {
        };
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    public void addHandCards(Card addedCard) {
        handCards.add(addedCard);
    }

    public int getHandCardsNumberSum() {
        return handCardsNumberSum;
    }

    public int getHandCardsPoint() {
        int sumPoint = 0;
        for (Card card : handCards) {
            sumPoint += card.getCardPoint();
        }
        return sumPoint;
    }

    public void setHandCardsNumberSum(int handCardsNumberSum) {
        this.handCardsNumberSum = handCardsNumberSum;
    }

    public boolean isBursted() {
        return isBursted;
    }

    public void setBursted(boolean bursted) {
        isBursted = bursted;
    }

    public boolean isFinishedDraw() {
        return isFinishedDraw;
    }

    public void setFinishedDraw(boolean finishedDraw) {
        isFinishedDraw = finishedDraw;
    }


    abstract void draw(CardBill cardBill);

    public void addHandCardNumberToSum(int number) {
        handCardsNumberSum += number;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }


    Card drawCardFromDeck(CardBill cardBill) {
        return cardBill.getGameCardBillDeck().get(cardBill.randomSelectDrawCards());
    }

    void addHandCardNumber(Card drewCard) {
        addHandCardNumberToSum(drewCard.getCardPoint());
        addHandCards(drewCard);
    }

    boolean judgeBursted() {
        if (getHandCardsNumberSum() > 21) {
            setBursted(true);
            return true;
        }
        return false;
    }

    void decideNotToDrawAnymore() {
        setFinishedDraw(true);
    }

    void displayDrawCard(Card card, Person person, boolean isOpen) {
        if (!isOpen) {
            if (person.getPersonName().equals("ディーラー") && person.getHandCards().size() == 2) {
                System.out.println("ディーラーの2枚目のカードは見せられません");
                return;
            }
            System.out.println("ディーラーはカードを引きました");
            return;
        }
        System.out.println(person.getPersonName() + "の引いたカードは " + card.getMarkName() + " の " + card.getNumberString());
        System.out.println(person.getPersonName() + "の得点は " + getHandCardsNumberSum());
    }

    Card drawCardProcess(CardBill cardBill) {
        Card drewCard = drawCardFromDeck(cardBill);
        addHandCardNumber(drewCard);
        cardBill.getGameCardBillDeck().remove(drewCard);
        return drewCard;
    }
}
