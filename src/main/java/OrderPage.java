import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    private By orderHeader = By.className("Order_Header__BZXOb");
    private By inputNameLocator = By.xpath(".//input[@placeholder='* Имя']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getOrderHeader() {
        return orderHeader;
    }

    public void inputName(String name){
        driver.findElement(inputNameLocator).isDisplayed();
        driver.findElement(inputNameLocator).clear();
        driver.findElement(inputNameLocator).sendKeys(name);
    }

}
