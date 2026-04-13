package pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.core.BasePage;

import java.math.BigDecimal;

public class CartPage extends BasePage  {

    By totalCartValue = By.cssSelector("td[data-title='Suma'] strong");
    By quantityInputField = By.cssSelector(".quantity .input-text");
    By updateCartButton = By.cssSelector(".actions button[name='update_cart']");
    By blockUWrapper = By.cssSelector(".blockUI");
    By couponInputField = By.cssSelector("#coupon_code");
    By couponApplyButton = By.cssSelector(".coupon button");
    By removeProductButton = By.cssSelector(".product-remove a[role='button']");
    By productInCartList = By.cssSelector(".cart_item");
    By emptyCartNotification = By.cssSelector(".woocommerce-notices-wrapper .cart-empty");
    By couponErrorText = By.cssSelector(".coupon-error-notice");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmptyCartMessageDisplayed() {
        return waitForVisibility(emptyCartNotification).isDisplayed();
    }

    public boolean isCartEmpty() {
        return driver.findElements(productInCartList).isEmpty();
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
        waitForDisappear(blockUWrapper);
        return this;
    }
    @Step("Apply coupon {couponCode}")
    public CartPage applyCoupon(String couponCode) {
        sendKeys(couponInputField, couponCode);
        clickElement(couponApplyButton);
        waitForDisappear(blockUWrapper);
        return this;
    }
    @Step("Remove product from cart")
    public CartPage removeProductFromCart() {
        clickElement(removeProductButton);
        waitForDisappear(blockUWrapper);
        return this;
    }

    public String readCouponErrorMessage() {
        return waitForVisibility(couponErrorText).getText();
    }
}
