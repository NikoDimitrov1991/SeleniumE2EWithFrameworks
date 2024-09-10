import PageObjects.*;
import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class TestUsingPageObject extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"}, retryAnalyzer = Retry.class)
    public void submitOrder(HashMap<String,String> input) throws InterruptedException {


        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        cartPage.verifyProductDisplay(input.get("product"));

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
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
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "java" + File.separator + "Data" + File.separator + "PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }


//    @DataProvider
//    public Object[][] getData() {
//        return new Object[][]{{"ndnikolaydimitrov@gmail.com", "Test123!", "ZARA COAT 3"}, {"nikolay.dimitrov@delasport.com", "Test1233", "ADIDAS ORIGINAL"}};
//    }


//    @DataProvider
//    public Object[][] getData() throws IOException {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("email", "ndnikolaydimitrov@gmail.com");
//        map.put("password", "Test123!");
//        map.put("product", "ZARA COAT 3");
//
//        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("email", "nikolay.dimitrov@delasport.com");
//        map1.put("password", "Test1233");
//        map1.put("product", "ADIDAS ORIGINAL");
//
//        List<HashMap<String,String>> data = getJsonDataToMap("System.getProperty(\"user.dir\") + \"src/test/java/Data/PurchaseOrder.json\"");
//        return new Object[][]{{map}, {map1}};
//    }
}

