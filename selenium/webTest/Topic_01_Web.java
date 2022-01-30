package webTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Web {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("Run Before Class");
		
		// Init driver
		// Init browser
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Init page
		// ...
		
		Assert.assertTrue(false);
	}

	@Test(groups = { "web", "regression" })
	public void TC_01_Add_New_Language() {
		System.out.println("TC_01_Add_New_Language");

	}

	@Test(groups = { "web", "regression" })
	public void TC_02_Change_Language() {
		System.out.println("TC_02_Change_Language");

	}

	@Test(groups = { "web", "regression" })
	public void TC_03_Move_Language() {
		System.out.println("TC_03_Move_Language");

	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("Run After Class");
		
		// Clean driver/ quit browser/...
		driver.quit();
	}

}
