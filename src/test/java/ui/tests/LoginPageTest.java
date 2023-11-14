package ui.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.model.User;
import ui.service.LoginPageService;
import ui.service.ProjectsListPageService;

public class LoginPageTest extends BaseTest {
    private static final String INVALID_CREDENTIALS_VALIDATION_MESSAGE = "These credentials do not match our records.";
    private static final String INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE = "Value '%s' does not match format email " +
            "of type string";
    private LoginPageService loginPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
    }

    @Test(description = "Check successful login with valid credentials")
    @Description("Login with valid credentials")
    public void checkSuccessfulLoginWithValidCredentials() {
        ProjectsListPageService projectsListPageService = loginPageService.login(new User());
        Assert.assertTrue(projectsListPageService.isProjectsPageDisplayed(), "Login failed");
    }

    @Test(description = "Check wrong email validation")
    @Description("Wrong email validation")
    public void checkWrongEmailValidation() {
        User userWithWrongEmail = new User("testEmail@mail.ru", "PasswordTesting9*");
        loginPageService.login(userWithWrongEmail);
        String actualValidationMessage = loginPageService.getLoginValidationMessage();
        String expectedValidationMessage = INVALID_CREDENTIALS_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check empty email validation")
    @Description("Empty email validation")
    public void checkEmptyEmailValidation() {
        User userWithEmptyEmail = new User("", "PasswordTesting9*");
        loginPageService.login(userWithEmptyEmail);
        Assert.assertTrue(loginPageService.isEmptyEmailValidationMessageDisplayed(), "Empty email " +
                "validation message hasn't been shown");
    }

    @Test(description = "Check empty password validation")
    @Description("Empty password validation")
    public void checkEmptyPasswordValidation() {
        User userWithEmptyPassword = new User("nufolyge@yogrow.co", "");
        loginPageService.login(userWithEmptyPassword);
        Assert.assertTrue(loginPageService.isEmptyPasswordValidationMessageDisplayed(), "Empty password " +
                "validation message hasn't been shown");
    }

    @Test(description = "Check wrong email format validation", dataProvider = "Get wrong format emails list")
    @Description("Wrong email format validation")
    public void checkWrongEmailFormatValidation(String email) {
        User userWithWrongFormatEmail = new User(email);
        loginPageService.login(userWithWrongFormatEmail);
        String actualValidationMessage = loginPageService.getWrongEmailFormatValidationMessage();
        String expectedValidationMessage = String.format(INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE,
                userWithWrongFormatEmail.getEmail());
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @DataProvider(name = "Get wrong format emails list")
    private static Object[][] getWrongFormatEmailsList() {
        return new Object[][]{
                {"abc.def@mail#archive.com"},
                {"abc..def@mail.com"},
                {".abc@mail.com"},
                {"abc.def@mail"},
                {"abc.def@mail..com"},
                {"email.domain.com"},
                {"email@domain@domain.com"},
                {"email.@domain.com"},
                {"@domain.com"},
        };
    }
}
