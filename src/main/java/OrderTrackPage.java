import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderTrackPage
{
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String TRACK_URL = "https://qa-scooter.praktikum-services.ru/track?t=";
    public static final String NEGATIVE_TRACK_URL = "https://qa-scooter.praktikum-services.ru/track?t=12345";

    //Картинка заказ не найден
    private final By ImgNotFoundLocator = By.xpath(".//img[@alt='Not found']");

    public OrderTrackPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //метод для получения локатора с картинкой заказ не найден
    public By getImgNotFoundLocator() {
        return ImgNotFoundLocator;
    }
    //Ожидание открытия негативного урл
    public void waitNegativeTrackUrl() {
        wait.until(ExpectedConditions.urlToBe(NEGATIVE_TRACK_URL));
    }
}
