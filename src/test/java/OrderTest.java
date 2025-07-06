import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
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
        orderPage.waitForTextToBeOrderHeader("Для кого самокат");
        //заполнили имя, фамилию, адрес, рандомную станцию метро, рандомный номер телефона
        orderPage.inputFirstName(firstName);
        orderPage.inputLastName(lastName);
        orderPage.inputAddress(address);
        orderPage.inputRandomMetro();
        orderPage.inputPhoneNumber();
        //Нажимаем кнопку далее
        orderPage.clickNextPageButton();
        //Дождались перехода формы
        orderPage.waitForTextToBeOrderHeader("Про аренду");
        //Заполнили дату доставки, период доставки
        orderPage.inputDate();
        orderPage.inputPeriod();
        orderPage.waitDropnownSelected();

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
        orderPage.waitForTextToBeModalHeader("Хотите оформить заказ?");
        //Нажатие на кнопку Да
        orderPage.clickOrderButtonYes();
        //Дождались открытия окна подтверждения с номером заказа
        orderPage.waitForTextToBeModalHeader("Заказ оформлен");
        orderPage.waitForTextToBeOrderNumber("Номер заказа:");
        //Достаем сообщение с номером заказа
        String orderText = orderPage.getTextOrderNumber();
        System.out.println(orderText);
        Pattern pattern = Pattern.compile("Номер заказа:\\s*(\\d+)");
        Matcher matcher = pattern.matcher(orderText);
        // Номер заказа найден
        Assertions.assertTrue(matcher.find(), "Номер заказа не найден в тексте: " + orderText);
        //Получаем номер заказ
        String orderNumber = matcher.group(1);
        // Можно дополнительно проверить, что номер не пустой и состоит из цифр
        Assertions.assertFalse(orderNumber.isEmpty(), "Номер заказа пустой");
        Assertions.assertTrue(orderNumber.matches("\\d+"), "Номер заказа содержит недопустимые символы");
        // Вывод номера заказа
        System.out.println("Извлечённый номер заказа: " + orderNumber);
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