package testng;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_Parameter {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emailTextbox = By.xpath("//input[@id='email']");
	By passwordTextbox = By.xpath("//input[@id='pass']");
	By loginButton = By.xpath("//button[@id='send2']");

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, @Optional("http://live.demoguru99.com/index.php/customer/account/login/") String urlValue) {
		
		System.out.println("Run with " + browserName);
		
		if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Please input correct browser name!");
		}
				
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		driver.get(urlValue);
		// driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test()
	public void TC_01_LoginToSystem() {

		driver.findElement(emailTextbox).sendKeys("min@gmail.com");
		driver.findElement(passwordTextbox).sendKeys("123456");
		driver.findElement(loginButton).click();
		// Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("min@gmail.com"));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		// driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		driver.findElement(By.xpath("//a[text()='Log In']")).click();

		//driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
