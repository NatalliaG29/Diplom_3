import Browsers.BrowserDriverSetting;
import PageObject.MainPage;
import PageObject.ProfilePage;
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
public class ButtonPersonalAccountTest {
    WebDriver driver;
    MainPage mainPage;
    ProfilePage profilePage;
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
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    public void clickButtonPersonalAccountOnMainPageSuccess() {
        profilePage = new ProfilePage(driver);
        mainPage.clickButtonPersonalAccountOnMainPage();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profilePage.BUTTON_SAVE));
        Assert.assertThat(driver.findElement(profilePage.BUTTON_SAVE).getText(), containsString("Сохранить"));
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
