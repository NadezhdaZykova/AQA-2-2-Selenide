import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DeliveryCardTest {

    @BeforeEach
    public void setting() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldBeSend() {
        SelenideElement form = $("[method=post]");
        form.$("[data-test-id=city] input").setValue("Нижний Новгород");
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.DELETE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        form.$("[data-test-id=date] input").setValue(new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime()));
        form.$("[data-test-id=name] input").setValue("Ежов Василий Егорович");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$("[data-test-id=agreement] .checkbox__box").click();
        form.$("[role=button] .button__content").click();
        $("[data-test-id=notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно забронирована на " + new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime())));
    }
}
