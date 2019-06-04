package javaonly;

public class Card {
    public enum Marks {
        HEART("ハート"),
        DIAMOND("ダイア"),
        CLOVER("クローバー"),
        SPADE("スペード");

        private String markName;

        Marks(String _markName) {
            this.markName = _markName;
        }

        public String getMarkName() {
            return markName;
        }
    }

    private int number;
    private Marks mark;

    Card(int number, Marks mark) {
        this.number = number;
        this.mark = mark;
    }

    public int getNumber() {
        return number;
    }

    public String getNumberString() {
        switch (getNumber()) {
            case 1:
                return "A";
            case 11:
                return "J";
            case 12:
                return "Q";

            case 13:
                return "K";
            default:
                return String.valueOf(getNumber());
        }
    }

    public int getCardPoint() {
        switch (getNumber()) {
            case 11:
            case 12:
            case 13:
                return 10;
            default:
                return getNumber();
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Marks getMark() {
        return mark;
    }

    public String getMarkName() {
        return mark.markName;
    }

    public void setMark(Marks mark) {
        this.mark = mark;
    }
}
