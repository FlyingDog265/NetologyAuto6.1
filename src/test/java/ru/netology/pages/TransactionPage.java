package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.element;
import static org.openqa.selenium.Keys.*;

public class TransactionPage {
    private final SelenideElement inputAmount = element("[data-test-id='amount'] input");
    private final SelenideElement inputSourceCard = element("[data-test-id='from'] input");
    private final SelenideElement buttonAdd = element("button[data-test-id = 'action-transfer']");

    public void transaction(String value, String source) {
        inputAmount.sendKeys(chord(CONTROL, "a"),
                BACK_SPACE,
                value.replace(" ", ""));
        inputSourceCard.sendKeys(chord(CONTROL, "a"),
                BACK_SPACE,
                source.replace(" ", ""));
        buttonAdd.click();
    }
}
