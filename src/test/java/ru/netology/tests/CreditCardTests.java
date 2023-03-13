package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataGenerator;
import ru.netology.data.SQLHelper;
import ru.netology.pages.HomePage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardTests {

    @BeforeAll
    static void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openHomePage() {
        open("http://localhost:8080");
    }

    @AfterEach
    public void clearDB() {
        SQLHelper.cleanDataBase();
    }

    @AfterAll
    static void turnOffAllure() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldPayApprovedCard() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoOfApprovedCard());
        cardPayment.successfulPayment();
        var paymentStatus = SQLHelper.getStatusCreditCard();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    public void shouldNotPayDeclinedCard() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoOfDeclinedCard());
        cardPayment.errorPayment();
        var paymentStatus = SQLHelper.getStatusCreditCard();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    public void shouldNotPayUnknownCard() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoOfUnknownCard());
        cardPayment.errorPayment();
    }

    @Test
    public void shouldInvalidFormatFieldIfCardNumberIsNotComplete() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCardNumberIsNotComplete());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldErrorPaymentIfCardNumberIsNull() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCardNumberIsNull());
        cardPayment.errorPayment();
    }

    @Test
    public void shouldInvalidFormatFieldIfCardNumberIsNumbersPlusLetters() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCardNumberIsNumbersPlusLetters());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldRequiredFieldIfCardNumberIsEmpty() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCardNumberIsEmpty());
        cardPayment.requiredField();
    }

    @Test
    public void shouldRequiredFieldIfMonthIsEmpty() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfMonthIsEmpty());
        cardPayment.requiredField();
    }

    @Test
    public void shouldInvalidFormatFieldIfMonthIsOneNumber() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfMonthIsOneNumber());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldExpiredIfMonthIsMore() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfMonthIsMore());
        cardPayment.expiredMonth();
    }

    @Test
    public void shouldExpiredIfExpiredLastMonth() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfExpiredLastMonth());
        cardPayment.expiredMonth();
    }

    @Test
    public void shouldExpiredIfMonthIsNull() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfMonthIsNull());
        cardPayment.expiredMonth();
    }

    @Test
    public void shouldExpiredIfMonthIsNullAndYearIsNext() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfMonthIsNullAndYearIsNext());
        cardPayment.expiredMonth();
    }

    @Test
    public void shouldInvalidFormatFieldIfYearIsOneNumber() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfYearIsOneNumber());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldExpiredIfYearIsNull() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfYearIsNull());
        cardPayment.expiredYear();
    }

    @Test
    public void shouldExpiredIfExpiredLastYear() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfExpiredInLastYear());
        cardPayment.expiredYear();
    }

    @Test
    public void shouldExpiredIfExpiredInMoreThan10Years() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfExpiredInMoreThan10Years());
        cardPayment.expiredMonth();
    }

    @Test
    public void shouldRequiredFieldIfYearIsEmpty() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfYearIsEmpty());
        cardPayment.requiredField();
    }

    @Test
    public void shouldInvalidFormatFieldIfOwnerIsIncludesLettersAndSymbols() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfOwnerIsIncludesLettersAndSymbols());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldInvalidFormatFieldIfOwnerIsCyrillicName() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfOwnerIsCyrillicName());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldInvalidFormatFieldIfOwnerIsIncludesArabicNumerals() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfOwnerIsIncludesArabicNumerals());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldInvalidFormatFieldIfOwnerIsIncludesRomanNumerals() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfOwnerIsIncludesRomanNumerals());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldInvalidFormatFieldIfOwnerIsOnlyLastName() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfOwnerIsOnlyLastName());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldInvalidFormatFieldIfOwnerIsMore100Symbols() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfOwnerIsMore100Symbols());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldRequiredFieldIfOwnerIsEmpty() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfOwnerIsEmpty());
        cardPayment.requiredField();
    }

    @Test
    public void shouldErrorPaymentIfCVCIsNull() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCVCIsNull());
        cardPayment.errorPayment();
    }

    @Test
    public void shouldInvalidFormatFieldIfCVCIsOneNumber() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCVCIsOneNumber());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldInvalidFormatFieldIfCVCIsTwoNumbers() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCVCIsTwoNumbers());
        cardPayment.invalidFormatField();
    }

    @Test
    public void shouldRequiredFieldIfCVCIsEmpty() {
        var startPage = new HomePage();
        var cardPayment = startPage.creditCard();
        cardPayment.fillingPaymentFormForCreditCard(DataGenerator.getCardInfoIfCVCIsEmpty());
        cardPayment.requiredField();
    }
}