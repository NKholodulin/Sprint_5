import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.stream.Stream;

public class QuestionTest {
    private WebDriver driver;
    MainPage mainPage;

    @BeforeEach
    void setUp() {
        // создали драйвер для браузера Chrome или Firefox
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        // перешли на страницу тестового приложения
        driver.get("https://qa-scooter.praktikum-services.ru/");
        // создали объект класса страницы с вопросами
        mainPage = new MainPage(driver);
    }

    @ParameterizedTest
    @MethodSource("locatorProvider")
    void checkQuestion(By questionLocator, By accordionLocator, String expected, String expectedQuestion) throws InterruptedException {

        mainPage.cookieConfirm();

        //Ожидание пока элемент станет доступен
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(questionLocator));

        //Прокрутка
        WebElement element = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        String actualQuestion = driver.findElement(questionLocator).getText();
        // раскрыли вопрос
        driver.findElement(questionLocator).click();

        //Ожидание пока элемент станет доступен
        wait.until(ExpectedConditions.elementToBeClickable(questionLocator));

        // получили текст элемента вопроса
        String accordion = driver.findElement(accordionLocator).getText();

        // сделали проверку, что полученные значения совпадают с ответом
        Assertions.assertEquals(expected, accordion, "Полученное значение не совпадает с ответом");
        Assertions.assertEquals(expectedQuestion, actualQuestion, "Полученное значение не совпадает с вопросом");

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
                        By.id("accordion__heading-0"),
                        By.id("accordion__panel-0"),
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                        "Сколько это стоит? И как оплатить?"
                ),
                Arguments.of(
                        By.id("accordion__heading-1"),
                        By.id("accordion__panel-1"),
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                        "Хочу сразу несколько самокатов! Так можно?"
                ),
                Arguments.of(
                        By.id("accordion__heading-2"),
                        By.id("accordion__panel-2"),
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                        "Как рассчитывается время аренды?"
                ),
                Arguments.of(
                        By.id("accordion__heading-3"),
                        By.id("accordion__panel-3"),
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                        "Можно ли заказать самокат прямо на сегодня?"
                ),
                Arguments.of(
                        By.id("accordion__heading-4"),
                        By.id("accordion__panel-4"),
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                        "Можно ли продлить заказ или вернуть самокат раньше?"
                ),
                Arguments.of(
                        By.id("accordion__heading-5"),
                        By.id("accordion__panel-5"),
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                        "Вы привозите зарядку вместе с самокатом?"
                ),
                Arguments.of(
                        By.id("accordion__heading-6"),
                        By.id("accordion__panel-6"),
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                        "Можно ли отменить заказ?"
                ),
                Arguments.of(
                        By.id("accordion__heading-7"),
                        By.id("accordion__panel-7"),
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области.",
                        "Я живу за МКАДом, привезёте?"
                )

        );
    }
}
