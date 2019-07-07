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

public class TutBySections {
    private WebDriver driver;
    private static final String TUT_BY_URL = "https://www.tut.by/";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TUT_BY_URL);
    }

    @Test
    public void testFortyTwoTutByMainPage() {
        WebElement sectionsPopUp = driver.findElement(By.xpath("//div[@class='topbarmore-c']"));
        Assert.assertFalse(sectionsPopUp.isDisplayed(), "Sections popup is displayed on main page");

        WebElement sectionsButton = driver.findElement(By.xpath("//a[@class='topbar-burger']"));
        sectionsButton.click();
        new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(300))
                    .withMessage("Sections popup was not found")
                    .until(driver1 -> driver1.findElement(By.xpath("//div[@class='topbarmore-c']")).isDisplayed());
        driver.findElement(By.xpath("//div[contains(@id, 'mainmenu')]//a[contains(@title, '42')]")).click();
        WebElement fortyTwoMainPage = driver.findElement(By.xpath("//div[contains(@class, 'b-irr')]//a[contains(@class, 'button')]"));
        Assert.assertTrue(fortyTwoMainPage.isDisplayed(), "42 main page is not displayed");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
