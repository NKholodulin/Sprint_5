import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

public class OrderTest {
    private WebDriver driver;
    MainPage mainPage;
    OrderPage orderPage;

    @ParameterizedTest
    @MethodSource("locatorProvider")
    void orderHeader(String orderButton, String color, String firstName, String lastName, String address, String comment) {
        driver = new ChromeDriver();
        //Оставил этот блок для себя
//        if ("chrome".equalsIgnoreCase(browser)) {
//            driver = new ChromeDriver();
//        } else if ("firefox".equalsIgnoreCase(browser)) {
//            driver = new FirefoxDriver();
//        } else {
//            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
//        }
        // перешли на страницу тестового приложения
        driver.get(MainPage.MAIN_URL);
        // создали объекты классов страниц
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);

        //подтвердили куки
        mainPage.cookieConfirm();

        // выбрали кнопку Заказать в хедере или центре страницы
        if ("middle".equalsIgnoreCase(orderButton)) {
            mainPage.clickOrderButtonHeader();
        } else if ("header".equalsIgnoreCase(orderButton)) {
            mainPage.clickOrderButtonMiddle();
        } else {
            throw new IllegalArgumentException("Неизвестная кнопка: " + orderButton);
        }

        //Дождались открытия формы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(orderPage.getOrderHeader(), "Для кого самокат"));
        //заполнили имя, фамилию, адрес, рандомную станцию метро, рандомный номер телефона
        orderPage.inputFirstName(firstName);
        orderPage.inputLastName(lastName);
        orderPage.inputAddress(address);
        orderPage.inputRandomMetro();
        orderPage.inputPhoneNumber();
        //Нажимаем кнопку далее
        orderPage.clickNextPageButton();
        //Дождались перехода формы
        wait.until(ExpectedConditions.textToBe(orderPage.getOrderHeader(), "Про аренду"));
        //Заполнили дату доставки, период доставки
        orderPage.inputDate();
        orderPage.inputPeriod();
        wait.until(ExpectedConditions.elementToBeClickable(orderPage.getDropnownSelectedLocator()));

        // выбрали цвет в зависимости от тестдаты
        if ("grey".equalsIgnoreCase(color)) {
            orderPage.selectBlackColor();
        } else if ("black".equalsIgnoreCase(color)) {
            orderPage.selectGreyColor();
        } else {
            throw new IllegalArgumentException("Неизвестный цвет: " + color);
        }

        // заполнили комментарий
        orderPage.inputComment(comment);
        //Нажали на кнопку заказать
        orderPage.clickOrderButton();
        //Дождались открытие окна подтверждения
        wait.until(ExpectedConditions.textToBePresentInElementLocated(orderPage.getOrderModalHeaderLocator(), "Хотите оформить заказ?"));
        //Нажатие на кнопку Да
        orderPage.clickOrderButtonYes();
        //Дождались открытия окна подтверждения с номером заказа
        wait.until(ExpectedConditions.textToBePresentInElementLocated(orderPage.getOrderModalHeaderLocator(), "Заказ оформлен"));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(orderPage.getOrderNumberLocator(), "Номер заказа:"));
    }

    @AfterEach
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }

    static Stream<Arguments> locatorProvider() {
        return Stream.of(
                // Реализуй тестовые данные
                Arguments.of("header", "black", "Нед", "Старк", "Винтерфелл", ""), Arguments.of("middle", "grey", "Иван", "Иванов", "Москва", "Позвонить за час"), Arguments.of("header", "grey", "Василиса", "Премудрая", "Город", "Комментарий"), Arguments.of("middle", "black", "Арья", "Старк", "Браавос", "1234567890-=!№;\"%:?*())))))))_+"));
    }
}