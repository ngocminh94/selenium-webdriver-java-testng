package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_04_Xpath_Css_II {
	WebDriver driver;
	String name, emailAddress, password, phone;

	// Action fields:
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button");

	// Error message:
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmPasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");

	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		name = "Min Trinh";
		emailAddress = "automation@gmail.net";
		password = "123456";
		phone = "0989550350";
	}

	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	
	@Test
	public void TC_01_Empty() {
		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");
	}

	
	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys("123@123@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("123@123@");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}

	
	@Test
	public void TC_03_Incorrect_Confirm_Email() {
		// Email nhập lại không đúng
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys("automation@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
	}

	
	@Test
	public void TC_04_Password_Less_Than_6_Chars() {
		// Mật khẩu phải có ít nhất 6 ký tự
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123");
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	
	@Test
	public void TC_05_Incorrect_Confirm_Password() {
		// Mật khẩu bạn nhập không khớp
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123459");
		driver.findElement(phoneTextboxBy).sendKeys(phone);

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
	}

	
	@Test
	public void TC_06_Invalid_Phone() {
		// Vui lòng nhập con số
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(confirmEmailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		driver.findElement(confirmPasswordTextboxBy).sendKeys(password);
		driver.findElement(phoneTextboxBy).sendKeys("asdf");

		driver.findElement(registerButtonBy).click();

		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập con số");

		
		// Clear dữ liệu vừa nhập để nhập lại
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("098");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");

		
		// Clear dữ liệu vừa nhập để nhập lại
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("098765432100");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");

		
		// Clear dữ liệu vừa nhập để nhập lại
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("123456");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
	}

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
