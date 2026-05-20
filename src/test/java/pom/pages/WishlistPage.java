package pom.pages;

import pom.core.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class WishlistPage extends BasePage {
    private final By wishlistProductList = By.cssSelector(".wishlist-items-wrapper tr");

    protected WishlistPage(WebDriver driver) {
        super(driver);
    }

    private int getListSize(By locator) {
        waitForVisibility(locator);
        return driver.findElements(locator).size();
    }

    public int getNumberOfProducts() {
        return getListSize(wishlistProductList);
    }
}
