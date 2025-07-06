import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String MAIN_URL = "https://qa-scooter.praktikum-services.ru/";
    //Кнопка подтверждения куки
    private final By cookieConfirmLocator = By.id("rcc-confirm-button");
    //Кнопка заказать в заголовке страницы
    private final By orderButtonHeader = By.className("Button_Button__ra12g");
    //Кнопка заказать в центре страницы
    private final By orderButtonMiddle = By.className("Button_Middle__1CSJM");
    //Логотип самоката
    private final By logoScooter = By.className("Header_LogoScooter__3lsAR");
    //Логотип яндекса
    private final By logoYandex = By.className("Header_LogoYandex__3TSOI");
    //Кнопка статус заказа
    private final By statusButton = By.className("Header_Link__1TAG7");
    //Поле ввода номера заказа
    private final By statusInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    //Кнопка Go
    private final By statusGo = By.xpath("//button[text()='Go!']");
    //Заголовок домашней страницы
    private final By homeHeaderLocator = By.className("Home_Header__iJKdX");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    //метод для нажатия на кнопку принятия куки
    public void cookieConfirm() {
        driver.findElement(cookieConfirmLocator).isDisplayed();
        driver.findElement(cookieConfirmLocator).click();
    }

    //метод для нажатия на кнопку Заказать в хедере
    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).isDisplayed();
        driver.findElement(orderButtonHeader).click();
    }

    //метод для нажатия на кнопку Заказать в центре страницы
    public void clickOrderButtonMiddle() {
        driver.findElement(orderButtonMiddle).isDisplayed();
        driver.findElement(orderButtonMiddle).click();
    }

    //Метод для нажатия на лого Самокат
    public void clickLogoScooter(){
        driver.findElement(logoScooter).isDisplayed();
        driver.findElement(logoScooter).click();
    }

    //Метод для нажатия на лого Яндекс
    public void clickLogoYandex(){
        driver.findElement(logoYandex).isDisplayed();
        driver.findElement(logoYandex).click();
    }

    //Метод для получения локатора хедера домашней страницы
    public By getHomeHeaderLocator() {
        return homeHeaderLocator;
    }

    //Метод для нажатия на кнопку статус
    public void clickStatus(){
        driver.findElement(statusButton).isDisplayed();
        driver.findElement(statusButton).click();
    }
    //Метод для заполнения номера заказа
    public void inputStatus(String orderNumber){
        driver.findElement(statusInput).isDisplayed();
        driver.findElement(statusInput).sendKeys(orderNumber);
        driver.findElement(statusGo).isDisplayed();
        driver.findElement(statusGo).click();
    }

    // Метод для ожидания текста в элементе
    public void waitTextToBePresentInElementLocatedHomeHeader() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(homeHeaderLocator, "на пару дней"));
    }
    //Метод для ожидания статуса
    public void waitStatusInputClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(statusInput));
    }
}
