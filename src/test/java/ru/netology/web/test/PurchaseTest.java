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


    private PaymentPage getPaymentForm(){
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();

        return mainPage.getPaymentPage();
    }

    private PaymentPage getCreditForm() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();

        return mainPage.getPaymentPage();
    }

    @DisplayName("1. Проверка возможности перехода к оплате по карте")
    @Test
    public void shouldOpenPaymentForm(){
        getPaymentForm();
    }

    @DisplayName("2. Проверка возможности перехода к покупке в кредит")
    @Test
    public void shouldOpenCreditForm(){
        getCreditForm();
    }

    @DisplayName("4. Успешная оплата по карте")
    @Test
    public void shouldPurchase(){
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();

        PaymentPage paymentPage =  getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. появится сообщение об успешной отправке, 2. в базе появится запись
        paymentPage.getSuccess();
    }

    @DisplayName("5. Отклоненная банком оплата по карте")
    @Test
    public void shouldDeclinePurchase(){
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getIncorrectCardInfo();

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. появится сообщение об ошибке
        paymentPage.getSuccess();
    }

    @DisplayName("6. Отправка формы оплаты с незаполненными полями")
    @Test
    public void shouldSendEmptyForm(){
        PaymentPage paymentPage = getPaymentForm();

        paymentPage.fillAndSubmit(new CardInfo());

        //assertion: под каждым полем появится сообщение об ошибке
        paymentPage.getValidationMessageList();
    }


}
