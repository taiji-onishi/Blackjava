package javaonly;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Player extends Person {
    private MainMethod mainMethod;

    Player() {
        super();
    }

    Player(MainMethod mainMethod) {
        super();
        setPersonName("あなた");
        this.mainMethod = mainMethod;
    }

    @Override
    void draw(CardBill cardBill) {
        if (getHandCards().size() > 0) {
            decideToDraw(cardBill);
            return;
        }
        drawCardTwice(cardBill);
    }

    void decideToDraw(CardBill cardBill) {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("\nあなたの現在の得点： " + getHandCardsNumberSum());
        System.out.println("カードを引きますか？[y/n]");

        String answer = null;
        try {
            answer = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doAction(answer, cardBill);
    }


    void doAction(String action, CardBill cardBill) {
        if (action.isEmpty()) {
            System.out.println("無効な入力です");
            decideToDraw(cardBill);
            return;
        }

        if (action.equals("y")) {
            drawCard(cardBill);
            return;
        } else if (action.equals("n")) {
            decideNotToDrawAnymore();
            mainMethod.turnChange();
            return;
        }
        System.out.println("無効な入力です");
        decideToDraw(cardBill);
    }

    void drawCard(CardBill cardBill) {
        Card drewCard = drawCardProcess(cardBill);
        displayDrawCard(drewCard, this, true);

        if (judgeBursted()) {
            mainMethod.thereIsBursted();
        } else {
            mainMethod.turnChange();
        }
    }

    void drawCardTwice(CardBill cardBill) {
        Card drewCard = drawCardProcess(cardBill);
        Card drewCardSecond = drawCardProcess(cardBill);

        System.out.println("あなたの引いたカードは " + drewCard.getMarkName() + " の " + drewCard.getNumberString());
        System.out.println("あなたの引いたカードは " + drewCardSecond.getMarkName() + " の " + drewCardSecond.getNumberString());
        System.out.println("あなたの現在の得点は： " + getHandCardsNumberSum());
    }
}
