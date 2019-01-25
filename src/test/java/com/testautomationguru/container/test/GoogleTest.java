package com.testautomationguru.container.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomationguru.container.pages.GooglePage;

public class GoogleTest {

	private WebDriver driver;
    private GooglePage google;

    @BeforeSuite
    public void initialDelay(){
    	
        //intentionally added this as chrome/firefox containers take few ms to register
        //to hub - test fails saying hub does not have node!!
        //very rare
        Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
    }
    @BeforeTest
    public void setUp() throws MalformedURLException {
    	
    	/*System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//driver.get("http://www.google.com");
		google = new GooglePage(driver);
    	*/
    	
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        dc.setCapability("acceptInsecureCerts", true);
        dc.setCapability("acceptSslCerts", true);
        dc.setCapability("headless", true);
        dc.setCapability("window-size=1920,1080", true);
        String host = System.getProperty("seleniumHubHost");
        //driver = new RemoteWebDriver(new URL("http://192.168.99.100:4444/wd/hub"), chromeCap);
        driver = new RemoteWebDriver(new URL("http://" + host + ":4444/wd/hub"), dc);
      	google = new GooglePage(driver); 
    }


   /* @Test
    public void googleTest() throws InterruptedException {
    	System.out.println("******** Starting the google Test method ");
        google.goTo();
        System.out.println("******** Searching the automation string ");
        google.searchFor("automation");
        Assert.assertTrue(google.getResults().size() >= 10);
        System.out.println("************ End of the google Test method ");
    }*/
    

    @Test
     public void googleTest() throws InterruptedException {
	System.out.println("******** Testing the Integration V1 ");
     	System.out.println("******** Starting the google Test method ");
         google.goTo();
         System.out.println("******** Lauching title");
        
         String title = google.printTitle();
         System.out.println("Title of the Google Is --> " + title);
         System.out.println("******** Performing Assert of the title");
         Assert.assertEquals(title, "www.google.com","Titiles are not matched");
         System.out.println("******** End of the google Test method ");
     }
     
    
    @AfterTest
    public void tearDown() throws InterruptedException {
    	System.out.println("************ Qutting the driver");
        driver.quit();
    }    
}
