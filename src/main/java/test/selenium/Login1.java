package test.selenium;

import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.apache.log4j.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Login1 {
    private static final Logger LOG = Logger.getLogger(Login1.class);
    public static final String PROPERTY_FILE = "/vodafone-test.properties";

    private static final Properties PROPERTIES = new Properties();
    static {
        InputStream propertyStream = Login1.class.getResourceAsStream(PROPERTY_FILE);
        try {
            PROPERTIES.load(propertyStream);
            propertyStream.close();
        }
        catch (Exception e) {
            LOG.fatal("Can't load application property. Is the file '" + PROPERTY_FILE + "' in classpath?", e);
            System.exit(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LOG.info("Start processing");

// Create a new instance of the Chrome driver
        WebDriver driver = new ChromeDriver();

// Wait For Page To Load
// Put a Implicit wait, this means that any search for elements on the page could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(60, SECONDS);

// Step1. Navigate to URL
        driver.get( PROPERTIES.getProperty("login.url") );
        driver.manage().timeouts().implicitlyWait(60, SECONDS);

        LOG.info("Opened the website...");
// Maximize the window.
        driver.manage().window().maximize();
// Click on 'My Vodafone' button
        WebElement arrow = driver.findElement(By.className("-arrow"));
        LOG.info("Found arrow: " + arrow.getTagName());
        arrow.click();
        //driver.findElement(By.className("vf-service-menu__item -wsc")).click();

// Find the form:
        WebElement loginForm = driver.findElement(By.className("vf-nav-login"));
        LOG.info("Found login form: " + loginForm.getTagName());

// Step2. Enter Phone number
        driver.findElement(By.id("phnr")).sendKeys(PROPERTIES.getProperty("phone.number"));

// Step2. Enter Password
        driver.findElement(By.id("pwd")).sendKeys(PROPERTIES.getProperty("site.password"));

// Step2. Click on 'Continue' button
        driver.manage().timeouts().implicitlyWait(120, SECONDS);
        driver.findElement(By.name("btn")).click();
        //driver.findElement(By.className("button actionButton bigButton")).click();

// Wait For Page To Load
        driver.manage().timeouts().implicitlyWait(120, SECONDS);
        Thread.sleep(10000); // ???????

//Step3. Log Kredit pro číslo / Credit for telephone number  - I'm not sure what it should be doing. I've added the only link with "Credit" word in it.
        driver.get( PROPERTIES.getProperty("credit.url") );
        driver.manage().timeouts().implicitlyWait(180, SECONDS);

//Step4. Go to My services – Calling & SMS
        driver.get( PROPERTIES.getProperty("sms.url") );
        driver.manage().timeouts().implicitlyWait(180, SECONDS);

//Step5 is skipped, because phone number is selected by default and there is only one number.
        // ???? what if there are other number? I believe it is expected from you to navigate the number...

//Step6. Unfold Missed Call Allert
        driver.findElement(By.id("missed_call_alert")).click();
        driver.manage().timeouts().implicitlyWait(180, SECONDS);

//Step6. Select Turnoff/Active buttons
        WebElement userName = driver.findElement(By.cssSelector("input#username"));
        driver.findElement(By.name("missed_call_alert_service[save]")).click();

//Step 7. Click on 'Logout' Button
//        driver.findElement(By.xpath("//*[@id='gb_71']")).click();

//Close the browser.
//        driver.close();
    }
}
