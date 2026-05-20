package pom.pages;

import pom.core.BasePage;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;

public class CartPage extends BasePage  {
    private final By totalCartValue = By.cssSelector("td[data-title='Suma'] strong");
    private final By quantityInputField = By.cssSelector("[name*='[qty]']");
    private final By updateCartButton = By.cssSelector(".actions button[name='update_cart']");
    private final By blockingOverlay = By.cssSelector(".blockUI");
    private final By couponInputField = By.cssSelector("#coupon_code");
    private final By couponApplyButton = By.cssSelector("[name='apply_coupon']");
    private final By removeProductButton = By.cssSelector(".product-remove a[role='button']");
    private final By productsInCartList = By.cssSelector(".cart_item");
    private final By emptyCartNotification = By.cssSelector(".woocommerce-notices-wrapper .cart-empty");
    private final By couponErrorText = By.cssSelector(".coupon-error-notice");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Read total cart amount")
    public BigDecimal readTotalCartAmount() {
        waitForVisibility(totalCartValue);
        return convertStringToBigDecimal(totalCartValue);
    }
    @Step("Set quantity to {quantity}")
    public CartPage setQuantity(int quantity) {
        clearInputField(quantityInputField);
        sendKeys(quantityInputField, String.valueOf(quantity));
        return this;
    }
    @Step("Update cart")
    public CartPage updateCart() {
        clickElement(updateCartButton);
        waitForDisappear(blockingOverlay);
        return this;
    }
    @Step("Apply coupon {couponCode}")
    public CartPage applyCoupon(String couponCode) {
        sendKeys(couponInputField, couponCode);
        clickElement(couponApplyButton);
        waitForDisappear(blockingOverlay);
        return this;
    }
    @Step("Remove product from cart")
    public CartPage removeProductFromCart() {
        clickElement(removeProductButton);
        waitForDisappear(blockingOverlay);
        return this;
    }

    public String readCouponErrorMessage() {
        return waitForVisibility(couponErrorText).getText();
    }

    public boolean isEmptyCartMessageDisplayed() {
        return waitForVisibility(emptyCartNotification).isDisplayed();
    }

    public boolean isCartEmpty() {
        return driver.findElements(productsInCartList).isEmpty();
    }
}
