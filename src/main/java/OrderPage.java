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
    private By orderHeader = By.className("Order_Header__BZXOb");
    private By inputFirstNameLocator = By.xpath(".//input[@placeholder='* Имя']");
    private By inputLastNameLocator = By.xpath(".//input[@placeholder='* Фамилия']");
    private By inputAddressLocator = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private By selectSearchInputLocator = By.className("select-search__input");
    private By selectSearchRowLocator = By.xpath(xpathExpressionForSelectSearchRowLocator);
    private By inputPhoneNumberLocator = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextPageButtonLocator = By.className("Button_Middle__1CSJM");
    private By inputDateLocator = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private By inputDatepickerLocator = By.className("react-datepicker__day--" + dayString);
    private By inputPeriodLocator = By.xpath(".//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    private By periodDropdownOption = By.xpath("//div[contains(@class, 'Dropdown-option') and normalize-space(text())='" + selectedPeriod + "']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getOrderHeader() {
        return orderHeader;
    }

    public void inputFirstName(String firstName) {
        driver.findElement(inputFirstNameLocator).isDisplayed();
        driver.findElement(inputFirstNameLocator).clear();
        driver.findElement(inputFirstNameLocator).sendKeys(firstName);
    }

    public void inputLastName(String lastName) {
        driver.findElement(inputLastNameLocator).isDisplayed();
        driver.findElement(inputLastNameLocator).clear();
        driver.findElement(inputLastNameLocator).sendKeys(lastName);
    }

    public void inputAddress(String address) {
        driver.findElement(inputAddressLocator).isDisplayed();
        driver.findElement(inputAddressLocator).clear();
        driver.findElement(inputAddressLocator).sendKeys(address);
    }

    public void inputRandomMetro() {
        driver.findElement(selectSearchInputLocator).isDisplayed();
        driver.findElement(selectSearchInputLocator).click();
        driver.findElement(selectSearchRowLocator).isDisplayed();
        driver.findElement(selectSearchRowLocator).click();
    }

    public void inputPhoneNumber() {
        driver.findElement(inputPhoneNumberLocator).isDisplayed();
        driver.findElement(inputPhoneNumberLocator).click();
        driver.findElement(inputPhoneNumberLocator).sendKeys(phoneNumber);
    }

    public void clickNextPageButton() {
        driver.findElement(nextPageButtonLocator).isDisplayed();
        driver.findElement(nextPageButtonLocator).click();
    }

    public void inputDate() {
        driver.findElement(inputDateLocator).isDisplayed();
        driver.findElement(inputDateLocator).click();
        driver.findElement(inputDatepickerLocator).isDisplayed();
        driver.findElement(inputDatepickerLocator).click();
    }

    public void inputPeriod() {
        driver.findElement(inputPeriodLocator).isDisplayed();
        driver.findElement(inputPeriodLocator).click();
        driver.findElement(periodDropdownOption).isDisplayed();
        driver.findElement(periodDropdownOption).click();
    }
}