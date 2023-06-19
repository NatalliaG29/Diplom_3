import Browsers.BrowserDriverSetting;
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

import static org.hamcrest.CoreMatchers.containsString;

public class RegisterTest {
    private WebDriver driver;
    UserClient userClient;
    private User user;
    RegisterPage registerPage;
    private String name = UserGenerator.getRandomUser().getName();
    private String email = UserGenerator.getRandomUser().getEmail();
    private String password = UserGenerator.getRandomUser().getPassword();
    private String authToken;

    @Before
    public void setUp() {
        driver = BrowserDriverSetting.browserDriverSetUp();
        registerPage = new RegisterPage(driver);
        driver.get(registerPage.getURL());
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
        authToken = userClient.create(user).extract().path("accessToken").toString();
    }
    @Test
    @DisplayName("Регистрация пользователя с корректными данными")
    public void registerUserWithCorrectDateSuccess() {
        registerPage.registerUser(name, email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registerPage.LOGIN_BUTTON));
        Assert.assertThat("Должна была открыться страница Вход",
                driver.findElement(registerPage.LOGIN_BUTTON).getText(), containsString("Войти"));
    }

    @Test
    @DisplayName("Попытка регистрации пользователя с некорректным паролем")
    public void checkRegisterUserWithIncorrectPasswordError() {
        registerPage.registerUser(name, email,"1234");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registerPage.INCORRECT_PASSWORD));
        Assert.assertThat("Должна была открыться страница Вход",
                driver.findElement(registerPage.INCORRECT_PASSWORD).getText(), containsString("Некорректный пароль"));
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
