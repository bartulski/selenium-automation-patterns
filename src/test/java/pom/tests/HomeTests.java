package pom.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pom.core.BaseTest;
import pom.pages.HomePage;

import java.math.BigDecimal;

public class HomeTests extends BaseTest {

    @Test
    void shouldShowHomePageContent() {
        HomePage homePage = new HomePage(driver);
        homePage.goToHomePage();

        String expectedPageTitle = "FakeStore – Sklep do nauki testowania";
        String expectedPageURL = configuration.getBaseURL().replaceAll("/+$", "");
        String actualURL = homePage.readCurrentUrl().replaceAll("/+$", "");

        Assertions.assertAll(
                () -> Assertions.assertEquals(
                        expectedPageURL
                        , actualURL
                        , "Page address is not correct"
                ),
                () -> Assertions.assertEquals(
                        expectedPageTitle
                        , homePage.readPageTitle()
                        , "Page title is incorrect"
                ),
                () -> Assertions.assertTrue(
                        (homePage.hasProductsCategories()),
                        "Categories with products are not displayed")
        );
    }

    @Test
    void shouldUpdateCartTotalAfterAddingOneProduct() {
        HomePage homePage = new HomePage(driver);

        homePage.goToHomePage().addWindsurfingProductToCart();

        BigDecimal expectedCartValue = homePage.readWindsurfingProductPrice();

        Assertions.assertEquals(
                0, expectedCartValue
                        .compareTo(homePage.readTotalCartAmount()),
                "Total cart value is not correct"
        );
    }
}
