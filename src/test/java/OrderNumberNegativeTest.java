import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderNumberNegativeTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderTrackPage orderTrackPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        // создали объекты классов страниц
        mainPage = new MainPage(driver);
        orderTrackPage = new OrderTrackPage(driver);
    }

    @Test
    void testFormSubmissionWithActions() {
        driver.get(MainPage.MAIN_URL);
        //подтвердили куки
        mainPage.cookieConfirm();
        mainPage.clickStatus();
        mainPage.waitStatusInputClickable();
        mainPage.inputStatus("12345");
        //Дождались открытия страницы заказа
        orderTrackPage.waitNegativeTrackUrl();
        assertTrue(orderTrackPage.getImgNotFound() > 0, "Элемент с alt='Not found' отсутствует на странице");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
