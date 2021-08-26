package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_TextArea {
	WebDriver driver;
	String loginPageUrl, userID, password, name, gender, dateOfBirthInput, dateOfBirthOutput, addressInput,
			addressOutput, city, state, pin, phone, email;
	String customerID;
	String editAddressInput, editAddressOutput, editCity, editState, editPin, editPhone, editEmail;
	String projectPath = System.getProperty("user.dir");
	
	JavascriptExecutor jsExecutor;

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
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Ép kiểu tường minh của Java
		jsExecutor = (JavascriptExecutor) driver;

		
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
		email = "min" + getRandomNumber() + "@mail.net";

		editAddressInput = "390 le van luong\\nthanh xuan";
		editAddressOutput = "390 le van luong thanh xuan";
		editCity = "Hai Phong";
		editState = "LA";
		editPin = "996699";
		editPhone = "0123450123";
		editEmail = "min" + getRandomNumber() + "@gmail.com";

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		driver.get("http://demo.guru99.com/v4/");
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();
		driver.findElement(By.xpath("//a[text()='here']")).click();

		driver.findElement(By.name("emailid")).sendKeys(email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text() = \"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(genderRadioBy).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dateOfBirthTextboxBy));
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

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(genderTextboxBy).getAttribute("value"), gender);
		Assert.assertEquals(driver.findElement(dateOfBirthTextboxBy).getAttribute("value"), dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(addressTextAreaBy).getAttribute("value"), addressInput);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);

		driver.findElement(addressTextAreaBy).clear();
		driver.findElement(addressTextAreaBy).sendKeys(editAddressInput);
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys(editPhone);
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);

		driver.findElement(By.name("sub")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(), customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirthOutput);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail);		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}