package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.util.List;

public class SearchStep {

    WebDriver driver;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.setExperimentalOption("excludeSwitches", java.util.Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
        driver = new ChromeDriver(options);
    }

    @Given("Booking search page is opened")
    public void bookingSearchPageIsOpened() {
        driver.get("https://www.booking.com/searchresults.en-gb.html");
        throw new PendingException();
    }

    @When("user searches for {string}")
    public void userSearchesFor(String hotel) {
        driver.findElement(By.xpath("//input[@name='ss']")).sendKeys(hotel);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        throw new PendingException();
    }

    @Then("{string} hotel is shown")
    public void hotelIsShown(String expectedResult) {
        List<WebElement> titles = driver.findElements(By.xpath("//div[@data-testid='title']"));
        boolean isHotelFound = false;
        for (WebElement title: titles)  {
            if (title.getText().equals(expectedResult)) {
                isHotelFound = true;
                break;
            }
        }
        Assert.assertTrue(isHotelFound);
    }

    @After
    public  void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}