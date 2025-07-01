import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    private By orderHeader = By.className("Order_Header__BZXOb");
    private By inputFirstNameLocator = By.xpath(".//input[@placeholder='* Имя']");
    private By inputLastNameLocator = By.xpath(".//input[@placeholder='* Фамилия']");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getOrderHeader() {
        return orderHeader;
    }

    public void inputFirstName(String name){
        driver.findElement(inputFirstNameLocator).isDisplayed();
        driver.findElement(inputFirstNameLocator).clear();
        driver.findElement(inputFirstNameLocator).sendKeys(name);
    }
    public void inputLastName(String name){
        driver.findElement(inputLastNameLocator).isDisplayed();
        driver.findElement(inputLastNameLocator).clear();
        driver.findElement(inputLastNameLocator).sendKeys(name);
    }

}
