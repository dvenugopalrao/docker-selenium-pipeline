package com.testautomationguru.container.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePage {

	    private final WebDriver driver;
	    private final WebDriverWait wait;

	    //@FindBy(name = "q")
	    @FindBy(xpath = "//*[contains(@title, 'Search')]")
	    private WebElement searchBox;
	    @FindBy(xpath = "//input[@value='Google Search']")
	   // @FindBy(css = ".FPdoLc.VlcLAe>center>input:nth-child(1)")
	    private WebElement searchButton;

	    @FindBy(className = "rc")
	    private List<WebElement> searchResults;

	    @FindBy(id = "foot")
	    private WebElement footer;

	    public GooglePage(final WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	        this.wait = new WebDriverWait(driver, 30);
	    }

	    public void goTo() {
	    	System.out.println("******* Lannching the google.com");
	        this.driver.get("https://www.google.com");
	    }

	    public void searchFor(String text) throws InterruptedException {
	        this.searchBox.sendKeys(text);
	        wait.until(ExpectedConditions.elementToBeClickable(this.searchButton));
	        this.searchButton.click();
	        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("rc")));
	    }

	    public String  printTitle() {
	    	
	    String title = this.driver.getTitle();
	    return title;
	        
	    }

	    
	    public List<WebElement> getResults() {
	        return this.searchResults;
	    }

	}

