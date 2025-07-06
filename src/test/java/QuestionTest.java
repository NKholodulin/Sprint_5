import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class QuestionTest {
    private WebDriver driver;
    MainPage mainPage;

    @ParameterizedTest
    @MethodSource("locatorProvider")
    void checkQuestion(int index, String expected, String expectedQuestion) throws InterruptedException {
        driver = new ChromeDriver();
        // Оставил этот блок для себя
//        if ("chrome".equalsIgnoreCase(browser)) {
//            driver = new ChromeDriver();
//        } else if ("firefox".equalsIgnoreCase(browser)) {
//            driver = new FirefoxDriver();
//        } else {
//            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
//        }
        // перешли на страницу тестового приложения
        driver.get(MainPage.MAIN_URL);
        // создали объект класса страницы с вопросами
        mainPage = new MainPage(driver);

        mainPage.cookieConfirm();

        //Ожидание пока элемент станет доступен
        mainPage.waitQuestionClickable(index);

        //Прокрутка
        mainPage.scrollToQuestion(index);
        // получили текст элемента вопроса
        String actualQuestion = mainPage.getQuestionText(index);
        // раскрыли вопрос
        mainPage.clickQuestion(index);

        //Ожидание пока элемент станет доступен
        mainPage.waitQuestionClickable(index);

        // получили текст элемента ответа
        String accordion = mainPage.getAnswerText(index);

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
        return IntStream.range(0, MainPage.QUESTIONS.length)
                .mapToObj(i -> Arguments.of(
                        i,
                        MainPage.ANSWERS[i],
                        MainPage.QUESTIONS[i]
                ));
    }
}