package automation.switchframe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;


public class AutomationTest {
    WebDriver driver;

    @Test
    public void yopmailIFrame() {

        //open browser
        WebDriverManager.chromedriver();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://yopmail.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Enter inbox
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        driver.findElement(By.id("login")).sendKeys("automationtest");
        driver.findElement(By.xpath("//button[@class='md']")).click();

        //extract text
        driver.switchTo().frame("ifmail");

        // show body email content
        String emailContentText = driver.findElement(By.tagName("body")).getText();
        System.out.println(emailContentText);

        // Assert one of the email content text
        String txtActualContent = driver.findElement(By.tagName("strong")).getText();
        String txtExpectedContent = "Thanks for connecting!";
        System.out.println("----------------------");
        System.out.println(txtExpectedContent);
        System.out.println("----------------------");
        Assert.assertEquals(txtActualContent, txtExpectedContent);
        driver.switchTo().parentFrame();
    }
}
