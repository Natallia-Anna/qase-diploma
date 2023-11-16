package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.driver.DriverSingleton;

import java.time.Duration;

public class Waiter {
    private final static int WAIT_30_SECONDS = 30;

    private Waiter() {
    }

    public static WebElement waitElementToBeDisplayedByLocator(By locator) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofMinutes(WAIT_30_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitElementToBeDisplayed(WebElement element) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_30_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }
}

