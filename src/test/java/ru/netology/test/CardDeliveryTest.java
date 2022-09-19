package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.main.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

 class CardDeliveryTest {

     @BeforeEach
     void setup() {
         open("http://localhost:9999");
     }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id=city] .input__control").setValue(validUser.getCity());
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").setValue(firstMeetingDate);
        $("[data-test-id=name] .input__control").setValue(validUser.getName());
        $("[data-test-id=phone] .input__control").setValue(validUser.getPhone());
        $(("[data-test-id=agreement]")).click();
        $(byText("Запланировать")).click();
        $(".notification").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $(byText("Запланировать")).click();
        $(byText("Перепланировать")).click();
        $(".notification").shouldBe(visible, Duration.ofSeconds(15));
    }
}

