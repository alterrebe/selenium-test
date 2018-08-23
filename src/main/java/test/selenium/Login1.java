package test.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login1 {
    public static void main(String[] args) {
// Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();
// Wait For Page To Load
// Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
// Navigate to URL
        driver.get("https://www.vodafone.cz/en");
// Maximize the window.
        driver.manage().window().maximize();
// Click on 'My Vodafone' button
        driver.findElement(By.id("My Vodafone")).click();
// Enter Phone number
        driver.findElement(By.id("Phone number")).sendKeys("773010016");
// Enter Password
        driver.findElement(By.id("Password")).sendKeys("2013");
// Click on 'Continue' button
        driver.findElement(By.id("Continue")).click();
// Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

//Click on 'Logout' Button
        driver.findElement(By.xpath("//*[@id='gb_71']")).click();
//Close the browser.
        driver.close();
    }
}
