import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YandexPage {
        private WebDriver driver;
        private WebDriverWait wait;

        public static final String YA_URL = "https://ya.ru/";

        public YandexPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }

        // Метод для ожидания url
        public void waitYaUrlContains() {
            wait.until(ExpectedConditions.urlContains(YA_URL));
        }
}
