package webdriver;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}


	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");

		By loginButton = By.cssSelector("button.fhs-btn-login");

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Verify button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		driver.findElement(By.cssSelector("input#login_username")).sendKeys("min@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		sleepInSecond(1);

		// Verify button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());

		driver.navigate().refresh();

		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

		// Remove disabled attribute of Login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled');", driver.findElement(loginButton));
		sleepInSecond(1);

		// Verify Login button with background color RED
		String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println("RGBA = " + rgbaColor);

		String hexaColor = Color.fromString(rgbaColor).asHex().toUpperCase();
		System.out.println("Hexa = " + hexaColor);

		Assert.assertEquals(hexaColor, "#C92127");
		// =
		// Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(),
		// "#C92127");

		driver.findElement(loginButton).click();

		Assert.assertEquals(
				driver.findElement(
						By.xpath("//div[@class='popup-login-content']" + "//label[text()='Số điện thoại/Email']"
								+ "/following-sibling::div[@class='fhs-input-alert']"))
						.getText(),
				"Thông tin này không thể để trống");

		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='popup-login-content']" + "//label[text()='Mật khẩu']"
						+ "/following-sibling::div[@class='fhs-input-alert']")).getText(),
				"Thông tin này không thể để trống");

	}

	
	public void TC_02_Radio_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");

		// Verify radio button petrol 2.0 deselected
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());

		driver.findElement(petrolTwo).click();
		sleepInSecond(1);

		// Verify radio button petrol 2.0 selected
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());

		driver.findElement(petrolTwo).click();
		sleepInSecond(1);

		// Verify radio button petrol 2.0 selected
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());

		By dieselTwo = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

		driver.findElement(dieselTwo).click();
		sleepInSecond(1);

		// Verify radio button petrol 2.0 deselected
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
		// Verify radio button diesel 2.0 selected
		Assert.assertTrue(driver.findElement(dieselTwo).isSelected());

		By petrolThree = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		Assert.assertFalse(driver.findElement(petrolThree).isEnabled());

	}

	
	public void TC_03_Checkbox_Default() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");

		checkToRadioCheckbox(rearSideCheckbox);
		sleepInSecond(1);

		// Verify checkbox Rear Side selected
		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
		
		

		By luggageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");

		// Verify checkbox Luggage deselected
		Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());

		checkToRadioCheckbox(luggageCheckbox);
		sleepInSecond(1);

		// Verify checkbox Luggage selected
		Assert.assertTrue(driver.findElement(luggageCheckbox).isSelected());

		uncheckToCheckbox(luggageCheckbox);
		sleepInSecond(1);

		// Verify checkbox Luggage deselected
		Assert.assertFalse(driver.findElement(luggageCheckbox).isSelected());

	}

	
	public void TC_04_Custom_Radio() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		// 1 - Dùng thẻ input nhưng ko click đc - có thể dùng verify đc
		// By winterRadioButton = By.xpath("//input[@value='Winter']");
		
		// driver.findElement(winterRadioButton).click();
		// sleepInSecond(1);
		// Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());
		
		// 2 - Dùng thẻ span click đc - nhưng ko verify đc
		// By winterRadioButton = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class, 'outer')]");
		
		// driver.findElement(winterRadioButton).click();
		// sleepInSecond(1);
		// Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());
		
		// 3 - Thẻ span để click - thẻ input để verify
		// By winterRadioSpan = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class, 'outer')]");
		// By winterRadioInput = By.xpath("//input[@value='Winter']");
		
		// driver.findElement(winterRadioSpan).click();
		// sleepInSecond(1);
		// Assert.assertTrue(driver.findElement(winterRadioInput).isSelected());
		
		// 4 - Dùng thẻ input (click & verify = JS)
		By winterRadioInput = By.xpath("//input[@value='Winter']");
		
		checkToCheckboxByJS(winterRadioInput);
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(winterRadioInput).isSelected());				
		
	}
	
	
	public void TC_05_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
		
		checkToCheckboxByJS(checkedCheckbox);
		checkToCheckboxByJS(indeterminateCheckbox);
		sleepInSecond(1);
		
		// Verify checkbox is selected
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());
		
		uncheckToCheckboxByJS(checkedCheckbox);
		uncheckToCheckboxByJS(indeterminateCheckbox);
		sleepInSecond(1);

		// Verify checkbox is deselected
		Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
		
	}
	
	
	public void TC_06_Radio_Checkbox_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");
		
		// Verify cách 1
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "false");
		// Verify cách 2
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		checkToRadioCheckbox(canthoRadio);
		sleepInSecond(1);
		
		// Verify cách 1
		Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
		// Verify cách 2
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
		
		List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		
		// Click all
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSecond(1);
		}
		
		// Verify all
		for (WebElement checkbox : checkboxes) {
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
		
	}
	
	
	@Test
	public void TC_07_Live_Guru() {
		driver.get("https://live.demoguru99.com/index.php/backendlogin");
		
		driver.findElement(By.id("username")).sendKeys("user01");
		driver.findElement(By.id("login")).sendKeys("guru99com");
		driver.findElement(By.cssSelector("input[title='Login']")).click();
		sleepInSecond(5);
		
		clickToCheckboxByCustomerName("Automation VN");
		clickToCheckboxByCustomerName("Mimi Mi Ionescu");
		clickToCheckboxByCustomerName("Thomas Samuel Smith");
		
	}
	
	public void clickToCheckboxByCustomerName(String customerName) {
		WebElement customerNameCheckbox = driver.findElement(By.xpath("//td[contains(text(), '" + customerName + "')]/preceding-sibling::td/input"));
		if (!customerNameCheckbox.isSelected()) {
			customerNameCheckbox.click();
		}
		
	}
	
	public void clickToElementByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}		
	
	public void checkToCheckboxByJS(By by) {
		if (!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}
	
	public void uncheckToCheckboxByJS(By by) {
		if (driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
		}
	}
	
	public void checkToRadioCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
	}

	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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