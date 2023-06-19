import Browsers.BrowserDriverSetting;
import PageObject.MainPage;
import PageObject.ForgotPasswordPage;
import PageObject.RegisterPage;
import User.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.Matchers.containsString;
public class LoginTest {

    WebDriver driver;
    MainPage mainPage;
    ForgotPasswordPage forgotPasswordPage;
    UserClient userClient;
    private User user = UserGenerator.getRandomUser();
    private String name = UserGenerator.getRandomUser().getName();
    private String email = UserGenerator.getRandomUser().getEmail();
    private String password = UserGenerator.getRandomUser().getPassword();
    private String authToken;
    RegisterPage registerPage;

    @Before
    public void setUp() {
        driver = BrowserDriverSetting.browserDriverSetUp();
        registerPage = new RegisterPage(driver);
        driver.get(registerPage.getURL());
        registerPage.registerUser(name, email, password);
        mainPage = new MainPage(driver);
        driver.get(mainPage.getUrl());
        forgotPasswordPage = new ForgotPasswordPage(driver);
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
        authToken = userClient.create(user).extract().path("accessToken").toString();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginWithButtonLoginOnMainPageSuccess() {
        driver.get(mainPage.getUrl());
        mainPage.loginWithButtonLoginOnMainPage(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.BUTTON_CREATE_ORDER));
        Assert.assertThat(driver.findElement(mainPage.BUTTON_CREATE_ORDER).getText(), containsString("Оформить заказ"));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginWithButtonPersonalAccountOnMainPageSuccess() {
        driver.get(mainPage.getUrl());
        mainPage.loginWithButtonPersonalAccountOnMainPage(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.BUTTON_CREATE_ORDER));
        Assert.assertThat(driver.findElement(mainPage.BUTTON_CREATE_ORDER).getText(), containsString("Оформить заказ"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginWithButtonLoginOnRegisterPageSuccess() {
        driver.get(registerPage.getURL());
        mainPage.loginWithButtonLoginOnRegisterPage(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.BUTTON_CREATE_ORDER));
        Assert.assertThat(driver.findElement(mainPage.BUTTON_CREATE_ORDER).getText(), containsString("Оформить заказ"));
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginWithButtonLoginOnForgotPasswordPageSuccess() {
        driver.get(forgotPasswordPage.getUrl());
        mainPage.loginWithButtonLoginOnForgotPasswordPage(email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(mainPage.BUTTON_CREATE_ORDER));
        Assert.assertThat(driver.findElement(mainPage.BUTTON_CREATE_ORDER).getText(), containsString("Оформить заказ"));
    }

    @After
    @DisplayName("Удаление пользователя")
    public void deleteUser() {
        userClient.deleteUser(authToken);
    }

    @After
    @DisplayName("Закрытие браузера")
    public void tearDown() {
        driver.quit();
    }
}
