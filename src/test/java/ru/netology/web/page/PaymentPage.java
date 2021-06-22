package ru.netology.web.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement form = $("form.form");

    public PaymentPage() {
        form.shouldBe(Condition.visible);
    }

    public void fillAndSubmit(CardInfo cardInfo) {
        SelenideElement numberField = $$("form.form input").get(0);
        numberField.setValue(cardInfo.getNumber());
        System.out.println("[fillAndSubmit]: " + cardInfo.getNumber());
        System.out.println("[fillAndSubmit]: " + numberField.getValue());

        SelenideElement monthField = $$("form.form input").get(1);
        monthField.setValue(cardInfo.getMonth());
        System.out.println(monthField.getValue());
        SelenideElement yearField = $$("form.form input").get(2);
        yearField.setValue(cardInfo.getYear());
        System.out.println(yearField.getValue());
        SelenideElement ownerField = $$("form.form input").get(3);
        ownerField.setValue(cardInfo.getOwner());
        System.out.println(ownerField.getValue());
        SelenideElement cvcField = $$("form.form input").get(4);
        cvcField.setValue(cardInfo.getCvc());
        System.out.println(cvcField.getValue());

        SelenideElement button = $("form.form button");
        button.click();
    }

    public void getSuccess() {
        SelenideElement notificationSuccess = $(".notification_status_ok ");

        System.out.println("[getSuccess]: " + notificationSuccess.getCssValue("visibility") );

        Duration timeout = Duration.ofSeconds(15);
        $(".notification_status_ok").shouldBe(visible, timeout);
        //$(".notification_status_ok").shouldBe(visible);

    }

    public void getError() {
        Duration timeout = Duration.ofSeconds(15);
        $(".notification_status_error").shouldBe(visible, timeout);
    }
}
