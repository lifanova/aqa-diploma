package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    // основной селектор, показываем, что страница загрузилась
    private SelenideElement heading = $("h2.heading");
    private SelenideElement paymentButton = $$("button").first();
    private SelenideElement creditButton = $$("button").last();

    public MainPage() {
        heading.shouldBe(visible);
    }

    public PaymentPage getPaymentPage(){
        paymentButton.click();

        return new PaymentPage();
    }

    public PaymentPage getCreditPage(){
        creditButton.click();

        return new PaymentPage();
    }
}
