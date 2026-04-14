package pom.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pom.helpers.WaitUtils;
import org.openqa.selenium.WebDriver;


public class StripeWidget {
    protected final WebDriver driver;
    private final WaitUtils waitUtils;
    private final By stripeDevToolsMainIframe = By.cssSelector("iframe[name^='__privateStripeFrame'][title='Ramka narzędzi programisty Stripe']");
    private final By stripeDevToolsButton = By.cssSelector("button[aria-label='Otwórz narzędzia dewelopera Stripe']");
    private final By stripeDevToolsInnerIframe = By.cssSelector("iframe[name^='__privateStripeFrame'][title='Ramka narzędzi programisty Stripe']");
    private final By stripeDevToolsSettingsButton = By.cssSelector("button[aria-controls='tabpanel-settings']");
    private final By stripeDevToolsTopLeftCornerButton = By.cssSelector("#tabpanel-settings [role='radiogroup']>:nth-child(1)");
    private final By stripeDevToolsHideButton = By.cssSelector("button[aria-label='Zamknij narzędzia dewelopera Stripe']");

    public StripeWidget(WebDriver driver, int waitInSeconds) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, waitInSeconds);
    }

    public void closeStripeWidget() {
        WebElement mainIframe = waitUtils.waitForVisibility(stripeDevToolsMainIframe);
        try {
            driver.switchTo().frame(mainIframe);
            waitUtils.waitForVisibility(stripeDevToolsButton).click();

            waitUtils.waitToDisappear(By.cssSelector(".is-entering"));

            waitUtils.waitTobeClickable(stripeDevToolsSettingsButton).click();

            waitUtils.waitTobeClickable(stripeDevToolsTopLeftCornerButton).click();

            waitUtils.waitForVisibility(By.cssSelector("[class*='--topLeft']"));

            waitUtils.waitTobeClickable(stripeDevToolsHideButton).click();

            waitUtils.waitToDisappear(By.cssSelector(".is-entering"));
        } finally {
            driver.switchTo().defaultContent();
        }
    }

}
