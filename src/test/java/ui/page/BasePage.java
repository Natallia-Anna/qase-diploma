package ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ui.driver.DriverSingleton;

public abstract class BasePage {
    protected WebDriver driver = DriverSingleton.getInstance().getDriver();

    protected BasePage() {
        PageFactory.initElements(driver, this);
    }
}
