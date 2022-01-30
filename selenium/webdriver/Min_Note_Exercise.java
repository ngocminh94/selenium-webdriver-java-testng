package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Min_Note_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// TOPIC 06_WEB BROWSER EXERCISE:

	// @Test
	public void TC_01_Verify_Page_Url() {
		// Step1: open web
		driver.get("http://live.demoguru99.com/");

		// Step2: Click My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Step3: Verify URL of Login Page =
		// http://live.demoguru99.com/index.php/customer/account/login/
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Step4: Click button Create An Account
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		// Step5: Verify URL of Register Page =
		// http://live.demoguru99.com/index.php/customer/account/create/
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	// @Test
	public void TC_02_Verify_Page_Title() {
		// Step1: open web
		driver.get("http://live.demoguru99.com/");

		// Step2: Click My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Step3: Verify title of Login Page = Customer Login
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");

		// Step4: Click button Create An Account
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		// Step5: Verify title of Register Page = Create New Customer Account
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}

	// @Test
	public void TC_03_Navigate() {
		// Step1: open web
		driver.get("http://live.demoguru99.com/");

		// Step2: Click My Account at footer
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		// Step3: Click button Create An Account
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();

		// Step4: Verify URL of Register Page =
		// http://live.demoguru99.com/index.php/customer/account/create/
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");

		// Step 5: Back to Login page
		driver.navigate().back();

		// Step 6: Verify URL of Login Page =
		// http://live.demoguru99.com/index.php/customer/account/login/
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");

		// Step 7: Forward to Register Page
		driver.navigate().forward();

		// Step 8: Verify title of Register Page = Create New Customer Account
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}

	// TOPIC 06_WEB ELEMENT EXERCISE:

	@Test
	public void TC_01_Is_Displayed() {
		// Step 1: Open web
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Step 2: Check Is_Displayed: Email, Age(Under 18), Education
		// Is_Not_Displayed: Name User5
		// Send key to Email, Education field
		// Select radio button Age(Under 18)
		// Print: Element is displayed/ not displayed
		
		WebElement emailTextbox = driver.findElement(By.id("mail"));
		if (emailTextbox.isDisplayed()) {emailTextbox.sendKeys("min@gmail.com");
		System.out.println("Element is displayed");
			
		} else {System.out.println("Element is not displayed");
		}
		
		WebElement ageUnder18Radio = driver.findElement(By.id("under_18"));
		if (ageUnder18Radio.isDisplayed()) {ageUnder18Radio.click();
		System.out.println("Element is displayed");
			
		} else {System.out.println("Element is not displayed");
		}
		
		WebElement educationTextArea = driver.findElement(By.id("edu"));
		if (educationTextArea.isDisplayed()) {educationTextArea.sendKeys("automation testing");
		System.out.println("Element is displayed");
			
		} else {System.out.println("Element is not displayed");
		}

		WebElement user5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (user5.isDisplayed()) {
			System.out.println("Element is displayed");
			
		} else {System.out.println("Element is not displayed");
		}
	}

	
	
	
	
	
	
	
	
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}