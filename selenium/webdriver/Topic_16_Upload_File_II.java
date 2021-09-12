package webdriver;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;

	String appleName = "Apple.png";
	String lemonName = "Lemon.png";
	String mangoName = "Mango.png";

	String appleFilePath = uploadFilePath + appleName;
	String lemonFilePath = uploadFilePath + lemonName;
	String mangoFilePath = uploadFilePath + mangoName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//driver = new EdgeDriver();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	
	public void TC_01_AutoIT_One_File() {
		
		
		

		// Verify files loaded successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + appleName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + lemonName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + mangoName + "']")).isDisplayed());

		// Click Start button -> upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
		}
		
		// Verify files uploaded successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + appleName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + lemonName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + mangoName + "']")).isDisplayed());
		
	}

	
	@Test
	public void TC_04_() {
		driver.get("https://gofile.io/uploadFiles");
		
		String parentID = driver.getWindowHandle();
		
		By uploadFile = By.xpath("//input[@type='file']");		
		driver.findElement(uploadFile).sendKeys(appleFilePath + "\n" + lemonFilePath + "\n" + mangoFilePath);
		
		driver.findElement(By.xpath("//a[@id='rowUploadSuccess-downloadPage']")).click();
		
		switchToWindowByID(parentID);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + appleName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + lemonName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + mangoName + "']")).isDisplayed());
		
		
		
	}
	

	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

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
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}