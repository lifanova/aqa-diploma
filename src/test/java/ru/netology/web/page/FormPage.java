package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.CardInfo;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPage {
    private SelenideElement form = $("form.form");

    public FormPage() {
        form.shouldBe(Condition.visible);
        //TODO: проверить заголовки или адреса, куда будут уходить post-запросы
    }

    public void fillAndSubmit(CardInfo cardInfo) {
        SelenideElement numberField = $$("form.form input").get(0);
        numberField.setValue(cardInfo.getNumber());

        SelenideElement monthField = $$("form.form input").get(1);
        monthField.setValue(cardInfo.getMonth());

        SelenideElement yearField = $$("form.form input").get(2);
        yearField.setValue(cardInfo.getYear());

        SelenideElement ownerField = $$("form.form input").get(3);
        ownerField.setValue(cardInfo.getOwner());

        SelenideElement cvcField = $$("form.form input").get(4);
        cvcField.setValue(cardInfo.getCvc());


        SelenideElement button = $("form.form button");
        button.click();
    }

    public void getSuccess() {
        Duration timeout = Duration.ofSeconds(15);
        $(".notification_status_ok").shouldBe(visible, timeout);
    }

    public void getError() {
        Duration timeout = Duration.ofSeconds(15);
        $(".notification_status_error").shouldBe(visible, timeout);
    }

    public void getValidationMessage() {
        // Неверный формат или Поле обязательно для заполнению
        $(".input__sub").shouldBe(visible);
    }

    public void getValidationMessageList() {
        // Сообщений об ошибках валидации должно быть столько же, сколько и полей формы
        int size = $$("form.form input").size();
        System.out.println(size);

        List<SelenideElement> items = $$("form.form input");
        for (SelenideElement item : items) {
            item.parent().parent().find(".input__sub").shouldBe(visible);
        }
    }
}
