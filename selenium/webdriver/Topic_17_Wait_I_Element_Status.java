package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	public void TC_01_Visible() {
		driver.findElement(By.xpath("//a[(text()='Tạo tài khoản mới')]")).click();

		driver.findElement(By.name("reg_email__")).sendKeys("abc@gmail.com");

		// Chờ cho element đc hiển thị
		// Hiển thị trên UI
		// Có trong DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextbox));

		driver.findElement(confirmEmailTextbox).sendKeys("abc@gmail.com");
	}

	public void TC_02_Invisible_01() {
		driver.findElement(By.xpath("//a[(text()='Tạo tài khoản mới')]")).click();

		// Case 1: Element ko có trên UI + vẫn có trong HTML
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
	}

	public void TC_02_Invisible_02() {
		driver.navigate().refresh();

		// Case 2: Element ko có trên UI + ko có trong HTML
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
	}

	
	public void TC_03_Presence() {
		driver.findElement(By.xpath("//a[(text()='Tạo tài khoản mới')]")).click();

		// Wait presence (có trong DOM + ko có trên UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));

		driver.findElement(By.name("reg_email__")).sendKeys("abc@gmail.com");
		
		// Wait presence (có trong DOM + có trên UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));
	}

	@Test
	public void TC_04_Staleness() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		driver.findElement(By.id("SubmitCreate")).click();
		
		// 1
		WebElement accountErrorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']"));
		
		// 2 -> element tại step1 bị update lại (no longer attach to DOM)
		driver.navigate().refresh();
		
		// 3 - wait element staleness: wait cho 1 element ko còn trạng thái cũ nữa
		explicitWait.until(ExpectedConditions.stalenessOf(accountErrorMessage));
		
		// StaleElementException: element đã bị thay đổi trạng thái nhưng vẫn lấy ra để thao tác

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}