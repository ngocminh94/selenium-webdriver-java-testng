package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Command_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	String firstName, lastName, fullName, email, password;	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		firstName = "Min";
		lastName = "Trinh";
		fullName = firstName + " " + lastName;
		email = "min" + getRandomNumber() + "@gmail.us";
		password = "123456";
		
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	
	
	@Test
	public void TC_01_Signup_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.cssSelector("#email")).sendKeys("min@gmail.com");
		driver.findElement(By.cssSelector("#new_username")).sendKeys("min");	
		
		By passwordTextbox = By.id("new_password");
		By signupButton = By.id("create-account");
		By newsletterCheckbox = By.id("marketing_newsletter");
		
		
		// Click Newsletter check box
		driver.findElement(newsletterCheckbox).click();
		
		// Lowercase
		driver.findElement(passwordTextbox).sendKeys("auto");
		
		// verify: label of lowercase -> update
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		
		// verify: Signup btn disabled
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());
		
		
		// Uppercase
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("AUTO");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());
		
		
		// Number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123456");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());		
		
		
		// Special char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("@@@###");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());		
				
		
		// >= 8 chars
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("🙃🙃🙃🙃");
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertFalse(driver.findElement(signupButton).isEnabled());		
				
		
		// Full valid data
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Auto12345!!!");
		
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']")).isDisplayed());
		Assert.assertTrue(driver.findElement(signupButton).isEnabled());		
		Assert.assertTrue(driver.findElement(newsletterCheckbox).isSelected());				
						
	}

	
	@Test
	public void TC_02_LiveGuru_Register() {
		driver.get("http://live.demoguru99.com/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		// Cach 1
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));
		
		// Cach 2
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/"
				+ "parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + fullName + "')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/"
				+ "parent::div/following-sibling::div[@class='box-content']/p[contains(string(),'" + email + "')]")).isDisplayed());
		
		driver.findElement(By.xpath("//a/span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();		
						
	}		
	
	@Test
	public void TC_03_LiveGuru_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		
		String contactInformation = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div[@class='box-content']/p")).getText();
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(email));		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
}