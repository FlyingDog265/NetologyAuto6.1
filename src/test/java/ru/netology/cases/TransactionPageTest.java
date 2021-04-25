package ru.netology.cases;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.helpers.LoginHelper;
import ru.netology.helpers.VerificationHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.LoginPage;
import ru.netology.pages.TransactionPage;
import ru.netology.pages.VerifyPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.helpers.CardHelper.cardNumber;
import static ru.netology.helpers.VerificationHelper.VerificationCode.getVerificationCodeFor;

public class TransactionPageTest {
    private DashboardPage dashboard;
    private final int amount = 5_200;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        LoginHelper.AuthInfo authInfo = LoginHelper.getAuthInfo();
        VerifyPage verificationPage = loginPage.validLogin(authInfo);
        VerificationHelper.VerificationCode verificationCode = getVerificationCodeFor(authInfo);
        dashboard = verificationPage.validVerify(verificationCode);
        dashboard.equalizeBalance();
    }

    @AfterEach
    void ending() {
        dashboard.equalizeBalance();
    }

    @Test
    @DisplayName("Проверка перевода на первую карту")
    void shouldTransferToFirstCard() {
        int expect1 = dashboard.getCardBalance(0) + amount;
        int expect2 = dashboard.getCardBalance(1) - amount;
        TransactionPage transactionPage = dashboard.transferMoney(0);
        transactionPage.transaction(Integer.toString(amount), cardNumber(1));
        int actual1 = dashboard.getCardBalance(0);
        int actual2 = dashboard.getCardBalance(1);
        assertEquals(expect1, actual1, "Баланс первой карты после перевода не совпадает!");
        assertEquals(expect2, actual2, "Баланс второй карты после перевода не совпадает!");
    }

    @Test
    @DisplayName("Проверка перевода на вторую карту")
    void shouldTransferToSecondCard() {
        int expect1 = dashboard.getCardBalance(0) - amount;
        int expect2 = dashboard.getCardBalance(1) + amount;
        TransactionPage transactionPage = dashboard.transferMoney(1);
        transactionPage.transaction(Integer.toString(amount), cardNumber(0));
        int actual1 = dashboard.getCardBalance(0);
        int actual2 = dashboard.getCardBalance(1);
        assertEquals(expect2, actual2, "Баланс первой карты после перевода не совпадает!");
        assertEquals(expect1, actual1, "Баланс второй карты после перевода не совпадает!");
    }
}
