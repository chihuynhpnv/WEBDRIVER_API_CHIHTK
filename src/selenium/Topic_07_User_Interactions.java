package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_User_Interactions {
    WebDriver driver;
    Actions action;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_HoverMouse () throws InterruptedException{ 
		
		driver.get("http://www.myntra.com/");
		//Hover to profile icon
		WebElement profileIcon = driver.findElement(By.xpath("//span[contains(@class, 'desktop-iconUser')]"));
		action.moveToElement(profileIcon).perform();
		
		Thread.sleep(3000);
		
		// Click to Login button
		WebElement loginButton = driver.findElement((By.xpath("//a[@class='desktop-linkButton' and text()='log in']")));
		
		// Login button click
		action.click(loginButton).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());	
	}
	
	@Test
	public void TC_02_ClickAndHold_Block () throws InterruptedException{ 
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("Element trước khi chọn = " + numberItems.size());
		
		// 0-3 (index) = 1 -4 (elemnt)
		action.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release().perform();
		Thread.sleep(3000);

		List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		System.out.println("Element sau khi chon = " + numberItemsSelected.size());

		Assert.assertEquals(numberItemsSelected.size(), 4);
		}
	@Test
	public void TC_03_ClickAndHold_Random () throws InterruptedException{ 
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(numberItems.get(0));
		action.click(numberItems.get(2));
		action.click(numberItems.get(4));
		action.click(numberItems.get(6));
		action.keyUp(Keys.CONTROL).perform();

		Thread.sleep(3000);

		List <WebElement> numberItemsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		System.out.println("Element sau khi chon = " + numberItemsSelected.size());

		Assert.assertEquals(numberItemsSelected.size(), 4);
		}
	
	@Test
	public void TC_04_DoubleClick () throws InterruptedException{ 
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClickMeButton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleClickMeButton).perform();

		Thread.sleep(3000);

		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "The Button was double-clicked.");
		alert.accept();

		//Assert.assertEquals(driver.switchTo().alert().getText(), "The button was double-clicked.");
		//driver.switchTo().alert().accept();
		}
	
	@Test
	public void TC_05_RightClick () throws InterruptedException{ 
		driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClickButton);
		WebElement quitOption = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')"));
		action.moveToElement(quitOption).perform();
		Thread.sleep(2000);
		
		WebElement hoverQuitButton = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]"));
		Assert.assertTrue(hoverQuitButton.isDisplayed());
		
		hoverQuitButton.click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(3000);

		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		Thread.sleep(3000);
		
		Assert.assertFalse(hoverQuitButton.isDisplayed());
		}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}