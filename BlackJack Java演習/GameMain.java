package javaonly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameMain implements MainMethod {
    private int numberOfPlayers;
    private CardBill cardBill;
    private Player player;
    private Dealer dealer;
    private boolean isPlayerTurn = false;
    private MainMethod mainMethod;

    GameMain(int number) {
        this.numberOfPlayers = number;
    }


    public void startGame() {
        initGame();
    }

    public void initGame() {
        mainMethod = GameMain.this;
        cardBill = new CardBill();
        cardBill.makeCardBill();

        makePlayers();
        player.draw(cardBill);
        dealer.draw(cardBill);

        startPlayerTurn();
    }

    public void startPlayerTurn() {
        isPlayerTurn = true;
        player.draw(cardBill);
    }

    public void makePlayers() {
        player = new Player(mainMethod);
        dealer = new Dealer(mainMethod);
    }

    private void gameEnd() {
        int playersSum = player.getHandCardsPoint();
        int dealersSum = dealer.getHandCardsPoint();

        System.out.println("あなたの得点は:" + playersSum + "です");
        System.out.println("ディーラーの得点は:" + dealersSum + "です");

        if (playersSum > dealersSum) {
            System.out.println("あなたの勝ちです");
        } else if (playersSum == dealersSum) {
            System.out.println("引き分けです");
        } else {
            System.out.println("あなたの負けです");
        }

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turnChange() {
        if (player.isFinishedDraw() && dealer.isFinishedDraw()) {
            System.out.println("両者引き終えたので、手札をオープンします");
            gameEnd();
            return;
        }

        if (player.isFinishedDraw()) {
            System.out.println("引き終わったため、あなたのターンをスキップします");
            isPlayerTurn = false;
            dealer.draw(cardBill);
            return;
        }
        if (dealer.isFinishedDraw()) {
            System.out.println("引き終わったため、ディーラーのターンをスキップします");
            isPlayerTurn = true;
            player.draw(cardBill);
            return;
        }

        if (isPlayerTurn) {
            isPlayerTurn = false;
            dealer.draw(cardBill);
            return;
        }
        isPlayerTurn = true;
        player.draw(cardBill);
    }

    @Override
    public void thereIsBursted() {
        if (player.isBursted()) {
            System.out.println("バーストしてしまったので、あなたの負けです");

        } else if (dealer.isBursted()) {
            System.out.println("ディーラーがバーストしたため、あなたの勝ちです。");
        }

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
