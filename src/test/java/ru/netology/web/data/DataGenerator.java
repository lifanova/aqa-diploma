package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Random;

public class DataGenerator {

    public static CardInfo getCorrectCardInfo() {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setNumber("4444 4444 4444 4441");
        cardInfo.setMonth("01");
        cardInfo.setYear("22");
        cardInfo.setOwner("Ivanov Ivan");
        cardInfo.setCvc("923");

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
        return "4444444444444441";
    }

    public static String getDeclinedCardNumber() {
        return "4444444444444442";
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

    public static String currentYear() {
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear() - 2000;
        return Integer.toString(year);
    }

    public static String pastYear() {
        Random random = new Random();
        int i = random.nextInt(18) + 1;
        return String.format("%02d", i);
    }

    public static String currentMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        return String.format("%02d", month);
    }

    public static String pastMonth() {
        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue() - 1;
        return String.format("%02d", month);
    }

    public static String invalidCardNumber() {
        Faker faker = new Faker();
        Random random = new Random();
        int i = random.nextInt(15) + 1;
        return Long.toString(faker.number().randomNumber(i, true));
    }
}
