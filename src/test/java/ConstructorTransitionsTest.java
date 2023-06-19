import Browsers.BrowserDriverSetting;
import PageObject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
public class ConstructorTransitionsTest {

    WebDriver driver;
    MainPage mainPage;
    @Before
    public void setUp() {
        driver = BrowserDriverSetting.browserDriverSetUp();
        mainPage = new MainPage(driver);
        driver.get(mainPage.getUrl());
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Булки»")
    public void clickOnBunsSuccess() {
        mainPage.clickSauces();
        mainPage.clickBuns();
        Assert.assertTrue(driver.findElement(mainPage.FILLINGS_NO_SELECTED).isDisplayed());
        Assert.assertTrue(driver.findElement(mainPage.SAUCES_NO_SELECTED).isDisplayed());
    }
    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void clickOnSaucesSuccess() {
        mainPage.clickSauces();
        Assert.assertTrue(driver.findElement(mainPage.BUNS_NO_SELECTED).isDisplayed());
        Assert.assertTrue(driver.findElement(mainPage.FILLINGS_NO_SELECTED).isDisplayed());
    }
    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    public void clickOnFillingSuccess() {
        mainPage.clickFillings();
        Assert.assertTrue(driver.findElement(mainPage.BUNS_NO_SELECTED).isDisplayed());
        Assert.assertTrue(driver.findElement(mainPage.SAUCES_NO_SELECTED).isDisplayed());
    }

    @After
    @DisplayName("Закрытие браузера")
    public void tearDown() {
        driver.quit();
    }
}
