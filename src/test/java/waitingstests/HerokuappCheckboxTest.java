package waitingstests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HerokuappCheckboxTest {
    private WebDriver driver;
    private static final String HEROKUAPP_URL = "http://the-internet.herokuapp.com/dynamic_controls";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HEROKUAPP_URL);
    }

    @Test
    public void testAddRemoveCheckbox() throws InterruptedException {
        driver.findElement(By.xpath("//form[contains(@id, 'checkbox')]//button")).click();

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .withMessage("Checkbox is still displayed on page")
                .until(driver1 -> driver1.findElements(By.xpath("//form[contains(@id, 'checkbox')]//div[@id = 'checkbox']")).isEmpty());

        driver.findElement(By.xpath("//form[contains(@id, 'checkbox')]//button")).click();

        Thread.sleep(1000);

        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .withMessage("Checkbox is not displayed on page")
                .until(driver1 -> !driver1.findElements(By.xpath("//input[@id = 'checkbox']")).isEmpty());
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
