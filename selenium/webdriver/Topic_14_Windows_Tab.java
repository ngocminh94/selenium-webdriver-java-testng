package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tab {
	WebDriver driver;
	WebDriverWait explicitWait;
	String childID;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Windows() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);

		// Switch vào FB tab bằng title
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("min@gmail.com");

		// Switch lại vào parent page (github.io)
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("auto");

		// Click Tiki link -> hành vi của app tự động nhảy qua tab đó
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);

		// Switch vào Tiki tab bằng title
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("Lenovo");
		driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).click();

		// Switch lại vào parent page (github.io)
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// Click Lazada link -> hành vi của app tự động nhảy qua tab đó
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(3);

		// Switch vào Lazada tab bằng title
		switchToWindowByTitle("LAZADA Vietnam™ - Mua Hàng Trực Tuyến Giá Tốt");

		closeAllWindowsWithoutParent(parentID);
		
		driver.findElement(By.id("email")).sendKeys("min@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Dùng cho duy nhất 2 window/ tab
	// Kiểm tra ID trước
	// Nếu khác parent
	// Thì mới switch
	public void switchToWindowByID(String parentID) {
		// Get ra tất cả các tab/ window đang có
		Set<String> allWindows = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng window
		for (String id : allWindows) {
			// Nếu có id nào khác parentID
			if (!id.equals(parentID)) {
				// thì switch vào id đó
				driver.switchTo().window(id);
			}
		}
	}

	// Dùng cho 2 window/ tab hoặc nhiều hơn
	// Switch vào từng window trước
	// Get title của window đó
	// Kiểm tra vs title mong muốn
	// Nếu = thì stop ko kiểm tra tiếp nữa
	public void switchToWindowByTitle(String expectedTitle) {
		// Get ra tất cả các tab/ window đang có
		Set<String> allWindows = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng window
		for (String id : allWindows) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
			// Nếu = thì stop ko kiểm tra tiếp nữa
			if (windowTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(String parentID) {
		// Get ra tất cả các tab/ window đang có
		Set<String> allWindows = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng window
		for (String id : allWindows) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		// Switch về parent 
		driver.switchTo().window(parentID);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}