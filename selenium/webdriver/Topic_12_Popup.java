package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	

	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		
		driver.findElement(By.cssSelector("button.login_")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());		
		
	}

	
	public void TC_02_Random_In_DOM() {
		driver.get("https://blog.testproject.io/");
	
		WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
		// Nếu pop up hiển thị thì close đi để thực hiện các step sau
		if (popup.isDisplayed()) {
			// Closen popup
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("div#close-mailch")).click();
			sleepInSecond(2);
		} else {
			System.out.println("Popup is not displayed");			
		}
		
		// Nếu ko hiển thị thì thực hiện các step sau luôn
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		sleepInSecond(2);
		// Verify title chưa text = selenium
		List<WebElement> articleTitle = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article.getText().contains("Selenium"));
		}		
		
	}	
	
	@Test
	public void TC_03_Random_Not_In_DOM() {
		driver.get("https://shopee.vn/");
		sleepInSecond(3);	
		
		// Nếu element ko có trong DOM thì hàm element ko tìm thấy
		// -> chờ hết timeout của implicit
		// -> Fail testcase ngay tại step đó
		// Throw ra 1 exception: NoSuchElement 
		// WebElement popup = driver.findElement(By.cssSelector("div.shopee-popup img"));
		
		// Nếu element ko có trong DOM thì hàm element ko tìm thấy
		// -> Trả về 1 empty list (size=0)
		// -> Ko đánh fail testcase
		// Ko throw exception		
		List<WebElement> pop_up = driver.findElements(By.cssSelector("div.shopee-popup img"));
		
		
		if (pop_up.size()>0 && pop_up.get(0).isDisplayed()) {
			// Closen popup
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
			sleepInSecond(2);
		} else {
			System.out.println("Popup is not displayed");			
		}
		
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("granola");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("div.shopee-searchbar>button")).click();
		
		
		
	}	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}