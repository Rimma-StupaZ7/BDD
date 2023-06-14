package test;

import org.junit.jupiter.api.Test;
import ru.netology.bdd.data.DataHelper;

import ru.netology.bdd.page.LoginPage;
import ru.netology.bdd.page.СardTransferPage;


import static com.codeborne.selenide.Selenide.open;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.bdd.data.DataHelper.getFirstCardInfo;
import static ru.netology.bdd.data.DataHelper.getSecondCardInfo;


public class MoneyTransferTest {

    @Test
    void shouldTransferFromFirstToSecond() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validlogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int amount = 1000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.doTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }

    @Test
    void shouldTransferFromSecondToFirst() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validlogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getSecondCardInfo();
        var secondCardInfo = getFirstCardInfo();
        int amount = 1000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.doTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
    @Test
    void shouldTransferMoreThanBalance() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validlogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getSecondCardInfo();
        var secondCardInfo = getFirstCardInfo();
        int amount = 100000;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        transferPage.doTransferIncorrectAmount(String.valueOf(amount), firstCardInfo);
        transferPage.getErrorMessage();


    }
    @Test
    void shouldTransferNoAmount() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validlogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getSecondCardInfo();
        var secondCardInfo = getFirstCardInfo();
        int amount = 0;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.doTransfer(String.valueOf(amount), firstCardInfo);
        transferPage.getErrorMessage();
    }

}

        

