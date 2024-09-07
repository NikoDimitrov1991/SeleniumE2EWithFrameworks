import PageObjects.CartPage;
import PageObjects.CheckoutPage;
import PageObjects.ConfirmationPage;
import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {

    @Test
    public void LoginErrorValidation() {

        ProductCatalogue productCatalogue = landingPage.loginApplication("ndnikolaydimitrov@gmail.com", "Test2!");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void ProductErrorValidation() throws InterruptedException {


        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("ndnikolaydimitrov@gmail.com", "Test123!");

        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.verifyProductDisplay(productName);

        Boolean match = cartPage.verifyProductDisplay(productName);
        org.testng.Assert.assertTrue(match);


    }
}
