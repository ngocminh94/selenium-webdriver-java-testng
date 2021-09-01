package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_TC_04 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	@Test
	public void TC_04_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		By parent = By.cssSelector("span[aria-owns='games_options']");
		By child = By.cssSelector("ul#games_options>li");
		
		selectItemInDropdown(parent, child, "Basketball");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span[aria-owns='games_options']>input")).getAttribute("aria-label"),
				"Basketball");

		selectItemInDropdown(parent, child, "Football");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span[aria-owns='games_options']>input")).getAttribute("aria-label"),
				"Football");
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

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void selectItemInDropdown(By parentBy, By childBy, String expectedTextItem) {
		// Chờ element này đc phép click
				explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy));

				// 1 - Click vào 1 element cho xổ ra hết các item
				driver.findElement(parentBy).click();
				sleepInSecond(2);

				// 2 - Wait cho tất cả các element đc load ra (có trong HTML/DOM(Document Object
				// Modal))
				// Store lại all element của dropdown
				List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				if (item.isDisplayed()) { // 3 - Nếu item cần chọn nhìn thấy đc -> click vào
					item.click();
				} else { // 4 - Nếu item cần chọn ko nhìn thấy đc (bị che bên dưới) -> scroll xuống và
							// click vào
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					item.click();
				}
				break;
			}
		}
	}

}
