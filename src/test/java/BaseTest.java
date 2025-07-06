import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;
    YandexPage yandexPage;
    OrderTrackPage orderTrackPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        // создали объекты классов страниц
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        yandexPage = new YandexPage(driver);
        orderTrackPage = new OrderTrackPage(driver);
    }

    @Test
    void testLogoScooter() {
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
    }
    @Test
    void testLogoYandex() {
        driver.get(MainPage.MAIN_URL);

        //Дождались открытия главной страницы
        mainPage.waitTextToBePresentInElementLocatedHomeHeader();
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
    @Test
    void testOrderNumberNegative() {
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
