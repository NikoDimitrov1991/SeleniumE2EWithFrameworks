import PageObjects.*;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TestUsingPageObject extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test
    public void submitOrder() throws InterruptedException {


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
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalogue productCatalogue = landingPage.loginApplication("ndnikolaydimitrov@gmail.com", "Test123!");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
       Assert.assertTrue( orderPage.VerifyOrderDisplay(productName));
    }
}

