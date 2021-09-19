package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_VI_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	
	WebElement element;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
	}

	public void TC_01_Element_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 5);

		// implicit và explicit = bn giây cũng đc (miễn là phù hợp đủ tg tìm thấy
		// element)
		// Vì tìm thấy element -> ko cần phải chờ hết timeout, ko có expection, ko fail
		// tcs

		System.out.println("Start implicit: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("End implicit: " + getDateTimeNow());

		System.out.println("Start explicit: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("End explicit: " + getDateTimeNow());

	}

	public void TC_02_1_Element_Not_Found_Only_Implicit() {
		driver.get("https://www.facebook.com/");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Chờ hết timeout/ tìm lại sau mỗi 0.5s/ hết timeout đánh fail tcs/ throw
		// NoSuchElementException

		System.out.println("Start implicit: " + getDateTimeNow());
		try {
			driver.findElement(By.cssSelector("input#testing"));
		} finally {
			System.out.println("End implicit: " + getDateTimeNow());
		}
	}

	public void TC_02_2_Element_Not_Found_Only_Explicit() {
		driver.get("https://www.facebook.com/");

		explicitWait = new WebDriverWait(driver, 5);

		// Chờ hết timeout/ tìm lại sau mỗi 0.5s/ hết timeout đánh fail tcs/ throw
		// TimeoutException

		System.out.println("Start explicit: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#testing")));
		} finally {
			System.out.println("End explicit: " + getDateTimeNow());
		}
	}

	public void TC_02_3_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");

		// Implicit > Explicit --> tổng time = time của implicit
		// Implicit = Explicit --> tổng time = time của implicit = explicit
		// Implicit < Explicit --> tổng time ko xác định dc --> thực tế ko nên sd case này
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 3);

		System.out.println("Start implicit: " + getDateTimeNow());
		
		try {
			// Nhận timeout của implicit
			driver.findElement(By.cssSelector("input#testing"));
		} catch (Exception e1) {
			
		}
		
		System.out.println("End implicit: " + getDateTimeNow());

		System.out.println("Start explicit: " + getDateTimeNow());
		
		// Nhận timeout của cả 2 trong hàm: visibilityOfElementLocated
		// driver.findElement(locator) -> bị ảnh hưởng timeout của implicit: 5s
		// elementIfVisible -> bị ảnh hưởng timeout của explicit: 3s
				
		try {
			element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#testing")));
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
		System.out.println("End explicit: " + getDateTimeNow());

		Assert.assertNull(element);
		
	}
	
	@Test
	public void TC_02_4_Element_Not_Found_Implicit_Explicit() {
		driver.get("https://www.facebook.com/");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);

		System.out.println("Start explicit: " + getDateTimeNow());

		try {
			// Nhận tham số là By
			// Nhận timeout của cả 2 trong hàm: visibilityOfElementLocated
			// driver.findElement(locator) -> bị ảnh hưởng timeout của implicit: 5s
			// elementIfVisible -> bị ảnh hưởng timeout của explicit: 5s
			// Throw exception: TimeoutException (explicit)
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#testing")));
			
			// Nhận tham số là By
			// Chạy từ trong ra: findElement -> nhận timeout của implicit
			// Ko tìm thấy element -> chờ hết timeout 5s -> đánh fail
			// Throw exception: NoSuchElement (implicit)
			// Ko chạy đến hàm visibilityOf (explicit)
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#testing"))));
			
		} catch (Exception e) {	
			e.printStackTrace();
		}		
		System.out.println("End explicit: " + getDateTimeNow());		
	}
	
	@Test
	public void TC_03() {
		driver.get("https://www.facebook.com/");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);

		System.out.println("Start explicit: " + getDateTimeNow());

		try {
			// Nhận tham số là By
			// Chạy từ trong ra: findElement -> nhận timeout của implicit
			// Ko tìm thấy element -> chờ hết timeout 5s -> đánh fail
			// Throw exception: NoSuchElement (implicit)
			// Ko chạy đến hàm visibilityOf (explicit)
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#testing"))));			
		} finally {	
			System.out.println("End explicit: " + getDateTimeNow());
		}	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}