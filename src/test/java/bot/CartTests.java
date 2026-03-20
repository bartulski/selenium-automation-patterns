package bot;

import core.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CartTests extends TestBase {
    private final String sauceLabsBackpackAddSelector = "#add-to-cart-sauce-labs-backpack";
    private final String sauceLabsBackpackRemoveSelector = "#remove-sauce-labs-backpack";
    private final String removeTextString = "Remove";
    private final String listAdddToCartButtonsLandingPageSelector = "div>button[id*='add-to-cart']";
    private final int amountOfRemoveButtons = 6;
    private final String listRemoveFromCartButtonsLandingPAgeSelector = "div>button[id*='remove']";


    @Test
    @DisplayName("Verifying if 'Remove' button updates")
    public void adding_to_card_should_update_button_name() {

        bot.waitForPresenceOfElementLocated(sauceLabsBackpackAddSelector);
        bot.click(sauceLabsBackpackAddSelector);
        bot.waitForPresenceOfElementLocated(sauceLabsBackpackRemoveSelector);

        Assertions.assertEquals(removeTextString,
                bot.getTextString(sauceLabsBackpackRemoveSelector),
                "button state does not change");
    }

    @Test
    @DisplayName("Verification if user can add all six products to the cart")
    public void verify_if_user_can_add_all_six_products_to_cart() {
        int numberOfClicks = bot.loopClicker(bot.getElements(listAdddToCartButtonsLandingPageSelector));

        Assertions.assertEquals(numberOfClicks, amountOfRemoveButtons
                , "difference between clicked buttons");

    }
}

/*
Koszyk
1.
Dodanie 1 produktu → badge koszyka = 1.
2.
Dodanie wielu produktów → badge koszyka rośnie zgodnie z liczbą.
3.
Usunięcie produktu z listy produktów → badge maleje.
4.
Usunięcie produktu z koszyka → badge maleje.
5.
Przejście do koszyka i powrót do listy produktów → stan koszyka zachowany.
6.
Dodanie + usunięcie tego samego produktu → wraca do stanu początkowego.
7.
Po odświeżeniu strony koszyk zachowuje stan (jeśli aplikacja tak działa).
 */