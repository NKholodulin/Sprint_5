import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;


    private By cookieConfirmLocator = By.id("rcc-confirm-button");

    public By getCookieConfirmLocator() {
        return cookieConfirmLocator;
    }


    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //метод для нажатия на кнопку принятия куки
    public void cookieConfirm(By cookieConfirmLocator){
        driver.findElement(cookieConfirmLocator).click();
    }

}
