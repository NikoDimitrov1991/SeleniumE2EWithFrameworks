import PageObjects.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;


public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client/");

        LandingPage landingPage = new LandingPage(driver);


        String productName = "ZARA COAT 3";
        driver.findElement(By.id("userEmail")).sendKeys("ndnikolaydimitrov@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test123!");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement prod = products.stream().filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

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
