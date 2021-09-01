package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown_List {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		
		String firstName = "Min";
		String lastName = "Trinh";
		String emailAddress = "min" + getRandomNumber() + "@gmail.us";
		String day = "15";
		String month = "December";
		String year = "2021";
		String company = "Auto";
		String password = "123456";
		
		By genderMaleBy = By.id("gender-male");
		By firstNameBy = By.id("FirstName");
		By lastNameBy = By.id("LastName");
		By dayDropdownBy = By.name("DateOfBirthDay");
		By monthDropdownBy = By.name("DateOfBirthMonth");
		By yearDropdownBy = By.name("DateOfBirthYear");
		By emailBy = By.id("Email");
		By companyBy = By.id("Company");
		
		driver.findElement(By.cssSelector("a.ico-register")).click();		
		driver.findElement(genderMaleBy).click();
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
		
		select = new Select(driver.findElement(dayDropdownBy));
		
		// chọn 1 item
		// select.selectByIndex(15); -> ko nên dùng vs t.hợp index ko cố định
		// select.selectByValue("15"); -> attibute value có thể là bất kì số nào -> nhìn vào ko hiểu		
		select.selectByVisibleText(day);		
		
		// kiểm tra dropdown có phải kiểu multiple select ko
		Assert.assertFalse(select.isMultiple());   // -> ko 
		
		// kiểm tra xem đã chọn đúng item chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		// get ra tổng số item trong dropdown là bn -> verify
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(monthDropdownBy));
		select.selectByVisibleText(month);
		
		select = new Select(driver.findElement(yearDropdownBy));
		select.selectByVisibleText(year);
		
		
		driver.findElement(emailBy).sendKeys(emailAddress);
		driver.findElement(companyBy).sendKeys(company);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		
		driver.findElement(By.cssSelector("a.ico-account")).click();
		
		Assert.assertTrue(driver.findElement(genderMaleBy).isSelected());
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(dayDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		select = new Select(driver.findElement(monthDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(yearDropdownBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"), company);
		
		
	}

	@Test
	public void TC_02_Rode() {
		driver.get("https://www.rode.com/wheretobuy");
		
		select = new Select(driver.findElement(By.id("where_country")));
		
		select.selectByVisibleText("Vietnam");
		
		driver.findElement(By.id("search_loc_submit")).click();
		
		// Dễ Fail
		//Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(), "29");
		// 1 - driver.findElement(By.cssSelector("div.result_count>span") -> founded
		// 2 - getText()
		// 3 - assertEquals
		// --> vì từ đầu lúc mở web đã xuất hiện locator "div.result_count>span"
		// --> có thể có trường hợp chưa kịp load đã thực hiện xong step 2 - get text 
		// --> text sai --> fail step 3
		
		
		// Dễ Pass hơn
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result_count']/span[text()='29']")).isDisplayed());
		// 1 - driver.findElement(By.xpath("//div[@class='result_count']/span[text()='29']")
		// locator trên có thêm đk "29"
		// -> Chưa tìm thấy "29": Chờ tiếp - mỗi nửa giây tìm lại 1 lần cho đến khi hết timeout (implicitWait)
		// Tìm thấy -> step 2 - check isDisplayed() -> true/false
		// 3 - assertTrue
		
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));		
		Assert.assertEquals(storeName.size(), 29);
		
		for (WebElement store : storeName) {
			System.out.println(store.getText());
		}
		
	
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