import PageObjects.ProductCatalogue;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest {

@Test
public void submitOrder(){

    ProductCatalogue productCatalogue = landingPage.loginApplication("ndnikolaydimitrov@gmail.com", "Test2!");
    Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
}
}
