package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_WebElement_WebBrowser {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By educationTextArea = By.xpath("//textarea[@id='edu']");
	By jobRole01 = By.xpath("//select[@id='job1']");
	By interestsDevelopment = By.xpath("//input[@id='development']");
	By slider01 = By.xpath("//input[@id='slider-1']");
	By buttonIsEnabled = By.xpath("//button[@id='button-enabled']");
	By password = By.xpath("//input[@id='password']");
	By ageRadioButtonIsDisabled = By.xpath("//input[@id='radio-disabled']");
	By biography = By.xpath("//textarea[@id='bio']");
	By jobRole02 = By.xpath("//select[@id='job2']");
	By interestsCheckboxIsDisabled = By.xpath("//input[@id='check-disbaled']");
	By slider02 = By.xpath("//input[@id='slider-2']");
	By buttonIsDisabled = By.xpath("//button[@id='button-disabled']");
	

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_CheckElementDisplayed() {
		
		if (isElementDisplayed(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		
		if (isElementDisplayed(ageUnder18Radio)) {
			driver.findElement(ageUnder18Radio).click();
		}
		
		if (isElementDisplayed(educationTextArea)) {
			driver.findElement(educationTextArea).sendKeys("Automation Testing");
		}
		
	}
	
	@Test
	public void TC_02_CheckElementEnabledOrDisabled() {
		
		if (isElementEnabled(emailTextbox)) {
			System.out.println("Result: 'Email' textbox is enabled");
		}
		else {
			System.out.println("Result: 'Email' textbox is disabled");
		}
		
		if (isElementEnabled(ageUnder18Radio)) {
			System.out.println("Result: 'Under 18' radio button is enabled");
		}
		else 
		{
			System.out.println("Result: 'Under 18' radio button is disabled");
		}
		
		if (isElementEnabled(educationTextArea)) {
			System.out.println("Result: 'Education' textarea is enabled");
		}
		else {
			System.out.println("Result: 'Education' textarea is disabled");
		}
		
		if (isElementEnabled(jobRole01)) {
			System.out.println("Result: 'Job role 01' textbox is enabled");
		}
		else {
			System.out.println("Result: 'Job role 01' textbox is disabled");
		}
		
		if (isElementEnabled(interestsDevelopment)) {
			System.out.println("Result: 'Development' checkbox is enabled");
		}
		else {
			System.out.println("Result: 'Development' checkbox is disabled");
		}
		
		if (isElementEnabled(slider01)) {
			System.out.println("Result: 'Slider 01' is enabled");
		}
		else {
			System.out.println("Result: 'Slider 01' is disabled");
		}
		
		if (isElementEnabled(buttonIsEnabled)) {
			System.out.println("Result: 'Button is enabled' is enabled");
		}
		else {
			System.out.println("Result: 'Button is enabled' is disabled");
		}
		
		// Elemented is disabled
		
		if (isElementEnabled(password)) {
			System.out.println("Result: 'Password' textbox is enabled");
		}
		else {
			System.out.println("Result: 'Password' textbox is disabled");
		}
		
		if (isElementEnabled(ageRadioButtonIsDisabled)) {
			System.out.println("Result: 'Radio button is disabled' radio button is enabled");
		}
		else 
		{
			System.out.println("Result: 'Radio button is disabled' radio button is disabled");
		}
			
		if (isElementEnabled(biography)) {
			System.out.println("Result: 'Biography' textarea is enabled");
		}
		else {
			System.out.println("Result: 'Biography' textarea is disabled");
		}
		
		if (isElementEnabled(jobRole02)) {
			System.out.println("Result: 'Job role 02' textbox is enabled");
		}
		else {
			System.out.println("Result: 'Job role 02' textbox is disabled");
		}
		
		if (isElementEnabled(interestsCheckboxIsDisabled)) {
			System.out.println("Result: 'Check box is disabled' checkbox is enabled");
		}
		else {
			System.out.println("Result: 'Check box is disabled' checkbox is disabled");
		}
		
		if (isElementEnabled(slider02)) {
			System.out.println("Result: 'Slider 02' is enabled");
		}
		else {
			System.out.println("Result: 'Slider 02' is disabled");
		}
		
		if (isElementEnabled(buttonIsDisabled)) {
			System.out.println("Result: 'Button is disabled' is enabled");
		}
		else {
			System.out.println("Result: 'Button is disabled' is disabled");
		}
				
	}

	
	@Test
	public void TC_03_CheckElementSelected() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		driver.findElement(ageUnder18Radio).click();
		driver.findElement(interestsDevelopment).click();
		
		if (isElementSelected(ageUnder18Radio)) {
			System.out.println("Under 18 radio button is selected");
		}	
		else {
			driver.findElement(ageUnder18Radio).click();
		}
		
		if (isElementSelected(interestsDevelopment)) {
			System.out.println("Development checkbox is selected");
			
		}	
		else {
			driver.findElement(interestsDevelopment).click();
		}
		
	}	
	
	//Khai báo
		private boolean isElementDisplayed(By byValue) {
		return driver.findElement(byValue).isDisplayed();
	}
	
	private boolean isElementEnabled (By byValue) {
		return driver.findElement(byValue).isEnabled();
		
	}
	
	private boolean isElementSelected (By byValue) {
		return driver.findElement(byValue).isEnabled();
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
