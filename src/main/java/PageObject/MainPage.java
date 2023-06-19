package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private String url = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    WebDriver driver;
    public String getUrl() {
        return url;
    }

    //Локатор кнопки "Войти в аккаунт" на главной странице
    private By BUTTON_LOGIN_MAIN_PAGE = By.xpath(".//button[text()='Войти в аккаунт']");

    //Локатор кнопки "Личный Кабинет" на главной странице
    private By BUTTON_PERSONAL_ACCOUNT_MAIN_PAGE = By.xpath(".//a[@href='/account']");

    //Локатор кнопки "Оформить заказ" на главном экране
    public By BUTTON_CREATE_ORDER = By.xpath(".//button[text()='Оформить заказ']");

    //Локатор поля "Email" на экране Вход
    private By FIELD_EMAIL = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @type = 'text']");

    //Локатор поля "Пароль" на экране Вход
    private By FIELD_PASSWORD = By.xpath(".//input[@class = 'text input__textfield text_type_main-default' and @type = 'password']");

    //Локатор кнопки "Войти" на экране Вход
    private By BUTTON_LOGIN_LOGIN_PAGE = By.xpath(".//button[text() ='Войти']");

    //Локатор кнопки "Войти" на экране Регистрация
    private By BUTTON_LOGIN_REGISTER_PAGE = By.xpath(".//a[text() = 'Войти']");

    //Локатор кнопки "Войти" на экране Восстановление пароля
    private By BUTTON_LOGIN_FORGOT_PASSWORD_PAGE = By.xpath(".//a[text() ='Войти']");

    //Локатор логотипа и Кнопки "Конструктор"
    private By BUTTON_LOGO = By.xpath(".//a[@href ='/']");

    //Локатор кнопки "Выход"
    private By BUTTON_LOGOUT = By.xpath(".//button[text() ='Выход']");


    //Локатор раздела "Булки"
    private By BUNS = By.xpath(".//span[text() ='Булки']");
    //Локатор раздела "Булки" когда он не выбран
    public By BUNS_NO_SELECTED = By.xpath(".//section[1]/div[1]/div[1][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");

    //Локатор раздела "Соусы"
    private By SAUCES = By.xpath(".//span[text() ='Соусы']");
    //Локатор раздела "Соусы" когда он не выбран
    public By FILLINGS_NO_SELECTED = By.xpath(".//section[1]/div[1]/div[3][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");

    //Локатор раздела "Начинки"
    private By FILLINGS = By.xpath(".//span[text() ='Начинки']");
    //Локатор раздела "Начинки" когда он не выбран
    public By SAUCES_NO_SELECTED = By.xpath(".//section[1]/div[1]/div[2][@class='tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect']");


    public void typeEmail(String email) {
        driver.findElement(FIELD_EMAIL).sendKeys(email);
    }
    public void typePassword(String password) {
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
    }

    public void loginWithButtonLoginOnMainPage(String email, String password) {
        driver.findElement(BUTTON_LOGIN_MAIN_PAGE).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(FIELD_EMAIL));
        typeEmail(email);
        typePassword(password);
        driver.findElement(BUTTON_LOGIN_LOGIN_PAGE).click();
    }

    public void loginWithButtonPersonalAccountOnMainPage(String email, String password) {
        driver.findElement(BUTTON_PERSONAL_ACCOUNT_MAIN_PAGE).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(FIELD_EMAIL));
        typeEmail(email);
        typePassword(password);
        driver.findElement(BUTTON_LOGIN_LOGIN_PAGE).click();
    }

    public void loginWithButtonLoginOnRegisterPage(String email, String password) {
        driver.findElement(BUTTON_LOGIN_REGISTER_PAGE).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(FIELD_EMAIL));
        typeEmail(email);
        typePassword(password);
        driver.findElement(BUTTON_LOGIN_LOGIN_PAGE).click();
    }

    public void loginWithButtonLoginOnForgotPasswordPage(String email, String password) {
        driver.findElement(BUTTON_LOGIN_FORGOT_PASSWORD_PAGE).click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(FIELD_EMAIL));
        typeEmail(email);
        typePassword(password);
        driver.findElement(BUTTON_LOGIN_LOGIN_PAGE).click();
    }
    public void clickButtonPersonalAccountOnMainPage() {
        driver.findElement(BUTTON_PERSONAL_ACCOUNT_MAIN_PAGE).click();
    }

    public void clickButtonLogoAndConstructorOnPersonalAccount() {
        driver.findElement(BUTTON_LOGO).click();
    }

    public void clickButtonLogoutOnPersonalAccount() {
        driver.findElement(BUTTON_LOGOUT).click();
    }

    public void clickBuns() {
        driver.findElement(BUNS).click();
    }
    public void clickSauces() {
        driver.findElement(SAUCES).click();
    }
    public void clickFillings() {
        driver.findElement(FILLINGS).click();
    }
}
