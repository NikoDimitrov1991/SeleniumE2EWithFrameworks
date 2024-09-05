import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;



public class TestUsingPageObject extends BaseTest {
    @Test
    public void submitOrder() throws IOException, InterruptedException {


        String productName = "ZARA COAT 3";
        LandingPage landingPage = launchApplication();
        ProductCatalogue productCatalogue = landingPage.loginApplication("ndnikolaydimitrov@gmail.com", "Test123!");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.verifyProductDisplay(productName);

        Boolean match = cartPage.verifyProductDisplay(productName);
        org.testng.Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Bulgaria");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMassage = confirmationPage.verifyConfirmationMessage();
        org.testng.Assert.assertTrue(confirmMassage.equalsIgnoreCase("THANKYOU FOR THE ORDER."), "The confirmation message does not match the expected value.");
        driver.quit();
    }
}

