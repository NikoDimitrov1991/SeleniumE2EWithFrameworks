package PageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement country;

    @FindBy(xpath = "//a[normalize-space()='Place Order']")
    WebElement submit;

    @FindBy(xpath = "(//span[@class='ng-star-inserted'])[1]")
    WebElement selectCountry;


    public void selectCountry(String countryName) {
        Actions a = new Actions(driver);
        a.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(By.cssSelector(".ta-results"));
        selectCountry.click();

    }


    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }


//    public void selectCountry(String country) {
//        WebElement countryInput = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
//        countryInput.click();
//        countryInput.sendKeys("Bulgaria");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//
//        driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[1]")).click();
//    }


}
