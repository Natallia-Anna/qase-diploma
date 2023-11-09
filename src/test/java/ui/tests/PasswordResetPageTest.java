package ui.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.model.User;
import ui.service.LoginPageService;

public class PasswordResetPageTest extends BaseTest {
    private static final String SUCCESSFUL_PASSWORD_RESET_VALIDATION_MESSAGE = "We have e-mailed your password " +
            "reset link!";
    private static final String USER_NOT_FOUND_VALIDATION_MESSAGE = "User not found.";
    private static final String INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE = "Value '%s' does not match format email " +
            "of type string";
    private LoginPageService loginPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
    }

    @Test(description = "Check successful password reset", priority = 1)
    @Description("Successful password reset")
    public void checkSuccessfulPasswordReset() {
        String actualValidationMessage = loginPageService
                .clickForgotPassword()
                .fillEmail(new User().getEmail())
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = SUCCESSFUL_PASSWORD_RESET_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Wrong validation " +
                "message");
    }

    @Test(description = "Check wrong reset password email format validation", priority = 2)
    @Description("Wrong reset password email format validation")
    public void checkWrongResetPasswordEmailFormatValidation() {
        String actualValidationMessage = loginPageService
                .clickForgotPassword()
                .fillEmail(".abc@mail.com")
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = String.format(INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE, ".abc@mail.com");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Wrong validation " +
                "message");
    }

    @Test(description = "Check wrong reset password email validation", priority = 3)
    @Description("Wrong reset password email validation")
    public void checkWrongResetPasswordEmailValidation() {
        User userWithWrongEmail = new User("testemail@gmail.com", "TestingPass1!");
        String actualValidationMessage = loginPageService
                .clickForgotPassword()
                .fillEmail(userWithWrongEmail.getEmail())
                .clickSendResetLinkButton()
                .getValidationMessage();
        Assert.assertEquals(actualValidationMessage, USER_NOT_FOUND_VALIDATION_MESSAGE, "Wrong validation " +
                "message");
    }

    @Test(description = "Check transfer to SSO Login page", priority = 4)
    @Description("Check transfer to 'SSO Login page'")
    public void checkNavigationToSsoLoginPage() {
        loginPageService.clickForgotPassword()
                .clickNavigateToSsoLoginPageButton();
        Assert.assertTrue(loginPageService.isSsoLoginPageOpened(), "No transfer to SSO Login page");
    }

    @Test(description = "Check navigation to 'Login page'", priority = 5)
    @Description("navigation to 'Login page'")
    public void checkNavigationToLoginPage() {
        loginPageService.clickForgotPassword()
                .clickNavigateToLoginPageButton();
        Assert.assertTrue(loginPageService.isLoginPageOpened(), "No transfer to Login page");
    }
}
