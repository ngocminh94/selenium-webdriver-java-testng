package webdriver;

import java.io.File;
import java.util.List;
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

public class Topic_16_Upload_File_I {
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

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	}

	
	public void TC_01_Sendkey_One_File() {
		By uploadFile = By.xpath("//input[@type='file']");
		
		driver.findElement(uploadFile).sendKeys(appleFilePath);
		driver.findElement(uploadFile).sendKeys(lemonFilePath);
		driver.findElement(uploadFile).sendKeys(mangoFilePath);

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
	public void TC_02_Sendkey_Multiple_Files() {
		By uploadFile = By.xpath("//input[@type='file']");
		
		// Note: với Fifefox bản cũ (47) -> ko work
		driver.findElement(uploadFile).sendKeys(appleFilePath + "\n" + lemonFilePath + "\n" + mangoFilePath);

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