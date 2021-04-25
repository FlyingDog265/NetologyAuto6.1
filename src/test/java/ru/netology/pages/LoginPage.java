package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.helpers.LoginHelper;

import static com.codeborne.selenide.Selenide.element;

public class LoginPage {
    private final SelenideElement inputLogin = element("[data-test-id=login] input");
    private final SelenideElement inputPassword = element("[data-test-id=password] input");
    private final SelenideElement buttonLogin = element("[data-test-id=action-login]");

    public VerifyPage validLogin(LoginHelper.AuthInfo info) {
        inputLogin.setValue(info.getLogin());
        inputPassword.setValue(info.getPassword());
        buttonLogin.click();
        return new VerifyPage();
    }
}
