import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class QuestionPage {
    private WebDriver driver;

    private By questionPriceLocator = By.id("accordion__heading-0");
    public By getQuestionPriceLocator() {
        return questionPriceLocator;
    }

    private By accordionPriceLocator = By.id("accordion__panel-0");
    public By getAccordionPriceLocator() {
        return accordionPriceLocator;
    }

    private By questionQuantityLocator = By.id("accordion__heading-9");
    private By accordionQuantityLocator = By.id("accordion__panel-9");
    private By questionTimeLocator = By.id("accordion__heading-10");
    private By accordionTimeLocator = By.id("accordion__panel-10");
    private By questionTodayLocator = By.id("accordion__heading-11");
    private By accordionTodayLocator = By.id("accordion__panel-11");
    private By questionExtendReturnLocator = By.id("accordion__heading-12");
    private By accordionExtendReturnLocator = By.id("accordion__panel-12");
    private By questionChargeLocator = By.id("accordion__heading-13");
    private By accordionChargeLocator = By.id("accordion__panel-13");
    private By questionCancelLocator = By.id("accordion__heading-14");
    private By accordionCancelLocator = By.id("accordion__panel-14");
    private By questionDeliveryLocator = By.id("accordion__heading-15");
    private By accordionDeliveryLocator = By.id("accordion__panel-15");


    public QuestionPage(WebDriver driver){
        this.driver = driver;
    }

    //метод для нажатия на кнопку открытия вопроса
    public void expandQuestion(By questionLocator){
        driver.findElement(questionLocator).click();
    }

    public String getAccordionText(By accordionLocator){
        return driver.findElement(accordionLocator).getText();
    }
}
