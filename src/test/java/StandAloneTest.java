import PageObjects.LandingPage;
import PageObjects.ProductCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;


public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");

        String productName = "ZARA COAT 3";
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        landingPage.loginApplication("ndnikolaydimitrov@gmail.com", "Test123!");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);




        driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        org.testng.Assert.assertTrue(match);
        driver.findElement(By.cssSelector(".totalRow button")).click();


        WebElement countryInput = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
        countryInput.click();
        countryInput.sendKeys("Bulgaria");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[1]")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();

        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        org.testng.Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }
}
