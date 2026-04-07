package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pom.core.BasePage;

import java.math.BigDecimal;

public class CartPage extends BasePage  {

    By totalCartAmount = By.cssSelector("td[data-title='Suma'] strong");
    By quantityInputField = By.cssSelector(".quantity .input-text");
    By updateCartButton = By.cssSelector(".actions button[name='update_cart']");
    By blockUIwrapper = By.cssSelector(".blockUI");
    By couponInputField = By.cssSelector("#coupon_code");
    By couponApplyButton = By.cssSelector(".coupon button");


    public CartPage(WebDriver driver) {
        super(driver);
    }

    public BigDecimal readTotalCartAmount() {
        waitForElementVisibility(totalCartAmount);
        return convertStringToBigDecimal(totalCartAmount);
    }


    public CartPage setQuantity(int quantity) {
        waitForElementVisibility(quantityInputField);
        clearInputField(quantityInputField);
        sendKeys(quantityInputField, String.valueOf(quantity));
        return this;
    }

    public CartPage updateCart() {
        clickElement(updateCartButton);
        waitForElementToDisappear(blockUIwrapper);
        return this;
    }

    public CartPage applyCoupon(String couponCode) {
        waitForElementVisibility(couponInputField);
        sendKeys(couponInputField, couponCode);
        clickElement(couponApplyButton);
        waitForElementToDisappear(blockUIwrapper);
        return this;
    }
}
