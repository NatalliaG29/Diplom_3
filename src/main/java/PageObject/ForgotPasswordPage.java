package PageObject;

import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private String url = "https://stellarburgers.nomoreparties.site/forgot-password";

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    WebDriver driver;
    public String getUrl() {
        return url;
    }
}
