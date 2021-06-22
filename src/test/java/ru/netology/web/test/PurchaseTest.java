package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.page.MainPage;
import ru.netology.web.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class PurchaseTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    private PaymentPage shouldOpenPaymentForm(){
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();

        return mainPage.getPaymentPage();
    }

    private PaymentPage shouldOpenCreditForm() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();

        return mainPage.getPaymentPage();
    }

    @DisplayName("4. Успешная оплата по карте")
    @Test
    public void shouldPurchase(){
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();

        PaymentPage paymentPage =  shouldOpenPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. появится сообщение об успешной отправке, 2. в базе появится запись
        paymentPage.getSuccess();
    }

}
