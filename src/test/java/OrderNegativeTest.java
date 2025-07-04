import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderNegativeTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    @ParameterizedTest
    @MethodSource("locatorProvider")
    void orderHeader(String browser) {

        // создали драйвер для браузера Chrome или Firefox
        if ("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
        // перешли на страницу тестового приложения
        driver.get(MainPage.MAIN_URL);
        // создали объекты классов страниц
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);

        //подтвердили куки
        mainPage.cookieConfirm();

        // выбрали кнопку Заказать в хедере
        mainPage.clickOrderButtonHeader();

        //Дождались открытия формы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(orderPage.getOrderHeader(), "Для кого самокат"));
        orderPage.clickNextPageButton();
        //блок проверок
        //имя
        WebElement errorMsg = driver.findElement(By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text() = 'Введите корректное имя']"));
        assertEquals("Введите корректное имя", errorMsg.getText());
        //фамилия
        errorMsg = driver.findElement(By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text() = 'Введите корректную фамилию']"));
        assertEquals("Введите корректную фамилию", errorMsg.getText());
        //адрес
        errorMsg = driver.findElement(By.xpath("//div[@class='Input_ErrorMessage__3HvIb' and text() = 'Введите корректный адрес']"));
        assertEquals("Введите корректный адрес", errorMsg.getText());
        //адрес
        errorMsg = driver.findElement(By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text() = 'Выберите станцию']"));
        assertEquals("Выберите станцию", errorMsg.getText());
        //адрес
        errorMsg = driver.findElement(By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text() = 'Введите корректный номер']"));
        assertEquals("Введите корректный номер", errorMsg.getText());
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

    static Stream<Arguments> locatorProvider() {
        return Stream.of(
                // Реализуй тестовые данные
                Arguments.of(
                        "chrome"
                ),
                Arguments.of(
                        "firefox"
                )
        );
    }

}
