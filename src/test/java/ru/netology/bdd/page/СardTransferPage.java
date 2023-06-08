package ru.netology.bdd.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

import static ru.netology.bdd.data.DataHelper.CardInfo;

import static com.codeborne.selenide.Selenide.$;


public class СardTransferPage {
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromCardNumberInput = $("[data-test-id='from'] input");
    private final SelenideElement button = $("[data-test-id='action-transfer']");
    private SelenideElement transferHead = $(byText("Пополнение карты"));
    private SelenideElement errorMessage = $(byText("Неверно указана сумма перевода"));

    public void CardTransferPage() {
        transferHead.shouldBe(visible);


    };

    public DashboardPage doTransfer(String amountToTransfer, CardInfo cardinfo) {
        amountInput.setValue(amountToTransfer);
        fromCardNumberInput.setValue(cardinfo.getCardNumber());
        button.click();
        return new DashboardPage();

    }

    public DashboardPage doTransferIncorrectAmount(String amountToTransfer, CardInfo cardinfo) {
        amountInput.setValue(amountToTransfer);
        fromCardNumberInput.setValue(cardinfo.getCardNumber());
        button.click();
        errorMessage.shouldBe(visible);
        return new DashboardPage();
    }

    public void getErrorMessage () {
        errorMessage.shouldBe(visible);

    }
}
