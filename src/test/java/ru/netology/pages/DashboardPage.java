package ru.netology.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.elements;
import static ru.netology.helpers.CardHelper.cardNumber;

public class DashboardPage {
    private final SelenideElement heading = element("h2[data-test-id='dashboard']");
    private final ElementsCollection cards = elements("li[class='list__item']");
    private final ElementsCollection buttons = elements("button[data-test-id='action-deposit']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int cardNumber) {
        String text = cards.get(cardNumber).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(" р.");
        return Integer.parseInt(text
                .substring(start + balanceStart.length(), finish)
                .trim());
    }

    public TransactionPage transferMoney(int cardNumber) {
        cards.get(cardNumber);
        buttons.get(cardNumber).click();
        return new TransactionPage();
    }

    public void equalizeBalance() {
        int firstBalance = getCardBalance(0);
        int secondBalance = getCardBalance(1);
        if (firstBalance > secondBalance) {
            int amount = firstBalance - 10_000;
            transferMoney(1).transaction(Integer.toString(amount), cardNumber(0));
        } else if (secondBalance > firstBalance) {
            int amount = secondBalance - 10_000;
            transferMoney(0).transaction(Integer.toString(amount), cardNumber(1));
        }
    }
}
