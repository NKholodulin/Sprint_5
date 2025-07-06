import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public static final String MAIN_URL = "https://qa-scooter.praktikum-services.ru/";
    // Константы с вопросами и ответами
    public static final String[] QUESTIONS = {
            "Сколько это стоит? И как оплатить?",
            "Хочу сразу несколько самокатов! Так можно?",
            "Как рассчитывается время аренды?",
            "Можно ли заказать самокат прямо на сегодня?",
            "Можно ли продлить заказ или вернуть самокат раньше?",
            "Вы привозите зарядку вместе с самокатом?",
            "Можно ли отменить заказ?",
            "Я живу за МКАДом, привезёте?"
    };

    public static final String[] ANSWERS = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };
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

    // Метод для получения вопроса по номеру
    public WebElement getQuestion(int index) {
        return driver.findElement(By.id("accordion__heading-" + index));
    }

    // Метод для получения панели ответа по номеру
    public WebElement getAnswerPanel(int index) {
        return driver.findElement(By.id("accordion__panel-" + index));
    }
    // Метод для клика по вопросу
    public void clickQuestion(int index) {
        getQuestion(index).click();
    }

    // Метод для получения текста вопроса
    public String getQuestionText(int index) {
        return getQuestion(index).getText();
    }

    // Метод для получения текста ответа
    public String getAnswerText(int index) {
        return getAnswerPanel(index).getText();
    }

    //
    public void waitQuestionClickable(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(getQuestion(index)));
    }
    //Прокрутка к вопросу
    public void scrollToQuestion(int index) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getQuestion(index));
        Thread.sleep(500);
    }

    //Метод для получения текста
    public String getTextHomeHeader(){
        driver.findElement(homeHeaderLocator);
        return driver.findElement(homeHeaderLocator).getText();
    }

}
