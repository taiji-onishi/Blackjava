package javaonly;


public class MainStart {

    public static void main(String[] args) {
        System.out.println("ゲームを開始します");
        //static
        GameMain gameMain = new GameMain(1);
        gameMain.startGame();
    }
}
