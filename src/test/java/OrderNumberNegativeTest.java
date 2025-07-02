import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderNumberNegativeTest {
    private WebDriver driver;
    MainPage mainPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        // создали объекты классов страниц
        mainPage = new MainPage(driver);
    }

    @Test
    void testFormSubmissionWithActions() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //подтвердили куки
        mainPage.cookieConfirm();
        mainPage.clickStatus();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getStatusInput()));

        mainPage.inputStatus("12345");
        //Дождались открытия страницы заказа
        wait.until(ExpectedConditions.urlToBe("https://qa-scooter.praktikum-services.ru/track?t=12345"));

        assertTrue(driver.findElements(By.xpath(".//img[@alt='Not found']")).size() > 0, "Элемент с alt='Not found' отсутствует на странице");


    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
