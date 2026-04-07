package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.core.BasePage;

import java.math.BigDecimal;

public class HomePage extends BasePage {
    By storeNoticeDismissButton = By.cssSelector(".woocommerce-store-notice__dismiss-link");
    By storeNoticePanelDismissed = By.cssSelector(".woocommerce-store-notice [style='display:none;']");
    By productCategories = By.cssSelector(".storefront-product-section");
    By windsurfingProductPageButton = By.cssSelector(".storefront-recent-products .post-4116");
    By addWindsurfingButton = By.cssSelector(".storefront-recent-products [data-product_id='4116']"); // poprawic
    By windsurfingActualPrice = By.cssSelector(".storefront-recent-products .post-4116 .amount");
    By addWindsurfingLoadingIcon = By.cssSelector(".storefront-recent-products .loading[data-product_id='4116']");
    By totalAmountTextField = By.cssSelector(".cart-contents .amount");
    By cartButton = By.cssSelector(".site-header-cart");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage goToHomePage() {
        driver.get(baseURL);
        waitForElementTobeClickable(storeNoticeDismissButton);
        clickElement(storeNoticeDismissButton);
        waitForElementToDisappear(storeNoticePanelDismissed);
        return this;
    }

    public HomePage addWindsurfingProductToCart() {
        waitForElementTobeClickable(addWindsurfingButton);
        clickElement(addWindsurfingButton);
        waitForElementToDisappear(addWindsurfingLoadingIcon);
        return this;
    }

    public BigDecimal readWindsurfingProductPrice() {
        waitForElementVisibility(windsurfingActualPrice);
        return convertStringToBigDecimal(windsurfingActualPrice);
    }

    public BigDecimal readTotalCartAmount() {
        waitForElementVisibility(totalAmountTextField);
        return convertStringToBigDecimal(totalAmountTextField);
    }

    public String readCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String readPageTitle() {
        return driver.getTitle();
    }

    public boolean hasProductsCategories() {
        waitForElementVisibility(productCategories);
        return !driver.findElements(productCategories).isEmpty();
    }

    public ProductPage goToWindsurfingPage() {
        waitForElementTobeClickable(windsurfingProductPageButton);
        clickElement(windsurfingProductPageButton);
        return new ProductPage(driver);
    }

    public CartPage goToCart() {
        clickElement(cartButton);
        return new CartPage(driver);
    }
}
