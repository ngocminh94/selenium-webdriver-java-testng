package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	
	String loginPageUrl, userID, password, name, gender, dateOfBirthInput, dateOfBirthOutput, addressInput,
	addressOutput, city, state, pin, phone, email;
	
	By nameTextboxBy = By.name("name");
	By genderRadioBy = By.xpath("//input[@value='f']");
	By genderTextboxBy = By.name("gender");
	By dateOfBirthTextboxBy = By.name("dob");
	By addressTextAreaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Live_Guru() {
		driver.get("http://live.demoguru99.com/");
		// navigateToUrlByJS("http://live.demoguru99.com/");
		
		String liveGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");		
		
		String liveGuruUrl = (String) executeForBrowser("return document.URL;");
		Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");
		
		highlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		
		highlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		
		highlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		
		String customerServiceTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		
		highlightElement("//input[@id='newsletter']");
		scrollToElement("//input[@id='newsletter']");
		sendkeyToElementByJS("//input[@id='newsletter']", generateEmail());
		
		highlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String bankGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(bankGuruDomain, "demo.guru99.com");	
		
	}

	
	public void TC_02_Validation_Message() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		String validationMessage;
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='lastName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		// last name
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("ngoc");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='firstName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		// first name
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("minh");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@");
	
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please enter an email address.");
		
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@22");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please match the requested format.");
		
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("auto@22.com");
		
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		
		validationMessage = getElementValidationMessage("//input[@id='password']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");	
		
	}	
	
	
	public void TC_03_Remove_Attribute() {
		driver.get("http://demo.guru99.com/v4/");
		
		name = "Min Trinh";
		gender = "female";
		dateOfBirthInput = "01/01/1990";
		dateOfBirthOutput = "1990-01-01";
		addressInput = "35 le van luong\nthanh xuan";
		addressOutput = "35 le van luong thanh xuan";
		city = "Hanoi";
		state = "Cali";
		pin = "668899";
		phone = "01234567890";
		email = "min" + generateEmail();
		
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text() = \"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();
		
		removeAttributeInDOM("//input[@id='dob']", "type");
		
		// jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateOfBirthTextboxBy));
		sleepInSecond(5);
		
		driver.findElement(dateOfBirthTextboxBy).sendKeys(dateOfBirthInput);
		driver.findElement(addressTextAreaBy).sendKeys(addressInput);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);

		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);

		
			
	}	
	
	@Test
	public void TC_04_Click_Sendkey() {
		// driver.get("http://live.demoguru99.com/");
		
		navigateToUrlByJS("http://live.demoguru99.com/");

		clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
		
		clickToElementByJS("//span[text()='Create an Account']");
		
		sendkeyToElementByJS("//input[@id='firstname']", "Min");
		sendkeyToElementByJS("//input[@id='lastname']", "Trinh");
		sendkeyToElementByJS("//input[@id='email_address']", generateEmail());
		sendkeyToElementByJS("//input[@id='password']", "123456");
		sendkeyToElementByJS("//input[@id='confirmation']", "123456");

		clickToElementByJS("//span[text()='Register']");
		
		Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));

		clickToElementByJS("//a[text()='Log Out']");
		
		// Kiểm tra hệ thống navigate về Home Page sau khi logout thành công (dùng isDisplayed để check wait)
		
		String homePageTitle = (String) executeForBrowser("return document.title;");
		Assert.assertEquals(homePageTitle, "Home page");
		
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean isExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
		}
	

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@gmail.net";
		
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}