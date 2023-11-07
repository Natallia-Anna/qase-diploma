package ui.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class ProjectsListPage extends BasePage {

    @FindBy(xpath = "//button[@id='createButton']")
    private WebElement createNewProjectButton;

    public boolean isProjectsPageDisplayed() {
        return Waiter.waitElementToBeDisplayed(createNewProjectButton).isDisplayed();
    }
}
