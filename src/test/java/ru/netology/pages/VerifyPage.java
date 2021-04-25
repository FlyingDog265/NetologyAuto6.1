package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.element;
import static ru.netology.helpers.VerificationHelper.VerificationCode;

public class VerifyPage {
    private final SelenideElement codeField = element("input[name='code']");
    private final SelenideElement verifyButton = element("button[data-test-id='action-verify']");

    public VerifyPage() {
        codeField.shouldBe(visible);
    }

    public DashboardPage validVerify(VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
