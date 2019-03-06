package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String invalidEmail = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(invalidEmail, "Please enter a valid email address. For example johndoe@domain.com.");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
	}
	
	@Test
	public void TC_03_LoginWithPasswordLessThanSixCharacters() {
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String invalidPassword = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(invalidPassword, "Please enter 6 or more characters without leading or trailing spaces.");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).clear();
	}
	
	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String incorrectPassword = driver.findElement(By.xpath("//li[@class='error-msg']")).getText();
		Assert.assertEquals(incorrectPassword, "Invalid login or password.");
	}
		
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}