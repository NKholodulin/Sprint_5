import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderNegativeTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
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
        orderPage.waitForTextToBeOrderHeader("Для кого самокат");
        orderPage.clickNextPageButton();
    }

    @Test
    void orderErrorFirstName() {
        //имя
        orderPage.waitErrorFirstName();
        String errorMsg = orderPage.getTextErrorFirstName();
        assertEquals("Введите корректное имя", errorMsg);
    }

    @Test
    void orderErrorLastName() {
        orderPage.waitErrorLastName();
        //фамилия
        String errorMsg = orderPage.getTextErrorLastName();
        assertEquals("Введите корректную фамилию", errorMsg);
    }

    @Test
    void orderErrorAddress() {
        orderPage.waitErrorAddress();
        //адрес
        String errorMsg = orderPage.getTextErrorAddress();
        assertEquals("Введите корректный адрес", errorMsg);
    }

    @Test
    void orderErrorStation() {
        orderPage.waitErrorStation();
        //станция
        String errorMsg = orderPage.getTextErrorStation();
        assertEquals("Выберите станцию", errorMsg);
    }

    @Test
    void orderErrorPhoneNumber() {
        orderPage.waitErrorPhoneNumber();
        //номер
        String errorMsg = orderPage.getTextErrorPhoneNumber();
        assertEquals("Введите корректный номер", errorMsg);
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
