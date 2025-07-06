import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String ORDER_URL = "https://qa-scooter.praktikum-services.ru/order";
    //генерация рандомного номера
    private PhoneNumberGenerator phoneNumberGenerator = new PhoneNumberGenerator();
    String phoneNumber = phoneNumberGenerator.generatePhoneNumber();

    //генерация локатора рандомной станции
    Random rand = new Random();
    private final int randomIndex = rand.nextInt(225);
    String xpathExpressionForSelectSearchRowLocator = String.format("//li[@class='select-search__row' and @data-index='%d']/button", randomIndex);

    //генерация рандомной даты от 1 до 28 числа месяца
    int day = rand.nextInt(28) + 1; // число от 1 до 28
    String dayString = String.format("%03d", day); // форматируем с ведущими нулями

    //генерация рандомного периода
    List<String> period = Arrays.asList(
            "сутки",
            "двое суток",
            "трое суток",
            "четверо суток",
            "пятеро суток",
            "шестеро суток",
            "семеро суток"
    );
    // выбираем случайный индекс
    String selectedPeriod = period.get(rand.nextInt(period.size()));

    //Создаем локаторы
    //Заголовок заказа
    private final By orderHeader = By.className("Order_Header__BZXOb");
    //Поле ввода имени
    private final By inputFirstNameLocator = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private final By inputLastNameLocator = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private final By inputAddressLocator = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле выбора станции метро
    private final By selectSearchInputLocator = By.className("select-search__input");
    //Рандомная станция из списка
    private final By selectSearchRowLocator = By.xpath(xpathExpressionForSelectSearchRowLocator);
    //Поле ввода телефона
    private final By inputPhoneNumberLocator = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private final By nextPageButtonLocator = By.className("Button_Middle__1CSJM");
    //Поле выбора даты
    private final By inputDateLocator = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Выбор рандомного числа от 1 до 28 текущего месяца
    private final By inputDatepickerLocator = By.className("react-datepicker__day--" + dayString);
    //Поле выбора срока аренды
    private final By inputPeriodLocator = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    //Выбор рандомного срока аренды
    private final By periodDropdownOption = By.xpath(".//div[contains(@class, 'Dropdown-option') and normalize-space(text())='" + selectedPeriod + "']");
    //Выбор черного цвета самоката
    private final By selectBlackColorLocator = By.xpath(".//input[@id=\"black\"]");
    //Выбор серого цвета самоката
    private final By selectGreyColorLocator = By.xpath(".//input[@id=\"grey\"]");
    //Поле ввода комментария
    private final By inputCommentLocator = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка заказать
    private final By orderButtonLocator = By.xpath(".//button[@class = \"Button_Button__ra12g Button_Middle__1CSJM\" and text()='Заказать']");
    //Заголовок окна подтверждения
    private final By orderModalHeaderLocator = By.className("Order_ModalHeader__3FDaJ");
    //Номер заказа
    private final By orderNumberLocator = By.className("Order_Text__2broi");
    //Кнопка да
    private final By orderButtonYesLocator = By.xpath(".//button[@class = \"Button_Button__ra12g Button_Middle__1CSJM\" and text()='Да']");
    //Кнопка Нет
    private final By orderButtonNoLocator = By.xpath(".//button[text()='Нет']");
    //Введите корректное имя
    private final By errorFirstNameLocator = By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text() = 'Введите корректное имя']");
    //Введите корректную фамилию
    private final By errorLastNameLocator = By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text() = 'Введите корректную фамилию']");
    //Введите корректный адрес
    private final By errorAddressLocator = By.xpath("//div[@class='Input_ErrorMessage__3HvIb' and text() = 'Введите корректный адрес']");
    //Выберите станцию
    private final By errorStationLocator = By.className("Order_MetroError__1BtZb");
    //Введите корректный номер
    private final By errorPhoneNumberLocator = By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text() = 'Введите корректный номер']");
    //Период выбран
    private final By dropnownSelectedLocator = By.cssSelector(".Dropdown-placeholder.is-selected");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //Ввод имени
    public void inputFirstName(String firstName) {
        driver.findElement(inputFirstNameLocator).isDisplayed();
        driver.findElement(inputFirstNameLocator).clear();
        driver.findElement(inputFirstNameLocator).sendKeys(firstName);
    }

    //Ввод фамилии
    public void inputLastName(String lastName) {
        driver.findElement(inputLastNameLocator).isDisplayed();
        driver.findElement(inputLastNameLocator).clear();
        driver.findElement(inputLastNameLocator).sendKeys(lastName);
    }

    //Ввод адреса
    public void inputAddress(String address) {
        driver.findElement(inputAddressLocator).isDisplayed();
        driver.findElement(inputAddressLocator).clear();
        driver.findElement(inputAddressLocator).sendKeys(address);
    }

    //Выбор рандомного метро из списка
    public void inputRandomMetro() {
        driver.findElement(selectSearchInputLocator).isDisplayed();
        driver.findElement(selectSearchInputLocator).click();
        driver.findElement(selectSearchRowLocator).isDisplayed();
        driver.findElement(selectSearchRowLocator).click();
    }

    //Ввод рандомного номера
    public void inputPhoneNumber() {
        driver.findElement(inputPhoneNumberLocator).isDisplayed();
        driver.findElement(inputPhoneNumberLocator).click();
        driver.findElement(inputPhoneNumberLocator).sendKeys(phoneNumber);
    }

    //Нажатие на кнопку далее
    public void clickNextPageButton() {
        driver.findElement(nextPageButtonLocator).isDisplayed();
        driver.findElement(nextPageButtonLocator).click();
    }

    //Выбор даты с 1 по 28 число текущего месяца
    public void inputDate() {
        driver.findElement(inputDateLocator).isDisplayed();
        driver.findElement(inputDateLocator).click();
        driver.findElement(inputDatepickerLocator).isDisplayed();
        driver.findElement(inputDatepickerLocator).click();
    }

    //Выбор срока
    public void inputPeriod() {
        driver.findElement(inputPeriodLocator).isDisplayed();
        driver.findElement(inputPeriodLocator).click();
        driver.findElement(periodDropdownOption).isDisplayed();
        driver.findElement(periodDropdownOption).click();
    }

    //Выбор черного цвета
    public void selectBlackColor() {
        driver.findElement(selectBlackColorLocator).isDisplayed();
        driver.findElement(selectBlackColorLocator).click();
    }

    //Выбор серого цвета
    public void selectGreyColor() {
        driver.findElement(selectGreyColorLocator).isDisplayed();
        driver.findElement(selectGreyColorLocator).click();
    }

    //Ввод комментария
    public void inputComment(String comment) {
        driver.findElement(inputCommentLocator).isDisplayed();
        driver.findElement(inputCommentLocator).clear();
        driver.findElement(inputCommentLocator).sendKeys(comment);
    }

    //Нажатие на кнопку заказать
    public void clickOrderButton() {
        driver.findElement(orderButtonLocator).isDisplayed();
        driver.findElement(orderButtonLocator).click();
    }

    //Нажатие на кнопку да
    public void clickOrderButtonYes() {
        driver.findElement(orderButtonYesLocator).isDisplayed();
        driver.findElement(orderButtonYesLocator).click();
    }

    //Нажатие на кнопку нет
    public void clickOrderButtonNo() {
        driver.findElement(orderButtonNoLocator).isDisplayed();
        driver.findElement(orderButtonNoLocator).click();
    }

    //Получение локатора ошибки ввода имени
    public By getErrorFirstNameLocator() {
        return errorFirstNameLocator;
    }
    //Получение локатора ошибки ввода фамилии
    public By getErrorLastNameLocator() {
        return errorLastNameLocator;
    }
    //Получение локатора ошибки ввода адреса
    public By getErrorAddressLocator(){
        return errorAddressLocator;
    }
    //Получение локатора ошибки выбора станции
    public By getErrorStationLocator() {
        return errorStationLocator;
    }
    //Получение локатора ошибки ввода телефонного номера
    public By getErrorPhoneNumberLocator() {
        return errorPhoneNumberLocator;
    }
    // Метод для ожидания текста в элементе orderHeader
    public void waitForTextToBeOrderHeader(String expectedText) {
        wait.until(ExpectedConditions.textToBe(orderHeader, expectedText));
    }
    //Метод для ожидания период выбран
    public void waitDropnownSelected() {
        wait.until(ExpectedConditions.elementToBeClickable(dropnownSelectedLocator));
    }
    //Метод для ожидания текста в orderModalHeader
    public void waitForTextToBeModalHeader(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(orderModalHeaderLocator, expectedText));
    }
    //Метод для ожидания текста в orderNumber
    public void waitForTextToBeOrderNumber(String expectedText) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(orderNumberLocator, expectedText));
    }
    //orderNumberLocator
    public String getTextOrderNumber() {
       driver.findElement(orderNumberLocator).isDisplayed();
       return driver.findElement(orderNumberLocator).getText();
    }

    // Методы для ожидания ошибок
    public void waitErrorFirstName() {
        wait.until(ExpectedConditions.presenceOfElementLocated(errorFirstNameLocator));
    }
    public void waitErrorLastName() {
        wait.until(ExpectedConditions.presenceOfElementLocated(errorLastNameLocator));
    }
    public void waitErrorAddress() {
        wait.until(ExpectedConditions.presenceOfElementLocated(errorAddressLocator));
    }
    public void waitErrorStation() {
        wait.until(ExpectedConditions.presenceOfElementLocated(errorStationLocator));
    }
    public void waitErrorPhoneNumber() {
        wait.until(ExpectedConditions.presenceOfElementLocated(errorPhoneNumberLocator));
    }
}