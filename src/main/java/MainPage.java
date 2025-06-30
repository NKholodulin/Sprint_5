import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    private By cookieConfirmLocator = By.id("rcc-confirm-button");
    private By orderButtonHeader = By.className("Button_Button__ra12g");
    private By orderButtonMiddle = By.className("Button_Middle__1CSJM");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для нажатия на кнопку принятия куки
    public void cookieConfirm() {
        driver.findElement(cookieConfirmLocator).click();
    }

    //метод для нажатия на кнопку Заказать в хедере
    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    //метод для нажатия на кнопку Заказать в центре страницы
    public void clickOrderButtonMiddle() {
        driver.findElement(orderButtonMiddle).click();
    }

}
