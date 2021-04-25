package ru.netology.helpers;

public class CardHelper {
    private CardHelper() {
    }

    public static String cardNumber(int cardNo) {
        String[] cardsNumbers = new String[2];
        cardsNumbers[0] = "5559 0000 0000 0001";
        cardsNumbers[1] = "5559 0000 0000 0002";

        return cardsNumbers[cardNo];
    }
}
