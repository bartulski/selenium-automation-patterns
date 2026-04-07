package bot.tests;

import bot.core.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutInformationTests extends TestBase {
    private final String sauceLabsBackpackAddSelector = "#add-to-cart-sauce-labs-backpack";
    private final String cartButtonSelector = "a.shopping_cart_link";
    private final String checkoutButtonSelector = "#checkout";
    private final String continueButtonSelector = "#continue";
    private final String firstNameTextFieldSelector = "#first-name";
    private final String errorMessageContainerSelector = ".error-message-container";
    private final String errorIconsSelector = ".checkout_info_wrapper svg.error_icon";
    private final String errorMessageTextSelector = ".error-message-container h3";
    private final String lastNameTextFieldSelector = "#last-name";


    private void openCheckoutInformationStep() {
        bot.validLogin();
        bot.waitForPresenceOfElementLocated(sauceLabsBackpackAddSelector);
        bot.click(sauceLabsBackpackAddSelector);
        bot.click(cartButtonSelector);
        bot.click(checkoutButtonSelector);
        bot.waitForPresenceOfElementLocated(continueButtonSelector);
    }

    @Test
    void shouldShowErrorWhenCheckoutFormIsSubmittedEmpty() {
        openCheckoutInformationStep();
        bot.click(continueButtonSelector);
        bot.waitForPresenceOfElementLocated(errorMessageContainerSelector);
        Assertions.assertTrue(bot.elementIsDisplayed(errorMessageContainerSelector)
                , "Error message container is not displayed when provided empty data");
    }

    @Test
    void shouldDisplayThreeXIconsWhenFormIsSubmittedEmpty() {
        openCheckoutInformationStep();
        bot.click(continueButtonSelector);
        bot.waitForPresenceOfElementLocated(errorMessageContainerSelector);

        int expectedDisplayedIconsNumber = 3;

        Assertions.assertEquals(expectedDisplayedIconsNumber, bot.getElements(errorIconsSelector).size()
                , "Number of icons is not correct, expected : "
                        + expectedDisplayedIconsNumber + "."
                        + "Displayed : "
                        + bot.getElements(errorIconsSelector).size() + ".");
    }

    @Test
    void shouldDisplayCorrectErrorWhenFirstNameIsEmpty() {
        openCheckoutInformationStep();
        bot.click(continueButtonSelector);
        bot.waitForPresenceOfElementLocated(errorMessageTextSelector);
        String expectedErrorText = "Error: First Name is required";

        Assertions.assertEquals(expectedErrorText,
                bot.getTextString(errorMessageTextSelector)
                ,"Error message displayed for empty First Name field is not correct");
    }

    @Test
    void shouldDisplayCorrectErrorWhenLastNameIsEmpty() {
        openCheckoutInformationStep();
        bot.sendKeys(firstNameTextFieldSelector, "First Name");
        bot.click(continueButtonSelector);
        bot.waitForPresenceOfElementLocated(errorMessageTextSelector);
        String expectedErrorText = "Error: Last Name is required";

        Assertions.assertEquals(expectedErrorText,
                bot.getTextString(errorMessageTextSelector)
                ,"Error message displayed for empty Last Name field is not correct");
    }

    @Test
    void shouldDisplayCorrectErrorWhenPostalCodeIsEmpty() {
        openCheckoutInformationStep();
        bot.sendKeys(firstNameTextFieldSelector, "First Name");
        bot.sendKeys(lastNameTextFieldSelector, "Last Name");
        bot.click(continueButtonSelector);
        bot.waitForPresenceOfElementLocated(errorMessageTextSelector);
        String expectedErrorText = "Error: Postal Code is required";

        Assertions.assertEquals(expectedErrorText,
                bot.getTextString(errorMessageTextSelector)
                ,"Error message displayed for empty Postal Code field is not correct");
    }
}
