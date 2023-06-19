import Browsers.BrowserDriverSetting;
import PageObject.MainPage;
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
public class ButtonLogoutTest {

    WebDriver driver;
    MainPage mainPage;
    RegisterPage registerPage;
    private User user = UserGenerator.getRandomUser();
    private String name = UserGenerator.getRandomUser().getName();
    private String email = UserGenerator.getRandomUser().getEmail();
    private String password = UserGenerator.getRandomUser().getPassword();
    UserClient userClient;
    private String authToken;

    @Before
    public void setUp() {
        driver = BrowserDriverSetting.browserDriverSetUp();
        registerPage = new RegisterPage(driver);
        driver.get(registerPage.getURL());
        registerPage.registerUser(name, email, password);
        mainPage = new MainPage(driver);
        driver.get(mainPage.getUrl());
        mainPage.loginWithButtonLoginOnMainPage(email, password);
        mainPage.clickButtonPersonalAccountOnMainPage();
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
        authToken = userClient.create(user).extract().path("accessToken").toString();
    }

    @Test
    @DisplayName("Проверка выхода по кнопке «Выйти» в личном кабинете")
    public void logoutButtonClickFromPrivateCabinetSuccess() {
        mainPage.clickButtonLogoutOnPersonalAccount();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registerPage.LOGIN_BUTTON));
        Assert.assertThat(driver.findElement(registerPage.LOGIN_BUTTON).getText(), containsString("Войти"));
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
