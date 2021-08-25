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

public class Topic_06_Web_Element_Command_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	By emailTextboxBy = By.id("mail");
	By educationTextareaBy = By.id("edu");
	By user5TextBy = By.xpath("//h5[text()='Name: User5']");
	
	By passwordTextboxBy = By.id("password");
	By slider2By = By.id("slider-2");
	
	By ageOver18RadioBy = By.id("over_18");
	By developmentCheckbox = By.id("development");
	By javaCheckbox = By.id("java");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
	}

	
	@Test
	public void TC_01_Is_Displayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement emailTextbox = driver.findElement(By.id("mail"));

		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		
		WebElement ageOver18Radio = driver.findElement(By.id("over_18"));

		if (ageOver18Radio.isDisplayed()) {
			ageOver18Radio.click();;
			System.out.println("Age over 18 is displayed");
		} else {
			System.out.println("Age over 18 is not displayed");
		}
		
		
		WebElement educationTextarea = driver.findElement(By.id("edu"));

		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}
		
		
		WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (user5Text.isDisplayed()) {
			System.out.println("User5 text is displayed");
			
		} else {
			System.out.println("User5 text is not displayed");
		}		
	}	
		

	@Test
	public void TC_02_Is_Displayed_Refactor() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
				
		if(isElementDisplayed(emailTextboxBy)) {
			sendkeyToElement(emailTextboxBy,"Automation Testing");
		}
		
		if(isElementDisplayed(ageOver18RadioBy)) {
			clickToElement(ageOver18RadioBy);
		}
		
		if(isElementDisplayed(educationTextareaBy)) {
			sendkeyToElement(educationTextareaBy,"Automation Testing");
		}
		
		Assert.assertFalse(isElementDisplayed(user5TextBy));
	}
	
	
	@Test
	public void TC_03_Is_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// Mong đợi element enabled
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(ageOver18RadioBy));
		Assert.assertTrue(isElementEnabled(educationTextareaBy));
		
		// Mong đợi element disabled
		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
		Assert.assertFalse(isElementEnabled(slider2By));		
	}
		
		
	@Test
	public void TC_04_Is_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		clickToElement(ageOver18RadioBy);
		clickToElement(developmentCheckbox);
		
		Assert.assertTrue(isElementSelected(ageOver18RadioBy));
		Assert.assertTrue(isElementSelected(developmentCheckbox));
		Assert.assertFalse(isElementSelected(javaCheckbox));	
		
		clickToElement(ageOver18RadioBy);
		clickToElement(developmentCheckbox);
		
		Assert.assertTrue(isElementSelected(ageOver18RadioBy));
		Assert.assertFalse(isElementSelected(developmentCheckbox));
		Assert.assertFalse(isElementSelected(javaCheckbox));
	}

	
	
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed");
			return false;
		}
	}
	
	
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disabled");
			return false;
		}
	}
	
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is deselected");
			return false;
		}
	}
	
	
	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
		
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}