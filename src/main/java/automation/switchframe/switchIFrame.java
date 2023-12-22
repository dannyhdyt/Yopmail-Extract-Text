package automation.switchframe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;


public class switchIFrame {
    WebDriver driver;

    @Test
    public void yopmailIFrame() {
        //open browser
        WebDriverManager.firefoxdriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://yopmail.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Enter inbox
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        driver.findElement(By.id("login")).sendKeys("automationtest");
        driver.findElement(By.xpath("//button[@class='md']")).click();

        //extract text
        driver.switchTo().frame("ifmail");
        String actualEmail = driver.findElement(By.id("mail")).getText();
        System.out.println("----this is the first email text content--- \n" + actualEmail);
        driver.switchTo().parentFrame();
    }
}
