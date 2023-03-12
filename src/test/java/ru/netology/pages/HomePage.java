package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    private SelenideElement forDebitCardButton = $(byText("Купить"));
    private SelenideElement forCreditCardButton = $(byText("Купить в кредит"));

    public DebitCardPage cardPayment() {
        forDebitCardButton.click();
        return new DebitCardPage ();
    }

    public CreditCardPage creditCard() {
        forCreditCardButton.click();
        return new CreditCardPage();
    }
}