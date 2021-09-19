package webdriver;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_II_FindElement {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();


		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	//  Đều chịu ảnh hưởng bởi timeout: implicit
	// 15s là thời gian tối đa để chờ cho element có trong DOM (HTML)
	// Nếu element xuất hiện trước 15s (thì thời gian còn lại ko cần chờ nữa)
	// Trg tg wait thì có cơ chế tìm lại sau mỗi 0.5s
	// Nếu sau 15s vẫn chưa xuất hiện thì tùy vào hàm đang sd: findElement/ findElements
	
	
	public void TC_01_Find_Element() {
		// Find single element
		// WebElement emailTextbox = driver.findElement(By.xpath(""));
		
		// 1 - Tìm thấy 1 matching node
		driver.findElement(By.id("email")).sendKeys("min@gmail.com");
		
		
		// 2 - Ko tìm thấy node nào
		// - Chờ hết timeout
		// - Throw exception: NoSuchElementExeption
		// - Đánh fail tcs ngay tại step đó
		// - Ko chạy step tiếp theo
		//driver.findElement(By.id("tiki")).isDisplayed();
		
		
		// 3 - Tìm thấy nhiều hơn 1 matching node
		// - Nếu nhiều hơn 1 node sẽ thao tác vs node đầu tiên
		// - ko quan tâm đến các node sau
		driver.findElement(By.cssSelector("div#pageFooter a")).click();		
	}

	@Test
	public void TC_02_Find_Elements() {
		// Find multiple elements
		List<WebElement> elements;

		// 1 - Tìm thấy 1 matching node
		// - Sẽ trả về 1 list chứa 1 element (node) đó -> size = 1
		elements = driver.findElements(By.cssSelector("input#email"));	
		System.out.println("List size = " + elements.size());
		
		// 2 - Ko tìm thấy node nào
		// - Vẫn phải chờ hết tieout của implicit
		// - Trg tg chờ có cơ chế tìm lại sau mỗi 0.5s
		// - Hết timeout ko đánh fail tcs
		// - Ko throw exception 
		// - Vẫn chạy các step tiếp theo
		elements = driver.findElements(By.cssSelector("input#tiki"));		
		System.out.println("List size = " + elements.size());
				
		// 3 - Tìm thấy nhiều hơn 1 matching node
		// - Sẽ trả về 1 list chứa n element (node) đó -> size = n
		driver.findElement(By.cssSelector("div#pageFooter a"));
		System.out.println("List size = " + elements.size());
		
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}