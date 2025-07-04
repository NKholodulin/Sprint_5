import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderTrackPage
{
    private WebDriver driver;

    public static final String TRACK_URL = "https://qa-scooter.praktikum-services.ru/track?t=";
    public static final String NEGATIVE_TRACK_URL = "https://qa-scooter.praktikum-services.ru/track?t=12345";

    //Картинка заказ не найден
    private By ImgNotFoundLocator = By.xpath(".//img[@alt='Not found']");

    public OrderTrackPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод для получения локатора с картинкой заказ не найден
    public By getImgNotFoundLocator() {
        return ImgNotFoundLocator;
    }
}
