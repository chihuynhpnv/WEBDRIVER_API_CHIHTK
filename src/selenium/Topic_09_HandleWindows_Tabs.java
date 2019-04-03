package selenium;
import java.util.concurrent.TimeUnit;
import java.util.Set;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Topic_09_HandleWindows_Tabs {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public void TC_01_CheckBackToParentPage() throws InterruptedException {
	driver.get("https://daominhdam.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentID);
		
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		switchToChildWindowByID(parentID); 
		
		String googleTitle = driver.getTitle();
		System.out.println(googleTitle);
		
		Assert.assertEquals(googleTitle, "Google");
		
		Assert.assertTrue(closeAllWithoutParentWindows(parentID));
		
		Thread.sleep(2000);
		
		Assert.assertEquals(driver.getTitle(),"SELENIUM WEBDRIVER FORM DEMO");
	}

    public void switchToChildWindowByID(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
        	System.out.println("Window ID = " + runWindow);
        	if (!runWindow.equals(parentID)) {
        		driver.switchTo().window(runWindow);
        		break;
        	}
        }

    }
    
    public boolean closeAllWithoutParentWindows(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();    
        for (String runWindows : allWindows) {	
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1)
                   return true;
        else
                   return false;
}
    @Test 
    public void TC_02_HDFCBank() throws InterruptedException {
    	driver.get("http://www.hdfcbank.com/");
    	String parentID = driver.getWindowHandle();
    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement popup = driver.findElement(By.xpath("//img[@class='popupCloseButton']"));
		if (popup.isDisplayed()) {
			driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
		}
    	
    	/*List <WebElement> notificationIframe = driver.findElements(By.xpath("//img[@class='popupbanner']"));
 	    int notificationIframeSize = notificationIframe.size() ; 
 	    System.out.println("Notification Iframe displayed = " + notificationIframeSize);
 	    
 	     if (notificationIframeSize > 0 ) {
 	    	driver.switchTo().frame(notificationIframe.get(0));
 		    Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
 		    jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//img[@class='popupCloseButton']"))); 
 		    System.out.println("Close popup successfully!");
 		    driver.switchTo().defaultContent(); 
 		     }
 	    System.out.println("Pass handle popup!");
 	    */
    	
    	driver.findElement(By.xpath("//a[text()='Agri']")).click();
    	switchToChildWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
    	Thread.sleep(2000);
    	
    	driver.findElement(By.xpath("//p[text()='Account Details']")).click();
    	
    	switchToChildWindowByTitle("Welcome to HDFC Bank NetBanking");
		Thread.sleep(2000);
		
    	driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
    	driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
    	switchToChildWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
    	Thread.sleep(2000);
    	
    	driver.findElement(By.xpath("//a[text()='CSR']")).click();
    	Assert.assertTrue(closeAllWithoutParentWindows(parentID));
    }
    	public void switchToChildWindowByTitle(String expectedTitle) {
    		Set<String> allWindows = driver.getWindowHandles();
    		for (String runWindows : allWindows) {
    			driver.switchTo().window(runWindows);
    			String currentWindow = driver.getTitle();
    			if (currentWindow.equals(expectedTitle)){
    				break;
    			}
    		}
    		
    	} 	 
    
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}