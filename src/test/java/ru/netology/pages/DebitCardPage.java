package ru.netology.pages;

import static com.codeborne.selenide.Selectors.byText;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class DebitCardPage {
    private SelenideElement paymentForm = $(byText("Купить"));
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");
    private SelenideElement ownerField = $(By.xpath("//span[text()='Владелец']/..//input"));
    private SelenideElement cvcField = $("[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));

    public DebitCardPage () {paymentForm.shouldBe(visible);
    }

    public void fillingPaymentFormForDebitCard (DataGenerator.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getName());
        cvcField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    private SelenideElement successNotification = $(byText("Успешно"));
    private SelenideElement errorNotification = $(byText("Ошибка"));
    private SelenideElement formatError = $(byText("Неверный формат"));
    private SelenideElement requiredField = $(byText("Поле обязательно для заполнения"));
    private SelenideElement expiredMonthField = $(byText("Неверно указан срок действия карты"));
    private SelenideElement expiredYearField = $(byText("Истёк срок действия карты"));

    public void successfulPayment() {successNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void errorPayment() {errorNotification.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void requiredField() {requiredField.shouldBe(visible);
    }

    public void invalidFormatField() {formatError.shouldBe(visible);
    }

    public void expiredMonth() {expiredMonthField.shouldBe(visible);
    }

    public void expiredYear() {expiredYearField.shouldBe(visible);
    }
}