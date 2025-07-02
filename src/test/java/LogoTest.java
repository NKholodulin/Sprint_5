import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        // создали объекты классов страниц
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    void testFormSubmissionWithActions() {
        driver.get("https://qa-scooter.praktikum-services.ru/order");
        //подтвердили куки
        mainPage.cookieConfirm();
        //Дождались открытия формы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBe(orderPage.getOrderHeader(), "Для кого самокат"));

        //Нажали на лого самоката
        mainPage.clickLogoScooter();

        //Дождались открытия главной страницы
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("Home_Header__iJKdX"), "на пару дней"));
        WebElement header = driver.findElement(By.className("Home_Header__iJKdX"));
        String text = header.getText();
        //Проверили что в хедере содержаться эти слова
        assertTrue(text.contains("Самокат") && text.contains("на пару дней"));

        //Нажали на лого Яндекса
        mainPage.clickLogoYandex();

        // Получаем все идентификаторы окон
        Set<String> windowHandles = driver.getWindowHandles();

        // Переключаемся на новое окно (предполагается, что оно одно)
        String newWindowHandle = null;
        for (String handle : windowHandles) {
            if (!handle.equals(driver.getWindowHandle())) {
                newWindowHandle = handle;
                break;
            }
        }

        // Переключаемся на новое окно
        driver.switchTo().window(newWindowHandle);
        wait.until(ExpectedConditions.urlContains("https://ya.ru/"));
        // Проверка URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://ya.ru/", currentUrl);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
