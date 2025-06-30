import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;

    private By orderHeader = By.className("Order_Header__BZXOb");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getOrderHeader() {
        return orderHeader;
    }

}
