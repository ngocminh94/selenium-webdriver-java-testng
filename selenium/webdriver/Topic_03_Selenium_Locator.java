package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Locator {
	// khai báo biến "driver" đại diện cho Selenium WebDriver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Mở trình duyệt Firefox lên
		driver = new FirefoxDriver();

		// Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// Mở app lên
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	
	@Test
	public void TC_01_FindElement() {

		// Single element --> WebElement
		 driver.findElement(By.className("")).click(); // -> action trực tiếp lên
		// element, ko lưu

		WebElement loginButton = driver.findElement(By.className("")); // -> lưu element trước
		loginButton.click(); // -> sau đó mới action lên

		// findElement: tìm element
		// By.xxx: vs locator nào
		// action gì lên element đó: click, getText, sendkey,...

		// Multiple element --> List<WebElement>
		List<WebElement> buttons = driver.findElements(By.className("")); // -> lưu trước
		buttons.get(0).click(); // -> sau đó mới action lên
	}

	
	
	public void TC_02_ID() {
		// Selenium Locator
		driver.findElement(By.id("send2")).click();

		// Verify email error mesage xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	
	
	
	public void TC_03_Class() {
		// driver.navigate().refresh();
		driver.findElement(By.className("validate-password")).sendKeys("123456789");
	}

	
	
	
	public void TC_04_Name() {
		driver.navigate().refresh();
		driver.findElement(By.name("send")).click();

		// Verify email error mesage xuất hiện
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}

	
	
	
	public void TC_05_Tagname() {
		// Hiển thị hết tất cả đường link ở màn hình này, sau đó getText ra
		List<WebElement> loginPageLinks= driver.findElements(By.tagName("a"));
		
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
		}
	}

	
	
	
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
	}

	
	
	
	public void TC_07_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());

	}

	
	
	@Test
	public void TC_08_Css() {
		driver.findElement(By.cssSelector("#email")).sendKeys("min@gmail.com");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("1234567890");
		
	}

	
	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("min.trinh@gmail.com");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("1234567890");

	}

	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
