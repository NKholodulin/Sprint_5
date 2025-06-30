import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    @BeforeEach
    void setUp() {
        // создали драйвер для браузера Chrome или Firefox
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        // перешли на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создали объект класса страницы с вопросами
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    void orderHeader() {
        mainPage.cookieConfirm();
        mainPage.clickOrderButtonHeader();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(orderPage.getOrderHeader(), "Для кого самокат"));

    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
