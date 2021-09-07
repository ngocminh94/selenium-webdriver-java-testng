package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Iframe_Frame {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	
	public void TC_01_Iframe() {
		driver.get("https://kyna.vn/");
		
		scrollToBottomPage();

		// Element: switch to FB iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));

		// Verify FB page có text = 168K likes
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),
				"168K lượt thích");
		
		// Về parent page trước
		driver.switchTo().defaultContent();
		
		// Switch to chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		sleepInSecond(2);
		
		// Click vào chat iframe
		driver.findElement(By.cssSelector("div.button_bar")).click();
		sleepInSecond(2);
		
		// Click vao button Gui tin nhan
		driver.findElement(By.cssSelector("input.submit")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div")).getText(), "Tên của bạn chưa được nhập");
		Assert.assertEquals(driver.findElement(By.cssSelector("select#serviceSelect+div")).getText(), "Bạn chưa chọn dịch vụ hỗ trợ");
		
		// Về parent page trước
		driver.switchTo().defaultContent();
		
		// Enter keyword to Search textbox
		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List<WebElement> courseName = driver.findElements(By.cssSelector("section div.content>h4"));
		
		// Verify course number = 10
		Assert.assertEquals(courseName.size(), 10);
			
		// Verify course name contains "Excel"
		for (WebElement course : courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().toLowerCase().contains("excel"));
		}
			
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// Switch vào frame Login 
		driver.switchTo().frame("login_page");
		
		// Enter text in Login form
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("mintest");
		
		// Click to Continue button
		driver.findElement(By.cssSelector("a.login-btn")).click();				
		
		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='fldPassword']")).isDisplayed());
		
		// Back to parent page
		driver.switchTo().defaultContent();
		
		// Switch to frame header
		driver.switchTo().frame("login_page");
		
		// Click on Term and condition at footer
		driver.findElement(By.xpath("//a[text()='Terms and Conditions']")).click();
			
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}