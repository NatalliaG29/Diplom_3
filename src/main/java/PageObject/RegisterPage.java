package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private String url = "https://stellarburgers.nomoreparties.site/register";

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }
    private final WebDriver driver;
    public String getURL() {
        return url;
    }

    //Локатор поля "Имя"
    private By FIELD_NAME = By.xpath(".//fieldset[1]/div/div/input[@class = 'text input__textfield text_type_main-default']");

    //Локатор поля "Email"
    private By FIELD_EMAIL = By.xpath(".//fieldset[2]/div/div/input[@class = 'text input__textfield text_type_main-default']");

    //Локатор поля "Пароль"
    private By FIELD_PASSWORD = By.xpath(".//fieldset[3]/div/div/input[@class = 'text input__textfield text_type_main-default']");

    //Локатор кнопки "Зарегистрироваться"
    private By BUTTON_REGISTER = By.xpath(".//button[text() = 'Зарегистрироваться']");

    public By LOGIN_BUTTON = By.xpath(".//button[text()='Войти']");
    public By INCORRECT_PASSWORD = By.xpath(".//p[text() = 'Некорректный пароль']");

    public void typeName(String name) {
        driver.findElement(FIELD_NAME).sendKeys(name);
    }

    public void typeEmail(String email) {
        driver.findElement(FIELD_EMAIL).sendKeys(email);
    }

    public void typePassword(String password) {
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
    }
    public void registerUser(String name, String email, String password) {
        typeName(name);
        typeEmail(email);
        typePassword(password);
        driver.findElement(BUTTON_REGISTER).click();
    }
}
