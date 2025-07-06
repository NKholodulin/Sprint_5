import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;
    YandexPage yandexPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        // создали объекты классов страниц
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        yandexPage = new YandexPage(driver);
    }

    @Test
    void testFormSubmissionWithActions() {
        driver.get(OrderPage.ORDER_URL);
        //подтвердили куки
        mainPage.cookieConfirm();
        //Дождались открытия формы
        orderPage.waitForTextToBeOrderHeader("Для кого самокат");

        //Нажали на лого самоката
        mainPage.clickLogoScooter();

        //Дождались открытия главной страницы
        mainPage.waitTextToBePresentInElementLocatedHomeHeader();
        String text = mainPage.getTextHomeHeader();
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
        yandexPage.waitYaUrlContains();
        // Проверка URL
        String currentUrl = driver.getCurrentUrl();
        assertEquals(YandexPage.YA_URL, currentUrl);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
