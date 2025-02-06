package passedTests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.Keys.BACK_SPACE;

public class DeliveryCardTest {
    public String generateDate(int shift) {
        String date;
        LocalDate localDate = LocalDate.now().plusDays(shift);
        date = DateTimeFormatter.ofPattern("dd.MM.yyyy").format(localDate);
        return date;
    }

    @BeforeEach
    void openUrl() {
        open("http://localhost:9999");
    }


    @AfterEach
    void tearDown() {
        closeWindow();
    }


    @Test
    void shouldTestSuccessOrderIfCorrectFilling() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text(date));
    }

    @Test
    void shouldTestSuccessOrderIfPlusThreeDays() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Встреча успешно забронирована"))
                .shouldBe(Condition.text(date))
                .shouldHave(Condition.text(date));
    }

    @Test
    void shouldTestUnsuccessOrderIfPlusTwoDays() {
        String date = generateDate(2);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Заказ на выбранную дату невозможен"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestSuccessOrderIfPlus365Days() {
        String date = generateDate(365);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Встреча успешно забронирована"))
                .shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Встреча успешно забронирована"))
                .shouldBe(Condition.text(date))
                .shouldHave(Condition.text(date));
    }

    @Test
    void shouldTestUnsuccessOrderIfNoName() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        date));
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestUnsuccessOrderIfTelNotContainsElevenFigures() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Телефон указан неверно"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestUnsuccessOrderIfNoCity() {
        String date = generateDate(3);
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE,
                        date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestUnsuccessOrderIfNoNumber() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE, date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestUnsuccessOrderIfNoDate() {
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Неверно введена дата"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestUnsuccessOrderIfCityNotFromList() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Саранск");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE, date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestUnsuccessOrderIfNameInEnglish() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE, date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Только русские буквы, пробелы и дефисы"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestUnsuccessOrderIfTelWithoutPlusOnStart() {
        String date = generateDate(3);
        $("[data-test-id='city'] .input__control").setValue("Самара");
        $("[data-test-id='date'] .input__control").doubleClick();
        $("[data-test-id='date'] .input__control")
                .sendKeys(Keys.chord(BACK_SPACE, date));
        $("[data-test-id='name'] .input__control").setValue("Иван Иванов");
        $("[data-test-id='phone'] .input__control").setValue("79891234567");
        $$(".checkbox__box").find(Condition.visible).click();
        $$("button").find(Condition.exactText("Забронировать")).click();
        $(withText("Телефон указан неверно"))
                .shouldBe(Condition.visible);
    }

}

