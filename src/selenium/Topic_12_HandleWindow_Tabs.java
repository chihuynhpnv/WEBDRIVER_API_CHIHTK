package selenium;
import java.util.List;
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


public class Topic_12_HandleWindow_Tabs {
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

	//Switch if only have 2 windows/ tabs
    public void switchToChildWindowByID(String parentID) {
    //Lấy ra ID của tất cả các cửa sổ
        Set<String> allWindows = driver.getWindowHandles();
    // Dùng vòng gặp để duyệt qua từng cửa sổ
        for (String runWindow : allWindows) {
        	System.out.println("Window ID = " + runWindow);
        	//Kiểm tra nếu ID của cửa sổ nào mà khác với ID của parent thi switch qua
        	if (!runWindow.equals(parentID)) {
        		// switch qua cua so do
        		driver.switchTo().window(runWindow);
        		
        		// Thoat khoi vong lap
        		break;
        	}
        }

    }
    
    // Close without parent window/ tab
    public boolean closeAllWithoutParentWindows(String parentID) {
    	
    	// Lấy ra tất cả các ID của cửa sổ đang có
        Set<String> allWindows = driver.getWindowHandles();
        
        // Dùng vòng lặp duyệt qua từng của sổ
        for (String runWindows : allWindows) {
        	
        	// Kiểm tra child ID nào mà khác ID của parent 		
            if (!runWindows.equals(parentID)) {
            	
            	// Switch qua trươc
                driver.switchTo().window(runWindows);
                
                // Đóng cái tab đang đứng 
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
    
    	List<WebElement> notificationIframe = driver.findElements(By.xpath("//div[@id='parentdiv']//img[@class='popupbanner']"));
    	int notificationIframeSize = notificationIframe.size();
    	System.out.println("Notification iframe displayed = " + notificationIframeSize);
    	
    	if (notificationIframeSize > 0) {
    	
    		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//img[@class='popupCloseButton']")));
    		
       	}
    	
    	// Click vafo Agri link
    	driver.findElement(By.xpath("//a[text()='Agri']")).click();
    	
    	// switch qua Agri page
    	switchToChildWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
    	Thread.sleep(2000);
    	
    	// Click vao accout Details link
    	driver.findElement(By.xpath("//p[text()='Account Details']")).click();
    	
    	//Switch qua frame chus Privacy Policy link 
    	driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
    	
    	// click vao policy link
    	driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
    	
    	// Switch qua Privacy Policy link
    	switchToChildWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
    	Thread.sleep(2000);
    	
    	//Click vao CSR link
    	driver.findElement(By.xpath("//a[text()='CSR']")).click();
    	
    	// Dong tat ca tab ngoai tru parent windows
    	Assert.assertTrue(closeAllWithoutParentWindows(parentID));
    }
    	//Switch have lon hon hooac bang 2 windows/ tab
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