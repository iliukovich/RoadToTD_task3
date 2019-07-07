package waitingstests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HerokuappHelloWorldText {
    private WebDriver driver;
    private static final String HEROKUAPP_URL = "http://the-internet.herokuapp.com/dynamic_loading/1";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HEROKUAPP_URL);
    }

    @Test
    public void testHelloWorldText() {
        WebElement helloWorldElement = driver.findElement(By.xpath("//div[@id='finish']//h4"));
        Assert.assertFalse(helloWorldElement.isDisplayed(), "Hello world is displayed on page");

        driver.findElement(By.xpath("//div[@id='start']//button")).click();

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .withMessage("'Hello World!' text is not displayed")
                .until(driver1 -> driver1.findElement(By.xpath("//div[@id='finish']//h4")).isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
