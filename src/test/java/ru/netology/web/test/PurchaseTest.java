package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.DataGenerator;
import ru.netology.web.page.CreditPage;
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


    private PaymentPage getPaymentForm() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();

        return mainPage.getPaymentPage();
    }

    private CreditPage getCreditForm() {
        open("http://localhost:8080");

        MainPage mainPage = new MainPage();

        return mainPage.getCreditPage();
    }

    @DisplayName("1. Проверка возможности перехода к оплате по карте")
    @Test
    public void shouldOpenPaymentForm() {
        getPaymentForm();
    }

    @DisplayName("2. Проверка возможности перехода к покупке в кредит")
    @Test
    public void shouldOpenCreditForm() {
        getCreditForm();
    }

    @DisplayName("3. Проверка возможности переключения между формами")
    @Test
    public void shouldSwitchBetweenForms() {
        MainPage mainPage = new MainPage();
        mainPage.getPaymentPage();
        mainPage.getCreditPage();
    }

    @DisplayName("4. Успешная оплата по карте")
    @Test
    public void shouldApprovePayment() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. появится сообщение об успешной отправке, 2. в базе появится запись
        paymentPage.getSuccess();
    }

    @DisplayName("5. Отклоненная банком оплата по карте")
    @Test
    public void shouldDeclinePayment() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getIncorrectCardInfo();

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. появится сообщение об ошибке
        paymentPage.getError();
    }

    @DisplayName("6. Отправка формы оплаты с незаполненными полями")
    @Test
    public void shouldSendEmptyPaymentForm() {
        PaymentPage paymentPage = getPaymentForm();

        paymentPage.fillAndSubmit(new CardInfo());

        //assertion: под каждым полем появится сообщение об ошибке
        paymentPage.getValidationMessageList();
    }

    @DisplayName("7. Успешная покупка в кредит")
    @Test
    public void shouldApproveCredit() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();

        CreditPage creditPage = getCreditForm();

        //act: заполняем данными и отправляем данные на сервер
        creditPage.fillAndSubmit(cardInfo);

        //assertion: 1. появится сообщение об успешной отправке, 2. в базе появится запись
        creditPage.getSuccess();
    }

    @DisplayName("8. Отклоненная банком покупка в кредит")
    @Test
    public void shouldDeclineCredit() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getIncorrectCardInfo();

        CreditPage creditPage = getCreditForm();

        //act: заполняем данными и отправляем данные на сервер
        creditPage.fillAndSubmit(cardInfo);

        //assertion: 1. появится сообщение об ошибке
        creditPage.getSuccess();
    }

    @DisplayName("9. Отправка формы кредита с незаполненными полями")
    @Test
    public void shouldSendEmptyCreditForm() {
        CreditPage creditPage = getCreditForm();

        creditPage.fillAndSubmit(new CardInfo());

        //assertion: под каждым полем появится сообщение об ошибке
        creditPage.getValidationMessageList();
    }

    @DisplayName("10. Проверка поля \"Номер карты\"")
    @Test
    public void shouldCheckNumber() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();
        cardInfo.setNumber("1234");

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. под полем ввода появится сообщение об ошибке
        paymentPage.getValidationMessage();
    }

    @DisplayName("11. Проверка поля \"Месяц\"")
    @Test
    public void shouldCheckMonth() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();
        cardInfo.setMonth("14");

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. под полем ввода появится сообщение об ошибке
        paymentPage.getValidationMessage();
    }

    @DisplayName("12. Проверка поля \"Год\"")
    @Test
    public void shouldCheckYear() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();
        cardInfo.setYear("90");

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. под полем ввода появится сообщение об ошибке
        paymentPage.getValidationMessage();
    }

    @DisplayName("13. Проверка поля \"Владелец\"")
    @Test
    public void shouldCheckOwner() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();
        cardInfo.setOwner("qq");

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. под полем ввода появится сообщение об ошибке
        paymentPage.getValidationMessage();
    }

    @DisplayName("14. Проверка поля \"CVC/CVv\"")
    @Test
    public void shouldCheckCvc() {
        // arrange: открываем страницу, получаем тестовые данные
        CardInfo cardInfo = DataGenerator.getCorrectCardInfo();
        cardInfo.setCvc("1");

        PaymentPage paymentPage = getPaymentForm();

        //act: заполняем данными и отправляем данные на сервер
        paymentPage.fillAndSubmit(cardInfo);

        //assertion: 1. под полем ввода появится сообщение об ошибке
        paymentPage.getValidationMessage();
    }
}