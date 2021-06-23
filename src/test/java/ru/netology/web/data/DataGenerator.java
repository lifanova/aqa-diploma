package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Random;

public class DataGenerator {

    public static CardInfo getCorrectCardInfo() {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setNumber(getApprovedCardNumber());

        cardInfo.setMonth(getMonth());
        cardInfo.setYear(getYear());

        cardInfo.setOwner(getName());

        cardInfo.setCvc(getCvc());

        return cardInfo;
    }

    public static CardInfo getIncorrectCardInfo() {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setNumber(getDeclinedCardNumber());
        cardInfo.setMonth(getMonth());
        cardInfo.setYear(getYear());

        cardInfo.setOwner(getName());

        cardInfo.setCvc(getCvc());
        return cardInfo;
    }

    public static CardInfo getFakerCardInfo() {
        CardInfo cardInfo = new CardInfo();

        cardInfo.setNumber(getCardNumber());
        cardInfo.setMonth(getMonth());
        cardInfo.setYear(getYear());

        cardInfo.setOwner(getName());

        cardInfo.setCvc(getCvc());

        return cardInfo;
    }

    public static String getCardNumber() {
        Faker faker = new Faker();
        return Long.toString(faker.number().randomNumber(16, true));
    }


    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getMonth() {
        Random random = new Random();
        int month = random.nextInt(12) + 1;

        return String.format("%02d", month);
    }

    public static String getYear() {
        Random random = new Random();
        int i = random.nextInt(5) + 1;

        LocalDate futureDate = LocalDate.now().plusYears(i);
        int year = futureDate.getYear() - 2000;

        return Integer.toString(year);
    }

    public static String getName() {
        Faker faker = new Faker();

        return faker.name().fullName();
    }

    public static String getCvc() {
        Random random = new Random();
        int a = random.nextInt(10);
        int b = random.nextInt(10);
        int c = random.nextInt(10);

        return String.format("%d%d%d" , a, b, c);
    }


}
