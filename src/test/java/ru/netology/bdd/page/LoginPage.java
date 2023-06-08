package ru.netology.bdd.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import ru.netology.bdd.data.DataHelper;


public class LoginPage {
    private SelenideElement loginInput = $("span[data-test-id='login'] input");
    private final SelenideElement passwordInput = $("span[data-test-id='password'] input");
    private final SelenideElement okButton = $("button[data-test-id='action-login']");

    public VerificationPage validlogin(DataHelper.AuthInfo info) {
        loginInput.setValue(info.getLogin());
        passwordInput.setValue(info.getPassword());
        okButton.click();
        return new VerificationPage();
    }
}