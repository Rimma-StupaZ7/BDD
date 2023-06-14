package ru.netology.bdd.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.bdd.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;

import static ru.netology.bdd.data.DataHelper.CardInfo;

import static com.codeborne.selenide.Selenide.$;


public class СardTransferPage {
    private final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private final SelenideElement fromCardNumberInput = $("[data-test-id='from'] input");
    private final SelenideElement button = $("[data-test-id='action-transfer']");
    private final SelenideElement transferHead = $(byText("Пополнение карты"));
    private final SelenideElement errorMessage = $(byText("Неверно указана сумма перевода"));

    //    public void CardTransferPage(String amountToTransfer, CardInfo cardinfo) {
//        transferHead.shouldBe(visible);
//    }
    public void CardTransferPage() {
        transferHead.shouldBe(visible);
    }


    public DashboardPage doTransfer(String amountToTransfer, CardInfo cardinfo) {
        amountInput.setValue(amountToTransfer);
        fromCardNumberInput.setValue(cardinfo.getCardNumber());
        button.click();
        return new DashboardPage();

    }

    public void doTransferIncorrectAmount(String amountToTransfer, DataHelper.CardInfo cardinfo) {
        amountInput.setValue(amountToTransfer);
        fromCardNumberInput.setValue(cardinfo.getCardNumber());
        transferHead.click();
    }
    public void getErrorMessage () {
       errorMessage.shouldBe(visible);
    }

}


// public void findErrorMessage(String expectedText) {
//    errorMessage.shouldHave(exactText(expectedText), Duration.ofSeconds(15)).shouldBe(visible);
//}
