import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;
import java.util.Arrays;
import java.util.List;

public class OrderPage {
    private WebDriver driver;

    //генерация рандомного номера
    private PhoneNumberGenerator phoneNumberGenerator = new PhoneNumberGenerator();
    String phoneNumber = phoneNumberGenerator.generatePhoneNumber();

    //генерация локатора рандомной станции
    Random rand = new Random();
    private int randomIndex = rand.nextInt(225);
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
    private By orderHeader = By.className("Order_Header__BZXOb");
    //Поле ввода имени
    private By inputFirstNameLocator = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private By inputLastNameLocator = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private By inputAddressLocator = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле выбора станции метро
    private By selectSearchInputLocator = By.className("select-search__input");
    //Рандомная станция из списка
    private By selectSearchRowLocator = By.xpath(xpathExpressionForSelectSearchRowLocator);
    //Поле ввода телефона
    private By inputPhoneNumberLocator = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка далее
    private By nextPageButtonLocator = By.className("Button_Middle__1CSJM");
    //Поле выбора даты
    private By inputDateLocator = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Выбор рандомного числа от 1 до 28 текущего месяца
    private By inputDatepickerLocator = By.className("react-datepicker__day--" + dayString);
    //Поле выбора срока аренды
    private By inputPeriodLocator = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    //Выбор рандомного срока аренды
    private By periodDropdownOption = By.xpath(".//div[contains(@class, 'Dropdown-option') and normalize-space(text())='" + selectedPeriod + "']");
    //Выбор черного цвета самоката
    private By selectBlackColorLocator = By.xpath(".//input[@id=\"black\"]");
    //Выбор серого цвета самоката
    private By selectGreyColorLocator = By.xpath(".//input[@id=\"grey\"]");
    //Поле ввода комментария
    private By inputCommentLocator = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка заказать
    private By orderButtonLocator = By.xpath(".//button[@class = \"Button_Button__ra12g Button_Middle__1CSJM\" and text()='Заказать']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getOrderHeader() {
        return orderHeader;
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
    public void selectBlackColor(){
        driver.findElement(selectBlackColorLocator).isDisplayed();
        driver.findElement(selectBlackColorLocator).click();
    }

    //Выбор серого цвета
    public void selectGreyColor(){
        driver.findElement(selectGreyColorLocator).isDisplayed();
        driver.findElement(selectGreyColorLocator).click();
    }

    //Ввод комментария
    public void inputComment(String comment){
        driver.findElement(inputCommentLocator).isDisplayed();
        driver.findElement(inputCommentLocator).clear();
        driver.findElement(inputCommentLocator).sendKeys(comment);
    }

    //Нажатие на кнопку заказать
    public void clickOrderButton(){
        driver.findElement(orderButtonLocator).isDisplayed();
        driver.findElement(orderButtonLocator).click();
    }

}