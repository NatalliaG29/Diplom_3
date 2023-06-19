package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private String url = "https://stellarburgers.nomoreparties.site/account/profile";

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    private final WebDriver driver;
    public String getURL() {
        return url;
    }

    //Локатор кнопки "Сохранить" на экране профиля
    public By BUTTON_SAVE = By.xpath(".//button[text() ='Сохранить']");
}
