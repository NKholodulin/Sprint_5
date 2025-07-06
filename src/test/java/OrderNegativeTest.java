import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        WebElement errorMsg = driver.findElement(orderPage.getErrorFirstNameLocator());
        assertEquals("Введите корректное имя", errorMsg.getText());
    }

    @Test
    void orderErrorLastName() {
        orderPage.waitErrorLastName();
        //фамилия
        WebElement errorMsg = driver.findElement(orderPage.getErrorLastNameLocator());
        assertEquals("Введите корректную фамилию", errorMsg.getText());
    }

    @Test
    void orderErrorAddress() {
        orderPage.waitErrorAddress();
        //адрес
        WebElement errorMsg = driver.findElement(orderPage.getErrorAddressLocator());
        assertEquals("Введите корректный адрес", errorMsg.getText());
    }

    @Test
    void orderErrorStation() {
        orderPage.waitErrorStation();
        //станция
        WebElement errorMsg = driver.findElement(orderPage.getErrorStationLocator());
        assertEquals("Выберите станцию", errorMsg.getText());
    }

    @Test
    void orderErrorPhoneNumber() {
        orderPage.waitErrorPhoneNumber();
        //номер
        WebElement errorMsg = driver.findElement(orderPage.getErrorPhoneNumberLocator());
        assertEquals("Введите корректный номер", errorMsg.getText());
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
