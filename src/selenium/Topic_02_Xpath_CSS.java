package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Random;

public class Topic_02_Xpath_CSS {
    WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}
	
	@Test
	public void TC_01_LoginWithEmailAndPasswordEmpty() {
		driver.findElement (By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String emailRequired = driver.findElement(By.id("advice-required-entry-email")).getText();	
		Assert.assertEquals(emailRequired, "This is a required field.");
//		kiểm tra xem một điều kiện có đúng hay không
		
		String passwordRequired = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(passwordRequired, "This is a required field.");
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement (By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String invalidEmail = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(invalidEmail, "Please enter a valid email address. For example johndoe@domain.com.");;
	}
	
	@Test
	public void TC_03_LoginWithPasswordLessThanSixCharacters() {
		driver.findElement (By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String invalidPassword = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(invalidPassword, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		driver.findElement (By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String incorrectPassword = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
		Assert.assertEquals(incorrectPassword, "Invalid login or password.");
	}
		
	@Test
	public void TC_05_CreateNewAccount() {
		String firstName = "Chi";
		String lastName = "Huynh";
		String email = "chihuynh" + randomNumber() + "@gmail.com";
		String password = "123456";
		
		driver.findElement (By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
//		String randomEmail = "chihuynh" + UUID.randomUUID().toString() + "@gmail.com";
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		String successMessage = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()= 'Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		String loggedOutMessage = driver.findElement(By.xpath("//div[@class='page-title']//h1[contains(text(),'logged out')]")).getText();
		Assert.assertEquals(loggedOutMessage, "YOU ARE NOW LOGGED OUT");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']//img[contains(@src,'logo.png')]")).isDisplayed());
	}
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(1000);
		System.out.println("Random number = " + number);
		return number;
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}