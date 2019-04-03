package selenium;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04_Textbox_Textarea {
    WebDriver driver;
    //Declare variable  data
    String customerName, gender, dateOfBirth, address, city, state, pin, phone, email, password;
    //Locate element
    By customerNameTextbox = By.xpath("//input[@name='name']");
    By genderRadio = By.xpath("//input[@value='f']");
    By dateOfBirthTextbox = By.xpath("//input[@id='dob']");
    By addressTextarea = By.xpath("//textarea[@name='addr']");
    By cityTexaddressTextareatbox = By.xpath("//input[@name='city']");
    By stateTextbox = By.xpath("//input[@name='state']");
    By pinTextbox = By.xpath("//input[@name='pinno']");
    By mobilePhoneTextbox = By.xpath("//input[@name='telephoneno']");
    By emailTextbox = By.xpath("//input[@name='emailid']");
    By passwordTextbox = By.xpath("//input[@name='password']");
    By submitButton = By.xpath("//input[@name='sub']");
        
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		//Map data
		customerName = " Automation Test";
		gender = "Female";
		dateOfBirth = "09/05/1997";
		address = "123 Le Duan";
		city = "Da Nang";
		state = "Hai Chau";
		pin = "60000";
		phone = "0396783679";
		email = "autotest" + randomNumber() + "@gmail.com";
		password = "123123";
	}
	
	@Test
	public void TC_01_CreateNewCustomer (){
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Add New Customer']")).isDisplayed());
		//input data for new customer 
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		driver.findElement(addressTextarea).sendKeys(address);
		driver.findElement(cityTexaddressTextareatbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobilePhoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();
		
		// Verify expected data = actual data
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("Random number = + number");
		return number;
	}

}


