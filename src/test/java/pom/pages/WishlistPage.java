package pom.pages;

import pom.core.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class WishlistPage extends BasePage {
    By wishlistProductList = By.cssSelector(".wishlist-items-wrapper tr");

    protected WishlistPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        return getListSize(wishlistProductList);
    }
}
