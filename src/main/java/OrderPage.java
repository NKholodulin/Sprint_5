import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    private By orderHeader = By.className("Order_Header__BZXOb");
    private By inputFirstNameLocator = By.xpath(".//input[@placeholder='* Имя']");
    private By inputLastNameLocator = By.xpath(".//input[@placeholder='* Фамилия']");
    private By inputAddressLocator = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getOrderHeader() {
        return orderHeader;
    }

    public void inputFirstName(String firstName){
        driver.findElement(inputFirstNameLocator).isDisplayed();
        driver.findElement(inputFirstNameLocator).clear();
        driver.findElement(inputFirstNameLocator).sendKeys(firstName);
    }
    public void inputLastName(String lastName){
        driver.findElement(inputLastNameLocator).isDisplayed();
        driver.findElement(inputLastNameLocator).clear();
        driver.findElement(inputLastNameLocator).sendKeys(lastName);
    }

    public void inputAddress(String address){
        driver.findElement(inputAddressLocator).isDisplayed();
        driver.findElement(inputAddressLocator).clear();
        driver.findElement(inputAddressLocator).sendKeys(address);
    }
}
