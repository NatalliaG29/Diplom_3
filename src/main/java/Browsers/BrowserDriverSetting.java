package Browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserDriverSetting {
    private final static Browser CURRENT_BROWSER = Browser.CHROME;

    public static WebDriver browserDriverSetUp(){
        WebDriver driver = null;
        switch (CURRENT_BROWSER) {
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                ChromeOptions optionsY = new ChromeOptions();
                optionsY.setBinary("C:\\Users\\gorni\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
                optionsY.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(optionsY);
                break;
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsC = new ChromeOptions();
                optionsC.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(optionsC);
                break;
        }
        return driver;
    }
}
