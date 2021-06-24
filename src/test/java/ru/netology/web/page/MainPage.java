package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    // основной селектор, показываем, что страница загрузилась
    private SelenideElement heading = $("h2.heading");
    private SelenideElement paymentButton =  $(byText("Купить")).parent().parent();//$$("button").first();
    private SelenideElement creditButton = $(byText("Купить в кредит")).parent().parent();//$$("button").last();

    public MainPage() {
        heading.shouldBe(visible);
    }

    public PaymentPage getPaymentPage(){
        paymentButton.click();

        return new PaymentPage();
    }

    public CreditPage getCreditPage(){
        creditButton.click();

        return new CreditPage();
    }
}
