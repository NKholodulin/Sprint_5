import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    void orderHeader(String browser, String orderButton, String firstName, String lastName, String address) {

        // создали драйвер для браузера Chrome или Firefox
        if ("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
        // перешли на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создали объект класса страницы с вопросами
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);

        //подтвердили куки
        mainPage.cookieConfirm();

        //нажали Заказать в хедере страницы
        //mainPage.clickOrderButtonHeader();
        mainPage.clickOrderButtonMiddle();
        // создали драйвер для браузера Chrome или Firefox
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
        //заполнили имя, фамилию, адрес
        orderPage.inputFirstName(firstName);
        orderPage.inputLastName(lastName);
        orderPage.inputAddress(address);
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
                        "chrome",
                        "header",
                        "Никита",
                        "Холодулин",
                        "Санчелеево"
                ),
                Arguments.of(
                        "firefox",
                        "middle",
                        "Виктория",
                        "Холодулина",
                        "Тольятти"
                )
        );
    }
}