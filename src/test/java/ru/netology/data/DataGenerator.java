package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {

    }

    static Faker faker = new Faker(new Locale("en"));

    public static String getApprovedCardNumber() {
        return "4444444444444441";
    }

    public static String getDeclinedCardNumber() {
        return "4444444444444442";
    }

    public static String getInvalidCardNumber() {
        return "4444444444444444";
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getLastMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearLaterThanCurrent() {
        return LocalDate.now().plusYears(10).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNextYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getLastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getCVC() {
        return Integer.toString(faker.number().numberBetween(100, 999));
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String name;
        private String cvc;
    }

    public static CardInfo getCardInfoOfApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoOfDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getCurrentMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoOfUnknownCard() {
        return new CardInfo(getInvalidCardNumber(), getCurrentMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfCardNumberIsNotComplete() {
        return new CardInfo("444444444444444", getCurrentMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfCardNumberIsNull() {
        return new CardInfo("0000000000000000", getCurrentMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfCardNumberIsNumbersPlusLetters() {
        return new CardInfo("444444444444444q", getCurrentMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfCardNumberIsEmpty() {
        return new CardInfo(" ", getCurrentMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfMonthIsEmpty() {
        return new CardInfo(getApprovedCardNumber(), " ", getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfMonthIsOneNumber() {
        return new CardInfo(getApprovedCardNumber(), "5", getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfMonthIsMore() {
        return new CardInfo(getApprovedCardNumber(), "14", getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfExpiredLastMonth() {
        return new CardInfo(getApprovedCardNumber(), getLastMonth(), getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfMonthIsNull() {
        return new CardInfo(getApprovedCardNumber(), "00", getCurrentYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfMonthIsNullAndYearIsNext() {
        return new CardInfo(getApprovedCardNumber(), "00", getNextYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfYearIsOneNumber() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), "5", getName(), getCVC());
    }

    public static CardInfo getCardInfoIfYearIsNull() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), "00", getName(), getCVC());
    }
    public static CardInfo getCardInfoIfExpiredInLastYear() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getLastYear(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfExpiredInMoreThan10Years(){
        return new CardInfo (getApprovedCardNumber(), getCurrentMonth(), getYearLaterThanCurrent(), getName(), getCVC());
    }

    public static CardInfo getCardInfoIfYearIsEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), " ", getName(), getCVC());
    }

    public static CardInfo getCardInfoIfOwnerIsIncludesLettersAndSymbols() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), "Ivan O@Bain", getCVC());
    }

    public static CardInfo getCardInfoIfOwnerIsCyrillicName() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), "Иван Петров", getCVC());
    }

    public static CardInfo getCardInfoIfOwnerIsIncludesArabicNumerals() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), "Ivan 2 Petrov", getCVC());
    }

    public static CardInfo getCardInfoIfOwnerIsIncludesRomanNumerals() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), "Ivan II Petrov", getCVC());
    }

    public static CardInfo getCardInfoIfOwnerIsOnlyLastName() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), "Petrov", getCVC());
    }

    public static CardInfo getCardInfoIfOwnerIsMore100Symbols() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), "Ivaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaan Petrov", getCVC());
    }

    public static CardInfo getCardInfoIfOwnerIsEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), " ", getCVC());
    }

    public static CardInfo getCardInfoIfCVCIsNull() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), getName(), "000");
    }

    public static CardInfo getCardInfoIfCVCIsOneNumber() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), getName(), "6");
    }

    public static CardInfo getCardInfoIfCVCIsTwoNumbers() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), getName(), "66");
    }

    public static CardInfo getCardInfoIfCVCIsEmpty() {
        return new CardInfo(getApprovedCardNumber(), getCurrentMonth(), getCurrentYear(), getName(), " ");
    }

}

