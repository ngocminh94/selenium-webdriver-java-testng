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

public class Topic_08_Custom_Dropdown_List {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// System.setProperty("webdriver.gecko.driver", projectPath +
		// "\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		// Wait để apply cho các trạng thái của element
		// (visible/invisible/presence/clickable/...)
		// - visible: có thể nhìn thấy và thao tác dc >< invisible
		// - presence: có trong cây html ko
		explicitWait = new WebDriverWait(driver, 15);

		// ép kiểu tường minh (Reference casting)
		jsExecutor = (JavascriptExecutor) driver;

		// Wait để tìm element (apply for findElement/findElements)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		By parent = By.id("number-button");
		By child = By.cssSelector("ul#number-menu div");

		selectItemInDropdown(parent, child, "5");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")));

		selectItemInDropdown(parent, child, "19");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']")));

		selectItemInDropdown(parent, child, "10");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='10']")));

		selectItemInDropdown(parent, child, "15");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(
				By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")));

	}

	// @Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		By parent = By.cssSelector("i.dropdown.icon");
		By child = By.cssSelector("div[role='option']>span");

		// selectItemInDropdown(parent, child, "Stevie Feliciano");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Stevie Feliciano']")));

		// selectItemInDropdown(parent, child, "Jenny Hess");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Jenny Hess']")));

		// selectItemInDropdown(parent, child, "Justen Kitsune");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and text()='Justen Kitsune']")));

	}

	// @Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		By parent = By.cssSelector("li.dropdown-toggle");
		By child = By.cssSelector("ul.dropdown-menu a");

		// selectItemInDropdown(parent, child, "Second Option");
		sleepInSecond(1);
		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")));

		// selectItemInDropdown(parent, child, "First Option");
		sleepInSecond(1);
		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")));

		// selectItemInDropdown(parent, child, "Third Option");
		sleepInSecond(1);
		Assert.assertTrue(
				isElementDisplayed(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")));

	}

	// Removed
	public void TC_04_Removed_KendoUI() {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

		// Chờ đến khi icon loading biến mất trong vòng 15s
		Assert.assertTrue(
				explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.kd-loader"))));

		// Chờ đến khi icon loading trong dropdown biến mất trong vòng 15s
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));

		// Chọn Category
		selectItemInDropdown(By.cssSelector("span[aria-owns='categories_listbox']"), By.cssSelector("ul#categories_listbox>li h3"), "Confections");

		// Chờ đến khi icon loading trong dropdown biến mất trong vòng 15s
		Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));

		// Chọn Product
		selectItemInDropdown(By.cssSelector("span[aria-owns='products_listbox']"), By.cssSelector("ul#products_listbox>li"), "Chocolade");

		// Chờ đến khi icon loading trong dropdown biến mất trong vòng 15s
		Assert.assertTrue(explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.k-i-loading"))));

		// Chọn Product
		selectItemInDropdown(By.cssSelector("span[aria-owns='shipTo_listbox']"), By.cssSelector("ul#shipTo_listbox>li"), "Luisenstr. 48");

	}

	// @Test
	public void TC_04_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInDropdown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector("ul#games_options>li"),
				"Basketball");
		sleepInSecond(1);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span[aria-owns='games_options']>input")).getAttribute("aria-label"),
				"Basketball");

		selectItemInDropdown(By.cssSelector("span[aria-owns='games_options']"), By.cssSelector("ul#games_options>li"),
				"Football");
		sleepInSecond(1);
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span[aria-owns='games_options']>input")).getAttribute("aria-label"),
				"Football");

	}

	// @Test
	public void TC_05_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		selectItemInEditableDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "Nissan");
		sleepInSecond(1);

		selectItemInEditableDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "Volvo");
		sleepInSecond(1);

		selectItemInEditableDropdown(By.cssSelector("div#default-place>input"),
				By.xpath("//ul[@class='es-list' and @style]/li"), "Porsche");
		sleepInSecond(1);

	}

	// @Test
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		selectItemInEditableDropdown(By.cssSelector("input.search"), By.cssSelector("div[role='option']>span"),
				"Angola");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and contains(text(), 'Angola')]")));

		selectItemInEditableDropdown(By.cssSelector("input.search"), By.cssSelector("div[role='option']>span"),
				"Armenia");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(By.xpath("//div[@role='alert' and contains(text(), 'Armenia')]")));

	}

	@Test
	public void TC_07_Multiple_Dropdown() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		String[] firstMonth = {"January", "April", "October"};
		String[] secondMonth = {"January", "April", "October", "December"};
		
		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", firstMonth);
		sleepInSecond(2);	
		Assert.assertTrue(areItemSelected(firstMonth));
		
		driver.navigate().refresh();
		
		selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", secondMonth);
		sleepInSecond(2);	
		Assert.assertTrue(areItemSelected(secondMonth));
		
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
		explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();
		
		// 1 - Click vào 1 element cho xổ ra hết các item
		driver.findElement(parentBy).click();
		
		// 2 - Wait cho tất cả cá element đc load ra (có trong HTML/DOM(Document Object Modal))
		// Store lại all element của dropdown
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) { // 3 - Nếu item cần chọn nhìn thấy đc -> click vào
				if (item.isDisplayed()) {
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
	
	
	public void selectItemInEditableDropdown(By parentBy, By childBy, String expectedTextItem) {
		// Chờ element này đc phép click
		// explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();

		// parentBy: textbox
		driver.findElement(parentBy).clear();
		driver.findElement(parentBy).sendKeys(expectedTextItem);

		// 2 - Wait cho tất cả cá element đc load ra (có trong HTML/DOM(Document Object
		// Modal))
		// Store lại all element của dropdown
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) { // 3 - Nếu item cần chọn nhìn thấy đc -> click vào
				if (item.isDisplayed()) {
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

	public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
		// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
		driver.findElement(By.xpath(parentXpath)).click();

		// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

		// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
		for (WebElement childElement : allItems) {
			// "January", "April", "July"
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);

					// 4: click vào item cần chọn
					childElement.click();
					sleepInSecond(1);
					
					List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + itemSelected.size());
					if (expectedValueItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
	}
  
    public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
		System.out.println("Text da chon = " + allItemSelectedText);

		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			boolean status = true;
			for (String item : months) {
				if (!allItemSelectedText.contains(item)) {
					status = false;
					return status;
				}
			}
			return status;
		} else if (numberItemSelected >= 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
	}	
	
}