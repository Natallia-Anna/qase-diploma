package ui.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.driver.DriverSingleton;
import utils.Waiter;

@Log4j2
public class LoginPage extends BasePage {
    private static final String LOGIN_PAGE_URL = "https://app.qase.io/login";
    private static final String EMPTY_FIELD_VALIDATION_MESSAGE = "//input[@name='%s']//ancestor::div[@class='tdishH']" +
            "//small";
    private static final String ADDITIONAL_LINK = "//a[contains(text(),'%s')]";

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='ic9QAx']")
    private WebElement wrongEmailFormatValidationMessage;

    @FindBy(xpath = "//span[@class='ic9QAx']")
    private WebElement loginValidationMessage;

    @FindBy(xpath = "//h1[contains(text(),'SSO Login')]")
    private WebElement loginSsoPageHeading;

    @FindBy(xpath = "//a[contains(text(),'Forgot password?')]")
    private WebElement forgotPasswordButton;

    public LoginPage fillEmail(String userName) {
        log.info("Fill 'Email' field");
        Waiter.waitElementToBeDisplayed(emailField).sendKeys(userName);
        return this;
    }

    public LoginPage fillPassword(String password) {
        log.info("Fill 'Password' field");
        passwordField.sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        log.info("Click 'Login' button");
        loginButton.click();
    }

    public String getLoginValidationMessage() {
        return Waiter.waitElementToBeDisplayed(loginValidationMessage).getText();
    }

    public boolean isEmptyFieldValidationMessageDisplayed(String type) {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(EMPTY_FIELD_VALIDATION_MESSAGE, type)))
                .isDisplayed();
    }

    public String getWrongEmailFormatValidationMessage() {
        return Waiter.waitElementToBeDisplayed(wrongEmailFormatValidationMessage).getText();
    }

    public LoginPage openLoginPage() {
        log.info("Open 'Login' page");
        DriverSingleton.getInstance().getDriver().get(LOGIN_PAGE_URL);
        return this;
    }

    public void clickForgotPasswordButton() {
        log.info("Click 'Forgot password' button");
        Waiter.waitElementToBeDisplayed(forgotPasswordButton).click();
    }

    public boolean isLoginPageOpened() {
        return Waiter.waitElementToBeDisplayed(loginButton).isDisplayed();
    }

    public boolean isSsoLoginPageOpened() {
        return Waiter.waitElementToBeDisplayed(loginSsoPageHeading).isDisplayed();
    }
}
