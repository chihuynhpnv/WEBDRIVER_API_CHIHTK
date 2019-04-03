package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
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
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(ageUnder18Radio));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertTrue(isElementEnabled(jobRole01));
		Assert.assertTrue(isElementEnabled(interestsDevelopment));
		Assert.assertTrue(isElementEnabled(slider01));
		Assert.assertTrue(isElementEnabled(buttonIsEnabled));
		Assert.assertFalse(isElementEnabled(password));
		Assert.assertFalse(isElementEnabled(ageRadioButtonIsDisabled));
		Assert.assertFalse(isElementEnabled(biography));
		Assert.assertFalse(isElementEnabled(jobRole02));
		Assert.assertFalse(isElementEnabled(interestsCheckboxIsDisabled));
		Assert.assertFalse(isElementEnabled(slider02));
		Assert.assertFalse(isElementEnabled(buttonIsDisabled));
	}
	
	@Test
	public void TC_03_CheckElementSelected() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		checkToCheckbox(ageUnder18Radio);
		checkToCheckbox(interestsDevelopment);
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertTrue(isElementSelected(interestsDevelopment));
		unCheckTocheckbox(interestsDevelopment);
		Assert.assertFalse(isElementSelected(interestsDevelopment));	
	}	
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(1000);
		System.out.println("Random number = " + number);
		return number; 
	}
	//Khai báo
	public boolean isElementDisplayed(By byValue) {
		if (driver.findElement(byValue).isDisplayed()) {
			System.out.println("Element [" + byValue + "] is displayed!");
			return true;
		} else {
			System.out.println("Element [" + byValue + "] is not displayed!");
			return false;
			
		}
	}
	
	public boolean isElementEnabled(By byValue) {
		if (driver.findElement(byValue).isEnabled()) {
			System.out.println("Element [" + byValue + "] is enabled!");
			return true;
		} else {
			System.out.println("Element [" + byValue + "] is not enabled");
			return false;
		}	
	}
	
	public boolean isElementSelected(By byValue) {
		if (driver.findElement(byValue).isSelected()) {
			System.out.println("Element [" + byValue + "] is selected ");
			return true;
		} else {
			System.out.println("Element [" + byValue + "] is de-selected");
			return false;
		}
	}
	
	public void checkToCheckbox(By byValue) {
		WebElement element = driver.findElement(byValue);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckTocheckbox(By byValue) {
		WebElement element = driver.findElement(byValue);
		if (element.isSelected()) {
			element.click();
		}
	}
			
}
