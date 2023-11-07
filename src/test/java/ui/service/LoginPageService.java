package ui.service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.model.User;
import ui.page.LoginPage;

@Log4j2
public class LoginPageService {
    private LoginPage loginPage;

    @Step("log in to the system")
    public ProjectsListPageService login(User user) {
        log.info("Log in to the system");
        loginPage = new LoginPage();
        loginPage.openLoginPage()
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return new ProjectsListPageService();
    }

    @Step("Getting login validation message")
    public String getLoginValidationMessage() {
        loginPage = new LoginPage();
        return loginPage.getLoginValidationMessage();
    }

    @Step("Verifying if empty email validation message displayed")
    public boolean isEmptyEmailValidationMessageDisplayed() {
        loginPage = new LoginPage();
        return loginPage.isEmptyFieldValidationMessageDisplayed("email");
    }

    @Step("Verifying if empty password validation message displayed")
    public boolean isEmptyPasswordValidationMessageDisplayed() {
        loginPage = new LoginPage();
        return loginPage.isEmptyFieldValidationMessageDisplayed("password");
    }

    @Step("Getting wrong email format validation message")
    public String getWrongEmailFormatValidationMessage() {
        loginPage = new LoginPage();
        return loginPage.getWrongEmailFormatValidationMessage();
    }
}
